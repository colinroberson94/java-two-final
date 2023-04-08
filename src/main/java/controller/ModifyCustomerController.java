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

    /**
     * Element of the Modify Customer GUI
     */
    @FXML
    private TextField customerIdTextField;

    /**
     * Element of the Modify Customer GUI
     */
    @FXML
    private TextField nameTextField;

    /**
     * Element of the Modify Customer GUI
     */
    @FXML
    private TextField addressTextField;

    /**
     * Element of the Modify Customer GUI
     */
    @FXML
    private TextField postalCodeTextField;

    /**
     * Element of the Modify Customer GUI
     */
    @FXML
    private TextField phoneNumberTextField;

    /**
     * Element of the Modify Customer GUI
     */
    @FXML
    private ComboBox<FirstLevelDivision> firstLevelDivisionComboBox;

    /**
     * Element of the Modify Customer GUI
     */
    @FXML
    private ComboBox<Countries> countryComboBox;

    /**
     * Element of the Modify Customer GUI
     */
    @FXML
    private Label customertIdLabel;

    /**
     * Element of the Modify Customer GUI
     */
    @FXML
    private Label customerNameLabel;

    /**
     * Element of the Modify Customer GUI
     */
    @FXML
    private Label customerAddrLabel;

    /**
     * Element of the Modify Customer GUI
     */
    @FXML
    private Label customerFirstLevelDivisionLabel;

    /**
     * Element of the Modify Customer GUI
     */
    @FXML
    private Label customerCountryLabel;

    /**
     * Element of the Modify Customer GUI
     */
    @FXML
    private Label customerPostalCodeLabel;

    /**
     * Element of the Modify Customer GUI
     */
    @FXML
    private Label customerPhoneNumberLabel;

    /**
     * Customers object reference used for passing an object from the Customer Controller to the Modify Customer Controller.
     */
    private static Customers customer = null;

    /**
     * Countries object reference used for populating a pre-selected object in the countryComboBox.
     */
    private Countries country = getCountryFromName(customer.getCustomerCountry());

    /**
     * FirstLevelDivision object reference used for populating a pre-selected object in the firstLevelDivisionComboBox.
     */
    private FirstLevelDivision fld = getFirstLevelDivisionFromName(customer.getCustomerFirstLevelDivision());

    /**
     * Initializes controller.
     *
     * @param url The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
     */
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

    /**
     * Modifies an existing customer with the values that have been input into the form.
     * Will throw an error for invalid inputs and display a corresponding dialog window.
     *
     * @param actionEvent Save button action
     */
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

    /**
     * Cancel the current operation and return to the customer controller.
     *
     * @param actionEvent Cancel button action
     */
    @FXML
    void onActionCancel(ActionEvent actionEvent) {
        customer = null;
        Main.switchStage(actionEvent, "/roberson/qam2/customer-screen.fxml");
    }

    /**
     * Populate the First Level Division Combobox with data based on the chosen country.
     *
     * @param actionEvent Country selected from ComboBox action
     */
    @FXML
    void onActionCountrySelected(ActionEvent actionEvent) {
        Countries country = countryComboBox.getSelectionModel().getSelectedItem();
        firstLevelDivisionComboBox.setItems(getFirstLevelDivisionOfCountry(country.getCountryId()));
    }

    /**
     * Static method that allows the customer controller to pass a selected customer object to the modify customer controller.
     *
     * @param customer The InHouse Part that is to be modified
     */
    public static void passCustomer (Customers customer) {
        ModifyCustomerController.customer = customer;
    }
}
