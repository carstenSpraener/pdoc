package de.spraener.pdoc.driver.openpdf;

import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import de.spraener.pdoc.style.PDocStyle;


public class StyleFontCreator {
    public static Font getFont(PDocStyle style) {
        if( style.getFontName()==null) {
            return null;
        }
        String fontName = style.getFontName();
        Float fontSize = 12f;
        if( style.getFontSize()!=null ) {
            fontSize = style.getFontSize();
        }
        boolean bold = false;
        if( style.isFontBold()!=null ) {
            bold = style.isFontBold().booleanValue();
        }
        boolean italic = false;
        if( style.isFontItalic()!=null ) {
            italic = style.isFontItalic().booleanValue();
        }
        int fontStyle = Font.UNDEFINED;
        if( bold ) {
            fontStyle = Font.BOLD;
            if( italic ) {
                fontStyle = Font.BOLDITALIC;
            }
        } else {
            if( italic ) {
                fontStyle = Font.ITALIC;
            }
        }
        if( style.isUnderlined()!=null && style.isUnderlined().booleanValue() ) {
            fontStyle = Font.UNDERLINE;
        }
       return  FontFactory.getFont(fontName,fontSize, fontStyle);
    }
}
