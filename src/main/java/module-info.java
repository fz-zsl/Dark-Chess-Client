module com.example.darkchess {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.darkchess to javafx.fxml;
    exports com.example.darkchess;
}