package controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customers;
import model.FirstLevelDivision;

import java.net.URL;
import java.util.ResourceBundle;

import static DAO.AppointmentQuery.deleteCustomerAppointments;
import static DAO.CustomerQuery.deleteCustomer;
import static DAO.CustomerQuery.getAllCustomers;
import static DAO.FirstLevelDivisionQuery.getAllFirstLevelDivisions;
import static controller.ModifyCustomerController.passCustomer;
import static roberson.qam2.Main.switchStage;

/**
 * Controller class that provides logic for the main Customers Form of the application.
 *
 * @author Colin Roberson
 */

public class CustomerScreenController implements Initializable {

    /**
     * Element of the Customer GUI
     */
    @FXML
    private  TableColumn<Customers, Integer> CustomerIdCol;

    /**
     * Element of the Customer GUI
     */
    @FXML
    private  TableColumn<Customers, String> CustomerNameCol;

    /**
     * Element of the Customer GUI
     */
    @FXML
    private  TableColumn<Customers, String> CustomerAddrCol;

    /**
     * Element of the Customer GUI
     */
    @FXML
    private  TableColumn<Customers, String> CustomerFirstLevelDivisionCol;

    /**
     * Element of the Customer GUI
     */
    @FXML
    private  TableColumn<Customers, String> CustomerCountryCol;

    /**
     * Element of the Customer GUI
     */
    @FXML
    private  TableColumn<Customers, String> CustomerPostalCodeCol;

    /**
     * Element of the Customer GUI
     */
    @FXML
    private  TableColumn<Customers, String> CustomerPhoneNumberCol;

    /**
     * Element of the Customer GUI
     */
    @FXML
    private  TableView<Customers> CustomerTable;

    /**
     * When the Exit button is clicked, exit the application.
     *
     * @param actionEvent Exit button action
     */
    @FXML
    void OnActionExit(ActionEvent actionEvent) {
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
     * When the Add Customer button is selected, switch to the Add Customer scene.
     *
     * @param actionEvent Add Customer button action
     */
    @FXML
    void OnActionAdd(ActionEvent actionEvent) {
        switchStage(actionEvent, "/roberson/qam2/add-customer-screen.fxml");
    }

    /**
     * Take the selected customer and pass it to the modify customer form.
     * If no customer is selected, throw an exception.
     *
     * @param actionEvent Modify customer button action
     */
    @FXML
    void OnActionUpdate(ActionEvent actionEvent) {
        try {
            if (CustomerTable.getSelectionModel().isEmpty()) {
                throw new RuntimeException();
            }

            Customers tempCustomer = CustomerTable.getSelectionModel().getSelectedItem();

            passCustomer(tempCustomer);

            switchStage(actionEvent, "/roberson/qam2/modify-customer-screen.fxml");
        } catch (RuntimeException e) {
            Alert alert = new Alert(Alert.AlertType.NONE, "No customer selected. Please select a customer to modify.", ButtonType.OK);
            alert.showAndWait();
        }
    }

    /**
     * Delete the selected customer. If no customer is selected, throw an exception.
     * Also deletes all of this customer's appointments.
     *
     * @param actionEvent Delete appointment button action
     */
    @FXML
    void OnActionDelete(ActionEvent actionEvent) {
        try {
            if (CustomerTable.getSelectionModel().isEmpty()) {
                throw new RuntimeException();
            }
            Customers customer = CustomerTable.getSelectionModel().getSelectedItem();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete " + customer.getCustomerName() + " ?", ButtonType.YES, ButtonType.NO);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                deleteCustomerAppointments(customer.getCustomerId());
                deleteCustomer(customer.getCustomerId());
                CustomerTable.setItems(getAllCustomers());
            }
        } catch (RuntimeException e) {
            Alert alert = new Alert(Alert.AlertType.NONE, "No customer selected. Please select a customer and then delete.", ButtonType.OK);
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
        ObservableList<Customers> allCustomers = getAllCustomers();
        ObservableList<FirstLevelDivision> allFirstLevelDivision = getAllFirstLevelDivisions();

        CustomerTable.setItems(allCustomers);

        CustomerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        CustomerNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        CustomerAddrCol.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
        CustomerFirstLevelDivisionCol.setCellValueFactory(new PropertyValueFactory<>("customerFirstLevelDivision"));
        CustomerCountryCol.setCellValueFactory(new PropertyValueFactory<>("customerCountry"));
        CustomerPostalCodeCol.setCellValueFactory(new PropertyValueFactory<>("customerPostalCode"));
        CustomerPhoneNumberCol.setCellValueFactory(new PropertyValueFactory<>("customerPhoneNumber"));

    }
}