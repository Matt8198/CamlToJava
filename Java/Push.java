import java.util.*;

//Sauvegarde d'une valeur sur la pile
class Push extends Instr {
	
    void exec_instr(Config cf) {
		//On ajoute le terme de la config dans la pile
		ValueSE x = new ValueSE(cf.get_value());
		
		//On ajoute à la pile avec addFirst comme pour LLE. 
		cf.get_stack().addFirst(x);
		
		//On dépile le code
		cf.get_code().pop();
		
    }
}
