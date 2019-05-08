/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mambrois
 */
public class PairV extends Value{
    private Value fst, snd;
    
    public PairV(Value fst, Value snd){
        this.fst = fst;
        this.snd = snd;
    }
    
    public Value getValue1(){
		return fst;
		}
    public Value getValue2(){
		return snd;
		}
     
    public void setValue1(Value new_Fst){
		fst = new_Fst;
		}
    public void setValue2(Value new_Snd){
		snd = new_Snd;
		}
        
	void print_value() {
		System.out.print("("+fst+",");
		System.out.print(snd+")");
    }
}
