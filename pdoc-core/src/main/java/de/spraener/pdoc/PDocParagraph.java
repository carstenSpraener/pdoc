package de.spraener.pdoc;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class PDocParagraph extends AbstractPDocElement implements PDocTextContainer {
    private List<Float> tabList = null;
    private String text;

    public PDocParagraph tabs( float ...tabs ) {
        if( tabList == null ) {
            tabList = new ArrayList<>();
        }
        tabList.clear();
        for( float t : tabs ) {
            tabList.add(t);
        }
        return this;
    }

    @Override
    public PDocParagraph style(String styleSpec) {
        return (PDocParagraph)super.style(styleSpec);
    }

    @Override
    public PDocParagraph paragraph(String name, Consumer<PDocParagraph> paragraphConsumer) {
        return (PDocParagraph) super.paragraph(name, paragraphConsumer);
    }

    @Override
    public String getText() {
        return this.text;
    }

    public PDocParagraph text( String text ) {
        this.text = text;
        return this;
    }

    @Override
    public String addTextPrefix(String contentPrefix) {
        this.text = contentPrefix + this.text;
        return this.text;
    }

    @Override
    public String addTextPostfix(String contentPostfix) {
        this.text = this.text + contentPostfix;
        return this.text;
    }
}
