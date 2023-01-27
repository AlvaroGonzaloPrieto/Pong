module com.alvaro.pong {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.alvaro.pong to javafx.fxml;
    exports com.alvaro.pong;
}