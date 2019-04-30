import java.util.*;

class Branch extends Instr {
	
    LinkedList<Instr> code1;
	LinkedList<Instr> code2;

    /* Constructors */
    public Branch(LinkedList<Instr> c1,LinkedList<Instr> c2) {
        code1=c1;
		code2=c2;
    }
    
	    
    LinkedList<Instr> get_code1() {
        return code1;
    }

    LinkedList<Instr> get_code2() {
        return code2;
    }
	
    void exec_instr(Config cf) {
        cf.get_code().pop();
    }
}
