package at.nsc.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**NIS RPN - Controller
 * @author Niklas Schachl
 * @version 1.0, 19.12.2020
 */
public class AboutController
{
    private static Stage stage = new Stage();
    public static void show(Stage mainStage)
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(AboutController.class.getResource("/at/nsc/view/aboutView.fxml"));
            Parent root = fxmlLoader.load();

            stage.initOwner(mainStage);
            stage.getIcons().add(new Image("/at/nsc/images/icon_logo.png"));
            stage.setTitle("About");
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
    public void action_close()
    {
        stage.close();
    }
}
