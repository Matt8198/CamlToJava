/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mambrois
 */
public class Snd extends Instr{
    
    void exec_inst(Config cf){
        cf.set_value(((PairV)(cf.get_value())).getValue2());
        cf.get_code().pop();
    }
    
}
