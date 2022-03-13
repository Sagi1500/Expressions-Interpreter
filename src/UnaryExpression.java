import java.util.Map;
import java.util.TreeMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

/**
 * UnaryExpression abstract class.
 * This UnaryExpression class has an Expression value, extents the BaseExpression abstract class.
 *
 * @author Sagi Wilentzik.
 */
public abstract class UnaryExpression extends BaseExpression {


    public static final String LEFT_COMMA = "(";
    public static final String RIGHT_COMMA = ")";
    public static final int FIRST_IN_LIST = 0;

    /**
     * Constructor.
     *
     * @param expression is the expression.
     */
    public UnaryExpression(Expression expression) {
        super(expression);
    }

    @Override
    public Boolean evaluate() throws Exception {
        Map<String, Boolean> assignment = new TreeMap<>();
        return evaluate(assignment);
    }

    @Override
    public List<String> getVariables() {
        Set<String> set = new TreeSet<>(getExpressionList().get(FIRST_IN_LIST).getVariables());
        return (new ArrayList<>(set));
    }

    @Override
    public String toString() {
        return this.getSymbol() + LEFT_COMMA + this.getExpression() + RIGHT_COMMA;
    }

    /**
     * getExpression method.
     *
     * @return the expression.
     */
    public Expression getExpression() {
        return this.getExpressionList().get(FIRST_IN_LIST);
    }

    @Override
    public Boolean equals(Expression expression1) {
        if (expression1.isVal() || expression1.isVar()) {
            return false;
        }
        return this.toString().equals(expression1.toString());
    }

    @Override
    public Boolean isVal() {
        return false;
    }

    @Override
    public List<Expression> getSub() {
        List<Expression> l = new ArrayList<>();
        l.add(this.getExpression());
        return l;
    }

}
