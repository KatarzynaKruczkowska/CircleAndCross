package com.company;

import static com.company.Texts.HELLO;
import static com.company.Texts.PLAYING_AS;

public class Player {
    private final String name;
    private final PlayerSignType sign;
    private static final String FORMATED_WELCOME = "%s %s %s %s %n";


    public Player(final String name, final PlayerSignType sign) {
        this.name = name;
        this.sign = sign;
        //ioManager.showMessage(format(FORMATED_WELCOME, HELLO, name, PLAYING_AS, sign));
        System.out.printf(FORMATED_WELCOME, HELLO, name, PLAYING_AS, sign);
    }

    public String getName() {
        return name;
    }

    public PlayerSignType getSign() {
        return sign;
    }
}
