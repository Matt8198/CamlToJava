(* Instructions of the CAM *)

open Miniml;;

type instr =
  PrimInstr of primop
| Cons
| Push
| Swap
| Return
| Quote of value
| Cur of code
| App
| Branch of code * code
(* new for recursive calls *)
| Call of var
| AddDefs of (var * code) list
| RmDefs of int
and value =
  NullV 
| VarV of Miniml.var
| IntV of int
| BoolV of bool
| PairV of value * value
| ClosureV of code * value
and code = instr list
  
type stackelem = Val of value | Cod of code


let rec chop n fds = match n,fds with
	(1,a::fds) -> fds
	|(n,fds) -> chop (n-1) (fds);;
	
(* terme, code, pile*)
let rec exec = function
   (PairV(x,y), PrimInstr(UnOp(Fst))::c, st,fds) -> exec(x,c,st,fds)
   | (PairV(x,y), PrimInstr(UnOp(Snd))::c, st,fds) -> exec(y,c,st,fds)
   | (x, Cons::c,(Val y)::d,fds) -> exec(PairV(y,x), c, d,fds)
   | (x, Push::c, d,fds) -> exec(x, c, (Val x)::d,fds)
   | (x, Swap::c,(Val y)::d,fds) -> exec(y, c, (Val x)::d,fds)
   | (t, (Quote v)::c, d,fds) -> exec(v, c, d,fds)
   | (PairV(ClosureV(x,y),z), (App::c) ,d,fds)-> exec( PairV(y,z),x , (Cod c)::d,fds)
   | ((BoolV b), Branch(c1, c2)::c, (Val x)::d,fds) ->
                exec(x, (if b then c1 else c2) ,(Cod c)::d,fds)
   | (PairV((IntV m), (IntV n)), PrimInstr(BinOp(BArith(BAadd)))::c,d,fds) ->
                exec(IntV (m + n), c , d,fds)
   | (PairV((IntV m), (IntV n)), PrimInstr(BinOp(BArith(BAsub)))::c,d,fds) ->
                exec(IntV (m - n), c , d,fds)
   | (PairV((IntV m), (IntV n)), PrimInstr(BinOp(BArith(BAmul)))::c,d,fds) ->
                exec(IntV (m * n), c , d,fds)
   | (PairV((IntV m), (IntV n)), PrimInstr(BinOp(BArith(BAdiv)))::c,d,fds) ->
                exec(IntV (m / n), c , d,fds)
   | (PairV((IntV m), (IntV n)), PrimInstr(BinOp(BArith(BAmod)))::c,d,fds) ->
                exec(IntV (m mod n), c , d,fds)
   | (PairV((IntV m), (IntV n)), PrimInstr(BinOp(BCompar(BCeq)))::c,d,fds) ->
                exec(BoolV (m == n), c , d,fds)
   | (x, (Cur c1)::c,d,fds)  -> exec(ClosureV(c1 ,x), c , d,fds)
   | (x, Return::c ,(Cod cc)::d,fds) -> exec(x, cc , d,fds)
   (*Appels récursifs*) (*List.assoc récupère la valeur de la clé donnée en premier parametre*)
   | (t, Call(f)::c,st,fds) -> (t,(List.assoc f fds)@c,st,fds)
   | (t, AddDefs(defs)::c,st,fds) -> (t,c,st,defs@fds)
   | (t, RmDefs(n)::c,st,fds) -> (t,c,st,chop n fds)
   (*Cas de base*)
   | cfg -> cfg;;


let rec access v env = match env with
	[] -> failwith "liste vide"
	|z::liste -> if z = v then [PrimInstr(UnOp(Snd))]
			else (PrimInstr(UnOp(Fst)))::(access v liste);;
	

let rec compile = function
	|(env,Bool(b)) -> [Quote(BoolV(b))]
	|(env,Int(i)) -> [Quote(IntV(i))]
	|(env,Var(v)) -> access v env
	|(env, Fn(v,e)) -> [Cur(compile(v::env, e)@[Return])]
	|(env, App(PrimOp(p),e)) -> compile(env,e)@[PrimInstr(p)]
	|(env, App(f,a)) -> [Push]@(compile(env,f))@[Swap]@(compile(env,a))@[Cons;App]
	|(env, Pair(e1,e2)) -> [Push]@(compile(env,e1))@[Swap]@(compile(env,e2))@[Cons]
	|(env, Cond(i,t,e)) -> [Push]@compile(env,i)@[Branch(compile(env,t)@[Return],compile(env,e)@[Return])]
	(* |(env,Fix(defs,e)) -> [AddDefs dc] @ ec @ [RmDefs (List.length dc)];; *)
	

let compile_prog = function
	Prog(t, exp) -> compile([], exp);;
				
let rec print_instr = function
	(Push::config) -> "LLE.add_elem(new Push())," ^ print_instr(config)
	|((Cur c)::config) ->"LLE.add_elem(new Cur("^print_instr(c)^"),"^print_instr(config)
	|(Return::config) -> "LLE.add_elem(new Return()),"^print_instr(config)
	|(Cons::config) -> "LLE.add_elem(new Cons())," ^ print_instr(config)
	|(Swap::config) -> "LLE.add_elem(new Swap())," ^ print_instr(config)
	|((Quote v)::config) -> "LLE.add_elem(new Quote("^ print_value(v) ^"),"^print_instr(config)
	|(App::config) -> "LLE.add_elem(new App()),"^print_instr(config)
	|(PrimInstr(UnOp(Fst))::config) -> "LLE.add_elem(new Fst()),"^print_instr(config)
	|(PrimInstr(UnOp(Snd))::config) -> "LLE.add_elem(new Snd()),"^print_instr(config)
	|[] -> "LLE.empty();"
and print_value = function 
	  NullV -> "new NullV();"
	| IntV(v) -> "new IntV("^(string_of_int v)^"),"
	| BoolV(b) -> "new BoolV("^(string_of_bool b)^"),"
	| PairV(x,y) -> "new PairV("^print_value(x)^","^print_value(y)^")"
	| ClosureV(c,v) -> "new ClosureV("^print_instr(c)^","^print_value(v)^")";;	

let print_gen_class_to_java = function 
	cfg -> "import java.util.*;" ^
			"public class Gen {" ^
			"public static LinkedList<Instr> code =" ^
				print_instr(cfg) ^"}";;