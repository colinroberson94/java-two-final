module roberson.qam2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens roberson.qam2 to javafx.fxml;
    exports roberson.qam2;
    exports controller;
    opens controller to javafx.fxml;
}