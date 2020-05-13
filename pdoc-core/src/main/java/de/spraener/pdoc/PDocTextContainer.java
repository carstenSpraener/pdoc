package de.spraener.pdoc;

public interface PDocTextContainer {
    String getText();
    String addTextPrefix(String contentPrefix);
    String addTextPostfix(String contentPostfix);
}
