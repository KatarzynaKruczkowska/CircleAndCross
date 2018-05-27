package com.company;

public interface IOManager {

    public void showMessage(final String message);

    public int getBoardSize();

    public String getName();

    public Coordinates getCoordinates(final int maxSize);

    public boolean getDecision(final String message);

    public void showBoard(final Board board);

}
