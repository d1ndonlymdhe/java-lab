module org.mdhe.jfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.mdhe.jfx to javafx.fxml;
    exports org.mdhe.jfx;
}