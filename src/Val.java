import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Val class.
 * This Val class has a Val that has a boolean value, implements the Expression interface.
 *
 * @author Sagi Wilentzik.
 */
public class Val implements Expression {

    private Boolean val;

    /**
     * Constructor.
     *
     * @param val is the new value.
     */
    public Val(Boolean val) {
        this.val = val;
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return val;
    }

    @Override
    public Boolean evaluate() throws Exception {
        return val;
    }

    @Override
    public List<String> getVariables() {
        return new ArrayList<>();
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return this;
    }

    @Override
    public String toString() {
        if (this.val) {
            return "T";
        }
        return "F";
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
    public String getSymbol() {
        return null;
    }


    @Override
    public Boolean isVal() {
        return true;
    }

    @Override
    public Boolean isVar() {
        return false;
    }

    @Override
    public String getVar() throws Exception {
        throw new Exception("Not a var");
    }

    @Override
    public List<Expression> getSub() {
        return new ArrayList<>();
    }

    /**
     * @return the val value.
     */
    public Boolean getVal() {
        return this.val;
    }

    /**
     * @param expression is the second expression.
     * @return true is equals, false otherwise.
     */
    public Boolean equals(Expression expression) {
        try {
            if (this.getVal() == expression.getVal()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println(" try to use get val on non val expression.");
        }
        return false;
    }
}
