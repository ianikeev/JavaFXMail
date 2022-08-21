package xyz.twoladsandacat.javafxmail;

import javafx.application.Application;
import javafx.stage.Stage;
import xyz.twoladsandacat.javafxmail.view.ViewFactory;

import java.io.IOException;

public class Launcher extends Application {
    @Override
    public void start(Stage stage) throws IOException {
//        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass()
//                .getResource("/xyz/twoladsandacat/javafxmail/view/MainWindow.fxml")));
//        Scene scene = new Scene(parent);
//
//        stage.setScene(scene);
//        stage.show();

        ViewFactory viewFactory=new ViewFactory(new EmailManager());
        viewFactory.showLoginWindow();
    }

    public static void main(String[] args) {
        launch();
    }
}
