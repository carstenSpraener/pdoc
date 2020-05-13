package de.spraener.pdoc;

import de.spraener.pdoc.style.PDocStyle;

public interface PDocElement {
    String getName();
    PDocElement getParent();
    void setParent(PDocElement parent);
    PDocStyle getStyle();
    String getPath();
    Iterable<PDocElement> getElements();
    PDocElement locate(String path);
}
