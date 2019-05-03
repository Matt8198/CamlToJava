import java.util.*;

public class CodeSE extends StackElem {

	LinkedList<Instr> code;
	
	public CodeSE (LinkedList<Instr> c){
		code = c;
	}
	LinkedList<Instr> get_code(){
		return code;
	}
	
}