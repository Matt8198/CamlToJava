/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mambrois
 */
public class BinOp extends Instr {
    enum operateur{Add,Sub,Mult,Div,Mod,Eq,Ge,Gt,Le,Lt,Ne};
    
    operateur op;
    
    public BinOp(operateur oper){
        op = oper;
    }
    
    public void exec_instr(Config cf){
		PairV pair = (PairV)cf.get_value();
		Value valeur = null;
        switch(op){
            case Add:
            case Sub:
            case Mult:
			case Mod:
            case Div:
                
                valeur = new IntV(operArith((IntV) pair.getValue1(),(IntV)pair.getValue2()));
               
                break;
			case Eq:
			case Ge:
			case Gt:
			case Le:
			case Lt:
			case Ne:
				valeur = new BoolV(operComp((IntV) pair.getValue1(), (IntV) pair.getValue2()));
				
        }
		cf.set_value(valeur);
		cf.get_code().pop();
    }
    
    public int operArith(IntV a, IntV b){
        switch(op){
            case Add:
                return a.get_int() + b.get_int();
            case Sub:
                return a.get_int() - b.get_int();
            case Mult:
                return a.get_int() * b.get_int();
            case Div:
                return a.get_int() / b.get_int();
			case Mod:
				return a.get_int() % b.get_int();
                
            default: 
                return 0;
        }
    }
	
	public boolean operComp(IntV a, IntV b){
        switch(op){
            case Eq:
                return a.get_int() == b.get_int();
            case Ge:
                return a.get_int() >= b.get_int();
            case Gt:
                return a.get_int() > b.get_int();
            case Le:
                return a.get_int() <= b.get_int();
			case Lt:
				return a.get_int() < b.get_int();
			case Ne:
				return a.get_int() != b.get_int();
                
            default: 
                return false;
        }
    }
}