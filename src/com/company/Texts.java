package com.company;

import static com.company.Main.MAX_BOARD_SIZE;
import static com.company.Main.MIN_BOARD_SIZE;

public class Texts {
    public final static String WELCOME = "Witam w grze kółko i krzyżyk.\n" +
            "Wpisz wielkość pola gry (standardowo 3 czyli 3 kolumny i 3 wiersze)\n" +
            "nie mniejszą niż " + MIN_BOARD_SIZE + " ale nie większą niż " + MAX_BOARD_SIZE + ". Aplikacja będzie prosić Cię\n" +
            "o wpisanie ruchu za pomocą symboli wiersza (liczba) i kolumny (litera).\n" +
            "Wybieraj tak, żeby uzupełnić jednakowym symbolem wszystkie pola w rzędzie, kolumnie, lub po przekątnej\n" +
            "Dobrej zabawy!!!";
    public static final String PLAYER = "Gracz";
    public static final String PROVIDE_BOARD_SIZE_TEXT = "Wpisz wielkość planszy >=" + MIN_BOARD_SIZE + " i <=" + MAX_BOARD_SIZE;
    public static final String WRONG_SIZE = "Wprowadzono wartość spoza zakresu, spróbuj jeszcze raz lub naciśnij ESC=wyjście";
    public static final String PROVIDE_ROW_NUMBER = "Podaj numer wiersza ";
    public static final String SELECTED = "Wybrano";
    public static final String AGAIN_PLAY = "Czy chcesz zagrać jeszcze raz? Naciśnij T ->Tak, lub inny klawisz ->wyjście";
    public static final String PROVIDE_COLUMN = "Podaj kolumnę (a, b, c...) ";
    public static final String NOT_EMPTY_PLACE = "Wybrane miejsce jest już zajęte, spróbuj jeszcze raz";
    public static final String WINNER = "Koniec gry, gratulacje dla gracza ";
    public static final String ROW = "wiersz";
    public static final String COLUMN = "kolumna";
    public static final String DIAGONAL_II = "przekątna ii czyli";
    public static final String DIAGONAL_JJ = "przekątna jj czyli";
    public static final String END_OF_THE_GAME = "Koniec rozgrywki";
}
