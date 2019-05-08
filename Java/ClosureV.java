import java.util.*;

//Class créant une ClosureV comportant un code et une valeur
class ClosureV extends Value {
    private LinkedList<Instr> code;
	private Value val;
	
    public ClosureV (LinkedList<Instr> c, Value v) {
		code=c;
		val=v;
    }

    public LinkedList<Instr> get_code () {
        return code;
    }
	
	
    public Value get_val () {
        return val;
    }

    void print_value() {
        System.out.print(val);
    }
}
