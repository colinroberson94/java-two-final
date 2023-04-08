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

    /**
     * Element of the Appointments GUI
     */
    @FXML
    private TableColumn<Appointments, Integer> AppointmentIdCol;

    /**
     * Element of the Appointments GUI
     */
    @FXML
    private TableColumn<Appointments, String> AppointmentTitleCol;

    /**
     * Element of the Appointments GUI
     */
    @FXML
    private TableColumn<Appointments, String> AppointmentDescrCol;

    /**
     * Element of the Appointments GUI
     */
    @FXML
    private TableColumn<Appointments, String> AppointmentLocationCol;

    /**
     * Element of the Appointments GUI
     */
    @FXML
    private TableColumn<Appointments, String> AppointmentContactCol;

    /**
     * Element of the Appointments GUI
     */
    @FXML
    private TableColumn<Appointments, String> AppointmentTypeCol;

    /**
     * Element of the Appointments GUI
     */
    @FXML
    private TableColumn<Appointments, LocalDateTime> AppointmentStartCol;

    /**
     * Element of the Appointments GUI
     */
    @FXML
    private TableColumn<Appointments, LocalDateTime> AppointmentEndCol;

    /**
     * Element of the Appointments GUI
     */
    @FXML
    private TableColumn<Appointments, Integer> AppointmentCustomerIdCol;

    /**
     * Element of the Appointments GUI
     */
    @FXML
    private TableColumn<Appointments, Integer> AppointmentUserIdCol;

    /**
     * Element of the Appointments GUI
     */
    @FXML
    private TableView<Appointments> AppointmentTable;

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
     * When the Add appointment button is selected, switch to the Add Appointment scene.
     *
     * @param actionEvent Add appointment button action
     */
    @FXML
    void onActionAdd(ActionEvent actionEvent) {
        switchStage(actionEvent, "/roberson/qam2/add-appointment-screen.fxml");
    }

    /**
     * Take the selected appointment and  pass it to the modify appointment form.
     * If no appointment is selected, throw an exception.
     *
     * @param actionEvent Modify appointment button action
     */
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

    /**
     * Delete the selected appointment. If no appointment is selected, throw an exception.
     *
     * @param actionEvent Delete appointment button action
     */
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

                Alert confirmation = new Alert(Alert.AlertType.NONE, "The " + appointment.getAppointmentType() + " appointment, "
                        + appointment.getAppointmentId() +", has been deleted.", ButtonType.OK);
                confirmation.showAndWait();

                AppointmentTable.setItems(getAllAppointments());
            }
        } catch (RuntimeException e) {
            Alert alert = new Alert(Alert.AlertType.NONE, "No appointment selected. Please select an appointment and then delete.", ButtonType.OK);
            alert.showAndWait();
        }
    }

    /**
     * Switch appointment table to show weekly appointments.
     *
     * @param actionEvent Weekly toggle button action
     */
    @FXML
    void OnActionViewWeekly(ActionEvent actionEvent) {
        AppointmentTable.setItems(getWeeklyAppointments());
    }

    /**
     * Switch appointment table to show monthly appointments.
     *
     * @param actionEvent Monthly toggle button action
     */
    @FXML
    void OnActionViewMonthly(ActionEvent actionEvent) {
        AppointmentTable.setItems(getMonthlyAppointments());
    }

    /**
     * Switch appointment table to show all appointments.
     *
     * @param actionEvent All toggle button action
     */
    @FXML
    void OnActionViewAll(ActionEvent actionEvent) {
        AppointmentTable.setItems(getAllAppointments());
    }

    /**
     * Initializes controller.
     *
     * @param url The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
     */
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