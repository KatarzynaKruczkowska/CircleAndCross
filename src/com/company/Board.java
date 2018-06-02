package com.company;

import static java.lang.Math.abs;

public class Board {
    private final int size;
    private final PlayerSignType[][] data;
    private int rowValue[];
    private int columnValue[];
    private int diagXxValue;
    private int diagYyValue;
    private int countOfEmptyField;

    public Board(final int size) {
        this.size = size;
        data = new PlayerSignType[size][size];
        this.rowValue = new int[size];
        this.columnValue = new int[size];
        this.diagXxValue = 0;
        this.diagYyValue = 0;
        this.countOfEmptyField = size * size;
        clear();
    }

    public int getSize() {
        return size;
    }

    public int getCountOfEmptyField() {
        return countOfEmptyField;
    }

    private void clear() {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                data[i][j] = PlayerSignType.EMPTY;
            }
            rowValue[i] = 0;
            columnValue[i] = 0;
        }
        diagXxValue = 0;
        diagYyValue = 0;
    }

    public boolean insertSign(final PlayerSignType sign, final int row, final int column) {
        if (row >= 0 && row < size
                && column >= 0 && column < size
                && data[row][column] == PlayerSignType.EMPTY) {
            data[row][column] = sign;
            countOfEmptyField -= 1;
            return true;
        }
        return false;
    }

    public void addSignValue(final int row, final int column) {
        rowValue[row] += data[row][column].intValue;
        columnValue[column] += data[row][column].intValue;
        if (row == column) {
            diagXxValue += data[row][column].intValue;
        }
        if (column == data.length - row - 1) {
            diagYyValue += data[row][column].intValue;
        }
    }

    public boolean checkWinner(final int row, final int column) {
        return abs(rowValue[row]) == size
                || abs(columnValue[column]) == size
                || abs(diagXxValue) == size
                || abs(diagYyValue) == size;
    }

    public String getSignText(final int row, final int column) {
        return data[row][column].printableSign;
    }

}