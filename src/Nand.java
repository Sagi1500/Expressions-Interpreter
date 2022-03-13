import java.util.Map;

/**
 * Nand class.
 * This Nand class has 2 Expression value, extents the BinaryExpression abstract.
 *
 * @author Sagi Wilentzik.
 */
public class Nand extends BinaryExpression {

    private static final String NAND = "A";

    /**
     * Constructor.
     *
     * @param ex1 is the first expression.
     * @param ex2 is the second expression.
     */
    public Nand(Expression ex1, Expression ex2) {
        super(ex1, ex2);
    }

    /**
     * evaluate method will evaluate each part of the expression.
     *
     * @param assignment - is the map for the expression.
     * @return the result of nand on the 2 expressions.
     * @throws Exception in case that exception throws in different class.
     */
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return !(this.getLeftExpression().evaluate(assignment) && this.getRightExpression().evaluate(assignment))
                || !(this.getRightExpression().evaluate(assignment)
                && this.getLeftExpression().evaluate(assignment));
    }

    /**
     * assign method.
     *
     * @param var        is the string to check.
     * @param expression is the expression.
     * @return new expression with 2 new sides.
     */
    public Expression assign(String var, Expression expression) {
        return new Nand(this.getLeftExpression().assign(var, expression),
                this.getRightExpression().assign(var, expression));
    }

    /**
     * getSymbol method.
     *
     * @return the symbol that represent nand.
     */
    @Override
    public String getSymbol() {
        return NAND;
    }

    @Override
    public Expression nandify() {
        Expression e1 = this.getLeftExpression().nandify();
        Expression e2 = this.getRightExpression().nandify();
        return new Nand(e1, e2);
    }

    @Override
    public Expression norify() {
        return new Nor(new Nor(new Nor(this.getLeftExpression().norify(), this.getLeftExpression().norify()),
                new Nor(this.getRightExpression().norify(), this.getRightExpression().norify())),
                new Nor(new Nor(this.getLeftExpression().norify(), this.getLeftExpression().norify()),
                        new Nor(this.getRightExpression().norify(), this.getRightExpression().norify())));
    }

    @Override
    public Expression simplify() {
        Expression e1 = this.getLeftExpression().simplify();
        Expression e2 = this.getRightExpression().simplify();
        if (e1.isVal() && e2.isVal()) {
            if (e1.equals(e2)) {
                return new Not(e1).simplify();
            }
            return new Val(true);
        }
        if (e1.isVal()) {
            if (e1.equals(new Val(true))) {
                return new Not(e2);
            } else {
                return new Not(e1).simplify();
            }
        }
        if (e2.isVal()) {
            if (e2.equals(new Val(true))) {
                return new Not(e1);
            } else {
                return new Not(e2).simplify();
            }
        }
        if (e1.equals(e2)) {
            return new Not(e1);
        }
        return new Nand(e1, e2);
    }
}
