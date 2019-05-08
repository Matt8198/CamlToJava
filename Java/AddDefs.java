import java.util.*;

class AddDefs extends Instr{
	LinkedList<Couple<String, LinkedList<Instr>>>defs;
	
	public AddDefs (LinkedList<Couple<String, LinkedList<Instr>>> f){
		defs=f;
	}
	
	void exec_instr(Config cf) {
		cf.get_code().pop();
		
		//On récupère le fds de la config pour les appels récursifs
		LinkedList<Couple<String, LinkedList<Instr>>> fds = cf.get_fds();
		
		//On ajoute fds dans defs
		defs.addAll(fds);
		
		cf.set_fds(defs);
		
		
    }
}