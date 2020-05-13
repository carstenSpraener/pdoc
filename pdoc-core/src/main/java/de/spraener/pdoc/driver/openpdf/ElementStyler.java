package de.spraener.pdoc.driver.openpdf;

import com.lowagie.text.Element;
import de.spraener.pdoc.style.PDocStyle;

public interface ElementStyler {
    ElementStyler applyTo(PDocStyle style, Element pdfElement);
}
