package de.spreaner.pdoc.driver.openpdf;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import de.spraener.pdoc.*;
import de.spraener.pdoc.context.PDocFilePrintContext;
import de.spraener.pdoc.driver.PDocDriver;

import java.io.OutputStream;

public class PDocOpenPDFDriver implements PDocDriver {
    @Override
    public PDocDriver renderDocument(PDoc doc, PDocPrintContext context) {
        try {
            OutputStream os = context.getOutputStream();
            Document document = new Document();
            PdfWriter.getInstance(document, context.getOutputStream());
            document.open();
            for (PDocElement e : doc.getElements()) {
                document.add(renderElement(document, context, e));
            }
            document.close();
            return this;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public PDocPrintContext createPrintContext(OutputStream outputStream) {
        try {
            return new PDocFilePrintContext(outputStream);
        } catch( Exception e ) {
            throw new RuntimeException(e);
        }
    }

    public boolean provides(String type ) {
        return type.startsWith("PDF");
    }

    private Element renderElement(Document document, PDocPrintContext context, PDocElement e) {
        if (e instanceof PDocParagraph) {
            return renderParagraph(document, context, (PDocParagraph) e);
        } else if (e instanceof PDocTable) {
            return renderTable(document, context, (PDocTable) e);
        }
        throw new RuntimeException("Unknown PDocElement: " + e);
    }

    private Element renderTable(Document document, PDocPrintContext context, PDocTable pDocTable) {
        Table table = new Table(pDocTable.getNofColumns());
        if (pDocTable.getStyle() != null) {
            TableStyler.instance().applyTo(pDocTable.getStyle(), table);
        }
        if (pDocTable.getColumnWidths() != null) {
            table.setWidths(pDocTable.getColumnWidths());
        }
        for (PDocCell c : pDocTable.getCells()) {
            table.addCell(renderTableCell(table, document, context, pDocTable, c));
        }
        return table;
    }

    private Cell renderTableCell(Table table, Document document, PDocPrintContext context, PDocTable pDocTable, PDocCell c) {
        Cell tCell = new Cell();
        if( c.getText()!=null ) {
            Paragraph celP = new Paragraph();
            if (c.getStyle() != null) {
                ParagraphStyler.instance().applyTo(c.getStyle(), celP);
            } else {
                celP.setSpacingAfter(2f);
            }
            celP.add(new Chunk(c.getText()));
            celP.setSpacingAfter(4);
            celP.setIndentationLeft(2);
            celP.setAlignment(ElementTags.ALIGN_CENTER);
            tCell.add(celP);
        }
        if (pDocTable.getStyle() != null) {
            TableCellStyler.instance().applyTo(pDocTable.getStyle(), tCell);
        }
        if (c.getStyle() != null) {
            TableCellStyler.instance().applyTo(c.getStyle(), tCell);
        }
        for( PDocElement child : c.getElements() ) {
            tCell.add(this.renderElement(document,context,child));
        }
        return tCell;
    }

    private Element renderParagraph(Document document, PDocPrintContext context, PDocParagraph p) {
        Paragraph pdfP = new Paragraph();
        applyStyle(p, pdfP, ParagraphStyler.instance());
        pdfP.add(new Chunk(context.evaluate(p.getText())));
        for( PDocElement child : p.getElements() ) {
            pdfP.add(this.renderElement(document,context,child));
        }
        return pdfP;
    }

    private void applyStyle(PDocElement pdocElement, Element pdfElement, ElementStyler styler) {
        if (pdocElement.getStyle() == null) {
            return;
        }
        styler.applyTo(pdocElement.getStyle(), pdfElement);
    }
}
