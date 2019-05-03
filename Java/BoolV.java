import java.util.*;

public class BoolV extends Value {
    /* Fields */
    Boolean b;

    /* Constructors */
    public BoolV (Boolean bo) {
		b = bo;
    }

    Boolean get_boolean () {
        return b;
    }
    void set_boolean (Boolean bo) {
        b = bo;
    }

    void print_value() {
        System.out.print(b);
    }
}
