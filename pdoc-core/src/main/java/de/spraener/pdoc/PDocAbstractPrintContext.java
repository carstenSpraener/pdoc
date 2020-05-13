package de.spraener.pdoc;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class PDocAbstractPrintContext implements PDocPrintContext {
    private Map<String,String> variables = new HashMap<>();
    private OutputStream outputStream = null;

    public PDocAbstractPrintContext(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    @Override
    public PDocPrintContext set(String name, String value) {
        this.variables.put(name,value);
        return this;
    }

    @Override
    public String get(String name) {
        return this.variables.get(name);
    }

    @Override
    public OutputStream getOutputStream() {
        return this.outputStream;
    }

    @Override
    public void close() throws IOException {
        this.outputStream.close();
    }

    @Override
    public String evaluate(String text) {
        if( text==null ) {
            return "";
        }
        String outText = text;
        while( outText.contains("{{")) {
            int startIdx = outText.indexOf("{{");
            int endIdx = outText.indexOf("}}", startIdx);
            String head = outText.substring(0, startIdx );
            String tail = outText.substring(endIdx+2);
            String varName = outText.substring(startIdx+2, endIdx);
            String value = get(varName);
            if( value== null ) {
                value = "VAR "+varName+" UNDEFINED";
            }
            outText = head + value + tail;
        }
        return outText;
    }

}
