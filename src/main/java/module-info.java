module xyz.twoladsandacat.javafxmail {
    requires atlantafx.base;
    requires com.jthemedetector;
    requires jakarta.activation;
    requires jakarta.mail;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    opens xyz.twoladsandacat.javafxmail;
    opens xyz.twoladsandacat.javafxmail.controller;
    opens xyz.twoladsandacat.javafxmail.model;
    opens xyz.twoladsandacat.javafxmail.view;

    exports xyz.twoladsandacat.javafxmail;
    exports xyz.twoladsandacat.javafxmail.model;
}
