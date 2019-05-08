import java.util.*;

//Construction d'une cloture
public class Cur extends Instr {
	
	private LinkedList<Instr> code;
	
	public Cur (LinkedList<Instr> c){
		code=c;
	}
	
    void exec_instr(Config cf) {
        //On récupère le terme de la config
		ValueSE x = new ValueSE(cf.get_value());
		
		//On crée une closure
		ClosureV clo = new ClosureV(code,x.get_value());
		
		//La closure est la nouvelle valeur de la config
		cf.set_value(clo);
		
		//On dépile le code
		cf.get_code().pop();
    }
}
