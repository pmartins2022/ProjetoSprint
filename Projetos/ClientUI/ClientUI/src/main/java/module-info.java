module com.grupo2.clientui {
  requires javafx.controls;
  requires javafx.fxml;
    requires spring.context;
  requires spring.web;
  requires spring.webflux;
  requires reactor.netty.http;
  requires reactor.netty.core;


  opens com.grupo2.clientui to javafx.fxml;
  exports com.grupo2.clientui;
  exports com.grupo2.clientui.ui;
  opens com.grupo2.clientui.ui to javafx.fxml;
}