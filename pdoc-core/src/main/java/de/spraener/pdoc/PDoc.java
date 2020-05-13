package de.spraener.pdoc;

import java.io.FileOutputStream;
import java.util.function.Consumer;

public class PDoc extends AbstractPDocElement {
    private PDocPrintContext context = null;

    @Override
    public PDoc style(String styleSpec) {
        return (PDoc)super.style(styleSpec);
    }

    @Override
    public PDoc paragraph(String name, Consumer<PDocParagraph> paragraphConsumer) {
        return (PDoc)super.paragraph(name, paragraphConsumer);
    }

    public PDoc setPrintContext(PDocPrintContext context ) {
        this.context = context;
        return this;
    }

    public void toPDF( String fileName) throws Exception {
        FileOutputStream fos = new FileOutputStream(fileName);
        toPDF(fos);
    }

    public void toPDF(FileOutputStream fos) {

    }
}
