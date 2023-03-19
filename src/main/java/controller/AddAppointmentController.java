package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Appointments;
import roberson.qam2.Main;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class AddAppointmentController implements Initializable {

    @FXML
    private Label apptIdLabel;
    @FXML
    private Label apptTitleLabel;
    @FXML
    private Label apptDescrLabel;
    @FXML
    private Label apptLocationLabel;
    @FXML
    private Label apptContactLabel;
    @FXML
    private Label apptTypeLabel;
    @FXML
    private Label apptEndTimeLabel;
    @FXML
    private Label apptStartTimeLabel;
    @FXML
    private Label apptCustomerIdLabel;
    @FXML
    private Label apptUserIdLabel;
    @FXML
    private TextField titleTextField;
    @FXML
    private TextField descrTextField;
    @FXML
    private TextField locationTextField;
    @FXML
    private TextField contactTextField;
    @FXML
    private TextField typeTextField;
    @FXML
    private TextField endTimeTextField;
    @FXML
    private TextField startTimeTextField;
    @FXML
    private TextField userIdTextField;
    @FXML
    private TextField customerIdTextField;

    @FXML
    void onActionCancel(ActionEvent event) {
        Main.switchStage(event, "/roberson/qam2/appointment-screen.fxml");
    }
    @FXML
    void onActionSave(ActionEvent actionEvent) {
        try {
            /*int appointmentId = rs.getInt("Appointment_ID");
            //String appointmentTitle = titleTextField.getText();
            String appointmentDescription = descrTextField.getText();
            String appointmentLocation = locationTextField.getText();
            String appointmentType = typeTextField.getText();
            LocalDateTime appointmentStart = startTimeTextField.getText().toLocalDateTime();
            LocalDateTime appointmentEnd = endTimeTextField.getText().toLocalDateTime();
            int userId = Integer.parseInt(userIdTextField.getText());
            int customerId = Integer.parseInt(customerIdTextField.getText());
            int contactId = Integer.parseInt(contactTextField.getText());

            Appointments appt = new Appointments(appointmentId, appointmentTitle, appointmentDescription, appointmentLocation,
                    appointmentType, appointmentStart, appointmentEnd, userId, customerId, contactId);*/
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please input a valid number", ButtonType.OK);
            alert.showAndWait();
        } catch (RuntimeException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getLocalizedMessage(), ButtonType.OK);
            alert.showAndWait();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
    }
    
}
