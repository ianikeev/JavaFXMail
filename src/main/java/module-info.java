module xyz.twoladsandacat.javafxmail {
    requires javafx.controls;
    requires javafx.fxml;


    opens xyz.twoladsandacat.javafxmail to javafx.fxml;
    opens xyz.twoladsandacat.javafxmail.view;
    exports xyz.twoladsandacat.javafxmail;
}
