import java.util.*;

class ClosureV extends Value {
    /* Fields */
    LinkedList<Instr> c;
	Value v;

    /* Constructors */
    public ClosureV (LinkedList<Instr> c, Value v) {
		c=c;
		v=v;
    }

    LinkedList<Instr> get_code () {
        return c;
    }
	
	
    Value get_val () {
        return v;
    }

    void print_value() {
        System.out.print(v);
    }
}
