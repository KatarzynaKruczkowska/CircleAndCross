package com.company;

import java.io.IOException;
import java.util.Scanner;

import static com.company.Texts.*;

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
        for (PlayerSignType[] signRow : board) {
            for (PlayerSignType sign : signRow) {
                sign = PlayerSignType.EMPTY;
            }
        }
        drawField(boardSize, board);
        do {
            rowNumber = getRowNumber(boardSize);
            columnNumber = getColumnNumber(boardSize);
            //przenieść do funkcji załaduj do tablicy
            System.out.println(SELECTED + rowNumber + "/" + columnNumber);
            if (board[rowNumber - 1][columnNumber - 1] == PlayerSignType.EMPTY) {
                board[rowNumber - 1][columnNumber - 1] = PlayerSignType.X;
            } else {
                System.out.println(NOT_EMPTY_PLACE);
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

    private static boolean verifyIfContinue(int columnRowCount, final PlayerSignType[][] board) {
        return verifyIfRowIsNotFull(columnRowCount, board)
                && verifyIfColumnIsNotFull(columnRowCount, board)
                && verifyIfDiagonalXxIsNotFull(columnRowCount, board)
                && verifyIfDiagonalYyIsNotFull(columnRowCount, board);
    }

    public static boolean verifyIfDiagonalYyIsNotFull(int boardSize, final PlayerSignType[][] board) {
        int[][] checking = new int[1][2];
        checking[0][0] = 0;
        checking[0][1] = 0;

        for (int i = 0; i < board.length; i++) {
            if (board[i][boardSize - i - 1] == PlayerSignType.X) checking[0][0] += 1;   // X
            if (board[i][boardSize - i - 1] == PlayerSignType.O) checking[0][1] += 1;   // O
        }

        if (checking[0][0] == boardSize) {
            System.out.printf(WIN_OUTPUT_FORMAT, WINNER_X, DIAGONAL_JJ);
            return false;
        }
        if (checking[0][1] == boardSize) {
            System.out.printf(WIN_OUTPUT_FORMAT, WINNER_O, DIAGONAL_JJ);
            return false;
        }
        return true;

    }

    public static boolean verifyIfDiagonalXxIsNotFull(int boardSize, final PlayerSignType[][] board) {
        final int[][] checking = new int[1][2];
        checking[0][0] = 0;
        checking[0][1] = 0;

        for (int i = 0; i < board.length; i++) {
            if (board[i][i] == ItIsX) checking[0][0] += 1;   // X
            if (board[i][i] == ItIsO) checking[0][1] += 1;   // O
        }

        if (checking[0][0] == boardSize) {
            System.out.printf(WIN_OUTPUT_FORMAT, WINNER_X, DIAGONAL_II);
            return false;
        }
        if (checking[0][1] == boardSize) {
            System.out.printf(WIN_OUTPUT_FORMAT, WINNER_O, DIAGONAL_II);
            return false;
        }
        return true;

    }

    public static boolean verifyIfColumnIsNotFull(int boardSize, final PlayerSignType[][] board) {
        int[][] checking = new int[boardSize][2];
        for (int i = 0; i < checking.length; i++) {
            for (int j = 0; j < 2; j++) {
                checking[i][j] = 0;
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[j][i] == ItIsX) checking[i][0] += 1;   // X
                if (board[j][i] == ItIsO) checking[i][1] += 1;   // O
            }
        }

        for (int i = 0; i < checking.length; i++) {
            if (checking[i][0] == boardSize) {
                System.out.printf(WIN_OUTPUT_FORMAT, WINNER_X, COLUMN, i + 1);
                return false;
            }
            if (checking[i][1] == boardSize) {
                System.out.printf(WIN_OUTPUT_FORMAT, WINNER_O, COLUMN, i + 1);
                return false;
            }
        }
        return true;
    }

    public static boolean verifyIfRowIsNotFull(int boardSize, final PlayerSignType[][] board) {
        int[][] checking = new int[boardSize][2];
        for (int i = 0; i < checking.length; i++) {
            for (int j = 0; j < 2; j++) {
                checking[i][j] = 0;
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == ItIsX) checking[i][0] += 1;   // X
                if (board[i][j] == ItIsO) checking[i][1] += 1;   // O
            }
        }

        for (int i = 0; i < checking.length; i++) {
            if (checking[i][0] == boardSize) {
                System.out.printf(WIN_OUTPUT_FORMAT, WINNER_X, ROW, i + 1);
//                System.out.println(WINNER_X + ROW + (i + 1));
                return false;
            }
            if (checking[i][1] == boardSize) {
                System.out.printf(WIN_OUTPUT_FORMAT, WINNER_O, ROW, i + 1);
                return false;
            }
        }
        return true;
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

    public static void drawField(int rowCount, final PlayerSignType[][] board) {

        System.out.println("");
        StringBuilder firstLine = new StringBuilder("     ");     // 3 spacje na początku na kolumnę numerów wierszy

        for (int i = 0; i < rowCount; i++) {
            firstLine.append((char) ('a' + i)).append(" ! ");
        }
        System.out.println(firstLine.toString());

        StringBuilder horizontalFullLine = new StringBuilder("   ");
        for (int i = 0; i < rowCount * 4 + 1; i++) {
            horizontalFullLine.append("-");
        }

        for (int i = 0; i < rowCount; i++) {
            System.out.println(horizontalFullLine);
            // linia z danymi z tabeli
            StringBuilder lineWithData = new StringBuilder(" ").append(i + 1).append(" |");
            for (int j = 0; j < rowCount; j++) {
                switch (board[i][j]) {
                    case EMPTY:
                        lineWithData.append("   |");
                        break;
                    default:
                        lineWithData.append(" ").append(board[i][j]).append(" |");
                }
            }

            System.out.println(lineWithData);
        }
        //linia pozioma
        System.out.println(horizontalFullLine);
    }
}
