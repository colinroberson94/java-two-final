package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import roberson.qam2.Main;

import java.net.URL;
import java.util.ResourceBundle;

public class ModifyCustomerController implements Initializable {
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField addressTextField;
    @FXML
    private TextField postalCodeTextField;
    @FXML
    private TextField phoneNumberTextField;
    @FXML
    private ComboBox firstLevelDivisionComboBox;
    @FXML
    private ComboBox countryComboBox;

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

    }

    @FXML
    void onActionSave(ActionEvent actionEvent) {
    }

    @FXML
    void onActionCancel(ActionEvent actionEvent) {
        Main.switchStage(actionEvent, "/roberson/qam2/customer-screen.fxml");
    }
}
