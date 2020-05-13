package de.spraener.pdoc.driver.html;

import de.spraener.pdoc.PDoc;
import de.spraener.pdoc.PDocPrintContext;
import de.spraener.pdoc.driver.PDocDriver;

public class PDocHtmlDriver implements PDocDriver {
    @Override
    public PDocDriver renderDocument(PDoc doc, PDocPrintContext context) {
        return null;
    }

    @Override
    public boolean provides(String providesType) {
        return providesType.contains("HTML");
    }
}
