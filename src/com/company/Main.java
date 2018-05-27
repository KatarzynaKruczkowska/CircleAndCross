package com.company;

import java.util.Scanner;

import static com.company.Texts.*;

public class Main {

    public static final int MIN_BOARD_SIZE = 2;
    public static final int MAX_BOARD_SIZE = 9;
    private static final int MIN_BOARD_ID = 1;
    private static final char BAD_SIGN = '@';
    private static final char YES = 'T';
    private static final String FORMATED_SELECT = "%s %d";
    private static final String FORMATED_PLAYER_AND_CHOICE = "%s %s %n %s %s %d %s %s";

    private static final Scanner INPUT = new Scanner(System.in);
    private static boolean shouldPlayAgain;
    private static PlayerSignType player;

    private static final IOManager ioManager = new IOManagerConsole();

    public static void main(String[] args) {
        ioManager.showMessage(WELCOME);
        char notEnd = YES;
        do {
            play();
            System.out.println(AGAIN_PLAY);
            notEnd = readSign();
        } while (notEnd == YES);
    }

    public static void play() {
        int rowNumber = 0;
        int columnNumber = 0;

        shouldPlayAgain = true;
        player = PlayerSignType.O;

        final int boardSize = getPlayingFieldSize(MIN_BOARD_SIZE, MAX_BOARD_SIZE);
        System.out.printf(FORMATED_SELECT, SELECTED, boardSize);

        final Board board = new Board(boardSize);

        do {
            drawField(board);
            player = changePlayer(player);
            System.out.println(PLAYER + player);
            rowNumber = getRowNumber(MIN_BOARD_ID, boardSize);
            columnNumber = getColumnNumber(boardSize);
            System.out.printf(FORMATED_PLAYER_AND_CHOICE, PLAYER, player, SELECTED, ROW, rowNumber + 1, COLUMN, columnNumber + 1);

            if (board.insertSign(player, rowNumber, columnNumber)) {
                board.addSignValue(rowNumber, columnNumber);
                if (board.checkWinner(rowNumber, columnNumber)) {
                    drawField(board);
                    System.out.println(WINNER + player.printableSign);
                    shouldPlayAgain = false;
                }
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

    public static int getPlayingFieldSize(int min, int max) {
        System.out.println(PROVIDE_BOARD_SIZE_TEXT);
        return getNumberFromUser(min, max);
    }

    public static int getRowNumber(int min, int max) {
        System.out.println(PROVIDE_ROW_NUMBER);
        return getNumberFromUser(min, max) - 1;
    }

    public static int getNumberFromUser(int min, int max) {
        int result = 0;
        do {
            result = readNumber();
            if (result < min || result > max) {
                System.out.println(WRONG_SIZE);
            }
            // obsługa esc
        } while (result < min || result > max);
        return result;
    }

    public static int readNumber() {
        int result = MIN_BOARD_SIZE - 1;
        try {
            result = Integer.parseInt(INPUT.nextLine());
        } catch (NumberFormatException error) {
            System.out.println(WRONG_SIZE);
        } catch (Exception error) {
            error.printStackTrace();
        }
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

    public static void drawField(final Board board) {

        System.out.println();
        final StringBuilder firstLine = new StringBuilder("     ");     // 3 spacje na początku na kolumnę numerów wierszy

        for (int i = 0; i < board.getSize(); i++) {
            firstLine.append((char) ('a' + i)).append(" ! ");
        }
        System.out.println(firstLine);

        final StringBuilder horizontalFullLine = new StringBuilder("   ");
        for (int i = 0; i < board.getSize(); i++) {
            horizontalFullLine.append("----");
        }
        horizontalFullLine.append("-");

        final StringBuilder lineWithData = new StringBuilder();
        for (int i = 0; i < board.getSize(); i++) {
            System.out.println(horizontalFullLine);
            lineWithData.setLength(0);
            lineWithData.append(" ").append(i + 1).append(" |");
            for (int j = 0; j < board.getSize(); j++) {
                lineWithData.append(" ").append(board.getSignText(i, j)).append(" |");
            }
            System.out.println(lineWithData);

        }
        //linia pozioma
        System.out.println(horizontalFullLine);
    }

}
