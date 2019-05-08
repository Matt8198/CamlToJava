import java.util.*;

class Push extends Instr {
	
    void exec_instr(Config cf) {
        
		
		//On ajoute la valeur de la config dans la pile
		ValueSE x = new ValueSE(cf.get_value());
		
		//On ajoute avec addFirst comme pour LLE.
		cf.get_stack().addFirst(x);
		
		//On dépile la liste de code
		cf.get_code().pop();
		
    }
}
