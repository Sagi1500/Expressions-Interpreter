import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * BaseExpression class.
 * This BaseExpression class has Expressions list value,
 * implements the Expression interface.
 *
 * @author Sagi Wilentzik.
 */
public abstract class BaseExpression implements Expression {

    private List<Expression> expressionList;

    /**
     * Constructor for unary base expression.
     *
     * @param expression is the expression.
     */
    public BaseExpression(Expression expression) {
        this.expressionList = new ArrayList<>();
        expressionList.add(expression);
    }

    /**
     * Constructor for binary base expression.
     *
     * @param leftExpression  is the left expression.
     * @param rightExpression is the right expression.
     */
    public BaseExpression(Expression leftExpression, Expression rightExpression) {
        this.expressionList = new ArrayList<>();
        expressionList.add(leftExpression);
        expressionList.add(rightExpression);
    }

    @Override
    public Boolean evaluate() throws Exception {
        Map<String, Boolean> assignment = new TreeMap<>();
        return evaluate(assignment);
    }

    @Override
    public abstract String toString();

    /**
     * @return the expressions list.
     */
    public List<Expression> getExpressionList() {
        return this.expressionList;
    }

    @Override
    public Boolean isVal() {
        return false;
    }

    @Override
    public Boolean isVar() {
        return false;
    }

    @Override
    public Boolean getVal() throws Exception {
        throw new Exception("Not a val");
    }

    @Override
    public String getVar() throws Exception {
        throw new Exception("Not a var");
    }

    @Override
    public List<Expression> getSub() {
        return new ArrayList<>();
    }
}
