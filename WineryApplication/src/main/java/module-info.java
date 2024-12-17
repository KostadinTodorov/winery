module com.oopproject.wineryapplication {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.oopproject.wineryapplication to javafx.fxml;
    exports com.oopproject.wineryapplication;
}