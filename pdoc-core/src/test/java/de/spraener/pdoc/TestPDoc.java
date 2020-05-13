package de.spraener.pdoc;

import de.spraener.pdoc.context.PDocFilePrintContext;
import de.spraener.pdoc.driver.PDocDriver;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TestPDoc {
    private PDocElement childToFind = null;

    @Test
    public void testOpenPDFDriver() throws Exception {
        PDoc doc = new PDoc();
        doc.paragraph("testp1", p -> {
            p.style("margin-left: 40; margin-top: 120; margin-bottom: 12; font-name: Arial; font-size: 36; underlined: true;");
            p.text("Hello World to {{name}}");
        });
        doc.table("table1", 3, t -> {
            t.style("border-width: 0; width: 100");
            t.setColumnWidth(300,100,100);
            t.cell("R1C1", c -> {
                c.style("border-width-left: 1");
                c.text("Row 1 Col 1");
            });
            t.cell("R1C2", c -> {
                c.style("border-width-left: 1");
                c.text("Row 1 Col 2");
            });
            t.cell("R1C3", c -> {
                c.style("border-width-left: 1");
                c.text("Row 1 Col 3");
            });
            t.cell("R2C1", c -> {
                c.style("colspan: 3; border-width-bottom: 1");
                c.paragraph("Paragraph", p-> {
                    p.style("font-size: 10; font-name: Times New Roman; margin-bottom: 12");
                    p.text("This cell contains a whole paragraph with a lot of text. The text should span over all three columns. For that it"+
                                    " is repeated several times. As you can see.\n"+
                                    "This cell contains a whole paragraph with a lot of text. The text should span over all three columns. For that it"+
                                    " is repeated several times. As you can see.\n"+
                                    "This cell contains a whole paragraph with a lot of text. The text should span over all three columns. For that it"+
                                    " is repeated several times. As you can see.\n"
                            );
                });
            });

            t.cell("R3C1", c -> {});
            t.cell("R3C2to3", c -> {
                childToFind = c;
               c.style("colspan: 2");
               c.paragraph("paragraph", p-> {
                   p.style("font-size: 12; spacing: 24");
                   p.text("This text spans over column 2 and 3 in the third row. It also uses a extra spacing between the lines of this paragraph.");
               });
            });

        });
        PDocElement child = doc.locate("table1.R3C2to3");
        assertNotNull(child);
        assertEquals(child,childToFind);

        PDocPrintContext context = new PDocFilePrintContext("test.pdf");
        context.set("name", "Carsten");
        PDocDriver driver = PDocDriver.loadDriverFor("PDF");
        driver.renderDocument(doc, context);
    }
}
