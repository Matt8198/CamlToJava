import java.util.*;

class Swap extends Instr {
	
    void exec_instr(Config cf) {
		//On dépile le code
        cf.get_code().pop();
		//On récupère la tête de pile
		ValueSE y =  (ValueSE) cf.get_stack().pop();
		
		//On récupère la valeur de de la config
		ValueSE x = new ValueSE(cf.get_value());
		//On ajoute à la config la valeur y
		cf.set_value(y.get_value());
		
		//On ajoute dans la pile x
		cf.get_stack().addFirst(x);
		
    }
}
