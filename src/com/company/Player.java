package com.company;

import static com.company.Texts.HELLO;
import static com.company.Texts.PLAYER;

public class Player {
    private final String name;
    private final PlayerSignType sign;
    private static final String FORMATED_WELCOME = "%s %s %s %s %n";


    public Player(final String name, final PlayerSignType sign) {
        this.name = name;
        this.sign = sign;
        //ioManager.showMessage(format(FORMATED_WELCOME, HELLO, name, PLAYER, sign));
        System.out.printf(FORMATED_WELCOME, HELLO, name, PLAYER, sign);
    }

    public String getName() {
        return name;
    }

    public PlayerSignType getSign() {
        return sign;
    }
}
