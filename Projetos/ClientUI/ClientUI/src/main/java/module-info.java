module com.grupo2.clientui {
  requires javafx.controls;
  requires javafx.fxml;


  opens com.grupo2.clientui to javafx.fxml;
  exports com.grupo2.clientui;
}