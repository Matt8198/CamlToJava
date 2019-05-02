import java.util.*;

class Config extends Object {
    Value v;
    LinkedList<Instr> c;
    LinkedList<StackElem> s;
	
	Map<String, LinkedList<Instr>> fds ;

    Value get_value() {
        return v;
    }
    LinkedList<Instr> get_code() {
        return c;
    }
    LinkedList<StackElem> get_stack() {
        return s;
    }
	Map<String,LinkedList<Instr>> get_fds(){
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
	void set_fds(Map<String,LinkedList<Instr>> new_fds){
		fds=new_fds;
	}

    /* Constructors */
    public Config (Value vl, LinkedList<Instr> cd, LinkedList<StackElem> se, Map<String,LinkedList<Instr>> f) {
        v = vl;
        c = cd;
        s = se;
		fds=f;
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
    }
    
}

