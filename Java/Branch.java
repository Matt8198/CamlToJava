import java.util.*;

//Branchement
public class Branch extends Instr {
	
    private LinkedList<Instr> code1;
	private LinkedList<Instr> code2;
	
    public Branch(LinkedList<Instr> c1,LinkedList<Instr> c2) {
        code1=c1;
		code2=c2;
    }
    
	    
    LinkedList<Instr> get_code1() {
        return code1;
    }

    LinkedList<Instr> get_code2() {
        return code2;
    }
	
    void exec_instr(Config cf) {
		
		//On dépile le code
        cf.get_code().pop();
		
		//On récupère le booleen
		BoolV b = (BoolV) cf.get_value();
		
		//On dépile la pile
		ValueSE x = (ValueSE) cf.get_stack().pop();
		
		//On donne la valeur de x dans le terme de la config
		cf.set_value(x.get_value());
		
		//On ajoute le reste du code dans la pile
		cf.get_stack().addFirst(new CodeSE(cf.get_code()));
		
		//On execute un code différent selon le booleen
		//Ici, on copie la liste pour éviter les problèmes évoqués dans le sujet
		if (b.get_boolean()){
			cf.set_code(new LinkedList<Instr> (code1));
		}
		else{
			cf.set_code(new LinkedList<Instr> (code2));
		}
    }
}
