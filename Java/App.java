import java.util.*;

//Application d'une cloture
public class App extends Instr {
	
    void exec_instr(Config cf) {
		//On dépile le code
		cf.get_code().pop();
		
		//On récupère le terme de la config qui est une paire entre une closure et une value
		PairV p = (PairV) cf.get_value();
		
		//On récupère la closure
		ClosureV clo = (ClosureV) (p.getValue1());		
		
		//On récupère la valeur de la closure
		ValueSE y = new ValueSE (clo.get_val());
		
		//On récupère le deuxieme champ de la Paire
		ValueSE z = new ValueSE (p.getValue2());
		
		//On crée la nouvelle paire
		PairV new_p = new PairV(y.get_value(),z.get_value());
		
		//On ajoute cette paire dans le terme de la config
		cf.set_value(new_p);
		
		//On ajoute le code dans la pile, tout comme Value, il y a ValueSE, la même pour le code
		cf.get_stack().addFirst(new CodeSE(cf.get_code()));
		
		//On ajoute le code de la closure dans la config
		//On utilise LinkedListe pour copier et ne pas avoir de problème
		cf.set_code(new LinkedList<Instr>(clo.get_code()));
		
		
		
		
        
    }
}
