import java.util.Map;

/**
 * Nand class.
 * This Nor class has 2 Expression value, extents the BinaryExpression abstract.
 *
 * @author Sagi Wilentzik.
 */
public class Nor extends BinaryExpression {

    private static final String NOR = "V";

    /**
     * Constructor.
     *
     * @param ex1 is the first expression.
     * @param ex2 is the second expression.
     */
    public Nor(Expression ex1, Expression ex2) {
        super(ex1, ex2);
    }

    /**
     * evaluate method will evaluate each part of the expression.
     *
     * @param assignment - is the map for the expression.
     * @return the result of nor on the 2 expressions.
     * @throws Exception in case that exception throws in different class.
     */
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {

        return !(this.getLeftExpression().evaluate(assignment) || this.getRightExpression().evaluate(assignment))
                || !(this.getRightExpression().evaluate(assignment)
                || this.getLeftExpression().evaluate(assignment));
    }

    /**
     * assign method.
     *
     * @param var        is the string to check.
     * @param expression is the expression.
     * @return new expression with 2 new sides.
     */
    public Expression assign(String var, Expression expression) {
        return new Nor(this.getLeftExpression().assign(var, expression),
                this.getRightExpression().assign(var, expression));
    }

    /**
     * getSymbol method.
     *
     * @return the symbol that represent nor.
     */
    @Override
    public String getSymbol() {
        return NOR;
    }

    @Override
    public Expression nandify() {
        return new Nand(new Nand(new Nand(this.getLeftExpression().nandify(), this.getLeftExpression().nandify()),
                new Nand(this.getRightExpression().nandify(), this.getRightExpression()).nandify()),
                new Nand(new Nand(this.getLeftExpression().nandify(), this.getLeftExpression().nandify()),
                        new Nand(this.getRightExpression().nandify(), this.getRightExpression().nandify())));
    }

    @Override
    public Expression norify() {
        Expression e1 = this.getLeftExpression().norify();
        Expression e2 = this.getRightExpression().norify();
        return new Nor(e1, e2);
    }

    @Override
    public Expression simplify() {
        Expression e1 = this.getLeftExpression().simplify();
        Expression e2 = this.getRightExpression().simplify();
        if (e1.isVal() && e2.isVal()) {
            if (e1.equals(e2)) {
                return new Not(e1).simplify();
            }
            return new Val(false);
        }
        if (e1.isVal()) {
            if (e1.equals(new Val(true))) {
                return new Val(false);
            } else {
                return new Not(e2);
            }
        }
        if (e2.isVal()) {
            if (e2.equals(new Val(true))) {
                return new Val(false);
            } else {
                return new Not(e1);
            }
        }
        if (e1.equals(e2)) {
            return new Not(e1);
        }
        return new Nor(e1, e2);
    }
}
