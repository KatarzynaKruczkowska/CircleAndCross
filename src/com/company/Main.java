package com.company;

import java.util.Scanner;

import static com.company.Texts.*;
import static java.lang.String.*;

public class Main {

    public static final int MIN_BOARD_SIZE = 2;
    public static final int MAX_BOARD_SIZE = 9;
    private static final int MIN_BOARD_ID = 1;
    //  private static final char BAD_SIGN = '@';
    private static final String FORMATED_SELECT = "%s %d";
    private static final String FORMATED_PLAYER_AND_CHOICE = "%s %s %n %s %s %d %s %d %n";
    private static final String FORMATED_PLAYER = "%s %s %s %n";
    private static final String FORMATED_WELCOME = "%s %s %s %s %n";
    private static final String FORMATED_WINNER = "%s %s (%s) %n";

    // private static final Scanner INPUT = new Scanner(System.in);
    private static boolean shouldPlayAgain;
    private static Player player;

    private static final IOManager ioManager = new IOManagerConsole();

    public static void main(String[] args) {
        ioManager.showMessage(WELCOME);
        boolean notEnd = true;
        do {
            play();
            notEnd = ioManager.getDecision(PLAY_AGAIN);
        } while (notEnd);
    }

    public static void play() {
        int rowNumber = 0;
        int columnNumber = 0;

        shouldPlayAgain = true;
        // 1
        ioManager.showMessage(PLAYER_1 + PROVIDE_YOUR_NAME);
        String name = ioManager.getName();
        final Player player1 = new Player(name, PlayerSignType.X);
        ioManager.showMessage(format(FORMATED_WELCOME, HELLO, name, PLAYER, PlayerSignType.X));
        // 2
        ioManager.showMessage(PLAYER_2 + PROVIDE_YOUR_NAME);
        name = ioManager.getName();
        final Player player2 = new Player(name, PlayerSignType.O);
        ioManager.showMessage(format(FORMATED_WELCOME, HELLO, name, PLAYER, PlayerSignType.O));

        final int boardSize = ioManager.getBoardSize(MIN_BOARD_SIZE, MAX_BOARD_SIZE);
        ioManager.showMessage(format(FORMATED_SELECT, SELECTED, boardSize));

        final Board board = new Board(boardSize);
        player = player1;
        do {
            ioManager.showBoard(board);
            System.out.printf(FORMATED_PLAYER, player.getName(), PLAYER, player.getSign());

            Coordinates rowAndColumn = new Coordinates(rowNumber, columnNumber);
            rowAndColumn = ioManager.getCoordinates(MIN_BOARD_ID, boardSize);
            rowNumber = rowAndColumn.getRow();
            columnNumber = rowAndColumn.getColumn();

            System.out.printf(FORMATED_PLAYER_AND_CHOICE, PLAYER, player.getName(), SELECTED, ROW, rowNumber + 1, COLUMN, columnNumber + 1);

            if (board.insertSign(player.getSign(), rowNumber, columnNumber)) {
                board.addSignValue(rowNumber, columnNumber);
                if (board.checkWinner(rowNumber, columnNumber)) {
                    ioManager.showBoard(board);
                    ioManager.showMessage(format(FORMATED_WINNER, WINNER, player.getName(), player.getSign()));
                    shouldPlayAgain = false;
                }
                if (board.getCountOfEmptyField() == 0) {
                    ioManager.showBoard(board);
                    shouldPlayAgain = false;
                }
                if (player == player1) {
                    player = player2;
                } else {
                    player = player1;
                }
            } else {
                ioManager.showMessage(NOT_EMPTY_PLACE);
            }
        } while (shouldPlayAgain);
        ioManager.showMessage(END_OF_THE_GAME);
    }

}
