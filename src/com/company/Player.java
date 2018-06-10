package com.company;


public class Player {
    private final String name;
    private final PlayerSignType sign;



    public Player(final String name, final PlayerSignType sign) {
        this.name = name;
        this.sign = sign;
    }

    public String getName() {
        return name;
    }

    public PlayerSignType getSign() {
        return sign;
    }
}
