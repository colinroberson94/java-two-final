package roberson.qam2;

import DAO.DBConnection;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The main class creates an application for customer appointment management.
 *
 * @author Colin Roberson
 */

public class Main extends Application {

    /**
     * The start method creates the stage that will be used for the life of the application and loads the initial scene.
     *
     * @param stage Primary stage for this application.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login-screen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 250);
        stage.setTitle("Appointment Scheduling");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Overloaded static method that will switch scene's when called.
     *
     * @param event Action that will trigger scene change.
     * @param fxmlPath Path to the scene that will be displayed.
     */
    public static void switchStage(ActionEvent event, String fxmlPath) {
        try {
            Parent scene;
            Stage stage;
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(Main.class.getResource(fxmlPath));
            stage.setScene(new Scene(scene));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Overloaded static method that will switch scene's when called.
     *
     * @param event Action that will trigger scene change.
     * @param fxmlPath Path to the scene that will be displayed.
     * @param x horizontal position of the stage
     * @param y vertical position of the stage
     */
    public static void switchStage(ActionEvent event, String fxmlPath, double x, double y) {
        try {
            Parent scene;
            Stage stage;
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.setX(x);
            stage.setY(y);
            scene = FXMLLoader.load(Main.class.getResource(fxmlPath));
            stage.setScene(new Scene(scene));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The main method is the entry point for the application.
     *
     * @param args args an array of command-line arguments for the application
     */
    public static void main(String[] args) {
        DBConnection.openConnection();
        launch();
        DBConnection.closeConnection();
    }
}