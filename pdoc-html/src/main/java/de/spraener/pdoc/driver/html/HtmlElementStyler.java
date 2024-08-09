package de.spraener.pdoc.driver.html;

import de.spraener.pdoc.style.PDocStyle;

public class HtmlElementStyler {
    private static final HtmlElementStyler instance = new HtmlElementStyler();

    private HtmlElementStyler() {
    }

    public static HtmlElementStyler instance() {
        return instance;
    }

    public HtmlElementStyler applyTo(PDocStyle style, HTMLElement htmlElement) {
        if( style==null ) {
            return this;
        }
        StringBuilder styleStmt = new StringBuilder();
        if( Boolean.TRUE.equals(style.isFontBold()) ) {
            styleStmt.append("font-weight: bold;");
        }
        if( Boolean.TRUE.equals(style.isUnderlined()) ) {
            htmlElement.setAttribute("underline", "true");
        }
        if( Boolean.TRUE.equals(style.isFontItalic()) ) {}
        if( style.getFontName()!=null ) {
            styleStmt.append("font-name: ").append(style.getFontName()).append(";");
        }
        if( style.getFontSize()!=null ) {
            styleStmt.append("font-size: ").append(style.getFontSize()).append("pt;");;
        }
        if( styleStmt.length()>0 ) {
            htmlElement.setAttribute("style", styleStmt.toString());
        }
        if( style.getColSpan()!=null && style.getColSpan().intValue()>1 ) {
            htmlElement.setAttribute("colspan", style.getColSpan().toString());
        }
        if( style.getWidth()!=null ) {
            htmlElement.setAttribute("width", style.getWidth().toString()+"%");
        }
        return this;
    }
}
