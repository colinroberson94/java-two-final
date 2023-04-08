package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Contacts;

import java.net.URL;
import java.util.ResourceBundle;

import static DAO.ContactsQuery.getAllContacts;
import static DAO.ReportsQuery.getTotalAppointmentsByCustomer;
import static DAO.ReportsQuery.getTotalAppointmentsByTypeAndMonth;
import static model.Appointments.getAppointmentsListByContact;
import static roberson.qam2.Main.switchStage;

/**
 * Controller class that provides logic for the reports form of the application.
 *
 * @author Colin Roberson
 */

public class ReportsController implements Initializable {

    /**
     * Element of the Reports GUI
     */
    @FXML
    private TableView TypeAndMonthTotalTable;

    /**
     * Element of the Reports GUI
     */
    @FXML
    private TableColumn ReportTypeCol;

    /**
     * Element of the Reports GUI
     */
    @FXML
    private TableColumn ReportMonthCol;

    /**
     * Element of the Reports GUI
     */
    @FXML
    private TableColumn ReportTotalCol;

    /**
     * Element of the Reports GUI
     */
    @FXML
    private Label viewApptsByContactLabel;

    /**
     * Element of the Reports GUI
     */
    @FXML
    private Label totalApptsByTypeMonthLabel;

    /**
     * Element of the Reports GUI
     */
    @FXML
    private TableView CustomerTotalTable;

    /**
     * Element of the Reports GUI
     */
    @FXML
    private TableColumn ReportCustomerNameCol;

    /**
     * Element of the Reports GUI
     */
    @FXML
    private TableColumn ReportCustomerTotalCol;

    /**
     * Element of the Reports GUI
     */
    @FXML
    private Label totalApptsByCustomerLabel;

    /**
     * Element of the Reports GUI
     */
    @FXML
    private ComboBox<Contacts> contactComboBox;

    /**
     * Element of the Reports GUI
     */
    @FXML
    private TableColumn AppointmentCustomerIdCol;

    /**
     * Element of the Reports GUI
     */
    @FXML
    private TableColumn AppointmentEndCol;

    /**
     * Element of the Reports GUI
     */
    @FXML
    private TableColumn AppointmentStartCol;

    /**
     * Element of the Reports GUI
     */
    @FXML
    private TableColumn AppointmentDescrCol;

    /**
     * Element of the Reports GUI
     */
    @FXML
    private TableColumn AppointmentTypeCol;

    /**
     * Element of the Reports GUI
     */
    @FXML
    private TableColumn AppointmentTitleCol;

    /**
     * Element of the Reports GUI
     */
    @FXML
    private TableColumn AppointmentIdCol;

    /**
     * Element of the Reports GUI
     */
    @FXML
    private TableView AppointmentTable;

    /**
     * Initializes controller.
     *
     * @param url The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        contactComboBox.setItems(getAllContacts());

        CustomerTotalTable.setItems(getTotalAppointmentsByCustomer());
        ReportCustomerNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        ReportCustomerTotalCol.setCellValueFactory(new PropertyValueFactory<>("total"));

        TypeAndMonthTotalTable.setItems(getTotalAppointmentsByTypeAndMonth());
        ReportTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        ReportMonthCol.setCellValueFactory(new PropertyValueFactory<>("month"));
        ReportTotalCol.setCellValueFactory(new PropertyValueFactory<>("total"));

    }

    /**
     * When the Exit button is clicked, exit the application.
     *
     * @param actionEvent Exit button action
     */
    @FXML
    void onActionExit(ActionEvent actionEvent) {
        System.exit(0);
    }

    /**
     * When the Appointments button is selected, switch to the Appointments screen.
     *
     * @param actionEvent Add part button action
     */
    @FXML
    void OnActionAppointments(ActionEvent actionEvent) {
        switchStage(actionEvent, "/roberson/qam2/appointment-screen.fxml");
    }

    /**
     * When the Reports button is selected, switch to the Reports screen.
     *
     * @param actionEvent Add part button action
     */
    @FXML
    void OnActionReports(ActionEvent actionEvent) {
        switchStage(actionEvent, "/roberson/qam2/reports-screen.fxml");
    }

    /**
     * When the Customers button is selected, switch to the Customers screen.
     *
     * @param actionEvent Add part button action
     */
    @FXML
    void OnActionCustomers(ActionEvent actionEvent) {
        switchStage(actionEvent, "/roberson/qam2/customer-screen.fxml");
    }

    /**
     * When a Contact is selected, populate the appointments table with their appointments.
     *
     * @param actionEvent Add part button action
     */
    @FXML
    void OnActionContactSelected(ActionEvent actionEvent) {
        Contacts tempContact = contactComboBox.getSelectionModel().getSelectedItem();
        AppointmentTable.setItems(getAppointmentsListByContact(tempContact.getContactId()));

        AppointmentIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        AppointmentTitleCol.setCellValueFactory(new PropertyValueFactory<>("appointmentTitle"));
        AppointmentTypeCol.setCellValueFactory(new PropertyValueFactory<>("appointmentType"));
        AppointmentDescrCol.setCellValueFactory(new PropertyValueFactory<>("appointmentDescription"));
        AppointmentStartCol.setCellValueFactory(new PropertyValueFactory<>("appointmentStart"));
        AppointmentEndCol.setCellValueFactory(new PropertyValueFactory<>("appointmentEnd"));
        AppointmentCustomerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));

    }
}
