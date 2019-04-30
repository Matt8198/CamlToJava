import java.util.*;

class ValueSE extends StackElem {
	Value val;
	
	public ValueSE (Value v){
		val = v;
	}
	
	Value get_value(){
		return val;
	}
	
	
}