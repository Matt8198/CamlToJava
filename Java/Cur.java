import java.util.*;

public class Cur extends Instr {
	
	LinkedList<Instr> code;
	
	public Cur (LinkedList<Instr> c){
		code=c;
	}
	
    void exec_instr(Config cf) {
        //On récupère la valeur de la config
		ValueSE x = new ValueSE(cf.get_value());
		
		//On crée une closure
		ClosureV clo = new ClosureV(code,x.get_value());
		
		//On met la closure dans la config
		cf.set_value(clo);
		
		//On lance le code
		cf.get_code().pop();
    }
}
