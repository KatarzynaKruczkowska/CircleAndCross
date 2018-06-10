package com.company;

import static com.company.Texts.*;

public class Main {

    public static final IOManager ioManager = new IOManagerConsole();
    private static final GameManager gameManager = new GameManager();

    public static void main(String[] args) {
        ioManager.showMessage(WELCOME);
        boolean notEnd;
        do {
            gameManager.play();
            notEnd = ioManager.getDecision(PLAY_AGAIN);
        } while (notEnd);
    }



}
