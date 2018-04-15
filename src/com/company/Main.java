package com.company;

import java.io.IOException;
import java.util.Scanner;

import static com.company.Texts.*;

public class Main {

    public static final int MIN_BOARD_SIZE = 1;
    public static final int MAX_BOARD_SIZE = 9;
    private static final int MIN_BOARD_ID = 1;
    private static final char BAD_SIGN = '@';

    private static final Scanner INPUT = new Scanner(System.in);
    private static boolean shouldPlayAgain;


    public static void main(String[] args) throws IOException {
        System.out.println(WELCOME);
        char theEnd = 'T';
        do {
            play();
            System.out.println(AGAIN_PLAY);
            theEnd = readSign();
        } while (theEnd == 'T');
    }

    public static void play() {
        int rowNumber = 0;
        int columnNumber = 0;
        int columnRowCount = 0;
        shouldPlayAgain = true;

        columnRowCount = getPlayingFieldSize(MAX_BOARD_SIZE);
        System.out.println(CHOOSED + columnRowCount);

        int[][] tab = new int[columnRowCount][columnRowCount];
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab.length; j++) tab[i][j] = 0;
        }
        drawField(columnRowCount, tab);
        do {
            // X=>1 O=>2 default=>0

            rowNumber = getRowNumber(columnRowCount);
            columnNumber = getColumnNumber(columnRowCount);
            //przenieść do funkcji załaduj do tablicy
            System.out.println(CHOOSED + rowNumber + "/" + columnNumber);
            if (tab[rowNumber - 1][columnNumber - 1] == 0) {
                tab[rowNumber - 1][columnNumber - 1] = 1;
            } else {
                System.out.println(NOT_EMPTY_PLACE);
                continue;
            }
            drawField(columnRowCount, tab);
            //sprawdzenie
            shouldPlayAgain = verifyIfContinue(columnRowCount, tab);

            //ruch "O"
            //drawField(columnRowCount,tab);


        } while (shouldPlayAgain);
    }

    public static boolean verifyIfContinue(int columnRowCount, int[][] tab) {
        if (verifyIfRowIsNotFull(columnRowCount, tab)) {
            if (verifyIfColumnIsNotFull(columnRowCount, tab)) {
                if (verifyIfDiagonal_II_IsNotFull(columnRowCount, tab)) {
                    if (verifyIfDiagonal_JJ_IsNotFull(columnRowCount, tab)) {
                        return (true);
                    }
                }
            }
        }
        System.out.println(END_OF_THE_GAME);
        return (false);
    }
    public static boolean verifyIfDiagonal_JJ_IsNotFull(int columnRowCount, int[][] tab) {
        int[][] checking = new int[1][2];
        checking[0][0] = 0;
        checking[0][1] = 0;

        for (int i = 0; i < tab.length; i++) {
            if (tab[i][columnRowCount-i-1] == 1) checking[0][0] += 1;   // X
            if (tab[i][columnRowCount-i-1] == 2) checking[0][1] += 1;   // O
        }

        if (checking[0][0] == columnRowCount) {
            System.out.println(WINNER_X + DIAGONAL_JJ);
            return (false);
        }
        if (checking[0][1] == columnRowCount) {
            System.out.println(WINNER_O + DIAGONAL_JJ);
            return (false);
        }
        return (true);

    }
    public static boolean verifyIfDiagonal_II_IsNotFull(int columnRowCount, int[][] tab) {
        int[][] checking = new int[1][2];
        checking[0][0] = 0;
        checking[0][1] = 0;

        for (int i = 0; i < tab.length; i++) {
            if (tab[i][i] == 1) checking[0][0] += 1;   // X
            if (tab[i][i] == 2) checking[0][1] += 1;   // O
        }

        if (checking[0][0] == columnRowCount) {
            System.out.println(WINNER_X + DIAGONAL_II);
            return (false);
        }
        if (checking[0][1] == columnRowCount) {
            System.out.println(WINNER_O + DIAGONAL_II);
            return (false);
        }
        return (true);

    }
    public static boolean verifyIfColumnIsNotFull(int columnRowCount, int[][] tab) {
        int[][] checking = new int[columnRowCount][2];
        for (int i = 0; i < checking.length; i++) {
            for (int j = 0; j < 2; j++) {
                checking[i][j] = 0;
            }
        }

        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab.length; j++) {
                if (tab[j][i] == 1) checking[i][0] += 1;   // X
                if (tab[j][i] == 2) checking[i][1] += 1;   // O
            }
        }

        for (int i = 0; i < checking.length; i++) {
            if (checking[i][0] == columnRowCount) {
                System.out.println(WINNER_X + COLUMN + (i + 1));
                return (false);
            }
            if (checking[i][1] == columnRowCount) {
                System.out.println(WINNER_O + COLUMN + (i + 1));
                return (false);
            }
        }
        return (true);
    }
    public static boolean verifyIfRowIsNotFull(int columnRowCount, int[][] tab) {
        int[][] checking = new int[columnRowCount][2];
        for (int i = 0; i < checking.length; i++) {
            for (int j = 0; j < 2; j++) {
                checking[i][j] = 0;
            }
        }

        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab.length; j++) {
                if (tab[i][j] == 1) checking[i][0] += 1;   // X
                if (tab[i][j] == 2) checking[i][1] += 1;   // O
            }
        }

        for (int i = 0; i < checking.length; i++) {
            if (checking[i][0] == columnRowCount) {
                System.out.println(WINNER_X + ROW + (i + 1));
                return (false);
            }
            if (checking[i][1] == columnRowCount) {
                System.out.println(WINNER_O + ROW + (i + 1));
                return (false);
            }
        }
        return (true);
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

    public static void drawField(int rowCount, int[][] tab2) {

        System.out.println("");
        String firstLine = "     ";     // 3 spacje na początku na kolumnę numerów wierszy

        for (int i = 0; i < rowCount; i++) firstLine += (char) ('a' + i) + " ! ";
        System.out.println(firstLine);

        String horizontalFullLine = "   ";
        for (int i = 0; i < rowCount * 4 + 1; i++) horizontalFullLine += "-";

        for (int i = 0; i < rowCount; i++) {
            System.out.println(horizontalFullLine);
            // linia z danymi z tabeli
            String lineWithData = " " + (i + 1) + " |";
            for (int j = 0; j < rowCount; j++) {
                switch (tab2[i][j]) {
                    case 1:
                        lineWithData += " X |";
                        break;
                    case 2:
                        lineWithData += " O |";
                        break;
                    default:
                        lineWithData += "   |";
                }
            }

            System.out.println(lineWithData);
        }
        //linia pozioma
        System.out.println(horizontalFullLine);
    }
}
