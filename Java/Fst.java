/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mambrois
 */
//Class permettant de choisir le premier élément d'une paire
public class Fst extends Instr{
    
    void exec_instr(Config cf){
		
        //On récupère le terme de la config
		PairV p = (PairV)(cf.get_value());
		
		//La nouveau terme est le premier élement de la paire
        cf.set_value(p.getValue1());
		
		//On dépile le code
        cf.get_code().pop();
    }
    
}
