package de.spraener.pdoc;

import de.spraener.pdoc.style.PDocStyle;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class AbstractPDocElement implements PDocElement {
    private PDocStyle style;
    private PDocElement parent;
    private String name = "ANNONYMOUS";
    private String path;
    private List<PDocElement> childs = new ArrayList<>();

    @Override
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public PDocElement getParent() {
        return this.parent;
    }

    @Override
    public void setParent(PDocElement parent) {
        this.parent = parent;
    }

    @Override
    public PDocStyle getStyle() {
        return this.style;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String getPath() {
        return this.path;
    }

    public AbstractPDocElement style(String styleSpec) {
        this.style = new PDocStyle(styleSpec);
        return this;
    }

    public AbstractPDocElement paragraph(String name, Consumer<PDocParagraph> paragraphConsumer ) {
        PDocParagraph p = new PDocParagraph();
        joinPDocElement(name, p);
        paragraphConsumer.accept(p);
        return this;
    }

    public AbstractPDocElement list(String name, Consumer<PDocList> listConsumer ) {
        PDocList list = new PDocList();
        joinPDocElement(name, list);
        listConsumer.accept(list);
        return this;
    }

    public AbstractPDocElement table(String name, int nofCols, Consumer<PDocTable> tableConsumer ) {
        PDocTable table = new PDocTable();
        joinPDocElement(name, table);
        table.setNofColumns(nofCols);
        tableConsumer.accept(table);
        return this;
    }

    protected void joinPDocElement(String name, AbstractPDocElement p) {
        p.setParent(this);
        p.setName(name);
        p.setPath(this.getName()+"/"+p.getName());
        childs.add(p);
    }

    public Iterable<PDocElement> getElements() {
        return this.childs;
    }

    @Override
    public PDocElement locate(String path) {
        if( path.startsWith(".") ) {
            return locate(path.substring(1));
        }
        if( path.indexOf('.') >= 0 ) {
            String childName = path.substring(0, path.indexOf('.'));
            String subPath = path.substring(path.indexOf('.')+1);
            for( PDocElement child : this.childs ) {
                if( child.getName().equals(childName) ) {
                    return child.locate(subPath);
                }
            }
            return null;
        }
        for( PDocElement child : this.childs ) {
            if( child.getName().equals(path) ) {
                return child;
            }
        }
        return null;
    }
}
