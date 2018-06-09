package com.company;

import java.util.Random;

import static com.company.Texts.*;
import static java.lang.String.format;

public class Main {

    public static final int MIN_BOARD_SIZE = 2;
    public static final int MAX_BOARD_SIZE = 9;
    private static final int MIN_BOARD_ID = 1;
    private static final String FORMATED_SELECT = "%s %d \n";
    private static final String FORMATED_CHOICE = "%s %s %d %s %d \n";
    private static final String FORMATED_PLAYER = "%s (%s) \n";
    private static final String FORMATED_WINNER = "%s %s (%s) \n";
    private static final String FORMATTED_PROVIDE_NAME = "%s %d %s \n";


    private static boolean shouldPlayAgain;
    private static Player player;

    private static final IOManager ioManager = new IOManagerConsole();

    public static void main(String[] args) {
        ioManager.showMessage(WELCOME);
        boolean notEnd;
        do {
            play();
            notEnd = ioManager.getDecision(PLAY_AGAIN);
        } while (notEnd);
    }

    public static void play() {

        shouldPlayAgain = true;
        // 1
        ioManager.showMessage(format(FORMATTED_PROVIDE_NAME, PLAYER, 1, PROVIDE_YOUR_NAME));
        String name = ioManager.getName();
        final Player player1 = new Player(name, PlayerSignType.X);
        // 2
        ioManager.showMessage(format(FORMATTED_PROVIDE_NAME, PLAYER, 2, PROVIDE_YOUR_NAME));
        name = ioManager.getName();
        final Player player2 = new Player(name, PlayerSignType.O);

        final int boardSize = ioManager.getBoardSize(MIN_BOARD_SIZE, MAX_BOARD_SIZE);
        ioManager.showMessage(format(FORMATED_SELECT, SELECTED, boardSize));

        final Board board = new Board(boardSize);
        player = player1;
        do {
            ioManager.showBoard(board);
            System.out.printf(FORMATED_PLAYER, player.getName(), player.getSign());

            final Coordinates coordinates = ioManager.getCoordinates(MIN_BOARD_ID, boardSize);

            System.out.printf(FORMATED_CHOICE, SELECTED, ROW, coordinates.row + 1, COLUMN, coordinates.column + 1);

            if (board.insertSign(player.getSign(), coordinates.row, coordinates.column)) {
                board.addSignValue(coordinates.row, coordinates.column);
                if (board.checkWinner(coordinates.row, coordinates.column)) {
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

    public static int getRandomPlayerIndex(final int playerCount) {
        final Random random = new Random();
        return random.nextInt(playerCount);
    }

}
