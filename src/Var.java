import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Class Var.
 * This Val class has a Var that has a string variable value, implements the Expression interface.
 *
 * @author Sagi Wilentzik.
 */
public class Var implements Expression {

    private String variable;

    /**
     * Constructor.
     *
     * @param str is the variable symbol.
     */
    public Var(String str) {
        this.variable = str;
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        Boolean value = assignment.get((this.variable));
        if (value == null) {
            throw new Exception("There is no value for" + this.variable);
        }
        return value;
    }

    @Override
    public Boolean evaluate() throws Exception {
        throw new Exception("There is no value for" + this.variable);
    }

    @Override
    public List<String> getVariables() {
        List<String> stringList = new ArrayList<>();
        stringList.add(variable);
        return stringList;
    }

    @Override
    public String toString() {
        return this.variable;
    }

    @Override
    public Expression assign(String var, Expression expression) {
        if (this.variable.equals(var)) {
            return expression;
        }
        return this;
    }

    @Override
    public Expression nandify() {
        return this;
    }

    @Override
    public Expression norify() {
        return this;
    }

    @Override
    public Expression simplify() {
        return this;
    }

    @Override
    public Boolean isVal() {
        return false;
    }

    @Override
    public Boolean isVar() {
        return true;
    }

    @Override
    public Boolean getVal() throws Exception {
        throw new Exception("Not a val");
    }

    @Override
    public Boolean equals(Expression expression) {
        if (expression.getSymbol() != null || expression.isVal()) {
            return false;
        }
        try {
            return expression.getVar().equals(this.getVar());
        } catch (Exception e) {
            System.out.println("Not a Var");
        }
        return false;
    }

    /**
     * @return the variable
     */
    public String getVar() {
        return this.variable;
    }

    @Override
    public List<Expression> getSub() {
        return new ArrayList<>();
    }

    /**
     * @return the symbol.
     */
    public String getSymbol() {
        return null;
    }
}
