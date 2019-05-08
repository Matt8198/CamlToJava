/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mambrois
 */
public class Fst extends Instr{
    
    void exec_instr(Config cf){
        //On récupère la valeur de la config
		PairV p = (PairV)(cf.get_value());
		// System.out.println("MA PAIRE " + p);
		// System.out.println("MA PAIRE V1" + p.getValue1());
		// System.out.println("MA PAIRE V2" + p.getValue2());
        cf.set_value(p.getValue1());
        cf.get_code().pop();
    }
    
}
