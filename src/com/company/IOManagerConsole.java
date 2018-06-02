package com.company;

import java.util.Scanner;

import static com.company.Main.MIN_BOARD_ID;
import static com.company.Texts.*;

public class IOManagerConsole implements IOManager {


    @Override
    public void showMessage(final String message) {
        System.out.println(message);
    }

    @Override
    public int getBoardSize() {
        int result = 0;
        Scanner INPUT = new Scanner(System.in);
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
    public String getName() {
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
    public Coordinates getCoordinates(final Board board) {

        int min = MIN_BOARD_ID;
        int max = board.getSize();
        int row = 1;
        int column = 1;
        showMessage(PROVIDE_ROW_NUMBER);
        do {
            row = getBoardSize();
            if (row < min || row > max) {
                System.out.println(WRONG_SIZE);
            }
        } while (row < min || row > max);

        showMessage(PROVIDE_COLUMN);
        Scanner INPUT = new Scanner(System.in);
        final String inputText = INPUT.nextLine();
        do {

            if (inputText.length() != 1) {
                showMessage(WRONG_SIZE);
                continue;
            }
            column = inputText.toUpperCase().charAt(0) - 'A' + 1;
            if (column < MIN_BOARD_ID || column > max) {
                System.out.println(WRONG_SIZE);
            }
            // obsługa esc
        } while (column < MIN_BOARD_ID || column > max);
        return null; //jak przypisać row i column? i jak zrobic return?
    }

    @Override
    public boolean getDecision(String message) {
        showMessage(message);
        showMessage(DECISION_PLEASE_TAKE);
        showMessage("1 - " + DECISION_YES);
        showMessage("2 - " + DECISION_NO);
        int result = 1;
        Scanner INPUT = new Scanner(System.in);
        try {
            result = INPUT.nextInt();
        } catch (NumberFormatException error) {
            showMessage(WRONG_FORMAT);
        } catch (Exception error) {
            error.printStackTrace();
        }
        if (result == 1) {
            return true;
        }
        return false;
    }


    @Override
    public void showBoard(Board board) {
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

