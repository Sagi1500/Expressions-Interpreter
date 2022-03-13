import java.util.Map;
import java.util.TreeMap;

/**
 * ExpressionsTest class.
 */
public class ExpressionsTest {

    /**
     * The main method will create an expression and prints his details as mention in the assignment.
     * @param args are the main arguments.
     */
    public static void main(String[] args) {
        Var x = new Var("x");
        Var y = new Var("y");
        Var z = new Var("z");
        Map<String, Boolean> ass = new TreeMap<>();
        ass.put("y", true);
        ass.put("x", false);
        ass.put("z", true);
        Expression e1 = new Or(new Xor(new Not(x), new Val(false)), new And(y, z));
        System.out.println(e1);
        try {
            System.out.println(e1.evaluate(ass));
        } catch (Exception e) {
            System.out.println("Error");
        }
        System.out.println(e1.nandify());
        System.out.println(e1.norify());
        System.out.println(e1.simplify());
    }
}
