package controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Appointments;
import roberson.qam2.Main;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import static DAO.AppointmentQuery.getAllAppointments;
import static roberson.qam2.Main.switchStage;

public class AppointmentScreenController implements Initializable {

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
    }

    @FXML
    void OnActionCustomers(ActionEvent actionEvent) {
        switchStage(actionEvent, "/roberson/qam2/customer-screen.fxml");
    }

    @FXML
    void onActionAdd(ActionEvent actionEvent) {
        switchStage(actionEvent, "/roberson/qam2/add-appointment-screen.fxml");
    }

    @FXML
    void onActionUpdate(ActionEvent actionEvent) {
        /*
        try {
            if (AppointmentTable.getSelectionModel().isEmpty()) {
                throw new RuntimeException();
            }

            Appointments tempAppt = AppointmentTable.getSelectionModel().getSelectedItem();
            //int index = Inventory.getAllParts().indexOf(tempAppt);

            ModifyAppointmentController.passAppointment(tempAppt, index);

            switchStage(actionEvent, "/roberson/qam2/customer-screen.fxml");

        } catch (RuntimeException e) {
            Alert alert = new Alert(Alert.AlertType.NONE, "No appointment selected. Please select an appointment to modify", ButtonType.OK);
            alert.showAndWait();
        }
        */
    }

    @FXML
    void onActionDelete(ActionEvent actionEvent) {

    }

    @FXML
    private TableColumn<Appointments, Integer> AppointmentIdCol;

    @FXML
    private TableColumn<Appointments, String> AppointmentTitleCol;

    @FXML
    private TableColumn<Appointments, String> AppointmentDescrCol;

    @FXML
    private TableColumn<Appointments, String> AppointmentLocationCol;

    @FXML
    private TableColumn<Appointments, String> AppointmentContactCol;

    @FXML
    private TableColumn<Appointments, String> AppointmentTypeCol;

    @FXML
    private TableColumn<Appointments, LocalDateTime> AppointmentStartCol;

    @FXML
    private TableColumn<Appointments, LocalDateTime> AppointmentEndCol;

    @FXML
    private TableColumn<Appointments, Integer> AppointmentCustomerIdCol;

    @FXML
    private TableColumn<Appointments, Integer> AppointmentUserIdCol;

    @FXML
    private TableView<Appointments> AppointmentTable;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Appointments> allAppointments = getAllAppointments();

        AppointmentTable.setItems(allAppointments);

        AppointmentIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        AppointmentTitleCol.setCellValueFactory(new PropertyValueFactory<>("appointmentTitle"));
        AppointmentDescrCol.setCellValueFactory(new PropertyValueFactory<>("appointmentDescription"));
        AppointmentLocationCol.setCellValueFactory(new PropertyValueFactory<>("appointmentLocation"));
        AppointmentContactCol.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        AppointmentTypeCol.setCellValueFactory(new PropertyValueFactory<>("appointmentType"));
        AppointmentStartCol.setCellValueFactory(new PropertyValueFactory<>("appointmentStart"));
        AppointmentEndCol.setCellValueFactory(new PropertyValueFactory<>("appointmentEnd"));
        AppointmentCustomerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        AppointmentUserIdCol.setCellValueFactory(new PropertyValueFactory<>("userId"));
    }
}