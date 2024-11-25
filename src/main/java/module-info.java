module Application {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.logging;
    requires java.sql;

    opens edu.metrostate to javafx.fxml;
    exports edu.metrostate;
}