import java.util.Map;

/**
 * Xnor class.
 * This Xnor class has 2 Expression value, extents the BinaryExpression abstract.
 *
 * @author Sagi Wilentzik.
 */
public class Xnor extends BinaryExpression {

    private static final String XNOR = "#";

    /**
     * Constructor.
     *
     * @param ex1 is the first expression.
     * @param ex2 is the second expression.
     */
    public Xnor(Expression ex1, Expression ex2) {
        super(ex1, ex2);
    }

    /**
     * evaluate method will evaluate each part of the expression.
     *
     * @param assignment - is the map for the expression.
     * @return the result of AND on the 2 expressions.
     * @throws Exception in case that exception throws in different class.
     */
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return this.getLeftExpression().evaluate(assignment) == this.getRightExpression().evaluate(assignment)
                || this.getRightExpression().evaluate(assignment) == this.getLeftExpression().evaluate(assignment);
    }

    /**
     * assign method.
     *
     * @param var        is the string to check.
     * @param expression is the expression.
     * @return new expression with 2 new sides.
     */
    public Expression assign(String var, Expression expression) {
        return new Xnor(this.getLeftExpression().assign(var, expression),
                this.getRightExpression().assign(var, expression));
    }

    /**
     * getSymbol method.
     *
     * @return the symbol that represent Xnor.
     */
    @Override
    public String getSymbol() {
        return XNOR;
    }

    @Override
    public Expression nandify() {
        return new Nand(new Nand(new Nand(this.getLeftExpression().nandify(), this.getLeftExpression().nandify()),
                new Nand(this.getRightExpression().nandify(), this.getRightExpression().nandify())),
                new Nand(this.getLeftExpression().nandify(), this.getRightExpression().nandify()));
    }

    @Override
    public Expression norify() {
        return new Nor(new Nor(this.getLeftExpression().norify(), new Nor(this.getLeftExpression().norify(),
                this.getRightExpression().norify())), new Nor(this.getRightExpression().norify(),
                new Nor(this.getLeftExpression().norify(), this.getRightExpression().norify())));
    }

    @Override
    public Expression simplify() {
        Expression e1 = this.getLeftExpression().simplify();
        Expression e2 = this.getRightExpression().simplify();
        if (e1.isVal() && e2.isVal()) {
            if (e1.equals(e2)) {
                return new Val(true);
            }
            return new Val(false);
        }
        if (e1.equals(e2)) {
            return new Val(true);
        }
        return new Xnor(e1, e2);
    }
}
