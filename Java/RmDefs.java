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
		LinkedList<Couple<String, LinkedList<Instr>>> fds = cf.get_fds();
		
		//On delete les n élements souhaité de la map
		for(int i=0;i<n;i++){
			fds.pop();
		}
		
		cf.set_fds(new LinkedList<Couple<String, LinkedList<Instr>>> (fds));
		
    }
	
}