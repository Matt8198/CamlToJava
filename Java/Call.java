import java.util.*;

//Appel d'une fonction
public class Call extends Instr{
	String v;
	
	public Call (String f){
		v=f;
	}
	
	void exec_instr(Config cf) {
		cf.get_code().pop();
		//On récupère le fds de la config pour les appels récursifs
		Map<String,LinkedList<Instr>> fds = cf.get_fds();
		//On récupère la value de Call qui sera la clé et on récupère la valeur de la fds
		LinkedList<Instr> code = fds.get(v);
		//On ajoute ce code
		cf.set_code(code);
    }
	


}