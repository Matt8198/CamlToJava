import java.util.*;

//Retour d'une fonction
public class Return extends Instr {
	
    void exec_instr(Config cf) {
		//On dépile le code
		cf.get_code().pop();
		
		//On dépile la pile qui est un code
		CodeSE c = (CodeSE) cf.get_stack().pop();
		
		//On ajoute le code que l'on a dépilé
		cf.set_code(c.get_code());
        
    }
}
