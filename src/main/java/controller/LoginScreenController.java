package controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.CurrUser;
import roberson.qam2.Main;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;

import static DAO.AppointmentQuery.getAppointmentsWithinFifteenMin;
import static DAO.UsersQuery.verifyUser;

public class LoginScreenController implements Initializable {

    /**
     * Element of the login GUI
     */
    public Label userLocation;

    /**
     * Element of the login GUI
     */
    public TextField usernameField;

    /**
     * Element of the login GUI
     */
    public PasswordField passwordField;

    /**
     * Element of the login GUI
     */
    public Button submitLoginButton;

    /**
     * Element of the login GUI
     */
    public Button cancelLoginButton;

    /**
     * Initializes controller.
     *
     * @param url The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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

    /**
     * When the Cancel button is clicked, exit the application.
     *
     * @param actionEvent Exit button action
     */
    public void onActionCancel(ActionEvent actionEvent) {
        System.exit(0);
    }

    /**
     * When the Submit button is clicked, log the login attempt and verify if the credentials are correct.
     * If credentials are invalid or fields are empty, throw an exception.
     * Displays a notification if there are any appointments within 15 minutes of login.
     *
     * @param actionEvent Exit button action
     */
    public void onActionSubmit(ActionEvent actionEvent) {

        ResourceBundle rb = ResourceBundle.getBundle("language/Nat", Locale.getDefault());

        try {
            String username = usernameField.getText();
            String password = passwordField.getText();

            FileWriter logFile = new FileWriter("login_activity.txt", true);
            PrintWriter outputLog = new PrintWriter(logFile);

            if (username.isEmpty() || password.isEmpty()) {
                throw new IllegalArgumentException();
            } else if (verifyUser(username, password)) {
                outputLog.println(Timestamp.valueOf(LocalDateTime.now()) + ": " + username + " SUCCESSFULLY logged in.");
                outputLog.close();

                // Check if there are any appointments. If the response is not null, then display a pop up with the name of the appointment.
                CurrUser currUser = CurrUser.getCurrUser();
                String appt = getAppointmentsWithinFifteenMin(currUser.getUserId());
                if (!(appt == null)) {
                    Alert alert = new Alert(Alert.AlertType.NONE, appt, ButtonType.OK);
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.NONE, rb.getString("Successful"), ButtonType.OK);
                    alert.showAndWait();
                }

                Main.switchStage(actionEvent, "/roberson/qam2/appointment-screen.fxml", 50, 50);
            } else {
                outputLog.println(Timestamp.valueOf(LocalDateTime.now()) + ": " + username + " FAILED to log in.");
                outputLog.close();
                throw new Exception();
            }
        } catch (IllegalArgumentException e) {
            Alert alert = new Alert(Alert.AlertType.NONE, rb.getString("BlankField"), ButtonType.OK);
            alert.showAndWait();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.NONE, rb.getString("Incorrect"), ButtonType.OK);
            alert.showAndWait();
        }
    }
}