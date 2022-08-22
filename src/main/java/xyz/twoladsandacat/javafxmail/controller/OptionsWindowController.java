package xyz.twoladsandacat.javafxmail.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import xyz.twoladsandacat.javafxmail.EmailManager;
import xyz.twoladsandacat.javafxmail.view.ColorTheme;
import xyz.twoladsandacat.javafxmail.view.FontSize;
import xyz.twoladsandacat.javafxmail.view.ViewFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class OptionsWindowController extends BaseController implements Initializable {

    public OptionsWindowController(EmailManager emailManager, ViewFactory viewFactory, String fxmlName) {
        super(emailManager, viewFactory, fxmlName);
    }

    @FXML
    private Slider fontSizePicker;

    @FXML
    private ChoiceBox<ColorTheme> themePicker;

    @FXML
    void applyButtonAction() {
        viewFactory.setColorTheme(themePicker.getValue());
        viewFactory.setFontSize(FontSize.values()[(int) fontSizePicker.getValue()]);
        viewFactory.updateStyles();
    }

    @FXML
    void cancelButtonAction() {
        Stage stage = (Stage) fontSizePicker.getScene().getWindow();
        viewFactory.closeStage(stage);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setUpThemePicker();
        setUpFontSizePicker();
    }

    private void setUpFontSizePicker() {
        fontSizePicker.setMin(0);
        fontSizePicker.setMax(FontSize.values().length - 1);
        fontSizePicker.setValue(viewFactory.getFontSize().ordinal());
        fontSizePicker.setMajorTickUnit(1);
        fontSizePicker.setMinorTickCount(0);
        fontSizePicker.setBlockIncrement(1);
        fontSizePicker.setSnapToTicks(true);
        fontSizePicker.setShowTickMarks(true);
        fontSizePicker.setShowTickLabels(true);
        fontSizePicker.setLabelFormatter(new StringConverter<Double>() {
            @Override
            public String toString(Double object) {
                int i = object.intValue();
                return FontSize.values()[i].toString();
            }

            @Override
            public Double fromString(String string) {
                return null;
            }
        });
        fontSizePicker.valueProperty().addListener((obs, oldVal, newVal) -> {
            fontSizePicker.setValue(newVal.intValue());
        });
    }

    private void setUpThemePicker() {
        themePicker.setItems(FXCollections.observableArrayList(ColorTheme.values()));
        themePicker.setValue(viewFactory.getColorTheme());
    }
}
