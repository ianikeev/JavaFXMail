package xyz.twoladsandacat.javafxmail;

import javafx.application.Application;
import javafx.stage.Stage;
import xyz.twoladsandacat.javafxmail.view.ViewFactory;

public class Launcher extends Application {
    @Override
    public void start(Stage stage) {
        ViewFactory viewFactory = new ViewFactory(new EmailManager());
        viewFactory.showLoginWindow();
        viewFactory.updateStyles();
    }

    public static void main(String[] args) {
        launch();
    }
}
