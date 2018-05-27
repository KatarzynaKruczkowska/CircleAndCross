package com.company;

public class Coordinates {
    private final int row;
    private final int column;

    public Coordinates(final int row, final int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
