module com.example.campsitemanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires xstream;
    opens com.example.campsitemanagementsystem.structures to xstream;
    opens com.example.campsitemanagementsystem.model to xstream;


    opens com.example.campsitemanagementsystem to javafx.fxml;
    exports com.example.campsitemanagementsystem;
}