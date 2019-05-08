/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mambrois
 */
//Opération Second : Récupérer le second élément d'une paire
public class Snd extends Instr{
    
    void exec_instr(Config cf){
		//On récupère le terme de la config qui est une paire
		PairV p = (PairV)(cf.get_value());
		//On ajoute le second terme de la paire qu'on ajoute dans la config
        cf.set_value(p.getValue2());
		//On dépile le code
        cf.get_code().pop();
    }
    
}
