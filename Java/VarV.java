import java.util.*;

public class VarV extends Value {
    /* Fields */
    String v;

    /* Constructors */
    public VarV (String i) {
	 v = i;
    }

    String get_var () {
        return v;
    }
    void set_var (String s) {
        v=s;
    }

    void print_value() {
        System.out.print(v);
    }
}
