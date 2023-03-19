package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Appointments;
import roberson.qam2.Main;

import java.net.URL;
import java.util.ResourceBundle;

public class ModifyAppointmentController implements Initializable {

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
    private static Appointments appointment = null;
    private static int index;

    @FXML
    void onActionCancel(ActionEvent event) {
        appointment = null;
        Main.switchStage(event, "/roberson/qam2/appointment-screen.fxml");
    }

    public static void passAppointment(Appointments appointment, int index) {
        ModifyAppointmentController.appointment = appointment;
        ModifyAppointmentController.index = index;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        titleTextField.setText(appointment.getAppointmentTitle());
        descrTextField.setText(appointment.getAppointmentDescription());
        locationTextField.setText(appointment.getAppointmentLocation());
        contactTextField.setText(String.valueOf(appointment.getContactId()));
        typeTextField.setText(appointment.getAppointmentType());
        endTimeTextField.setText(appointment.getAppointmentEnd().toString());
        startTimeTextField.setText(appointment.getAppointmentStart().toString());
        userIdTextField.setText(String.valueOf(appointment.getUserId()));
        customerIdTextField.setText(String.valueOf(appointment.getCustomerId()));
    }
}
