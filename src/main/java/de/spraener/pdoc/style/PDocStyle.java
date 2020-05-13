package de.spraener.pdoc.style;

import java.util.StringTokenizer;

public class PDocStyle {
    public static final int TOP = 0;
    public static final int RIGHT = 1;
    public static final int BOTTOM = 2;
    public static final int LEFT = 3;
    public Float spacing;
    Float[] borderWidth = new Float[4];
    Float margin[] = new Float[4];
    String fontName;
    Float fontSize;
    Boolean fontBold;
    Boolean fontItalic;
    Boolean fontUnderlined;

    Float width;
    Integer colSpan;

    public PDocStyle() {}

    public PDocStyle(String styleSpec) {
        parseStyleSpex(styleSpec);
    }

    private void parseStyleSpex(String styleSpec) {
        StringTokenizer specs = new StringTokenizer(styleSpec, ";");
        while( specs.hasMoreTokens() ) {
            try {
                StringTokenizer instrSpec = new StringTokenizer(specs.nextToken(), ":");
                String name = instrSpec.nextToken().trim();
                String value = instrSpec.nextToken().trim();
                PDocStyleToken token = PDocStyleToken.from(name);
                token.applyTo(this,value);
            } catch( Exception e ) {
                // TODO: Fehler in der Style-Angabe... Wat nu?
            }
        }
    }

    public Float[] getMargin() {
        return margin;
    }

    public String getFontName() {
        return fontName;
    }

    public Float getFontSize() {
        return fontSize;
    }

    public Boolean isFontBold() {
        return fontBold;
    }

    public Boolean isFontItalic() {
        return fontItalic;
    }

    public Boolean isUnderlined() {
        return fontUnderlined;
    }

    public Float[] getBorderWidth() {
        return borderWidth;
    }

    public Float getWidth() {
        return width;
    }

    public Integer getColSpan() {
        return colSpan;
    }

    public Float getSpacing() {
        return spacing;
    }
}
