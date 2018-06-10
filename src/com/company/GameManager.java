package com.company;

import java.util.Random;

import static com.company.Main.ioManager;
import static com.company.Texts.*;
//import static com.company.Texts.END_OF_THE_GAME;
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

    private static void play() {

        boolean shouldPlayAgain = true;
        String tableOfPlayers[][] = new String[NUMBER_OF_PLAYERS][2];

        // 1
        ioManager.showMessage(format(FORMATTED_PROVIDE_NAME, PLAYER, 1, PROVIDE_YOUR_NAME));
        String name = ioManager.getName();
        PlayerSignType sign = PlayerSignType.O;
        if (getRandomPlayerIndex(NUMBER_OF_PLAYERS) == 0) {
            sign = PlayerSignType.X;
        }

//tylko po co mi tablica skoro mam imię i znak przypisane do obiektu Player (1 i 2)
        final Player player1 = new Player(name, sign);
        tableOfPlayers[0][0] = name;
        tableOfPlayers[0][1] = sign.printableSign;

        ioManager.showMessage(format(FORMATED_WELCOME, HELLO, player1.getName(), PLAYING_AS, player1.getSign()));

        // 2
        ioManager.showMessage(format(FORMATTED_PROVIDE_NAME, PLAYER, 2, PROVIDE_YOUR_NAME));
        name = ioManager.getName();
        if (sign == PlayerSignType.X) {
            sign = PlayerSignType.O;
        } else {
            sign = PlayerSignType.X;
        }
        final Player player2 = new Player(name, sign);
        tableOfPlayers[1][0] = name;
        tableOfPlayers[1][1] = sign.printableSign;

        ioManager.showMessage(format(FORMATED_WELCOME, HELLO, player2.getName(), PLAYING_AS, player2.getSign()));

        final int boardSize = ioManager.getBoardSize(MIN_BOARD_SIZE, MAX_BOARD_SIZE);
        ioManager.showMessage(format(FORMATED_SELECT, SELECTED, boardSize));
        final Board board = new Board(boardSize);
        Player currentPlayer = player1;
        if (getRandomPlayerIndex(NUMBER_OF_PLAYERS) == 1) {
            currentPlayer = player2;
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
                if (currentPlayer == player1) {
                    currentPlayer = player2;
                } else {
                    currentPlayer = player1;
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
