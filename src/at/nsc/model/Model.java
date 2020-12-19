package at.nsc.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.nio.DoubleBuffer;
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
    private ArrayList<Double> list_numbers = new ArrayList<Double>();

    public ObservableList<String> getObservableList() {
        List<String> strings = new ArrayList<>(stack);
        Collections.reverse(strings);
        return FXCollections.observableList(strings);
    }

    public void  clearStack()
    {
        stack.clear();
        list_numbers.clear();
    }

    public void pushToStack(String value)
    {
        stack.push(value);
    }

    public StringBuilder getStringBuilder()
    {
        return stringBuilder;
    }

    public double calculate()
    {
        double result = 0;
        Collections.reverse(stack);

        while (!stack.isEmpty())
        {
            String operator = stack.pop();
            switch (operator)
            {
                case "+":
                    result = plus(result);
                    break;
                case "-":
                    result = minus(result);
                    break;
                case "*":
                    result = multiply(result);
                    break;
                case "/":
                    result = divide(result);
                    break;
                default:
                    list_numbers.add(Double.parseDouble(operator));
                    break;
            }
        }
        return result;
    }

    private double plus(double result)
    {
        for (int i = 0; i < list_numbers.size(); i++)
        {
            result = result + list_numbers.get(i);
        }
        return result;
    }

    private double minus(double result)
    {
        for (int i = 0; i < list_numbers.size(); i++)
        {
            result = result - list_numbers.get(i);
        }
        return result;
    }

    private double multiply(double result)
    {
        try
        {
            for (int i = 0; i < list_numbers.size(); i++)
            {
                result = list_numbers.get(i) * list_numbers.get(i+1);
            }
        }
        catch (Exception exception)
        {

        }
        return result;
    }

    private double divide(double result)
    {
        try
        {
            for (int i = 0; i < list_numbers.size(); i++)
            {
                result = list_numbers.get(i) / list_numbers.get(i+1);
            }
        }
        catch (Exception exception)
        {

        }
        return result;
    }
}
