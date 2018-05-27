package com.company;

public class IOManagerConsole implements IOManager {

    @Override
    public void showMessage(final String message) {
        System.out.println(message);
    }

    @Override
    public int getBoardSize() {
        return 0;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Coordinates getCoordinates(int maxSize) {
        return null;
    }

    @Override
    public boolean getDecision(String message) {
        return false;
    }

    @Override
    public void showBoard(Board board) {

    }
}
