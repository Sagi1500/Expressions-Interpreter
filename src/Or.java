import java.util.Map;

/**
 * Or class.
 *  This Or class has 2 Expression value, extents the BinaryExpression abstract.
 *
 * @author Sagi Wilentzik.
 */
public class Or extends BinaryExpression {

    private static final String OR = "|";

    /**
     * Constructor.
     * @param ex1 is the first expression.
     * @param ex2 is the second expression.
     */
    public Or(Expression ex1, Expression ex2) {
        super(ex1, ex2);
    }

    /**
     * evaluate method will evaluate each part of the expression.
     * @param assignment - is the map for the expression.
     * @return the result of or on the 2 expressions.
     * @throws Exception i
     * n case that exception throws in different class.
     */
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return (this.getLeftExpression().evaluate(assignment) || this.getRightExpression().evaluate(assignment))
                || (this.getRightExpression().evaluate(assignment)
                || this.getLeftExpression().evaluate(assignment));
    }

    /**
     * assign method.
     * @param var is the string to check.
     * @param expression is the expression.
     * @return new expression with 2 new sides.
     */
    public Expression assign(String var, Expression expression) {
        return new Or(this.getLeftExpression().assign(var, expression),
                this.getRightExpression().assign(var, expression));
    }

    /**
     * getSymbol method.
     * @return the symbol that represent xor.
     */
    @Override
    public String getSymbol() {
        return OR;
    }

    @Override
    public Expression nandify() {
        return new Nand(new Nand(this.getLeftExpression().nandify(), this.getLeftExpression().nandify()),
                new Nand(this.getRightExpression().nandify(), this.getRightExpression().nandify()));
    }

    @Override
    public Expression norify() {
        return new Nor(new Nor(this.getLeftExpression().norify(), this.getRightExpression().norify()),
                new Nor(this.getLeftExpression().norify(), this.getRightExpression().norify()));
    }

    @Override
    public Expression simplify() {
        Expression e1 = this.getLeftExpression().simplify();
        Expression e2 = this.getRightExpression().simplify();
        if (e1.isVal()) {
            if (e1.equals(new Val(true))) {
                return new Val(true);
            } else {
                return e2;
            }
        }
        if (e2.isVal()) {
            if (e2.equals(new Val(true))) {
                return new Val(true);
            } else {
               return e1;
            }
        }
        if (e1.equals(e2)) {
            return e1;
        }
        return new Or(e1, e2);
    }
}
