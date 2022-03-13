import java.util.Map;
import java.util.TreeMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

/**
 * BinaryExpression abstract class.
 * This BinaryExpression class has 2 Expression value, extents the BaseExpression abstract class.
 *
 * @author Sagi Wilentzik.
 */
public abstract class BinaryExpression extends BaseExpression {

    public static final String LEFT_COMMA = "(";
    public static final String RIGHT_COMMA = ")";
    public static final String SPACE = " ";
    public static final int FIRST_IN_LIST = 0;
    public static final int SECOND_IN_LIST = 1;

    /**
     * Contractor.
     *
     * @param leftExpression  - is the expression at the left side of the sign.
     * @param rightExpression - is the expression at the right side of the sign.
     */
    public BinaryExpression(Expression leftExpression, Expression rightExpression) {
        super(leftExpression, rightExpression);
    }

    @Override
    public Boolean evaluate() throws Exception {
        Map<String, Boolean> assignment = new TreeMap<>();
        return evaluate(assignment);
    }

    @Override
    public List<String> getVariables() {
        Set<String> set = new TreeSet<>();
        set.addAll(getExpressionList().get(FIRST_IN_LIST).getVariables());
        set.addAll(getExpressionList().get(SECOND_IN_LIST).getVariables());
        return (new ArrayList<>(set));
    }

    @Override
    public String toString() {
        return LEFT_COMMA + this.getLeftExpression() + SPACE
                + this.getSymbol() + SPACE + this.getRightExpression() + RIGHT_COMMA;
    }

    /**
     * getRightExpression method.
     *
     * @return the expression at the right side of the sign.
     */
    public Expression getRightExpression() {
        return this.getExpressionList().get(SECOND_IN_LIST);
    }

    /**
     * getLeftExpression method.
     *
     * @return the expression at the left side of the sign.
     */
    public Expression getLeftExpression() {
        return this.getExpressionList().get(FIRST_IN_LIST);
    }

    @Override
    public Boolean isVal() {
        return false;
    }

    @Override
    public Boolean equals(Expression expression1) {
        if (expression1.isVal() || expression1.isVar()) {
            return false;
        }
        return this.toString().equals(expression1.toString());
    }

    @Override
    public List<Expression> getSub() {
        List<Expression> l = new ArrayList<>();
        l.add(this.getLeftExpression());
        l.add(this.getRightExpression());
        return l;
    }

}
