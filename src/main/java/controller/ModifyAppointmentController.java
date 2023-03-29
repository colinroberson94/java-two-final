package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Appointments;
import model.Contacts;
import model.Customers;
import model.Users;
import roberson.qam2.Main;

import java.net.URL;
import java.util.ResourceBundle;

import static DAO.ContactsQuery.getAllContacts;
import static DAO.CustomerQuery.getAllCustomers;
import static DAO.UsersQuery.getAllUsers;
import static model.Contacts.getContactFromId;
import static model.Customers.getCustomerFromId;
import static model.Users.getUserFromId;

public class ModifyAppointmentController implements Initializable {

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
    private static Appointments appointment = null;
    private Contacts contact = getContactFromId(appointment.getContactId());
    private Users user = getUserFromId(appointment.getUserId());
    private Customers customer = getCustomerFromId(appointment.getCustomerId());

    @FXML
    void onActionCancel(ActionEvent event) {
        appointment = null;
        Main.switchStage(event, "/roberson/qam2/appointment-screen.fxml");
    }

    public static void passAppointment(Appointments appointment) {
        ModifyAppointmentController.appointment = appointment;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        titleTextField.setText(appointment.getAppointmentTitle());
        descrTextField.setText(appointment.getAppointmentDescription());
        locationTextField.setText(appointment.getAppointmentLocation());
        contactComboBox.setItems(getAllContacts());
        contactComboBox.setValue(contact);
        typeTextField.setText(appointment.getAppointmentType());
        endTimeTextField.setText(appointment.getAppointmentEnd().toString());
        startTimeTextField.setText(appointment.getAppointmentStart().toString());
        userComboBox.setItems(getAllUsers());
        userComboBox.setValue(user);
        customerComboBox.setItems(getAllCustomers());
        customerComboBox.setValue(customer);
    }
}
