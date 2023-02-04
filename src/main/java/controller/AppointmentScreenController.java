package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import roberson.qam2.Main;

import java.net.URL;
import java.util.ResourceBundle;

public class AppointmentScreenController implements Initializable {


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void onActionExit(ActionEvent actionEvent) {
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
}