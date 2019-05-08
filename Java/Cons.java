import java.util.*;

class Cons extends Instr {
	
    void exec_instr(Config cf) {
		
		
		// System.out.println("OUOUOU" + cf.get_stack());
        //Récupérons la tête de la stack;
		ValueSE y = (ValueSE) (cf.get_stack().pop());
		//On crée la Paire de la Value de la config avec celle de la pile
		Value x = cf.get_value();
		//Création de la paire
		PairV p = new PairV(y.get_value(),x);
		//On ajoute la Paire
		cf.set_value(p);
		//On dépile le code.
		cf.get_code().pop();
		
    }
}
