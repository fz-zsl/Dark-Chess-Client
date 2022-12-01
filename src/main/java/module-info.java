module com.example.darkchess {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.media;


    opens com.example.darkchess to javafx.fxml;
    exports com.example.darkchess;
}