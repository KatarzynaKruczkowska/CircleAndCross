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
        if (row >= 0 && row < size
                && column >= 0 && column < size
                && data[row][column] == PlayerSignType.EMPTY) {
            data[row][column] = sign;

            return true;
        }
        return false;
    }

    public String getSignText(final int row, final int column) {
        return data[row][column].printableSign;
    }

    public boolean isSignEqual(final PlayerSignType sign, final int row, final int column) {
        return data[row][column] == sign;
    }

    public int countDiagonalXxValue() {
        int intForCheck = 0;

        for (int i = 0; i < data.length; i++) {
            if (data[i][i] == PlayerSignType.X) {
                intForCheck += 1;
            } else if (data[i][i] == PlayerSignType.O) {
                intForCheck -= 1;
            }
        }
        return intForCheck;
    }

    public int countDiagonalYyValue() {
        int intForCheck = 0;

        for (int i = 0; i < data.length; i++) {
            intForCheck += data[i][data.length - i - 1].intValue;
        }
        return intForCheck;
    }
}