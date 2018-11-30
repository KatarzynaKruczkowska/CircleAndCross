package com.company;

public enum PlayerSignType {
    X("X", 1),
    O("O", -1),
    EMPTY(" ", 0);

    public final String printableSign;
    public final int intValue;

    PlayerSignType(final String printableSign, final int intValue) {
        this.printableSign = printableSign;
        this.intValue = intValue;
    }
}
