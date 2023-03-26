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

import static DAO.AppointmentQuery.addAppointment;

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
            String title = titleTextField.getText();
            String description = descrTextField.getText();
            String location = locationTextField.getText();
            String type = typeTextField.getText();
            LocalDateTime start = startTimeTextField.getText().toLocalDateTime();
            LocalDateTime end = endTimeTextField.getText().toLocalDateTime();
            int userId = Integer.parseInt(userIdTextField.getText());
            int customerId = Integer.parseInt(customerIdTextField.getText());
            int contactId = Integer.parseInt(contactTextField.getText());
            addAppointment(title, description, location, type, start, end, userId, customerId, contactId);

           Main.switchStage(actionEvent, "/roberson/qam2/customer-screen.fxml");

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
