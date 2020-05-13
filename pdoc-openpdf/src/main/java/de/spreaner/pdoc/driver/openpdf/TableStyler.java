package de.spreaner.pdoc.driver.openpdf;

import com.lowagie.text.Cell;
import com.lowagie.text.Element;
import com.lowagie.text.Table;
import de.spraener.pdoc.style.PDocStyle;

public class TableStyler implements ElementStyler {
    private static TableStyler instance = new TableStyler();

    private TableStyler() {}

    public static ElementStyler instance() {
        return instance;
    }

    @Override
    public ElementStyler applyTo(PDocStyle style, Element pdfElement) {
        Table table = (Table)pdfElement;
        if( style.getBorderWidth()[PDocStyle.TOP] != null) {
            table.setBorderWidthTop(style.getBorderWidth()[PDocStyle.TOP]);
        }
        if( style.getBorderWidth()[PDocStyle.RIGHT] != null) {
            table.setBorderWidthRight(style.getBorderWidth()[PDocStyle.RIGHT]);
        }
        if( style.getBorderWidth()[PDocStyle.BOTTOM] != null) {
            table.setBorderWidthBottom(style.getBorderWidth()[PDocStyle.BOTTOM]);
        }
        if( style.getBorderWidth()[PDocStyle.LEFT] != null) {
            table.setBorderWidthLeft(style.getBorderWidth()[PDocStyle.LEFT]);
        }
        if( style.getWidth() != null ) {
            table.setWidth(style.getWidth());
        }
        return this;
    }
}
