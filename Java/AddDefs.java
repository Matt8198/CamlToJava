import java.util.*;

//Appel d'une fonction
class Call extends Instr{
	Map<String,LinkedList<Instr>> defs;
	
	public Call (Map<String,LinkedList<Instr>> f){
		defs=f;
	}
	
	void exec_instr(Config cf) {
		cf.get_code().pop();
		//On récupère le fds de la config pour les appels récursifs
		Map<String,LinkedList<Instr>> fds = cf.get_fds();
		
		//On ajoute fds dans defs
		defs.putAll(fds);
		
    }
	


}