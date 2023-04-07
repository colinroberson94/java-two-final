package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.Appointments;
import model.Contacts;
import model.Customers;
import model.Users;
import roberson.qam2.Main;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ResourceBundle;

import static DAO.AppointmentQuery.addAppointment;
import static DAO.ContactsQuery.getAllContacts;
import static DAO.CustomerQuery.getAllCustomers;
import static DAO.UsersQuery.getAllUsers;

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
    private TextField typeTextField;
    @FXML
    private TextField endTimeTextField;
    @FXML
    private TextField startTimeTextField;
    @FXML
    private ComboBox<Contacts> contactComboBox;
    @FXML
    private ComboBox<Customers> customerComboBox;
    @FXML
    private ComboBox<Users> userComboBox;
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

            String title = titleTextField.getText();
            String description = descrTextField.getText();
            String location = locationTextField.getText();
            String type = typeTextField.getText();
            LocalDateTime start = startDatePicker.getValue().atTime(LocalTime.parse(startTimeTextField.getText()));
            LocalDateTime end = endDatePicker.getValue().atTime(LocalTime.parse(endTimeTextField.getText()));

            Users user = userComboBox.getSelectionModel().getSelectedItem();
            int userId = user.getUserId();

            Customers customer = customerComboBox.getSelectionModel().getSelectedItem();
            int customerId = customer.getCustomerId();

            Contacts contact = contactComboBox.getSelectionModel().getSelectedItem();
            int contactId = contact.getContactId();

            if (Appointments.withinBusinessHours(start, end)) {
                addAppointment(title, description, location, type, start, end, userId, customerId, contactId);
            } else {
                throw new IllegalArgumentException();
            }

           Main.switchStage(actionEvent, "/roberson/qam2/appointment-screen.fxml");
            System.out.println(testing);

        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please input a valid number", ButtonType.OK);
            alert.showAndWait();
        } catch (DateTimeParseException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please input a valid time (HH:MM:SS)", ButtonType.OK);
            alert.showAndWait();
        } catch (IllegalArgumentException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "This appointment falls outside of business hours. Please input a time between 8:00AM-10:00PM EST", ButtonType.OK);
            alert.showAndWait();
        } catch (RuntimeException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getLocalizedMessage(), ButtonType.OK);
            alert.showAndWait();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        contactComboBox.setItems(getAllContacts());
        customerComboBox.setItems(getAllCustomers());
        userComboBox.setItems(getAllUsers());
    }
    
}
