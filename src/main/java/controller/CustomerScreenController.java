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

import static DAO.CustomerQuery.getAllCustomers;
import static DAO.FirstLevelDivisionQuery.getAllFirstLevelDivisions;

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

    public void OnActionExit(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void OnActionAppointments(ActionEvent actionEvent) {
        Main.switchStage(actionEvent, "/roberson/qam2/appointment-screen.fxml");
    }

    public void OnActionReports(ActionEvent actionEvent) {
    }

    public void OnActionCustomers(ActionEvent actionEvent) {
        Main.switchStage(actionEvent, "/roberson/qam2/customer-screen.fxml");
    }

    public void OnActionAdd(ActionEvent actionEvent) {

    }

    public void OnActionUpdate(ActionEvent actionEvent) {

    }

    public void OnActionDelete(ActionEvent actionEvent) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Customers> allCustomers = getAllCustomers();
        ObservableList<FirstLevelDivision> allFirstLevelDivision = getAllFirstLevelDivisions();

        CustomerTable.setItems(allCustomers);

        CustomerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        CustomerNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        CustomerAddrCol.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
        CustomerFirstLevelDivisionCol.setCellValueFactory(new PropertyValueFactory<>("division"));
        //CustomerCountryCol.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        CustomerPostalCodeCol.setCellValueFactory(new PropertyValueFactory<>("customerPostalCode"));
        CustomerPhoneNumberCol.setCellValueFactory(new PropertyValueFactory<>("customerPhoneNumber"));

    }
}