import java.util.*; 
public class Gen { 
public static LinkedList<Instr> code =
LLE.add_elem(new AddDefs(LLE.add_elem(new Couple("ack",
LLE.add_elem(new Cur(
LLE.add_elem(new Cur(
LLE.add_elem(new Push(),
LLE.add_elem(new Push(),
LLE.add_elem(new Fst(),
LLE.add_elem(new Snd(),
LLE.add_elem(new Swap(),
LLE.add_elem(new Quote(new IntV(0)),
LLE.add_elem(new Cons(),
LLE.add_elem(new BinOp(BinOp.operateur.Eq),
LLE.add_elem(new Branch(
LLE.add_elem(new Push(),
LLE.add_elem(new Snd(),
LLE.add_elem(new Swap(),
LLE.add_elem(new Quote(new IntV(1)),
LLE.add_elem(new Cons(),
LLE.add_elem(new BinOp(BinOp.operateur.Add),
LLE.add_elem(new Return(),LLE.empty()))))))),
LLE.add_elem(new Push(),
LLE.add_elem(new Push(),
LLE.add_elem(new Snd(),
LLE.add_elem(new Swap(),
LLE.add_elem(new Quote(new IntV(0)),
LLE.add_elem(new Cons(),
LLE.add_elem(new BinOp(BinOp.operateur.Eq),
LLE.add_elem(new Branch(
LLE.add_elem(new Push(),
LLE.add_elem(new Push(),
LLE.add_elem(new Call("ack"),
LLE.add_elem(new Swap(),
LLE.add_elem(new Push(),
LLE.add_elem(new Fst(),
LLE.add_elem(new Snd(),
LLE.add_elem(new Swap(),
LLE.add_elem(new Quote(new IntV(1)),
LLE.add_elem(new Cons(),
LLE.add_elem(new BinOp(BinOp.operateur.Sub),
LLE.add_elem(new Cons(),
LLE.add_elem(new App(),
LLE.add_elem(new Swap(),
LLE.add_elem(new Quote(new IntV(1)),
LLE.add_elem(new Cons(),
LLE.add_elem(new App(),
LLE.add_elem(new Return(),LLE.empty())))))))))))))))))),
LLE.add_elem(new Push(),
LLE.add_elem(new Push(),
LLE.add_elem(new Call("ack"),
LLE.add_elem(new Swap(),
LLE.add_elem(new Push(),
LLE.add_elem(new Fst(),
LLE.add_elem(new Snd(),
LLE.add_elem(new Swap(),
LLE.add_elem(new Quote(new IntV(1)),
LLE.add_elem(new Cons(),
LLE.add_elem(new BinOp(BinOp.operateur.Sub),
LLE.add_elem(new Cons(),
LLE.add_elem(new App(),
LLE.add_elem(new Swap(),
LLE.add_elem(new Push(),
LLE.add_elem(new Push(),
LLE.add_elem(new Call("ack"),
LLE.add_elem(new Swap(),
LLE.add_elem(new Fst(),
LLE.add_elem(new Snd(),
LLE.add_elem(new Cons(),
LLE.add_elem(new App(),
LLE.add_elem(new Swap(),
LLE.add_elem(new Push(),
LLE.add_elem(new Snd(),
LLE.add_elem(new Swap(),
LLE.add_elem(new Quote(new IntV(1)),
LLE.add_elem(new Cons(),
LLE.add_elem(new BinOp(BinOp.operateur.Sub),
LLE.add_elem(new Cons(),
LLE.add_elem(new App(),
LLE.add_elem(new Cons(),
LLE.add_elem(new App(),
LLE.add_elem(new Return(),LLE.empty()))))))))))))))))))))))))))))))))))),
LLE.add_elem(new Return(),LLE.empty())))))))))),
LLE.add_elem(new Return(),LLE.empty()))))))))))),
LLE.add_elem(new Return(),LLE.empty()))),LLE.empty())), LLE.empty())),
LLE.add_elem(new Push(),
LLE.add_elem(new Push(),
LLE.add_elem(new Call("ack"),
LLE.add_elem(new Swap(),
LLE.add_elem(new Quote(new IntV(3)),
LLE.add_elem(new Cons(),
LLE.add_elem(new App(),
LLE.add_elem(new Swap(),
LLE.add_elem(new Quote(new IntV(2)),
LLE.add_elem(new Cons(),
LLE.add_elem(new App(),
LLE.add_elem(new RmDefs(1),LLE.empty()))))))))))))); 
}