package controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Appointments;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import static DAO.AppointmentQuery.*;
import static controller.ModifyAppointmentController.passAppointment;
import static roberson.qam2.Main.switchStage;

public class AppointmentScreenController implements Initializable {
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
    void onActionAdd(ActionEvent actionEvent) {
        switchStage(actionEvent, "/roberson/qam2/add-appointment-screen.fxml");
    }

    @FXML
    void onActionUpdate(ActionEvent actionEvent) {
        try {
            if (AppointmentTable.getSelectionModel().isEmpty()) {
                throw new RuntimeException();
            }

            Appointments tempAppointment = AppointmentTable.getSelectionModel().getSelectedItem();

            passAppointment(tempAppointment);

            switchStage(actionEvent, "/roberson/qam2/modify-appointment-screen.fxml");
        } catch (RuntimeException e) {
            Alert alert = new Alert(Alert.AlertType.NONE, "No customer selected. Please select a customer to modify.", ButtonType.OK);
            alert.showAndWait();
        }
    }
    @FXML
    void onActionDelete(ActionEvent actionEvent) {
        try {
            if (AppointmentTable.getSelectionModel().isEmpty()) {
                throw new RuntimeException();
            }
            Appointments appointment = AppointmentTable.getSelectionModel().getSelectedItem();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete " + appointment.getAppointmentTitle() + " ?", ButtonType.YES, ButtonType.NO);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                deleteAppointment(appointment.getAppointmentId());
                AppointmentTable.setItems(getAllAppointments());

                Alert confirmation = new Alert(Alert.AlertType.NONE, "The " + appointment.getAppointmentType() + " appointment, "
                        + appointment.getAppointmentId() +", has been deleted.", ButtonType.OK);
                confirmation.showAndWait();
            }
        } catch (RuntimeException e) {
            Alert alert = new Alert(Alert.AlertType.NONE, "No appointment selected. Please select an appointment and then delete.", ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    void OnActionViewWeekly(ActionEvent actionEvent) {
        AppointmentTable.setItems(getWeeklyAppointments());
    }

    @FXML
    void OnActionViewMonthly(ActionEvent actionEvent) {
        AppointmentTable.setItems(getMonthlyAppointments());
    }

    @FXML
    void OnActionViewAll(ActionEvent actionEvent) {
        AppointmentTable.setItems(getAllAppointments());
    }
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