package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.Appointments;
import roberson.qam2.Main;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

import static DAO.AppointmentQuery.addAppointment;

public class AddAppointmentController implements Initializable {

    @FXML
    private Label apptStartDateLabel;
    @FXML
    private Label apptEndDateLabel;
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
    private TextField apptIdTextField;
    @FXML
    private DatePicker startDatePicker;
    @FXML
    private DatePicker endDatePicker;

    @FXML
    void onActionCancel(ActionEvent event) {
        Main.switchStage(event, "/roberson/qam2/appointment-screen.fxml");
    }
    @FXML
    void onActionSave(ActionEvent actionEvent) {
        try {
            LocalDateTime testing = startDatePicker.getValue().atTime(LocalTime.parse(startTimeTextField.getText()));

//            String title = titleTextField.getText();
//            String description = descrTextField.getText();
//            String location = locationTextField.getText();
//            String type = typeTextField.getText();
//            LocalDateTime start = startTimeTextField.getText().toLocalDateTime();
//            LocalDateTime end = endTimeTextField.getText().toLocalDateTime();
//            int userId = Integer.parseInt(userIdTextField.getText());
//            int customerId = Integer.parseInt(customerIdTextField.getText());
//            int contactId = Integer.parseInt(contactTextField.getText());
//            addAppointment(title, description, location, type, start, end, userId, customerId, contactId);
//
//           Main.switchStage(actionEvent, "/roberson/qam2/customer-screen.fxml");
            System.out.println(testing);

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
