import java.util.*;

public class ValueSE extends StackElem {
	Value val;
	
	public ValueSE (Value v){
		val = v;
	}
	
	Value get_value(){
		return val;
	}
	
	
}