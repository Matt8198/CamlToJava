import java.util.*;

//Retrait de n fonctions locales
public class RmDefs extends Instr{
	private int n;
	
	public RmDefs (int nb){
		n=nb;
	}
	
	void exec_instr(Config cf) {
		//On dépile le code
		cf.get_code().pop();

		//On récupère la pile de définitions de fonctions de la config pour les appels récursifs
		LinkedList<Couple<String, LinkedList<Instr>>> fds = cf.get_fds();
		
		//On delete les n élements souhaité de la pile
		for(int i=0;i<n;i++){
			fds.pop();
		}
		
		//On remplace la pile de définitions de fonctions par la nouvelle sans les n premiers élements
		//A noter que l'on copie la liste pour éviter tout problèmes
		cf.set_fds(new LinkedList<Couple<String, LinkedList<Instr>>> (fds));
		
    }
	
}