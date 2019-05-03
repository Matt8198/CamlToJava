import java.util.*;

//Appel d'une fonction
public class RmDefs extends Instr{
	int n;
	
	public RmDefs (int nb){
		n=nb;
	}
	
	void exec_instr(Config cf) {
		cf.get_code().pop();
		//On récupère le fds de la config pour les appels récursifs
		Map<String,LinkedList<Instr>> fds = cf.get_fds();
		
		//On delete les n élements souhaité de la map
		int suppr=0;
		for (Object key : fds.keySet()) {
			if (suppr<n) {
				fds.remove(key);
				suppr++;
			}
		}
		
    }
	
}