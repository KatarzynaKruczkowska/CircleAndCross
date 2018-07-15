package com.company;

public class TooLowPlayersException extends Exception {

    public TooLowPlayersException() {
        super("Too low players");
    }
}
