module xyz.twoladsandacat.javafxmail {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires atlantafx.base;
    requires jakarta.activation;
    requires jakarta.mail;


    opens xyz.twoladsandacat.javafxmail;
    opens xyz.twoladsandacat.javafxmail.controller;
    opens xyz.twoladsandacat.javafxmail.view;

    exports xyz.twoladsandacat.javafxmail;
}
