package de.spraener.pdoc;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class PDocTable extends AbstractPDocElement {
    private int nofColumns;
    private float[]columnWidths = null;
    private List<PDocCell> cells = new ArrayList<>();

    public PDocTable cell(String name, Consumer<PDocCell> cellConsumer) {
        PDocCell cell = new PDocCell();
        joinPDocElement(name, cell);
        this.cells.add(cell);
        cellConsumer.accept(cell);
        return this;
    }
    public int getNofColumns() {
        return nofColumns;
    }

    public void setNofColumns(int nofColumns) {
        this.nofColumns = nofColumns;
    }

    public Iterable<? extends PDocCell> getCells() {
        return this.cells;
    }

    public void setColumnWidth(float ...width) {
        this.columnWidths = width;
    }

    public float[] getColumnWidths() {
        return columnWidths;
    }
}
