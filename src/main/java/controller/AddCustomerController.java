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
import static DAO.FirstLevelDivisionQuery.getAllFirstLevelDivisions;
import static model.FirstLevelDivision.getFirstLevelDivisionOfCountry;

public class AddCustomerController implements Initializable {
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        countryComboBox.setItems(getAllCountries());
        if (!countryComboBox.getSelectionModel().isEmpty()) {
            Countries country = countryComboBox.getSelectionModel().getSelectedItem();
            firstLevelDivisionComboBox.setItems(getFirstLevelDivisionOfCountry(country.getCountryId()));
        }
    }

    @FXML
    void onActionSave(ActionEvent actionEvent) {
        try {
            String name = nameTextField.getText();
            String address = addressTextField.getText();
            String postalCode = postalCodeTextField.getText();
            String phone = phoneNumberTextField.getText();
            Countries country = countryComboBox.getSelectionModel().getSelectedItem();
            FirstLevelDivision firstLevelDivsion = firstLevelDivisionComboBox.getSelectionModel().getSelectedItem();
            Integer fldID = firstLevelDivsion.getDivisionId();
            addCustomer(name, address, postalCode, phone, fldID);

            Main.switchStage(actionEvent, "/roberson/qam2/customer-screen.fxml");
            }  catch (RuntimeException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getLocalizedMessage(), ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    void onActionCancel(ActionEvent actionEvent) {
        Main.switchStage(actionEvent, "/roberson/qam2/customer-screen.fxml");
    }
}