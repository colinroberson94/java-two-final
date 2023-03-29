package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.Countries;
import model.Customers;
import model.FirstLevelDivision;
import roberson.qam2.Main;

import java.net.URL;
import java.util.ResourceBundle;

import static DAO.CountryQuery.getAllCountries;
import static DAO.CustomerQuery.updateCustomer;
import static model.Countries.getCountryFromName;
import static model.FirstLevelDivision.getFirstLevelDivisionFromName;
import static model.FirstLevelDivision.getFirstLevelDivisionOfCountry;

public class ModifyCustomerController implements Initializable {
    @FXML
    private TextField customerIdTextField;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField addressTextField;
    @FXML
    private TextField postalCodeTextField;
    @FXML
    private TextField phoneNumberTextField;
    @FXML
    private ComboBox<FirstLevelDivision> firstLevelDivisionComboBox;
    @FXML
    private ComboBox<Countries> countryComboBox;

    @FXML
    private Label customertIdLabel;
    @FXML
    private Label customerNameLabel;
    @FXML
    private Label customerAddrLabel;
    @FXML
    private Label customerFirstLevelDivisionLabel;
    @FXML
    private Label customerCountryLabel;
    @FXML
    private Label customerPostalCodeLabel;
    @FXML
    private Label customerPhoneNumberLabel;

    private static Customers customer = null;
    private Countries country = getCountryFromName(customer.getCustomerCountry());
    private FirstLevelDivision fld = getFirstLevelDivisionFromName(customer.getCustomerFirstLevelDivision());


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        customerIdTextField.setText(String.valueOf(customer.getCustomerId()));
        nameTextField.setText(customer.getCustomerName());
        addressTextField.setText(customer.getCustomerAddress());
        postalCodeTextField.setText(customer.getCustomerPostalCode());
        phoneNumberTextField.setText(customer.getCustomerPhoneNumber());

        countryComboBox.setItems(getAllCountries());
        countryComboBox.setValue(country);

        firstLevelDivisionComboBox.setItems(getFirstLevelDivisionOfCountry(country.getCountryId()));
        firstLevelDivisionComboBox.setValue(fld);
    }

    @FXML
    void onActionSave(ActionEvent actionEvent) {
        try {
            String name = nameTextField.getText();
            String address = addressTextField.getText();
            String postalCode = postalCodeTextField.getText();
            String phone = phoneNumberTextField.getText();
            FirstLevelDivision firstLevelDivsion = firstLevelDivisionComboBox.getSelectionModel().getSelectedItem();
            Integer fldID = firstLevelDivsion.getDivisionId();
            Integer customerId = Integer.parseInt(customerIdTextField.getText());
            updateCustomer(name, address, postalCode, phone, fldID, customerId);

            Main.switchStage(actionEvent, "/roberson/qam2/customer-screen.fxml");
        }  catch (RuntimeException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getLocalizedMessage(), ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    void onActionCancel(ActionEvent actionEvent) {
        customer = null;
        Main.switchStage(actionEvent, "/roberson/qam2/customer-screen.fxml");
    }

    @FXML
    void onActionCountrySelected(ActionEvent actionEvent) {
        Countries country = countryComboBox.getSelectionModel().getSelectedItem();
        firstLevelDivisionComboBox.setItems(getFirstLevelDivisionOfCountry(country.getCountryId()));
    }

    public static void passCustomer (Customers customer) {
        ModifyCustomerController.customer = customer;
    }
}
