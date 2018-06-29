package com.company;

public class TooManyPlayersException extends Exception {

    public TooManyPlayersException() {
        super("Too many players");
    }
}
