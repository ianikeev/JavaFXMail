package xyz.twoladsandacat.javafxmail.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import xyz.twoladsandacat.javafxmail.EmailManager;
import xyz.twoladsandacat.javafxmail.controller.services.LoginService;
import xyz.twoladsandacat.javafxmail.model.EmailAccount;
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
        if (fieldsAreValid()) {
            EmailAccount emailAccount = new EmailAccount(emailAddressField.getText(), passwordField.getText());
            LoginService loginService = new LoginService(emailAccount, emailManager);
            EmailLoginResult emailLoginResult = loginService.login();

            switch (emailLoginResult){
                case SUCCESS -> System.out.println("Login successful for " + emailAccount);
            }
        }
        System.out.println("Login button action invoked");

        viewFactory.showMainWindow();

        // Get the current stage. No built-in JavaFx method available
        Stage stage = (Stage) errorLabel.getScene().getWindow();
        viewFactory.closeStage(stage);
    }

    private boolean fieldsAreValid() {
        if (emailAddressField.getText().isEmpty()) {
            errorLabel.setText("Please enter email");
            return false;
        }
        if (passwordField.getText().isEmpty()) {
            errorLabel.setText("Please enter password");
            return false;
        }
        return true;
    }

}
