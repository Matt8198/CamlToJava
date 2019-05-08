import java.util.*;

class ClosureV extends Value {
    /* Fields */
    LinkedList<Instr> code;
	Value val;

    /* Constructors */
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
