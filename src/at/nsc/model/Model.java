package at.nsc.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**NIS RPN - Controller
 * @author Niklas Schachl
 * @version 1.0, 10.12.2020
 */
public class Model
{
    private Stack<String> stack = new Stack<String>();
    private StringBuilder stringBuilder = new StringBuilder();

    public double getValue()
    {
        double value = Double.parseDouble(stack.pop());
        return value;
    }

    public ObservableList<String> getObservableList() {
        List<String> strings = new ArrayList<>(stack);
        Collections.reverse(strings);
        return FXCollections.observableList(strings);
    }

    public void  clearStack()
    {
        stack.clear();
    }

    public String getOperator()
    {
        return stack.pop();
    }

    public void pushToStack(String value)
    {
        stack.push(value);
    }

    public StringBuilder getStringBuilder()
    {
        return stringBuilder;
    }
}
