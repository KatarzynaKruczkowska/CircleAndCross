package com.company;

import static com.company.Texts.PLAY_AGAIN;
import static com.company.Texts.WELCOME;

public class Main {

    public static void main(String[] args) {
        final IOManager ioManager = new IOManagerConsole();
        final GameManager gameManager = new GameManager(ioManager);
        ioManager.showMessage(WELCOME);
        boolean notEnd;
        do {
            try {
                gameManager.play();
            } catch (TooManyPlayersException error) {
                ioManager.showMessage("Too many players! Please play again with smaller amount.");
            } catch (TooLowPlayersException error) {
                ioManager.showMessage("Too low players! Please play again with bigger amount.");
            }
            notEnd = ioManager.getDecision(PLAY_AGAIN);
        } while (notEnd);
    }



}
