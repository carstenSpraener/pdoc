package de.spraener.pdoc.style;

import java.util.function.BiConsumer;

public enum PDocStyleToken {
    MARGIN_LEFT("margin-left", (style,value) -> {
        style.margin[PDocStyle.LEFT] = new Float(value);
    }),
    MARGIN_RIGHT("margin-right", (style,value) -> {
        style.margin[PDocStyle.RIGHT] = new Float(value);
    }),
    MARGIN_BOTTOM("margin-bottom", (style,value) -> {
        style.margin[PDocStyle.BOTTOM] = new Float(value);
    }),
    MARGIN_TOP("margin-top", (style,value) -> {
        style.margin[PDocStyle.TOP] = new Float(value);
    }),
    BOLD("bold", (style,value) -> {
        style.fontBold = new Boolean(value);
    }),
    ITALIC("italic", (style,value) -> {
        style.fontItalic= new Boolean(value);
    }),
    UNDERLINED("underlined", (style,value) -> {
        style.fontUnderlined = new Boolean(value);
    }),
    FONT_NAME("font-name", (style,value) -> {
        style.fontName = value;
    }),
    FONT_SIZE("font-size", (style,value) -> {
        style.fontSize = new Float(value);
    }),
    BORDER_WIDTH("border-width", (style,value)-> {
        Float width = new Float(value);
        for( int idx=0; idx<style.borderWidth.length; idx++ ) {
            style.borderWidth[idx] = width;
        }
    }),
    BORDER_WIDTH_TOP("border-width-top", (style,value)-> {
        Float width = new Float(value);
        style.borderWidth[PDocStyle.TOP] = width;
    }),
    BORDER_WIDTH_RIGHT("border-width-right", (style,value)-> {
        Float width = new Float(value);
        style.borderWidth[PDocStyle.RIGHT] = width;
    }),
    BORDER_WIDTH_BOTTOM("border-width-bottom", (style,value)-> {
        Float width = new Float(value);
        style.borderWidth[PDocStyle.BOTTOM] = width;
    }),
    BORDER_WIDTH_LEFT("border-width-left", (style,value)-> {
        Float width = new Float(value);
        style.borderWidth[PDocStyle.LEFT] = width;
    }),
    WIDTH("width", (style,value)-> {
        Float width = new Float(value);
        style.width = width;
    }),
    COLSPAN("colspan", (style,value)-> {
        Integer span = new Integer(value);
        style.colSpan = span;
    }),
    SPACING("spacing", (style,value) -> {
        style.spacing = new Float(value);
    })
    ;

    public String name;
    private BiConsumer<PDocStyle, String> consumer;

    PDocStyleToken(String name, BiConsumer<PDocStyle, String> consumer) {
        this.name = name;
        this.consumer = consumer;
    }

    public static PDocStyleToken from(String name) {
        for( PDocStyleToken t : PDocStyleToken.values() ) {
            if( t.name.equals(name) ) {
                return t;
            }
        }
        return null;
    }

    public void applyTo(PDocStyle pDocStyleInstruction, String strValue) {
        this.consumer.accept(pDocStyleInstruction,strValue);
    }
}