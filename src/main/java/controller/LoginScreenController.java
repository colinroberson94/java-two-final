package controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import roberson.qam2.Main;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import static DAO.UsersQuery.verifyUser;

public class LoginScreenController implements Initializable {
    public Label userLocation;
    public TextField usernameField;
    public PasswordField passwordField;
    public MenuButton languageSelector;
    public Button submitLoginButton;
    public Button cancelLoginButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // testing resource bundle and changing languages
            ZoneId zone = ZoneId.systemDefault();
            userLocation.setText(zone.toString());

            ResourceBundle rb = ResourceBundle.getBundle("language/Nat", Locale.getDefault());
            if (Locale.getDefault().getLanguage().equals("fr")) {
                usernameField.setPromptText(rb.getString("Username"));
                passwordField.setPromptText(rb.getString("Password"));
                submitLoginButton.setText(rb.getString("Submit"));
                cancelLoginButton.setText(rb.getString("Cancel"));
            }
    }

    public void onActionCancel(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void onActionSubmit(ActionEvent actionEvent) {
        try {
            String username = usernameField.getText();
            String password = passwordField.getText();

            FileWriter logFile = new FileWriter("login_activity.txt", true);
            PrintWriter outputLog = new PrintWriter(logFile);

            if (verifyUser(username, password)) {
                outputLog.println(Timestamp.valueOf(LocalDateTime.now()) + ": " + username + " SUCCESSFULLY logged in.");
                outputLog.close();
                Main.switchStage(actionEvent, "/roberson/qam2/appointment-screen.fxml");
            }
            else if (username.isEmpty() || password.isEmpty()) {
                throw new IllegalArgumentException();
            }
            else {
                outputLog.println(Timestamp.valueOf(LocalDateTime.now()) + ": " + username + " FAILED to log in.");
                outputLog.close();
                throw new Exception();
            }
        } catch (IllegalArgumentException e) {
            Alert alert = new Alert(Alert.AlertType.NONE, "Username or Password cannot be blank", ButtonType.OK);
            alert.showAndWait();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.NONE, "Incorrect username or password", ButtonType.OK);
            alert.showAndWait();
        }
    }
}