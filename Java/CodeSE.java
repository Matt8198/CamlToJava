import java.util.*;

class CodeSE extends StackElem {

	private LinkedList<Instr> code;
	
	public CodeSE (LinkedList<Instr> c){
		code = c;
	}
	LinkedList<Instr> get_code(){
		return code;
	}
	
}