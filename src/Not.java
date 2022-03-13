import java.util.Map;

/**
 * Not class.
 * The Not class has an Expression value, extents the UnaryExpression abstract.
 *
 * @author Sagi Wilentzik.
 */
public class Not extends UnaryExpression {

    private static final String NOT = "~";

    /**
     * Constructor.
     *
     * @param expression1 is the new expression.
     */
    public Not(Expression expression1) {
        super(expression1);
    }

    /**
     * evaluate method will evaluate each part of the expression.
     *
     * @param assignment - is the map for the expression.
     * @return the result of not expression.
     * @throws Exception in case that exception throws in different class.
     */
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return !this.getExpression().evaluate(assignment);
    }

    /**
     * assign method.
     *
     * @param var         is the string to check.
     * @param expression1 is the expression.
     * @return new Not expression.
     */
    public Expression assign(String var, Expression expression1) {
        return new Not(this.getExpression().assign(var, expression1));
    }

    /**
     * getSymbol method.
     *
     * @return the symbol that represent Not.
     */
    @Override
    public String getSymbol() {
        return NOT;
    }

    @Override
    public Expression nandify() {
        return new Nand(this.getExpression().nandify(), this.getExpression().nandify());
    }

    @Override
    public Expression norify() {
        return new Nor(this.getExpression().norify(), this.getExpression().norify());
    }

    @Override
    public Expression simplify() {
        Expression e1 = this.getExpression().simplify();
        if (e1.simplify().isVar()) {
            return new Not(e1);
        }
        if (e1.equals(new Val(true))) {
            return new Val(false);
        }
        if (e1.equals(new Val(false))) {
            return new Val(true);
        }
        return new Not(e1);
    }

}


