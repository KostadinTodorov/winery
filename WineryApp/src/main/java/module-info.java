module com.example.wineryapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.wineryapp to javafx.fxml;
    exports com.example.wineryapp;
}