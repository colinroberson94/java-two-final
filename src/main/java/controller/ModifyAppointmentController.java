package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.*;
import roberson.qam2.Main;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ResourceBundle;

import static DAO.AppointmentQuery.addAppointment;
import static DAO.AppointmentQuery.updateAppointment;
import static DAO.ContactsQuery.getAllContacts;
import static DAO.CustomerQuery.getAllCustomers;
import static DAO.UsersQuery.getAllUsers;
import static model.Contacts.getContactFromId;
import static model.Customers.getCustomerFromId;
import static model.Users.getUserFromId;

public class ModifyAppointmentController implements Initializable {

    @FXML
    private Label apptDateLabel;
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
    private DatePicker datePicker;
    private static Appointments appointment = null;
    private Contacts contact = getContactFromId(appointment.getContactId());
    private Users user = getUserFromId(appointment.getUserId());
    private Customers customer = getCustomerFromId(appointment.getCustomerId());

    @FXML
    void onActionCancel(ActionEvent event) {
        appointment = null;
        Main.switchStage(event, "/roberson/qam2/appointment-screen.fxml");
    }

    @FXML
    void onActionSave(ActionEvent actionEvent) {
        try {
            String title = titleTextField.getText();
            String description = descrTextField.getText();
            String location = locationTextField.getText();
            String type = typeTextField.getText();
            LocalDateTime start = datePicker.getValue().atTime(LocalTime.parse(startTimeTextField.getText()));
            LocalDateTime end = datePicker.getValue().atTime(LocalTime.parse(endTimeTextField.getText()));

            Users user = userComboBox.getSelectionModel().getSelectedItem();
            int userId = user.getUserId();

            Customers customer = customerComboBox.getSelectionModel().getSelectedItem();
            int customerId = customer.getCustomerId();

            Contacts contact = contactComboBox.getSelectionModel().getSelectedItem();
            int contactId = contact.getContactId();

            int apptId = appointment.getAppointmentId();

            if (Appointments.withinBusinessHours(start, end)) {
                updateAppointment(title, description, location, type, start, end, userId, customerId, contactId, apptId);
            } else {
                throw new IllegalArgumentException();
            }

            Main.switchStage(actionEvent, "/roberson/qam2/appointment-screen.fxml");

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

    public static void passAppointment(Appointments appointment) {
        ModifyAppointmentController.appointment = appointment;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        apptIdTextField.setText(String.valueOf(appointment.getAppointmentId()));
        titleTextField.setText(appointment.getAppointmentTitle());
        descrTextField.setText(appointment.getAppointmentDescription());
        locationTextField.setText(appointment.getAppointmentLocation());

        contactComboBox.setItems(getAllContacts());
        contactComboBox.setValue(contact);

        typeTextField.setText(appointment.getAppointmentType());

        LocalDate apptStartDate = appointment.getAppointmentStart().toLocalDate();
        LocalDate apptEndDate = appointment.getAppointmentEnd().toLocalDate();
        LocalTime apptStartTime = appointment.getAppointmentStart().toLocalTime();
        LocalTime apptEndTime = appointment.getAppointmentEnd().toLocalTime();

        datePicker.setValue(apptStartDate);
        datePicker.setValue(apptEndDate);
        endTimeTextField.setText(apptEndTime.toString());
        startTimeTextField.setText(apptStartTime.toString());

        userComboBox.setItems(getAllUsers());
        userComboBox.setValue(user);

        customerComboBox.setItems(getAllCustomers());
        customerComboBox.setValue(customer);
    }
}
