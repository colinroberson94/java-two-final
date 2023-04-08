package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.Countries;
import model.FirstLevelDivision;
import roberson.qam2.Main;

import java.net.URL;
import java.util.ResourceBundle;

import static DAO.CountryQuery.getAllCountries;
import static DAO.CustomerQuery.addCustomer;
import static model.FirstLevelDivision.getFirstLevelDivisionOfCountry;

public class AddCustomerController implements Initializable {

    /**
     * Element of the Add Customer GUI
     */
    @FXML
    private TextField nameTextField;

    /**
     * Element of the Add Customer GUI
     */
    @FXML
    private TextField addressTextField;

    /**
     * Element of the Add Customer GUI
     */
    @FXML
    private TextField postalCodeTextField;

    /**
     * Element of the Add Customer GUI
     */
    @FXML
    private TextField phoneNumberTextField;

    /**
     * Element of the Add Customer GUI
     */
    @FXML
    private ComboBox<FirstLevelDivision> firstLevelDivisionComboBox;

    /**
     * Element of the Add Customer GUI
     */
    @FXML
    private ComboBox<Countries> countryComboBox;

    /**
     * Element of the Add Customer GUI
     */
    @FXML
    private Label customertIdLabel;

    /**
     * Element of the Add Customer GUI
     */
    @FXML
    private Label customerNameLabel;

    /**
     * Element of the Add Customer GUI
     */
    @FXML
    private Label customerAddrLabel;

    /**
     * Element of the Add Customer GUI
     */
    @FXML
    private Label customerFirstLevelDivisionLabel;

    /**
     * Element of the Add Customer GUI
     */
    @FXML
    private Label customerCountryLabel;

    /**
     * Element of the Add Customer GUI
     */
    @FXML
    private Label customerPostalCodeLabel;

    /**
     * Element of the Add Customer GUI
     */
    @FXML
    private Label customerPhoneNumberLabel;

    /**
     * Initializes controller.
     *
     * @param url The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        countryComboBox.setItems(getAllCountries());
    }

    /**
     * Creates a new customer with the values that have been input into the form.
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
            addCustomer(name, address, postalCode, phone, fldID);

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
}