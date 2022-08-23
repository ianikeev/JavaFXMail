package xyz.twoladsandacat.javafxmail.view;

import atlantafx.base.theme.PrimerDark;
import atlantafx.base.theme.PrimerLight;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import xyz.twoladsandacat.javafxmail.EmailManager;
import xyz.twoladsandacat.javafxmail.controller.BaseController;
import xyz.twoladsandacat.javafxmail.controller.LoginWindowController;
import xyz.twoladsandacat.javafxmail.controller.MainWindowController;
import xyz.twoladsandacat.javafxmail.controller.OptionsWindowController;

import java.io.IOException;
import java.util.ArrayList;

public class ViewFactory {

    private final EmailManager emailManager;
    private final ArrayList<Stage> activeStages;
    private boolean mainViewInitialized = false;

    public ViewFactory(EmailManager emailManager) {
        this.emailManager = emailManager;
        activeStages = new ArrayList<>();
    }

    // View options handling:
    private ColorTheme colorTheme = ColorTheme.DEFAULT;
    private FontSize fontSize = FontSize.MEDIUM;

    public ColorTheme getColorTheme() {
        return colorTheme;
    }

    public void setColorTheme(ColorTheme colorTheme) {
        this.colorTheme = colorTheme;
    }

    public FontSize getFontSize() {
        return fontSize;
    }

    public void setFontSize(FontSize fontSize) {
        this.fontSize = fontSize;
    }

    public void showLoginWindow() {
        System.out.println("show login window called");

        BaseController controller = new LoginWindowController(emailManager, this,
                "/xyz/twoladsandacat/javafxmail/view/LoginWindow.fxml");
        initializeStage(controller);
    }

    public boolean isMainViewInitialized() {
        return mainViewInitialized;
    }

    public void showMainWindow() {
        System.out.println("show main window called");

        BaseController controller = new MainWindowController(emailManager, this,
                "/xyz/twoladsandacat/javafxmail/view/MainWindow.fxml");
        initializeStage(controller);
        mainViewInitialized = true;
    }

    public void showOptionsWindow() {
        System.out.println("show options window called");

        BaseController controller = new OptionsWindowController(emailManager, this,
                "/xyz/twoladsandacat/javafxmail/view/OptionsWindow.fxml");
        initializeStage(controller);
    }

    public void closeStage(Stage stage) {
        stage.close();
        activeStages.remove(stage);
    }

    private void initializeStage(BaseController baseController) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(baseController.getFxmlName()));
        fxmlLoader.setController(baseController);
        Parent parent;
        try {
            parent = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        activeStages.add(stage);
    }

    public void updateStyles() {
        for (var stage : activeStages) {
            var scene = stage.getScene();
            // Handle the css
            switch (colorTheme) {
                case DEFAULT -> {
                    scene.getStylesheets().clear();
                    Application.setUserAgentStylesheet(Application.STYLESHEET_MODENA);
                }
                case LIGHT -> {
                    scene.getStylesheets().clear();
                    Application.setUserAgentStylesheet(new PrimerLight().getUserAgentStylesheet());
                }
                case DARK -> {
                    scene.getStylesheets().clear();
                    Application.setUserAgentStylesheet(new PrimerDark().getUserAgentStylesheet());
                }
            }
        }
    }
}
