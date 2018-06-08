package com.company;

import java.util.Scanner;

import static com.company.Texts.*;
import static java.lang.String.format;

public class IOManagerConsole implements IOManager {

    private final Scanner INPUT = new Scanner(System.in);
    private static final String FORMATED_PROVIDE_NAME = "%s %s %n";

    @Override
    public void showMessage(final String message) {
        System.out.println(message);
    }

    @Override
    public int getBoardSize(final int min, final int max) {
        showMessage(PROVIDE_BOARD_SIZE_TEXT);
        int result = 0;
        do {
            result = getNumber();
            if (result < min || result > max) {
                showMessage(WRONG_SIZE);
            }
        } while (result < min || result > max);
        return result;
    }

    public int getNumber() {
        int result = 0;
        try {
            result = Integer.parseInt(INPUT.nextLine());
        } catch (NumberFormatException error) {
            showMessage(WRONG_FORMAT);
        } catch (Exception error) {
            error.printStackTrace();
        }
        return result;
    }

    @Override
    public String getName(final String player) {
        showMessage(format(FORMATED_PROVIDE_NAME, player, PROVIDE_YOUR_NAME));
        String result = "";
        Scanner INPUT = new Scanner(System.in);
        try {
            result = INPUT.nextLine();  //next da tylko pierwsze słowo, nextLine odczyta wszystko przed enterem
        } catch (Exception error) {
            error.printStackTrace();
        }
        return result;
    }

    @Override
    public Coordinates getCoordinates(final int minId, final int maxId) {

        int row = 1;
        int column = 1;
        showMessage(PROVIDE_ROW_NUMBER);
        do {
            row = getNumber();
            if (row < minId || row > maxId) {
                showMessage(WRONG_SIZE);
            }
        } while (row < minId || row > maxId);

        showMessage(PROVIDE_COLUMN);
        final String inputText = INPUT.nextLine();
        do {

            if (inputText.length() != 1) {
                showMessage(WRONG_SIZE);
                continue;
            }
            column = inputText.toUpperCase().charAt(0) - 'A' + 1;
            if (column < minId || column > maxId) {
                showMessage(WRONG_SIZE);
            }
            // obsługa esc
        } while (column < minId || column > maxId);
        Coordinates coordinates = new Coordinates(row - 1, column - 1);
        return coordinates;
        //return null; //jak przypisać row i column? i jak zrobic return?
    }

    @Override
    public boolean getDecision(String message) {
        showMessage(message);
        showMessage(TAKE_DECISION);
        showMessage("1 - " + DECISION_YES);
        showMessage("2 - " + DECISION_NO);
        int result = getNumber();
        return result == 1;
    }


    @Override
    public void showBoard(Board board) {
        showMessage("");
        final StringBuilder firstLine = new StringBuilder("     ");     // 3 spacje na początku na kolumnę numerów wierszy

        for (int i = 0; i < board.getSize(); i++) {
            firstLine.append((char) ('a' + i)).append(" ! ");
        }
        showMessage(firstLine.toString());

        final StringBuilder horizontalFullLine = new StringBuilder("   ");
        for (int i = 0; i < board.getSize(); i++) {
            horizontalFullLine.append("----");
        }
        horizontalFullLine.append("-");

        final StringBuilder lineWithData = new StringBuilder();
        for (int i = 0; i < board.getSize(); i++) {
            showMessage(horizontalFullLine.toString());
            lineWithData.setLength(0);
            lineWithData.append(" ").append(i + 1).append(" |");
            for (int j = 0; j < board.getSize(); j++) {
                lineWithData.append(" ").append(board.getSignText(i, j)).append(" |");
            }
            showMessage(lineWithData.toString());

        }
        //linia pozioma
        showMessage(horizontalFullLine.toString());
    }

}

