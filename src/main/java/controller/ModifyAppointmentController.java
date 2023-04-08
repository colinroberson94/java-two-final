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

import static DAO.AppointmentQuery.updateAppointment;
import static DAO.ContactsQuery.getAllContacts;
import static DAO.CustomerQuery.getAllCustomers;
import static DAO.UsersQuery.getAllUsers;
import static model.Contacts.getContactFromId;
import static model.Customers.getCustomerFromId;
import static model.Users.getUserFromId;

public class ModifyAppointmentController implements Initializable {

    /**
     * Element of the Modify Appointment GUI
     */
    @FXML
    private Label apptDateLabel;

    /**
     * Element of the Modify Appointment GUI
     */
    @FXML
    private Label apptIdLabel;

    /**
     * Element of the Modify Appointment GUI
     */
    @FXML
    private Label apptTitleLabel;

    /**
     * Element of the Modify Appointment GUI
     */
    @FXML
    private Label apptDescrLabel;

    /**
     * Element of the Modify Appointment GUI
     */
    @FXML
    private Label apptLocationLabel;

    /**
     * Element of the Modify Appointment GUI
     */
    @FXML
    private Label apptContactLabel;

    /**
     * Element of the Modify Appointment GUI
     */
    @FXML
    private Label apptTypeLabel;

    /**
     * Element of the Modify Appointment GUI
     */
    @FXML
    private Label apptEndTimeLabel;

    /**
     * Element of the Modify Appointment GUI
     */
    @FXML
    private Label apptStartTimeLabel;

    /**
     * Element of the Modify Appointment GUI
     */
    @FXML
    private Label apptCustomerIdLabel;

    /**
     * Element of the Modify Appointment GUI
     */
    @FXML
    private Label apptUserIdLabel;

    /**
     * Element of the Modify Appointment GUI
     */
    @FXML
    private TextField titleTextField;

    /**
     * Element of the Modify Appointment GUI
     */
    @FXML
    private TextField descrTextField;

    /**
     * Element of the Modify Appointment GUI
     */
    @FXML
    private TextField locationTextField;

    /**
     * Element of the Modify Appointment GUI
     */
    @FXML
    private TextField typeTextField;

    /**
     * Element of the Modify Appointment GUI
     */
    @FXML
    private TextField endTimeTextField;

    /**
     * Element of the Modify Appointment GUI
     */
    @FXML
    private TextField startTimeTextField;

    /**
     * Element of the Modify Appointment GUI
     */
    @FXML
    private ComboBox<Contacts> contactComboBox;

    /**
     * Element of the Modify Appointment GUI
     */
    @FXML
    private ComboBox<Customers> customerComboBox;

    /**
     * Element of the Modify Appointment GUI
     */
    @FXML
    private ComboBox<Users> userComboBox;

    /**
     * Element of the Modify Appointment GUI
     */
    @FXML
    private TextField apptIdTextField;

    /**
     * Element of the Modify Appointment GUI
     */
    @FXML
    private DatePicker datePicker;

    /**
     * Appointments object reference used for passing an object from the Appointment Controller to the Modify Appointment Controller.
     */
    private static Appointments appointment = null;

    /**
     * Contacts object reference used for populating a pre-selected object in the contactComboBox.
     */
    private Contacts contact = getContactFromId(appointment.getContactId());

    /**
     * Users object reference used for populating a pre-selected object in the userComboBox.
     */
    private Users user = getUserFromId(appointment.getUserId());

    /**
     * Customers object reference used for populating a pre-selected object in the customerComboBox.
     */
    private Customers customer = getCustomerFromId(appointment.getCustomerId());

    /**
     * Cancel the current operation and return to the appointment controller.
     *
     * @param actionEvent Cancel button action
     */
    @FXML
    void onActionCancel(ActionEvent actionEvent) {
        appointment = null;
        Main.switchStage(actionEvent, "/roberson/qam2/appointment-screen.fxml");
    }

    /**
     * Modifies an existing appointment with the values that have been input into the form.
     * Will throw an error for invalid inputs and display a corresponding dialog window.
     *
     * @param actionEvent Save button action
     */
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

    /**
     * Static method that allows the appointment controller to pass a selected appointment object to the modify appointment controller.
     *
     * @param appointment The InHouse Part that is to be modified
     */
    public static void passAppointment(Appointments appointment) {
        ModifyAppointmentController.appointment = appointment;
    }

    /**
     * Initializes controller and populates the fields with the values of the selected Appointment.
     *
     * @param url The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
     */
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
