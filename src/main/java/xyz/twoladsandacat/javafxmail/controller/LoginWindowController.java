package xyz.twoladsandacat.javafxmail.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import xyz.twoladsandacat.javafxmail.EmailManager;
import xyz.twoladsandacat.javafxmail.view.ViewFactory;

public class LoginWindowController extends BaseController {

    @FXML
    private TextField emailAddressField;

    @FXML
    private Label errorLabel;

    @FXML
    private PasswordField passwordField;

    public LoginWindowController(EmailManager emailManager, ViewFactory viewFactory, String fxmlName) {
        super(emailManager, viewFactory, fxmlName);
    }

    @FXML
    void loginButtonAction() {
        System.out.println("Login button action invoked");

        viewFactory.showMainWindow();

        // Get the current stage. No built-in JavaFx method available
        Stage stage = (Stage) errorLabel.getScene().getWindow();
        viewFactory.closeStage(stage);
    }

}
