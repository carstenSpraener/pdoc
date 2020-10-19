package de.spraener.pdoc.context;

import de.spraener.pdoc.PDocAbstractPrintContext;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class PDocFilePrintContext extends PDocAbstractPrintContext {
    public PDocFilePrintContext(OutputStream outputStream) throws IOException{
        super(outputStream);
    }

    public PDocFilePrintContext(String fileName) throws IOException {
        super(new FileOutputStream(fileName));
    }
}
