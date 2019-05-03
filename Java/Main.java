// compile with: javac *java
// run with: java Main

import java.util.*;

public class Main {
    
    public static void main(String[] args){

        LinkedList<Integer> ll1, ll2;
        LinkedList<Instr> example_code ;
		Map<String,LinkedList<Instr>> map;
        Config cfg;
        

        cfg = new Config(new NullV(), Gen.code, LLE.empty(),new HashMap<String,LinkedList<Instr>>());
        cfg.exec();
        cfg.get_value().print_value();
        System.out.println();
        
    }

}
