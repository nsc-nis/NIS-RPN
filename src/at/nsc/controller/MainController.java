package at.nsc.controller;

import at.nsc.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.net.URL;
import java.util.*;

/**NIS RPN - Controller
 * @author Niklas Schachl
 * @version 1.0, 10.12.2020
 */
public class MainController implements Initializable
{
    private Stage stage;
    private at.nsc.model.Model model = new Model();
    private StringBuilder inputs = new StringBuilder();
    private StringBuilder values;
    private int indexOfSB;

    @FXML
    private Label label_input;

    @FXML
    private ListView<String> listView_operations;

    public static void show(Stage stage)
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(MainController.class.getResource("/at/nsc/view/view.fxml"));
            Parent root = fxmlLoader.load();

            //get controller which is connected to this fxml file
            MainController ctrl = fxmlLoader.getController();
            ctrl.stage = stage;

            stage.getIcons().add(new Image("/at/nsc/images/icon_logo.png"));
            stage.setTitle("NIS RPN Calculator");
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch (Exception exception)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Internal Error");
            alert.setContentText(String.format("An internal Error occurred. Please restart the program%nor contact the developer on GitHub%n%nError message: %s", exception.getMessage()));
            alert.showAndWait();
            System.err.println(exception.getMessage());
            exception.printStackTrace(System.err);
        }
    }

    @FXML
    private void action_number(ActionEvent event) {

        Object node = event.getSource();
        Button button = (Button)node;

        inputs.append(button.getText());
        indexOfSB = inputs.indexOf(button.getText());
        values.append(button.getText());
        label_input.setText(inputs.toString());
    }

    @FXML
    private void action_enter()
    {
        if (inputs.isEmpty())
        {
            double result = model.calculate();
            label_input.setText(String.format("%f", result));
            clear();
        }
        else
        {
            String result = values.toString();
            model.pushToStack(result);
            values.delete(0, values.length());
            inputs.delete(0, inputs.length());
            listView_operations.setItems(model.getObservableList());
            label_input.setText("");
        }
    }

    @FXML
    private void action_C()
    {
        inputs.deleteCharAt(indexOfSB);
        label_input.setText(inputs.toString());
    }

    @FXML
    private void action_clear()
    {
        label_input.setText("");
        clear();
    }

    @FXML
    private void action_point(ActionEvent event)
    {
        Object node = event.getSource();
        Button button = (Button)node;

        inputs.append(button.getText());
        indexOfSB = inputs.indexOf(button.getText());
        values.append(".");
        label_input.setText(inputs.toString());
    }

     @FXML
     private void action_divide(ActionEvent event)
     {
         Object node = event.getSource();
         Button button = (Button)node;

         inputs.append(button.getText());
         indexOfSB = inputs.indexOf(button.getText());
         values.append("/");
         label_input.setText(inputs.toString());
     }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        values = model.getStringBuilder();
        listView_operations.setItems(model.getObservableList());
    }

    public void clear()
    {
        values.delete(0, values.length());
        inputs.delete(0, inputs.length());
        model.clearStack();
        listView_operations.setItems(model.getObservableList());
    }
}
