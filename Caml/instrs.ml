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
   | (PairV((IntV m), (IntV n)), PrimInstr(BinOp(BCompar(BCge)))::c,d,fds) ->
                exec(BoolV (m >= n), c , d,fds)
   | (PairV((IntV m), (IntV n)), PrimInstr(BinOp(BCompar(BCgt)))::c,d,fds) ->
                exec(BoolV (m > n), c , d,fds)
   | (PairV((IntV m), (IntV n)), PrimInstr(BinOp(BCompar(BCle)))::c,d,fds) ->
                exec(BoolV (m <= n), c , d,fds)
   | (PairV((IntV m), (IntV n)), PrimInstr(BinOp(BCompar(BClt)))::c,d,fds) ->
                exec(BoolV (m < n), c , d,fds)
   | (PairV((IntV m), (IntV n)), PrimInstr(BinOp(BCompar(BCne)))::c,d,fds) ->
                exec(BoolV (m <> n), c , d,fds)
   | (x, (Cur c1)::c,d,fds)  -> exec(ClosureV(c1 ,x), c , d,fds)
   | (x, Return::c ,(Cod cc)::d,fds) -> exec(x, cc , d,fds)
   (*Appels récursifs*) (*List.assoc récupère la valeur de la clé donnée en premier parametre*)
   | (t, Call(f)::c,st,fds) -> (t,(List.assoc f fds)@c,st,fds)
   | (t, AddDefs(defs)::c,st,fds) -> (t,c,st,defs@fds)
   | (t, RmDefs(n)::c,st,fds) -> (t,c,st,chop n fds)
   (*Cas de base*)
   | cfg -> cfg;;

(*Première Version de access.*)
(* let rec access v env = match env with *)
	(* [] -> failwith "liste vide" *)
	(* |z::liste -> if z = v then [PrimInstr(UnOp(Snd))] *)
			(* else (PrimInstr(UnOp(Fst)))::(access v liste);; *)

(*On doit définir la listes de noms de fonctions pour access
sinon nous aurons des erreurs de type*)
let rec accessGenList v env = match env with
	[] -> failwith "liste vide"
	|(f :: env) -> if v=f then [Call f] else (accessGenList v env);;
			
(*Faisons une deuxième version de access pour les appels récursifs*)
(*Ici, on génère une liste (gen) de Fst/Snd pour une EVar et un Call pour un Edef.
On reprend le modèle de la première version access v env*)
let rec accessGen v env gen= match env with
	[] -> failwith "liste vide"
	| (EVar(x)::env) -> if v=x then gen@[PrimInstr(UnOp(Snd))]
			else (accessGen v env ((PrimInstr(UnOp(Fst)))::gen))
	| (EDef(f)::env) -> accessGenList v f;;

(*Nous allons appeler la fonction accessGen avec la fonction access v env ou on *)
(* initialisera notre liste généré à vide comme ceci
IL NE FAUT PAS OUBLIER DE METTRE DANS LA REGLE DU FN LE EVAR SINON ERREUR DE TYPE DANS COMPILE*)
let access v env = accessGen v env [];;
(*Cette fonction a été testé avec l'exemple du cours, qui donne les mêmes résultats*)
	
(*Pour pouvoir compiler le let rec, il nous faut rajouter les noms des fonctions de defs
dans l'environnement *)
let rec addNameFunction defs = match defs with
	|((name, body)::defs) -> name :: addNameFunction(defs)
	| [] -> [];;

(* puis compiler le corps de chacune des fonctions de defs dans cet env*)	
let rec compileBody defs env f = match defs with
	|((name, body)::defs) -> (name,f(env,body)) :: (compileBody (defs) (env) (f) )
	|[] -> [];;
	
	
let rec compile = function
	|(env,Bool(b)) -> [Quote(BoolV(b))]
	|(env,Int(i)) -> [Quote(IntV(i))]
	|(env,Var(v)) -> access v env
	|(env, Fn(v,e)) -> [Cur((compile(EVar(v)::env, e))@[Return])]
	|(env, App(PrimOp(p),e)) -> compile(env,e)@[PrimInstr(p)]
	|(env, App(f,a)) -> [Push]@(compile(env,f))@[Swap]@(compile(env,a))@[Cons;App]
	|(env, Pair(e1,e2)) -> [Push]@(compile(env,e1))@[Swap]@(compile(env,e2))@[Cons]
	|(env, Cond(i,t,e)) -> [Push]@compile(env,i)@[Branch(compile(env,t)@[Return],compile(env,e)@[Return])]
	(*Compilation des appels récursifs*)
	|(env,Fix(defs,e)) -> let dc = (compileBody (defs) (EDef((addNameFunction defs))::env) (compile)) in 
								let ec = compile(EDef((addNameFunction defs))::env,e) in 
									[AddDefs dc] @ ec @ [RmDefs (List.length dc)];;
	

	
	
let compile_prog = function
	Prog(t, exp) -> compile([], exp);;
				
let rec print_instr = function
	(Push::config) -> "\nLLE.add_elem(new Push()," ^ print_instr(config)^")"
	|((Cur c)::config) ->"\nLLE.add_elem(new Cur("^print_instr(c)^"),"^print_instr(config)^")"
	|(Return::config) -> "\nLLE.add_elem(new Return(),"^print_instr(config)^")"
	|(Cons::config) -> "\nLLE.add_elem(new Cons()," ^ print_instr(config)^")"
	|(Swap::config) -> "\nLLE.add_elem(new Swap()," ^ print_instr(config)^")"
	|((Quote v)::config) -> "\nLLE.add_elem(new Quote("^ print_value(v) ^"),"^print_instr(config)^")"
	|(App::config) -> "\nLLE.add_elem(new App(),"^print_instr(config)^")"
	|(Branch(c1,c2) :: config) -> "\nLLE.add_elem(new Branch("^print_instr(c1)^","^print_instr(c2)^")," ^ print_instr(config)^")"
	|((Call f)::config) -> "\nLLE.add_elem(new Call(\""^f^"\")," ^ print_instr(config) ^ ")"
	|((AddDefs defs)::config) -> "\nLLE.add_elem(new AddDefs("^ print_defs defs ^"),"^print_instr(config) ^")"
	|((RmDefs n)::config) -> "\nLLE.add_elem(new RmDefs("^string_of_int(n)^"),"^print_instr(config)^ ")"
	|(PrimInstr(UnOp(Fst))::config) -> "\nLLE.add_elem(new Fst(),"^print_instr(config)^")"
	|(PrimInstr(UnOp(Snd))::config) -> "\nLLE.add_elem(new Snd(),"^print_instr(config)^")"
	|(PrimInstr(BinOp(BArith(BAadd)))::config) -> "\nLLE.add_elem(new BinOp(BinOp.operateur.Add),"^print_instr(config)^")"
	|(PrimInstr(BinOp(BArith(BAsub)))::config) -> "\nLLE.add_elem(new BinOp(BinOp.operateur.Sub),"^print_instr(config)^")"
	|(PrimInstr(BinOp(BArith(BAmul)))::config) -> "\nLLE.add_elem(new BinOp(BinOp.operateur.Mult),"^print_instr(config)^")"
	|(PrimInstr(BinOp(BArith(BAmod)))::config) -> "\nLLE.add_elem(new BinOp(BinOp.operateur.Mod),"^print_instr(config)^")"
	|(PrimInstr(BinOp(BArith(BAdiv)))::config) -> "\nLLE.add_elem(new BinOp(BinOp.operateur.Div),"^print_instr(config)^")"
	|(PrimInstr(BinOp(BCompar(BCeq)))::config) -> "\nLLE.add_elem(new BinOp(BinOp.operateur.Eq),"^print_instr(config)^")"
	|(PrimInstr(BinOp(BCompar(BCge)))::config) -> "\nLLE.add_elem(new BinOp(BinOp.operateur.Ge),"^print_instr(config)^")"
	|(PrimInstr(BinOp(BCompar(BCgt)))::config) -> "\nLLE.add_elem(new BinOp(BinOp.operateur.Gt),"^print_instr(config)^")"
	|(PrimInstr(BinOp(BCompar(BCle)))::config) -> "\nLLE.add_elem(new BinOp(BinOp.operateur.Le),"^print_instr(config)^")"
	|(PrimInstr(BinOp(BCompar(BClt)))::config) -> "\nLLE.add_elem(new BinOp(BinOp.operateur.Lt),"^print_instr(config)^")"
	|(PrimInstr(BinOp(BCompar(BCne)))::config) -> "\nLLE.add_elem(new BinOp(BinOp.operateur.Ne),"^print_instr(config)^")"
	|[] -> "LLE.empty()"
and print_value = function 
	  NullV -> "new NullV()"
	| IntV(v) -> "new IntV("^(string_of_int v)^")"
	| BoolV(b) -> "new BoolV("^(string_of_bool b)^")"
	| PairV(x,y) -> "new PairV("^print_value(x)^","^print_value(y)^")"
	| ClosureV(c,v) -> "new ClosureV("^print_instr(c)^","^print_value(v)^")"
and print_defs = function
	((name,body)::defs) -> "LLE.add_elem(new Couple(\""^name^"\","^(print_instr body)^"), "^(print_defs defs)^")"
    | [] -> "LLE.empty()";;
	
let print_gen_class_to_java = function 
	cfg -> "import java.util.*; \n" ^
			"public class Gen { \n" ^
			"public static LinkedList<Instr> code =" ^
				print_instr(cfg) ^"; \n}";;