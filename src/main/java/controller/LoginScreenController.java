package controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import roberson.qam2.Main;

import java.net.URL;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;

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

        System.out.println("Initialized!");
    }

    public void onActionCancel(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void onActionSubmit(ActionEvent actionEvent) {
        // TODO - just testing
        try {
            String username = usernameField.getText();
            String password = passwordField.getText();

            if (verifyUser(username, password)) {
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