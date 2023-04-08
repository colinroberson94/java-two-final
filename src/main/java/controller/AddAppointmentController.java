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

/**
 * Controller class that provides logic for the Add Appointment Form of the application.
 *
 * @author Colin Roberson
 */

public class AddAppointmentController implements Initializable {

    /**
     * Element of the Add Appointment GUI
     */
    @FXML
    private Label apptDateLabel;

    /**
     * Element of the Add Appointment GUI
     */
    @FXML
    private Label apptIdLabel;

    /**
     * Element of the Add Appointment GUI
     */
    @FXML
    private Label apptTitleLabel;

    /**
     * Element of the Add Appointment GUI
     */
    @FXML
    private Label apptDescrLabel;

    /**
     * Element of the Add Appointment GUI
     */
    @FXML
    private Label apptLocationLabel;

    /**
     * Element of the Add Appointment GUI
     */
    @FXML
    private Label apptContactLabel;

    /**
     * Element of the Add Appointment GUI
     */
    @FXML
    private Label apptTypeLabel;

    /**
     * Element of the Add Appointment GUI
     */
    @FXML
    private Label apptEndTimeLabel;

    /**
     * Element of the Add Appointment GUI
     */
    @FXML
    private Label apptStartTimeLabel;

    /**
     * Element of the Add Appointment GUI
     */
    @FXML
    private Label apptCustomerIdLabel;

    /**
     * Element of the Add Appointment GUI
     */
    @FXML
    private Label apptUserIdLabel;

    /**
     * Element of the Add Appointment GUI
     */
    @FXML
    private TextField titleTextField;

    /**
     * Element of the Add Appointment GUI
     */
    @FXML
    private TextField descrTextField;

    /**
     * Element of the Add Appointment GUI
     */
    @FXML
    private TextField locationTextField;

    /**
     * Element of the Add Appointment GUI
     */
    @FXML
    private TextField typeTextField;

    /**
     * Element of the Add Appointment GUI
     */
    @FXML
    private TextField endTimeTextField;

    /**
     * Element of the Add Appointment GUI
     */
    @FXML
    private TextField startTimeTextField;

    /**
     * Element of the Add Appointment GUI
     */
    @FXML
    private ComboBox<Contacts> contactComboBox;

    /**
     * Element of the Add Appointment GUI
     */
    @FXML
    private ComboBox<Customers> customerComboBox;

    /**
     * Element of the Add Appointment GUI
     */
    @FXML
    private ComboBox<Users> userComboBox;

    /**
     * Element of the Add Appointment GUI
     */
    @FXML
    private TextField apptIdTextField;

    /**
     * Element of the Add Appointment GUI
     */
    @FXML
    private DatePicker datePicker;

    /**
     * Cancel the current operation and return to the appointment controller.
     *
     * @param actionEvent Cancel button action
     */
    @FXML
    void onActionCancel(ActionEvent actionEvent) {
        Main.switchStage(actionEvent, "/roberson/qam2/appointment-screen.fxml");
    }

    /**
     * Creates a new appointment with the values that have been input into the form.
     * Will throw an error for invalid inputs and display a corresponding dialog window.
     *
     * @param actionEvent Save button action
     */
    @FXML
    void onActionSave(ActionEvent actionEvent) {
        try {
            LocalDateTime testing = datePicker.getValue().atTime(LocalTime.parse(startTimeTextField.getText()));

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

            if (Appointments.withinBusinessHours(start, end)) {
                if (!Appointments.overlapsWithOtherAppointments(start, end)) {
                    addAppointment(title, description, location, type, start, end, userId, customerId, contactId);
                } else {
                    throw new RuntimeException("This appointment overlaps with other appointments");
                }
            } else {
                throw new RuntimeException("This appointment falls outside of business hours. Please input a time between Mon-Fri 8:00AM-10:00PM EST");
            }

           Main.switchStage(actionEvent, "/roberson/qam2/appointment-screen.fxml");
            System.out.println(testing);

        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please input a valid number", ButtonType.OK);
            alert.showAndWait();
        } catch (DateTimeParseException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please input a valid time (HH:MM:SS)", ButtonType.OK);
            alert.showAndWait();
        } catch (RuntimeException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getLocalizedMessage(), ButtonType.OK);
            alert.showAndWait();
        }
    }

    /**
     * Initializes controller.
     *
     * @param url The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        contactComboBox.setItems(getAllContacts());
        customerComboBox.setItems(getAllCustomers());
        userComboBox.setItems(getAllUsers());
    }
    
}
