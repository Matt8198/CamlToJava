import java.util.*;

abstract class StackElem extends Object {
	
	LinkedList<Instr> code;
	Value val;
	
	public StackElem (LinkedList <Instr> code, Value val){
		code= code;
		val= val;
	}
	
	LinkedList<Instr> get_code(){
		return code;
	}
	
	Value get_value(){
		return val;
	}
}
