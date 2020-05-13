package de.spraener.pdoc;

import java.util.ArrayList;
import java.util.List;

public class PDocList extends AbstractPDocElement {
    private List<PDocElement> itemsList = new ArrayList<>();

    public PDocList addItem(PDocElement listItem) {
        this.itemsList.add(listItem);
        return this;
    }
}
