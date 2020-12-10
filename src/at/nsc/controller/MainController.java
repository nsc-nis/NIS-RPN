package at.nsc.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**NIS RPN - Controller
 * @author Niklas Schachl
 * @version 1.0, 10.12.2020
 */
public class MainController
{
    private Stage stage;

    public static void show(Stage stage)
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(MainController.class.getResource("/at/nsc/view/view.fxml"));
            Parent root = fxmlLoader.load();

            //get controller which is connected to this fxml file
            MainController ctrl = fxmlLoader.getController();
            ctrl.stage = stage;

            //stage.getIcons().add(new Image("/at/nsc/icon_user.png"));
            stage.setTitle("NIS RPN Calculator");
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch (Exception exception)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Internal Error");
            alert.setContentText("An internal Error occurred.Please restart the program");
            alert.showAndWait();
            System.err.println("Something wrong with view.fxml: " + exception.getMessage());
            exception.printStackTrace(System.err);
        }
    }
}
