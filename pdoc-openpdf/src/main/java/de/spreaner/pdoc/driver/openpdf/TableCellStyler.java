package de.spreaner.pdoc.driver.openpdf;

import com.lowagie.text.Cell;
import com.lowagie.text.Element;
import de.spraener.pdoc.style.PDocStyle;

public class TableCellStyler implements ElementStyler {
    private static TableCellStyler instance = new TableCellStyler();

    private TableCellStyler() {}

    public static ElementStyler instance() {
        return instance;
    }

    @Override
    public ElementStyler applyTo(PDocStyle style, Element pdfElement) {
        Cell cell = (Cell)pdfElement;
        cell.setBorder(0);
        if( style.getBorderWidth()[PDocStyle.TOP] != null) {
            cell.setBorderWidthTop(style.getBorderWidth()[PDocStyle.TOP]);
        }
        if( style.getBorderWidth()[PDocStyle.RIGHT] != null) {
            cell.setBorderWidthRight(style.getBorderWidth()[PDocStyle.RIGHT]);
        }
        if( style.getBorderWidth()[PDocStyle.BOTTOM] != null) {
            cell.setBorderWidthBottom(style.getBorderWidth()[PDocStyle.BOTTOM]);
        }
        if( style.getBorderWidth()[PDocStyle.LEFT] != null) {
            cell.setBorderWidthLeft(style.getBorderWidth()[PDocStyle.LEFT]);
        }
        if( style.getColSpan() != null ) {
            cell.setColspan(style.getColSpan());
        }
        return this;
    }
}
