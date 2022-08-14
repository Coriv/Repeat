module com.repeat.repeat {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.repeat.repeat to javafx.fxml;
    exports com.repeat.repeat;
    exports com.repeat.repeat.data;
    opens com.repeat.repeat.data to javafx.fxml;
    exports com.repeat.repeat.panel;
    opens com.repeat.repeat.panel to javafx.fxml;
}