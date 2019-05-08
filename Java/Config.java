import java.util.*;

class Config extends Object {
    Value v;
    LinkedList<Instr> c;
    LinkedList<StackElem> s;
	
	LinkedList<Couple<String, LinkedList<Instr>>> fds ;

    Value get_value() {
        return v;
    }
    LinkedList<Instr> get_code() {
        return c;
    }
    LinkedList<StackElem> get_stack() {
        return s;
    }
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
			// System.out.println("VOICI LA CONFIG EN COURS" + c.get(0));
			// System.out.println("Je look la valeur config" + this.v);
			// System.out.println("Je look le code config"+this.c);
			// System.out.println("Je look la pile config" + this.s);
			// System.out.println();
            c.get(0).exec_instr(this);
			
			
            // System.out.println("");
			return true;
        }
        
    }

    // run to completion
    void exec() {
		System.out.println("VOICI LE CODE DE DEPART" + this.c);
        // to be implemented
        while (exec_step()){
            
        }
    }

    // run for n steps
    void step(int n) {
        // to be implemented
    }
    
}

