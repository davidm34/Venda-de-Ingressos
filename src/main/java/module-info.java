module org.example.main {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires com.google.gson;


    opens org.example to javafx.fxml, com.google.gson;
    exports org.example;
}