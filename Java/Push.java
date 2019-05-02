import java.util.*;

class Push extends Instr {
	
    void exec_instr(Config cf) {
        
		//On ajoute la valeur de la config dans la pile
		ValueSE x = new ValueSE(cf.get_value());
		cf.get_stack().add(x);
		
		//On lance la config
		cf.get_code().pop();
    }
}
