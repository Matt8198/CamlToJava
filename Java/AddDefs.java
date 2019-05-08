import java.util.*;

//Ajout de fonctions locales
class AddDefs extends Instr{
	private LinkedList<Couple<String, LinkedList<Instr>>>defs;
	
	public AddDefs (LinkedList<Couple<String, LinkedList<Instr>>> f){
		defs=f;
	}
	
	void exec_instr(Config cf) {
		//On dépile le code
		cf.get_code().pop();
		
		//On récupère la pile de définitions de fonctions de la config pour les appels récursifs
		LinkedList<Couple<String, LinkedList<Instr>>> fds = cf.get_fds();
		
		//On ajoute fds dans la pile
		defs.addAll(fds);
		
		//On remplace la pile par la nouvelle qui est composé de defs et de fds
		cf.set_fds(defs);
		
		
    }
}