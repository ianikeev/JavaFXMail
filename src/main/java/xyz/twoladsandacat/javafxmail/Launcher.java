package xyz.twoladsandacat.javafxmail;

import com.jthemedetecor.OsThemeDetector;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import xyz.twoladsandacat.javafxmail.view.ColorTheme;
import xyz.twoladsandacat.javafxmail.view.ViewFactory;

public class Launcher extends Application {
    @Override
    public void start(Stage stage) {
        ViewFactory viewFactory = new ViewFactory(new EmailManager());
        viewFactory.showLoginWindow();

        final OsThemeDetector detector = OsThemeDetector.getDetector();
        boolean isDarkThemeUsed = detector.isDark();
        detector.registerListener(isDark -> {
            Platform.runLater(() -> {
                if (isDark) {
                    viewFactory.setColorTheme(ColorTheme.DARK);
                } else {
                    viewFactory.setColorTheme(ColorTheme.LIGHT);
                }
            });
        });
        if (isDarkThemeUsed) {
            viewFactory.setColorTheme(ColorTheme.DARK);
        } else {
            viewFactory.setColorTheme(ColorTheme.LIGHT);
        }
        viewFactory.updateStyles();

    }

    public static void main(String[] args) {
        launch();
    }
}
