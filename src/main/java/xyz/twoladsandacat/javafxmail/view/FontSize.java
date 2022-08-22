package xyz.twoladsandacat.javafxmail.view;

public enum FontSize {
    SMALL,
    MEDIUM,
    BIG;

    public static String getCssPath(FontSize fontSize) {
        return switch (fontSize) {
            case BIG -> "/css/fontBig.css";
            case MEDIUM -> "/css/fontMedium.css";
            case SMALL -> "/css/fontSmall.css";
        };
    }
}
