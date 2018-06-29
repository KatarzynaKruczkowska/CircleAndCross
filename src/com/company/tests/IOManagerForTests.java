package com.company.tests;

import com.company.Board;
import com.company.Coordinates;
import com.company.IOManager;

public class IOManagerForTests implements IOManager {

    @Override
    public void showMessage(String message) {

    }

    @Override
    public int getBoardSize(int min, int max) {
        return 3;
    }

    @Override
    public String getName() {
        return "KUBA";
    }

    @Override
    public Coordinates getCoordinates(int minId, int maxId) {
        return new Coordinates(1, 1);
    }

    @Override
    public boolean getDecision(String message) {
        return false;
    }

    @Override
    public void showBoard(Board board) {

    }
}
