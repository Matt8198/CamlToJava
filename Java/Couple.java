import java.util.*;

//Construction d'un couple permettant de mettre un type en 
//type de LinkedList (notamment pour la pile de définitions de fonctions)
public class Couple<First, Second>{
    private First fst;
    private Second snd;
    
    public Couple(First f, Second s){
        this.fst = f;
        this.snd = s;
    }
    
    public First get_fst(){
		return fst;
		}
    
    public Second get_snd(){
		return snd;
		}
    
    
}