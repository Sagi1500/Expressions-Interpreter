import java.util.Map;

/**
 * Xor class.
 * This Xor class has 2 Expression value, extents the BinaryExpression abstract.
 *
 * @author Sagi Wilentzik.
 */
public class Xor extends BinaryExpression {

    private static final String XOR = "^";

    /**
     * Constructor.
     *
     * @param ex1 is the first expression.
     * @param ex2 is the second expression.
     */
    public Xor(Expression ex1, Expression ex2) {
        super(ex1, ex2);
    }

    /**
     * evaluate method will evaluate each part of the expression.
     *
     * @param assignment - is the map for the expression.
     * @return the result of XOR on the 2 expressions.
     * @throws Exception in case that exception throws in different class.
     */
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return (this.getLeftExpression().evaluate(assignment) ^ this.getRightExpression().evaluate(assignment))
                || (this.getRightExpression().evaluate(assignment)
                ^ this.getRightExpression().evaluate(assignment));
    }

    /**
     * assign method.
     *
     * @param var        is the string to check.
     * @param expression is the expression.
     * @return new expression with 2 new sides.
     */
    public Expression assign(String var, Expression expression) {
        return new Xor(this.getLeftExpression().assign(var, expression),
                this.getRightExpression().assign(var, expression));
    }

    /**
     * getSymbol method.
     *
     * @return the symbol that represent xor.
     */
    @Override
    public String getSymbol() {
        return XOR;
    }

    @Override
    public Expression nandify() {
        return new Nand(new Nand(this.getLeftExpression().nandify(), new Nand(this.getLeftExpression().nandify(),
                this.getRightExpression().nandify())), new Nand(this.getRightExpression().nandify(),
                new Nand(this.getLeftExpression().nandify(), this.getRightExpression().nandify())));
    }

    @Override
    public Expression norify() {
        return new Nor(new Nor(new Nor(this.getLeftExpression().norify(), this.getLeftExpression().norify()),
                new Nor(this.getRightExpression().norify(), this.getRightExpression().norify())),
                new Nor(this.getLeftExpression().norify(), this.getRightExpression().norify()));
    }

    @Override
    public Expression simplify() {
        Expression e1 = this.getLeftExpression().simplify();
        Expression e2 = this.getRightExpression().simplify();
        if (e1.isVal() && e2.isVal()) {
            if (e1.equals(e2)) {
                return new Val(false);
            }
            return new Val(true);
        }
        if (e1.isVal()) {
            if (e1.equals(new Val(true))) {
                return new Not(e2);
            } else {
                return e2;
            }
        }
        if (e2.isVal()) {
            if (e2.equals(new Val(true))) {
                return new Not(e1);
            } else {
                return e1;
            }
        }
        if (e1.equals(e2)) {
            return new Val(false);
        }
        return new Xor(e1, e2);
    }
}
