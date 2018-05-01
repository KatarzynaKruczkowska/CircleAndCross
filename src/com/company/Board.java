package com.company;

public class Board {
    private final int size;
    private final PlayerSignType[][] data;

    public Board(final int size) {
        this.size = size;
        data = new PlayerSignType[size][size];
        clear();
    }

    private void clear() {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                data[i][j] = PlayerSignType.EMPTY;
            }
        }
    }

    public boolean insertSign(final PlayerSignType sign, final int row, final int column) {
        if (row > 0 && row < size
                && column > 0 && column < size
                && data[row][column] == PlayerSignType.EMPTY) {
            data[row][column] = sign;
            return true;
        }
        return false;
    }
}