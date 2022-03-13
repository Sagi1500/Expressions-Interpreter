import java.util.List;
import java.util.Map;

/**
 * Expression interface.
 * The Expression interface has methods we do on expressions.
 *
 * @author Sagi Wilentzik.
 */
public interface Expression {

    /**
     * Evaluate the expression using the variable values provided in the assignment, and return the result.
     *
     * @param assignment is the map.
     * @return the value of the expression
     * @throws Exception If the expression contains a variable which is not in the assignment, an exception  is thrown.
     */
    Boolean evaluate(Map<String, Boolean> assignment) throws Exception;

    /**
     * A convenience method. Like the `evaluate(assignment)` method above, but uses an empty assignment.
     *
     * @return the value of the expression
     * @throws Exception if the car is not in the expression.
     */
    Boolean evaluate() throws Exception;

    /**
     * @return a list of the variables in the expression.
     */
    List<String> getVariables();

    /**
     * @return a nice string representation of the expression.
     */
    String toString();

    /**
     * Returns a new expression in which all occurrences of the variable
     * var are replaced with the provided expression (Does not modify the current expression).
     *
     * @param var        is the string.
     * @param expression is the expression.
     * @return the new expression.
     */
    Expression assign(String var, Expression expression);

    /**
     * @return the expression tree resulting from converting all the operations to the logical Nand operation.
     */
    Expression nandify();

    /**
     * @return the expression tree resulting from converting all the operations to the logical Nor operation.
     */
    Expression norify();

    /**
     * @return a simplified version of the current expression.
     */
    Expression simplify();

    /**
     * @return the symbol of the expression.
     */
    String getSymbol();

    /**
     * @return true if the expression is val, otherwise returns false.
     */
    Boolean isVal();

    /**
     * @return true if the expression is var, otherwise returns false.
     */
    Boolean isVar();

    /**
     * @param expression is the second expression.
     * @return true if the expressions are equals, otherwise return false.
     */
    Boolean equals(Expression expression);

    /**
     * @return the value.
     * @throws Exception it is not a val
     */
    Boolean getVal() throws Exception;

    /**
     * @return the variable.
     * @throws Exception it is not a variable.
     */
    String getVar() throws Exception;

    /**
     * @return a list that contain all the part of the expression.
     */
    List<Expression> getSub();

}