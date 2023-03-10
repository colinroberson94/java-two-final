package controller;

import DAO.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import roberson.qam2.Main;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginScreenController implements Initializable {
    public Label userLocation;
    public TextField usernameField;
    public PasswordField passwordField;
    public MenuButton languageSelector;
    public Button submitLoginButton;
    public Button cancelLoginButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Initialized!");
    }

    public void onActionCancel(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void onActionSubmit(ActionEvent actionEvent) {
        // TODO - just testing
        try {
            if ((usernameField.getText().contentEquals("username")) && passwordField.getText().contentEquals("password")) {
                Main.switchStage(actionEvent, "/roberson/qam2/appointment-screen.fxml");
            }
            else {
                throw new Exception();
            }
        }
        catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.NONE, "Incorrect username or password", ButtonType.OK);
            alert.showAndWait();
        }
    }
}