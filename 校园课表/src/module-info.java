module clirent {
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;

    opens sample to javafx.graphics, javafx.fxml;
    opens sample2 to javafx.graphics, javafx.fxml,javafx.base;

}