package at.nsc.main;

import at.nsc.controller.MainController;
import javafx.application.Application;
import javafx.stage.Stage;

/**NIS RPN - Main
 * @author Niklas Schachl
 * @version 1.0, 10.12.2020
 */
public class Main extends Application
{
    public static void main (String[] args)
    {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception
    {
        MainController.show(stage);
    }
}
