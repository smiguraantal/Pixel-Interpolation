module com.example.pixelinterpolation {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.pixelinterpolation to javafx.fxml;
    exports com.example.pixelinterpolation;
    exports com.example.pixelinterpolation.controller;
    opens com.example.pixelinterpolation.controller to javafx.fxml;
    exports com.example.pixelinterpolation.interpolator;
    opens com.example.pixelinterpolation.interpolator to javafx.fxml;
    exports com.example.pixelinterpolation.formatter;
    opens com.example.pixelinterpolation.formatter to javafx.fxml;
}