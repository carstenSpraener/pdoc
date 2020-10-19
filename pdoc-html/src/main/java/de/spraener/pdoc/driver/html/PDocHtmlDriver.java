package de.spraener.pdoc.driver.html;

import de.spraener.pdoc.*;
import de.spraener.pdoc.context.PDocFilePrintContext;
import de.spraener.pdoc.driver.PDocDriver;

import java.io.OutputStream;
import java.io.PrintWriter;

public class PDocHtmlDriver implements PDocDriver {
    @Override
    public PDocDriver renderDocument(PDoc doc, PDocPrintContext context) {
        PrintWriter pw = new PrintWriter(context.getOutputStream());
        HTMLElement body = new HTMLElement(null, "body");
        pw.println("<html>");
        for(PDocElement e : doc.getElements() ) {
            renderElement(body, context, e);
        }
        body.toHTML(pw, "  ", context);
        pw.println("</html>");
        pw.flush();

        return this;
    }

    private HTMLElement renderElement(HTMLElement parent, PDocPrintContext context, PDocElement e) {
        if (e instanceof PDocParagraph) {
            return renderParagraph(parent, context, (PDocParagraph) e);
        } else if (e instanceof PDocTable) {
            return renderTable(parent, context, (PDocTable) e);
        }
        throw new RuntimeException("Unknown PDocElement: " + e);
    }

    private HTMLElement renderTable(HTMLElement parent, PDocPrintContext context, PDocTable e) {
        HTMLElement table = new HTMLElement(parent,"table");
        HtmlElementStyler.instance().applyTo(e.getStyle(), table);
        HTMLElement activeRow = new HTMLElement(table, "tr");
        int nofCols=0;
        for( PDocCell tCell : e.getCells() ) {
            nofCols += renderTableCell(activeRow, context, tCell);
            if( nofCols >= e.getNofColumns() ) {
                activeRow = new HTMLElement(table, "tr");
                nofCols = 0;
            }
        }
        return table;
    }

    private int renderTableCell(HTMLElement activeRow, PDocPrintContext context, PDocCell pdocCell) {
        HTMLElement tCell = new HTMLElement(activeRow, "td");
        HtmlElementStyler.instance().applyTo(pdocCell.getStyle(), tCell);
        tCell.setBodyText(pdocCell.getText());
        Integer colSpan = null;
        if( pdocCell.getStyle()!=null ) {
            colSpan = pdocCell.getStyle().getColSpan();
        }
        if( colSpan == null ) {
            return 1;
        }
        for( PDocElement child : pdocCell.getElements() ) {
            renderElement(tCell,context,child);
        }
        return colSpan.intValue();
    }

    private HTMLElement renderParagraph(HTMLElement parent,  PDocPrintContext context, PDocParagraph p) {
        HTMLElement htmlP = new HTMLElement(parent, "p");
        htmlP.setBodyText(p.getText());
        HtmlElementStyler.instance().applyTo( p.getStyle(), htmlP);
        for( PDocElement childE : p.getElements() ) {
            renderElement(htmlP, context, childE);
        }
        return htmlP;
    }

    @Override
    public PDocPrintContext createPrintContext(OutputStream outputStream) {
        try {
            return new PDocFilePrintContext(outputStream);
        } catch( Exception e ) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean provides(String providesType) {
        return providesType.contains("HTML");
    }
}
