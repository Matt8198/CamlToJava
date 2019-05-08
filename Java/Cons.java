import java.util.*;

//Construction d'une paire
class Cons extends Instr {
	
    void exec_instr(Config cf) {
				
        //Récupérons la tête de la pile;
		ValueSE y = (ValueSE) (cf.get_stack().pop());
		
		//On récupère le terme de la config
		Value x = cf.get_value();
		
		// Création de la paire comportant la tete de pile et le terme
		PairV p = new PairV(y.get_value(),x);
		
		//On ajoute la Paire en tant que nouveau terme
		cf.set_value(p);
		
		//On dépile le code.
		cf.get_code().pop();
		
    }
}
