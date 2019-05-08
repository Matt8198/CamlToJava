import java.util.*;

//Appel d'une fonction
public class Call extends Instr{
	private String v;
	
	public Call (String f){
		v=f;
	}
	
	void exec_instr(Config cf) {
		//On dépile le code
		cf.get_code().pop();
		
		//On récupère la pile de définitions de fonctions de la config pour les appels récursifs
		LinkedList<Couple<String, LinkedList<Instr>>> fds = cf.get_fds();
		
		//On doit récupérer le nom de fonction et ajouter le code correspondant au nom rechérche
		for (int i=0; i< fds.size();i++){
			//Si on retrouve le nom de la fonction
			if (fds.get(i).get_fst().equals(v)){
				//On récupère le corps de la fonction en le copiant (pour éviter des problèmes)
				LinkedList<Instr> code = new LinkedList<Instr>(fds.get(i).get_snd());
				//Que l'on ajoute dans le code
				code.addAll(cf.get_code());
				//Code que l'on ajoute dans la nouvelle config
				cf.set_code(code);
				//Inutile de continuer la boucle puisque l'on s'arrete des que l'on a trouvé le nom
				break;
			}
		}
		
    }
	


}