package com.company;

import java.util.*;

import static com.company.Texts.*;
import static java.lang.String.format;

public class GameManager {

    static final int MIN_BOARD_SIZE = 2;
    static final int MAX_BOARD_SIZE = 9;
    private static final int MIN_BOARD_ID = 1;
    private static final int NUMBER_OF_PLAYERS = 2;
    private static final String FORMATED_SELECT = "%s %d \n";
    private static final String FORMATED_CHOICE = "%s %s %d %s %d \n";
    private static final String FORMATED_PLAYER = "%s (%s) \n";
    private static final String FORMATED_WINNER = "%s %s (%s) \n";
    private static final String FORMATTED_PROVIDE_NAME = "%s %d %s \n";
    private static final String FORMATED_WELCOME = "%s %s %s %s \n";

    private final IOManager ioManager;

    public GameManager(final IOManager ioManager) {
        this.ioManager = ioManager;
    }

    public void play() throws TooManyPlayersException {
        final List<Player> players = initPlayers(NUMBER_OF_PLAYERS);
        playGame(players);
        ioManager.showMessage(END_OF_THE_GAME);
    }

    private int getRandomPlayerIndex(final int playerCount) {
        final Random random = new Random();
        return random.nextInt(playerCount);
    }

    public List<Player> initPlayers(final int playersCount) throws TooManyPlayersException {
        final List<PlayerSignType> sign = new ArrayList<>(Arrays.asList(PlayerSignType.values()));
        sign.remove(PlayerSignType.EMPTY);

        if (sign.size() < playersCount) {
            throw new TooManyPlayersException();
        }

        Collections.shuffle(sign);

        final List<Player> players = new ArrayList<>(playersCount);
        for (int i = 0; i < playersCount; i++) {
            ioManager.showMessage(format(FORMATTED_PROVIDE_NAME, PLAYER, i + 1, PROVIDE_YOUR_NAME));
            String name = ioManager.getName();
            players.add(new Player(name, sign.get(i)));
            ioManager.showMessage(format(FORMATED_WELCOME, HELLO, players.get(i).getName(), PLAYING_AS, players.get(i).getSign()));
        }
        return players;
    }

    private void playGame(final List<Player> players) {
        boolean shouldPlayAgain = true;
        final int boardSize = ioManager.getBoardSize(MIN_BOARD_SIZE, MAX_BOARD_SIZE);
        ioManager.showMessage(format(FORMATED_SELECT, SELECTED, boardSize));
        final Board board = new Board(boardSize);
        int activePlayerId = getRandomPlayerIndex(NUMBER_OF_PLAYERS);

        ioManager.showMessage(DRAWN_PLAYER + "\n" + format(FORMATED_PLAYER, players.get(activePlayerId).getName(), players.get(activePlayerId).getSign()));

        do {
            final Player player = players.get(activePlayerId);
            ioManager.showBoard(board);
            ioManager.showMessage(format(FORMATED_PLAYER, player.getName(), player.getSign()));

            final Coordinates coordinates = ioManager.getCoordinates(MIN_BOARD_ID, boardSize);

            ioManager.showMessage(format(FORMATED_CHOICE, SELECTED, ROW, coordinates.row + 1, COLUMN, coordinates.column + 1));

            if (board.insertSign(players.get(activePlayerId).getSign(), coordinates.row, coordinates.column)) {
                board.addSignValue(coordinates.row, coordinates.column);
                if (board.checkWinner(coordinates.row, coordinates.column)) {
                    ioManager.showBoard(board);
                    ioManager.showMessage(format(FORMATED_WINNER, WINNER, player.getName(), player.getSign()));
                    shouldPlayAgain = false;
                }
                if (board.getCountOfEmptyField() == 0 && shouldPlayAgain) {
                    ioManager.showBoard(board);
                    shouldPlayAgain = false;
                }
                activePlayerId = ++activePlayerId % NUMBER_OF_PLAYERS;
            } else {
                ioManager.showMessage(NOT_EMPTY_PLACE);
            }
        } while (shouldPlayAgain);
    }
}
