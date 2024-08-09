package de.spraener.pdoc;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import de.spraener.pdoc.context.PDocFilePrintContext;
import de.spraener.pdoc.driver.PDocDriver;

public class TestPDoc {
    private PDocElement childToFind = null;

    @Test
    public void testOpenPDFDriver() throws Exception {
        PDoc doc = PDocDemoDocument.instance();

        PDocElement child = doc.locate("table1.R3C2to3");
        assertNotNull(child);
        assertTrue(child instanceof PDocCell);
        PDocCell cell = (PDocCell)child;
        PDocParagraph p = (PDocParagraph)cell.getElements().iterator().next();
        assertTrue(p.getText().contains("This text spans over column 2 and 3 in"));

        PDocPrintContext context = new PDocFilePrintContext("test.pdf");
        context.set("name", "Carsten");
        PDocDriver driver = PDocDriver.loadDriverFor("PDF");
        driver.renderDocument(doc, context);
    }
}
