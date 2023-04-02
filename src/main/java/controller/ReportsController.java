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
import model.Users;

import java.net.URL;
import java.util.ResourceBundle;

import static DAO.ContactsQuery.getAllContacts;
import static model.Appointments.getAppointmentsListByContact;
import static model.Appointments.getAppointmentsListByUser;
import static roberson.qam2.Main.switchStage;

public class ReportsController implements Initializable {

    @FXML
    private TableView TypeAndMonthTotalTable;
    @FXML
    private TableColumn ReportTypeCol;
    @FXML
    private TableColumn ReportMonthCol;
    @FXML
    private TableColumn ReportTotalCol;
    @FXML
    private Label viewApptsByContactLabel;
    @FXML
    private Label totalApptsByTypeMonthLabel;
    @FXML
    private TableView CustomerTotalTable;
    @FXML
    private TableColumn ReportCustomerNameCol;
    @FXML
    private TableColumn ReportCustomerTotalCol;
    @FXML
    private Label totalApptsByCustomerLabel;
    @FXML
    private ComboBox<Contacts> contactComboBox;
    @FXML
    private TableColumn AppointmentCustomerIdCol;
    @FXML
    private TableColumn AppointmentEndCol;
    @FXML
    private TableColumn AppointmentStartCol;
    @FXML
    private TableColumn AppointmentDescrCol;
    @FXML
    private TableColumn AppointmentTypeCol;
    @FXML
    private TableColumn AppointmentTitleCol;
    @FXML
    private TableColumn AppointmentIdCol;
    @FXML
    private TableView AppointmentTable;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        contactComboBox.setItems(getAllContacts());
    }

    @FXML
    void onActionExit(ActionEvent actionEvent) {
        System.exit(0);
    }

    @FXML
    void OnActionAppointments(ActionEvent actionEvent) {
        switchStage(actionEvent, "/roberson/qam2/appointment-screen.fxml");
    }

    @FXML
    void OnActionReports(ActionEvent actionEvent) {
        switchStage(actionEvent, "/roberson/qam2/reports-screen.fxml");
    }

    @FXML
    void OnActionCustomers(ActionEvent actionEvent) {
        switchStage(actionEvent, "/roberson/qam2/customer-screen.fxml");
    }

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
