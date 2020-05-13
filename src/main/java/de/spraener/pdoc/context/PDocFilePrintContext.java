package de.spraener.pdoc.context;

import de.spraener.pdoc.PDocAbstractPrintContext;

import java.io.FileOutputStream;
import java.io.IOException;

public class PDocFilePrintContext extends PDocAbstractPrintContext {
    public PDocFilePrintContext(String fileName) throws IOException {
        super(new FileOutputStream(fileName));
    }
}
