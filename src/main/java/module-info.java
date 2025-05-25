module com.library.carrentalsystem {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.library.carrentalsystem to javafx.fxml;
    exports com.library.carrentalsystem;
}