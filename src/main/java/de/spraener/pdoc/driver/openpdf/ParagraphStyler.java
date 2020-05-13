package de.spraener.pdoc.driver.openpdf;

import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import de.spraener.pdoc.style.PDocStyle;

public class ParagraphStyler implements ElementStyler {
    private static ParagraphStyler instance = new ParagraphStyler();
    private ParagraphStyler(){}

    public static ParagraphStyler instance() {
        return instance;
    }

    @Override
    public ElementStyler applyTo(PDocStyle style, Element pdfElement) {
        Paragraph p = (Paragraph)pdfElement;
        Float margin[] = style.getMargin();
        if( margin[PDocStyle.TOP] != null ) {
            p.setSpacingBefore(margin[PDocStyle.TOP]);
        }
        if( margin[PDocStyle.RIGHT] != null ) {
            p.setIndentationRight(margin[PDocStyle.RIGHT]);
        }
        if( margin[PDocStyle.BOTTOM] != null ) {
            p.setSpacingAfter(margin[PDocStyle.BOTTOM]);
        }
        if( margin[PDocStyle.LEFT] != null ) {
            p.setIndentationLeft(margin[PDocStyle.LEFT]);
        }
        Font font = StyleFontCreator.getFont(style);
        if( font!=null ) {
            p.setFont(font);
        }
        if( style.getSpacing()!=null ) {
            p.setExtraParagraphSpace(style.getSpacing());
        }
        return this;
    }
}
