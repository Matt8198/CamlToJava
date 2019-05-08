import java.util.*;

class Config extends Object {
    private Value v;
    private LinkedList<Instr> c;
    private LinkedList<StackElem> s;
	
	//Notre pile de définitions de fonctions
	private LinkedList<Couple<String, LinkedList<Instr>>> fds ;

    Value get_value() {
        return v;
    }
    LinkedList<Instr> get_code() {
        return c;
    }
    LinkedList<StackElem> get_stack() {
        return s;
    }
	
	//Retourne notre pile de définitions de fonctions
	LinkedList<Couple<String, LinkedList<Instr>>> get_fds(){
		return fds;
	}

		
    void set_value(Value nv) {
        v = nv;
    }
    void set_code(LinkedList<Instr> nc) {
        c = nc;
    }
    void set_stack(LinkedList<StackElem> ns) {
        s = ns;
    }
	
	//Remplace notre pile de définitions de fonctions
	void set_fds(LinkedList<Couple<String, LinkedList<Instr>>> new_fds){
		fds=new_fds;
	}

    /* Constructors */
    public Config (Value vl, LinkedList<Instr> cd, LinkedList<StackElem> se, LinkedList<Couple<String, LinkedList<Instr>>> defs) {
        v = vl;
        c = cd;
        s = se;
		fds=defs;
    }


    // one-step execution 
    boolean exec_step() {
        // to be implemented
        if (c.isEmpty()){
            return false;
        }
        else{
            c.get(0).exec_instr(this);			
			return true;
        }
        
    }

    // run to completion
    void exec() {
        // to be implemented
        while (exec_step()){
            
        }
    }

    // run for n steps
    void step(int n) {
        // to be implemented
		for(int i=0;i<n;i++){
		}
    }
    
}

