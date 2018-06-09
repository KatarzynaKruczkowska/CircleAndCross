package com.company;

public interface IOManager {

    public void showMessage(final String message);

    public int getBoardSize(final int min, final int max);

    public String getName();

    public Coordinates getCoordinates(final int minId, final int maxId);

    public boolean getDecision(final String message);

    public void showBoard(final Board board);

}
