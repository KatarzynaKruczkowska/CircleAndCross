package com.company;

import java.util.*;

import static com.company.Texts.*;
import static java.lang.String.format;

public class GameManager /*implements OnEndGameListener*/ {

    static final int MIN_BOARD_SIZE = 2;
    static final int MAX_BOARD_SIZE = 9;
    private static final int MIN_BOARD_ID = 1;
    private static final int MIN_PLAYERS = 2;
    private static final int NUMBER_OF_PLAYERS = 2;
    private static final String FORMATED_SELECT = "%s %d \n";
    private static final String FORMATED_CHOICE = "%s %s %d %s %d \n";
    private static final String FORMATED_PLAYER = "%s (%s) \n";
    private static final String FORMATED_WINNER = "%s %s (%s) \n";
    private static final String FORMATTED_PROVIDE_NAME = "%s %d %s \n";
    private static final String FORMATED_WELCOME = "%s %s %s %s \n";

    private final IOManager ioManager;
    private List<Player> players;
    private Board board;
    private boolean shouldContinue;

    public GameManager(final IOManager ioManager) {
        this.ioManager = ioManager;
    }

    public void play() throws TooManyPlayersException, TooLowPlayersException {
        players = initPlayers(NUMBER_OF_PLAYERS);
        playGame(players);
        ioManager.showMessage(END_OF_THE_GAME);
    }

    private int getRandomPlayerIndex(final int playerCount) {
        final Random random = new Random();
        return random.nextInt(playerCount);
    }

    public List<Player> initPlayers(final int playersCount) throws TooManyPlayersException, TooLowPlayersException {
        if (playersCount < MIN_PLAYERS) {
            throw new TooLowPlayersException();
        }

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

    private void onEndGame(final Player player) {
        ioManager.showBoard(board);
        if (player != null) {
            ioManager.showMessage(format(FORMATED_WINNER, WINNER, player.getName(), player.getSign()));
        }
        shouldContinue = false;
    }

    private Player findPlayer(PlayerSignType sign) {
        if (sign == null) {
            return null;
        }
        for (Player player : players) {
            if (player.getSign() == sign) {
                return player;
            }
        }
        return null;
    }

    private void playGame(final List<Player> players) {
        shouldContinue = true;
        final int boardSize = ioManager.getBoardSize(MIN_BOARD_SIZE, MAX_BOARD_SIZE);
        ioManager.showMessage(format(FORMATED_SELECT, SELECTED, boardSize));

        //First version (anonymous class)
//        board = new Board(boardSize, new OnEndGameListener() {
//            @Override
//            public void onEndGame(PlayerSignType sign) {
//                GameManager.this.onEndGame(findPlayer(sign));
//            }
//        });

        //Second version (lambda)
        board = new Board(boardSize, sign -> GameManager.this.onEndGame(findPlayer(sign)));

        //third version (method reference)
//        board = new Board(boardSize, this::onEndGame);

        //fourth version (implementation of listener)
//        board = new Board(boardSize, this);

        int activePlayerId = getRandomPlayerIndex(NUMBER_OF_PLAYERS);

        ioManager.showMessage(DRAWN_PLAYER + "\n" + format(FORMATED_PLAYER, players.get(activePlayerId).getName(), players.get(activePlayerId).getSign()));

        do {
            final Player player = players.get(activePlayerId);
            ioManager.showBoard(board);
            ioManager.showMessage(format(FORMATED_PLAYER, player.getName(), player.getSign()));

            final Coordinates coordinates = ioManager.getCoordinates(MIN_BOARD_ID, boardSize);

            ioManager.showMessage(format(FORMATED_CHOICE, SELECTED, ROW, coordinates.row + 1, COLUMN, coordinates.column + 1));

            if (board.insertSign(players.get(activePlayerId).getSign(), coordinates.row, coordinates.column)) {
                activePlayerId = ++activePlayerId % NUMBER_OF_PLAYERS;
            } else {
                ioManager.showMessage(NOT_EMPTY_PLACE);
            }
        } while (shouldContinue);
    }

//    @Override
//    public void onEndGame(PlayerSignType sign) {
//        GameManager.this.onEndGame(findPlayer(sign));
//    }
}
