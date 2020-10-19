import de.spraener.pdoc.PDoc;
import de.spraener.pdoc.PDocDemoDocument;
import de.spraener.pdoc.PDocPrintContext;
import de.spraener.pdoc.driver.PDocDriver;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.OutputStream;

public class TestHTMLDriver {

    @Test
    public void testHtml() throws Exception {
        PDoc pdoc = PDocDemoDocument.instance();

        PDocDriver driver = PDocDriver.loadDriverFor("HTML");
        OutputStream os = new FileOutputStream("test.html");
        PDocPrintContext context = driver.createPrintContext(os);
        context.set("name", "Philipp Docktail");
        driver.renderDocument(pdoc, context);
    }
}
