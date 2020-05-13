package de.spraener.pdoc;

import java.io.OutputStream;

public interface PDocPrintContext extends AutoCloseable {
    OutputStream getOutputStream();
    PDocPrintContext set(String name, String value);
    String get(String name);
    String evaluate(String text);
}
