package com.company;

import java.util.Scanner;

import static com.company.Texts.*;
import static java.lang.String.*;

public class Main {

    public static final int MIN_BOARD_SIZE = 2;
    public static final int MAX_BOARD_SIZE = 9;
    public static final int MIN_BOARD_ID = 1;
    private static final char BAD_SIGN = '@';
    private static final char YES = 'T';
    private static final String FORMATED_SELECT = "%s %d";
    private static final String FORMATED_PLAYER_AND_CHOICE = "%s %s %n %s %s %d %s %d %n";
    private static final String FORMATED_PLAYER = "%s %s %s %n";
    private static final String FORMATED_WELCOME = "%s %s %n";

    private static final Scanner INPUT = new Scanner(System.in);
    private static boolean shouldPlayAgain;
    private static PlayerSignType player;

    private static final IOManager ioManager = new IOManagerConsole();

    public static void main(String[] args) {
        ioManager.showMessage(WELCOME);
        boolean notEnd = true;
        do {
            play();
            notEnd = ioManager.getDecision(AGAIN_PLAY);
        } while (notEnd);
    }

    public static void play() {
        int rowNumber = 0;
        int columnNumber = 0;

        shouldPlayAgain = true;
        player = PlayerSignType.O;

        ioManager.showMessage(PROVIDE_YOUR_NAME);
        final String name = ioManager.getName();
        ioManager.showMessage(format(FORMATED_WELCOME, HELLO, name));

        //ioManager.showMessage(PROVIDE_BOARD_SIZE_TEXT);
        //final int boardSize = getNumberFromUser(MIN_BOARD_SIZE, MAX_BOARD_SIZE);
        final int boardSize = ioManager.getBoardSize(MIN_BOARD_SIZE, MAX_BOARD_SIZE);
        ioManager.showMessage(format(FORMATED_SELECT, SELECTED, boardSize));

        final Board board = new Board(boardSize);

        player = changePlayer(player);
        do {
            ioManager.showBoard(board);
            System.out.printf(FORMATED_PLAYER, name, PLAYER, player);
            rowNumber = getRowNumber(MIN_BOARD_ID, boardSize);
            columnNumber = getColumnNumber(boardSize);
            System.out.printf(FORMATED_PLAYER_AND_CHOICE, PLAYER, player, SELECTED, ROW, rowNumber + 1, COLUMN, columnNumber + 1);

            if (board.insertSign(player, rowNumber, columnNumber)) {
                board.addSignValue(rowNumber, columnNumber);
                if (board.checkWinner(rowNumber, columnNumber)) {
                    ioManager.showBoard(board);
                    System.out.println(WINNER + player.printableSign);
                    shouldPlayAgain = false;
                }
                if (board.getCountOfEmptyField() == 0) {
                    ioManager.showBoard(board);
                    shouldPlayAgain = false;
                }
                player = changePlayer(player);
            } else {
                System.out.println(NOT_EMPTY_PLACE);
            }
        } while (shouldPlayAgain);
        System.out.println(END_OF_THE_GAME);
    }

    public static PlayerSignType changePlayer(PlayerSignType player) {
        if (player == PlayerSignType.O) {
            player = PlayerSignType.X;
        } else {
            player = PlayerSignType.O;
        }
        return player;
    }

    public static int getRowNumber(int min, int max) {
        System.out.println(PROVIDE_ROW_NUMBER);
        return getNumberFromUser(min, max) - 1;
    }

    public static int getNumberFromUser(int min, int max) {
        int result = 0;
        do {
            result = ioManager.getBoardSize();
            if (result < min || result > max) {
                System.out.println(WRONG_SIZE);
            }
        } while (result < min || result > max);
        return result;
    }

    public static int getColumnNumber(int max) {
        int result = 0;
        System.out.println(PROVIDE_COLUMN);
        do {
            result = readSign() - 'A' + 1;
            if (result < MIN_BOARD_ID || result > max) {
                System.out.println(WRONG_SIZE);
            }
            // obsługa esc
        } while (result < MIN_BOARD_ID || result > max);
        return result - 1;
    }

    public static char readSign() {
        final String inputText = INPUT.nextLine();
        if (inputText.length() != 1) {
            return BAD_SIGN;
        }
        return inputText.toUpperCase().charAt(0);
    }

}
