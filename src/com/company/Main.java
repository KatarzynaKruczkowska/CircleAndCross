package com.company;

import java.io.IOException;
import java.util.Scanner;

import static java.lang.Math.abs;

import static com.company.PlayerSignType.X;
import static com.company.Texts.*;
import static java.lang.Math.signum;

public class Main {

    public static final int MIN_BOARD_SIZE = 1;
    public static final int MAX_BOARD_SIZE = 9;
    private static final int MIN_BOARD_ID = 1;
    private static final char BAD_SIGN = '@';
    private static final char YES = 'T';
    private static final int ItIsO = 2; //oznacza O w tabeli//
    private static final int ItIsX = 1; //oznacza X w tabeli//
    private static final String WIN_OUTPUT_FORMAT = "%s %s %d\n";

    private static final Scanner INPUT = new Scanner(System.in);
    private static int boardSize = 0;
    private static boolean shouldPlayAgain;


    public static void main(String[] args) throws IOException {
        System.out.println(WELCOME);
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

        boardSize = getPlayingFieldSize(MAX_BOARD_SIZE);
        System.out.println(SELECTED + boardSize);

        final PlayerSignType[][] board = new PlayerSignType[boardSize][boardSize];
//        for (PlayerSignType[] signRow : board) {
//            for (PlayerSignType sign : signRow) {
//                sign = PlayerSignType.EMPTY;
//            }
//        }

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                board[i][j] = PlayerSignType.EMPTY;
            }
        }

        drawField(boardSize, board);
        do {
            rowNumber = getRowNumber(boardSize);
            columnNumber = getColumnNumber(boardSize);
            System.out.println(SELECTED + rowNumber + "/" + columnNumber);  //czy to zmieniać - jest numer powinna być litera
            if (!putToTheBoard(rowNumber, columnNumber, X, board)) {
                continue;
            }

            drawField(boardSize, board);
            //sprawdzenie
            shouldPlayAgain = verifyIfContinue(boardSize, board);

            //ruch "O"
            //drawField(boardSize,board);


        } while (shouldPlayAgain);
        System.out.println(END_OF_THE_GAME);
    }

    private static boolean putToTheBoard(int rowNumber, int columnNumber, PlayerSignType oneSing, PlayerSignType[][] board) {
        if (board[rowNumber - 1][columnNumber - 1] == PlayerSignType.EMPTY) {
            board[rowNumber - 1][columnNumber - 1] = oneSing;
        } else {
            System.out.println(NOT_EMPTY_PLACE);
            return false;
        }
        return true;
    }

    private static boolean verifyIfContinue(int boardSize, final PlayerSignType[][] board) {
        return verifyIfRowIsNotFull(boardSize, board)
                && verifyIfColumnIsNotFull(boardSize, board)
                && verifyIfDiagonalXxIsNotFull(boardSize, board)
                && verifyIfDiagonalYyIsNotFull(boardSize, board);
    }

    public static boolean verifyIfDiagonalYyIsNotFull(int boardSize, final PlayerSignType[][] board) {
        int intForCheck = 0;

        for (int i = 0; i < board.length; i++) {
            switch (board[i][boardSize - i - 1]) {
                case X:
                    intForCheck += 1;
                case O:
                    intForCheck -= 1;
            }
        }

        if (abs(intForCheck) == boardSize) {
            announcementOfTheWinner(intForCheck, DIAGONAL_JJ, 2);  //jak numerować przekątne?
            return false;
        }
        return true;

    }

    public static boolean verifyIfDiagonalXxIsNotFull(int boardSize, final PlayerSignType[][] board) {
        int intForCheck = 0;

        for (int i = 0; i < board.length; i++) {
            switch (board[i][i]) {
                case X:
                    intForCheck += 1;
                case O:
                    intForCheck -= 1;
            }
        }

        if (abs(intForCheck) == boardSize) {
            announcementOfTheWinner(intForCheck, DIAGONAL_II, 1);  //jak numerować przekątne?
            return false;
        }
        return true;

    }

    public static boolean verifyIfColumnIsNotFull(int boardSize, final PlayerSignType[][] board) {
        int[] tabForCheck = new int[boardSize];
        for (int i = 0; i < tabForCheck.length; i++) {
            tabForCheck[i] = 0;
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                switch (board[j][i]) {
                    case X:
                        tabForCheck[i] += 1;
                    case O:
                        tabForCheck[i] -= 1;
                }
            }
        }

        for (int i = 0; i < tabForCheck.length; i++) {
            if (abs(tabForCheck[i]) == boardSize) {
                announcementOfTheWinner(tabForCheck[i], COLUMN, (i + 1));
                return false;
            }
        }
        return true;
    }

    public static boolean verifyIfRowIsNotFull(int boardSize, final PlayerSignType[][] board) {
        int[] tabForCheck = new int[boardSize];
        for (int i = 0; i < tabForCheck.length; i++) {
            tabForCheck[i] = 0;
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                switch (board[j][i]) {
                    case X:
                        tabForCheck[i] += 1;
                    case O:
                        tabForCheck[i] -= 1;
                }
            }
        }

        for (int i = 0; i < tabForCheck.length; i++) {
            if (abs(tabForCheck[i]) == boardSize) {
                announcementOfTheWinner(tabForCheck[i], ROW, (i + 1));
                return false;
            }
        }
        return true;
    }

    public static void announcementOfTheWinner(int valueOfRow, String rowColDiagName, int rowColDiagNumber) {
        if (valueOfRow > 0) {
            System.out.printf(WIN_OUTPUT_FORMAT, WINNER_X, rowColDiagName, rowColDiagNumber);
        } else {
            System.out.printf(WIN_OUTPUT_FORMAT, WINNER_O, rowColDiagName, rowColDiagNumber);
        }
    }

    public static int getPlayingFieldSize(int max) {
        System.out.println(PROVIDE_BOARD_SIZE_TEXT);
        return getNumberFromUser(max);
    }

    public static int getRowNumber(int max) {
        System.out.println(PROVIDE_ROW_NUMBER);
        return getNumberFromUser(max);
    }

    public static int getNumberFromUser(int max) {
        int result = 0;
        do {
            result = readNumber();
            if (result < MIN_BOARD_ID || result > max) {
                System.out.println(WRONG_BOARD_SIZE);
            }
            // obsługa esc
        } while (result < MIN_BOARD_ID || result > max);
        return result;
    }

    public static int readNumber() {
        int result = MIN_BOARD_SIZE - 1;
        try {
            result = Integer.parseInt(INPUT.nextLine());
        } catch (NumberFormatException error) {
            System.out.println(WRONG_BOARD_SIZE);
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
                System.out.println(WRONG_BOARD_SIZE);
            }
            // obsługa esc
        } while (result < MIN_BOARD_ID || result > max);
        return result;

    }

    public static char readSign() {
        String inputText = INPUT.nextLine();
        if (inputText.length() != 1) {
            return BAD_SIGN;
        }
        return inputText.toUpperCase().charAt(0);
    }

    public static void drawField(int boardSize, final PlayerSignType[][] board) {

        System.out.println("");
        final StringBuilder firstLine = new StringBuilder("     ");     // 3 spacje na początku na kolumnę numerów wierszy

        for (int i = 0; i < boardSize; i++) {
            firstLine.append((char) ('a' + i)).append(" ! ");
        }
        System.out.println(firstLine.toString());

        final StringBuilder horizontalFullLine = new StringBuilder("   ");
        for (int i = 0; i < boardSize; i++) {
            horizontalFullLine.append("----");
        }
        horizontalFullLine.append("-");

        for (int i = 0; i < boardSize; i++) {
            System.out.println(horizontalFullLine.toString());
            // linia z danymi z tabeli
            //String s = " ";
            final StringBuilder lineWithData = new StringBuilder(" ");
            lineWithData.append((i + 1) + " |");
            for (int j = 0; j < boardSize; j++) {
                if (board[i][j] == PlayerSignType.EMPTY) {
                    lineWithData.append("   |");
                } else {
                    lineWithData.append(" ").append(board[i][j]).append(" |");
                }
            }
            //s = lineWithData.toString();
            System.out.println(lineWithData.toString());

        }
        //linia pozioma
        System.out.println(horizontalFullLine.toString());
    }
}
