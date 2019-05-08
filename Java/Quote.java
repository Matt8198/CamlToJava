import java.util.*;

//Constante
class Quote extends Instr {
    private Value v;

    
    Value get_value() {
        return v;
    }

    void set_value(Value nv) {
        v = nv;
    }

    /* Constructors */
    public Quote (Value vl) {
        v = vl;
    }
    
    void exec_instr(Config cf) {
        cf.set_value(v);
        cf.get_code().pop();
    }
}
