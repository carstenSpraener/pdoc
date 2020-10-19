package de.spraener.pdoc.driver.html;

import de.spraener.pdoc.PDocPrintContext;

import javax.swing.text.html.HTML;
import java.io.PrintWriter;
import java.util.*;

public class HTMLElement {
    private String name;
    private Map<String,String> attributes = new HashMap<>();
    private List<HTMLElement> childElements = new ArrayList<>();
    private String bodyText;
    private HTMLElement parent;

    public HTMLElement(HTMLElement parent, String name) {
        this.parent = parent;
        this.name = name;
        if( parent!=null ) {
            parent.addChild(this);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBodyText() {
        return bodyText;
    }

    public void setBodyText(String bodyText) {
        this.bodyText = bodyText;
    }

    public HTMLElement getParent() {
        return parent;
    }

    public void setParent(HTMLElement parent) {
        this.parent = parent;
    }

    public HTMLElement setAttribute(String name, String value ) {
        this.attributes.put(name, value);
        return this;
    }

    public HTMLElement addChild( HTMLElement child ) {
        this.childElements.add(child);
        child.setParent(this);
        return this;
    }

    public void toHTML(PrintWriter pw, String prefix, PDocPrintContext context) {
        pw.print(prefix+"<"+this.name);
        if( !this.attributes.isEmpty() ) {
            for( String attrName: this.attributes.keySet() ) {
                pw.print(" "+attrName+"='"+this.attributes.get(attrName)+"'");
            }
        }
        pw.println( ">");
        if( this.bodyText!=null ) {
            pw.println(context.evaluate(this.bodyText));
        }
        for( HTMLElement child : this.childElements ) {
            child.toHTML(pw, prefix+"  ", context);
        }
        pw.println(prefix+"</"+this.name+">");
    }
}
