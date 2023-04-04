package controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customers;
import model.FirstLevelDivision;
import roberson.qam2.Main;

import java.net.URL;
import java.util.ResourceBundle;

import static DAO.AppointmentQuery.deleteCustomerAppointments;
import static DAO.CustomerQuery.deleteCustomer;
import static DAO.CustomerQuery.getAllCustomers;
import static DAO.FirstLevelDivisionQuery.getAllFirstLevelDivisions;
import static controller.ModifyCustomerController.passCustomer;
import static roberson.qam2.Main.switchStage;

public class CustomerScreenController implements Initializable {

    @FXML
    private  TableColumn<Customers, Integer> CustomerIdCol;
    @FXML
    private  TableColumn<Customers, String> CustomerNameCol;
    @FXML
    private  TableColumn<Customers, String> CustomerAddrCol;
    @FXML
    private  TableColumn<Customers, String> CustomerFirstLevelDivisionCol;
    @FXML
    private  TableColumn<Customers, String> CustomerCountryCol;
    @FXML
    private  TableColumn<Customers, String> CustomerPostalCodeCol;
    @FXML
    private  TableColumn<Customers, String> CustomerPhoneNumberCol;
    @FXML
    private  TableView<Customers> CustomerTable;

    @FXML
    void OnActionExit(ActionEvent actionEvent) {
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
    void OnActionAdd(ActionEvent actionEvent) {
        switchStage(actionEvent, "/roberson/qam2/add-customer-screen.fxml");
    }

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