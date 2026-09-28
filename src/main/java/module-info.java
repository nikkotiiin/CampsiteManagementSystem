module com.example.campsitemanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.campsitemanagementsystem to javafx.fxml;
    exports com.example.campsitemanagementsystem;
}