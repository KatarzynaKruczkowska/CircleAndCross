package com.company;

import java.util.Random;

import static com.company.Main.ioManager;
import static com.company.Texts.*;
import static java.lang.String.format;

public class GameManager {

    public static final int MIN_BOARD_SIZE = 2;
    public static final int MAX_BOARD_SIZE = 9;
    private static final int MIN_BOARD_ID = 1;
    private static final int NUMBER_OF_PLAYERS = 2;
    private static final String FORMATED_SELECT = "%s %d \n";
    private static final String FORMATED_CHOICE = "%s %s %d %s %d \n";
    private static final String FORMATED_PLAYER = "%s (%s) \n";
    private static final String FORMATED_WINNER = "%s %s (%s) \n";
    private static final String FORMATTED_PROVIDE_NAME = "%s %d %s \n";
    private static final String FORMATED_WELCOME = "%s %s %s %s \n";

    public static void play() {

        boolean shouldPlayAgain = true;
        final Player players[] = new Player[NUMBER_OF_PLAYERS];
        final PlayerSignType sign[] = new PlayerSignType[NUMBER_OF_PLAYERS];

        if (getRandomPlayerIndex(NUMBER_OF_PLAYERS) == 0) {
            sign[0] = PlayerSignType.X;
            sign[1] = PlayerSignType.O;
        } else {
            sign[0] = PlayerSignType.O;
            sign[1] = PlayerSignType.X;
        }

        for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
            ioManager.showMessage(format(FORMATTED_PROVIDE_NAME, PLAYER, i + 1, PROVIDE_YOUR_NAME));
            String name = ioManager.getName();

            players[i] = new Player(name, sign[i]);

            ioManager.showMessage(format(FORMATED_WELCOME, HELLO, players[i].getName(), PLAYING_AS, players[i].getSign()));

        }

        final int boardSize = ioManager.getBoardSize(MIN_BOARD_SIZE, MAX_BOARD_SIZE);
        ioManager.showMessage(format(FORMATED_SELECT, SELECTED, boardSize));
        final Board board = new Board(boardSize);

        Player currentPlayer = players[0];
        if (getRandomPlayerIndex(NUMBER_OF_PLAYERS) == 1) {
            currentPlayer = players[1];
        }

        ioManager.showMessage(DRAWN_PLAYER);
        ioManager.showMessage(format(FORMATED_PLAYER, currentPlayer.getName(), currentPlayer.getSign()));

        do {
            ioManager.showBoard(board);
            ioManager.showMessage(format(FORMATED_PLAYER, currentPlayer.getName(), currentPlayer.getSign()));

            final Coordinates coordinates = ioManager.getCoordinates(MIN_BOARD_ID, boardSize);

            System.out.printf(FORMATED_CHOICE, SELECTED, ROW, coordinates.row + 1, COLUMN, coordinates.column + 1);

            if (board.insertSign(currentPlayer.getSign(), coordinates.row, coordinates.column)) {
                board.addSignValue(coordinates.row, coordinates.column);
                if (board.checkWinner(coordinates.row, coordinates.column)) {
                    ioManager.showBoard(board);
                    ioManager.showMessage(format(FORMATED_WINNER, WINNER, currentPlayer.getName(), currentPlayer.getSign()));
                    shouldPlayAgain = false;
                }
                if (board.getCountOfEmptyField() == 0) {
                    ioManager.showBoard(board);
                    shouldPlayAgain = false;
                }
                if (currentPlayer == players[0]) {
                    currentPlayer = players[1];
                } else {
                    currentPlayer = players[0];
                }
            } else {
                ioManager.showMessage(NOT_EMPTY_PLACE);
            }
        } while (shouldPlayAgain);
        ioManager.showMessage(END_OF_THE_GAME);
    }

    public static int getRandomPlayerIndex(final int playerCount) {
        final Random random = new Random();
        return random.nextInt(playerCount);
    }
}
