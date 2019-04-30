import java.util.*;

class Cur extends Instr {
	
	LinkedList<Instr> code;
	
	public Cur (LinkedList<Instr> code){
		code=code;
	}
	
    void exec_instr(Config cf) {
        cf.get_code();
    }
}
