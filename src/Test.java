import java.util.Map;
import java.util.TreeMap;

/**
 * Test class.
 */
public class Test {

    /**
     * Test main function.
     *
     * @param args arguments array.
     */
    @SuppressWarnings("checkstyle:RegexpSingleline")
    public static void main(String[] args) {
        Var x = new Var("x");
        Var y = new Var("y");
        Var z = new Var("z");
        Map<String, Boolean> ass = new TreeMap<>();
        ass.put("y", true);
        ass.put("x", false);
        ass.put("z", true);
        Expression e1 = new Or(x, new And(y, z));
        Expression e2 = new Xor(x, y);
        System.out.println(e1);
        System.out.println(e2);
        e1 = e1.assign("x", e2);
        System.out.println(e1);
        try {
            System.out.println(e1.evaluate(ass));
        } catch (Exception exception) {
            System.out.println("exception-: can't evaluate");
        }

        Expression e = new Xor(new Var("x"), new Var("y"));
        System.out.println(e.nandify());
        System.out.println(e.norify());
        System.out.println("simplify");
        // and
        System.out.println();
        System.out.println("And test");
        Expression e3 = new And(new Val(false), x);
        System.out.println(e3);
        System.out.println(e3.simplify());
        Expression e4 = new And(new Val(true), x);
        System.out.println(e4);
        System.out.println(e4.simplify());
        Expression e5 = new And(x, x);
        System.out.println(e5);
        System.out.println(e5.simplify());
        Expression e6 = new And(x, new Val(false));
        System.out.println(e6);
        System.out.println(e6.simplify());
        Expression e7 = new And(x, new Val(true));
        System.out.println(e7);
        System.out.println(e7.simplify());


        //or
        System.out.println();
        System.out.println("Or test");
        Expression e8 = new Or(new Val(false), x);
        System.out.println(e8);
        System.out.println(e8.simplify());
        Expression e9 = new Or(new Val(true), x);
        System.out.println(e9);
        System.out.println(e9.simplify());
        Expression e10 = new Or(x, x);
        System.out.println(e10);
        System.out.println(e10.simplify());
        Expression e11 = new Or(x, new Val(false));
        System.out.println(e11);
        System.out.println(e11.simplify());
        Expression e12 = new Or(x, new Val(true));
        System.out.println(e12);
        System.out.println(e12.simplify());


        //Xor
        System.out.println();
        System.out.println("Xor test");
        Expression e13 = new Xor(new Val(false), x);
        System.out.println(e13);
        System.out.println(e13.simplify());
        Expression e14 = new Xor(new Val(true), x);
        System.out.println(e14);
        System.out.println(e14.simplify());
        Expression e15 = new Xor(x, x);
        System.out.println(e15);
        System.out.println(e15.simplify());
        Expression e16 = new Xor(x, new Val(false));
        System.out.println(e16);
        System.out.println(e16.simplify());
        Expression e17 = new Xor(x, new Val(true));
        System.out.println(e17);
        System.out.println(e17.simplify());

        //Nand
        System.out.println();
        System.out.println("Nand test");
        Expression e18 = new Nand(new Val(false), x);
        System.out.println(e18);
        System.out.println(e18.simplify());
        Expression e19 = new Nand(new Val(true), x);
        System.out.println(e19);
        System.out.println(e19.simplify());
        Expression e20 = new Nand(x, x);
        System.out.println(e20);
        System.out.println(e20.simplify());
        Expression e21 = new Nand(x, new Val(false));
        System.out.println(e21);
        System.out.println(e21.simplify());
        Expression e22 = new Nand(x, new Val(true));
        System.out.println(e22);
        System.out.println(e22.simplify());

        //Nor
        System.out.println();
        System.out.println("Nor test");
        Expression e23 = new Nor(new Val(false), x);
        System.out.println(e23);
        System.out.println(e23.simplify());
        Expression e27 = new Nor(new Val(true), x);
        System.out.println(e27);
        System.out.println(e27.simplify());
        Expression e24 = new Nor(x, x);
        System.out.println(e24);
        System.out.println(e24.simplify());
        Expression e25 = new Nor(x, new Val(false));
        System.out.println(e25);
        System.out.println(e25.simplify());
        Expression e26 = new Nor(x, new Val(true));
        System.out.println(e26);
        System.out.println(e26.simplify());

        // Xnor
        System.out.println();
        System.out.println("Xnor test");
        Expression e28 = new Xnor(x, x);
        System.out.println(e28);
        System.out.println(e28.simplify());

        System.out.println("End of my test");
        System.out.println();

        Expression e29 = new Xor(new And(new Var("x"), new Val(false)), new Or(new Var("y"), new Val(false)));
        System.out.println(e29);
        // the result is:
        // ((x & F) ^ (y | F))
        System.out.println(e29.simplify());
        // the result is:
        // (x ^ y)

        // check.
        Expression check = new Val(true);
        System.out.println(new Not(check));
        Expression check1 = new Nand(new And(new Var("s"), new Var("o")), new Var("k"));
        System.out.println(check1);
        System.out.println(check1.nandify());

    }
}

