module com.example.obliczanievatu {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.obliczanievatu to javafx.fxml;
    exports com.example.obliczanievatu;
}