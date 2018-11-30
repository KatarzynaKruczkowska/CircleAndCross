package com.company;

import static com.company.GameManager.MAX_BOARD_SIZE;
import static com.company.GameManager.MIN_BOARD_SIZE;

public class Texts {
    public final static String WELCOME = "Witam w grze kółko i krzyżyk.\n" +
            "Wpisz wielkość pola gry (standardowo 3 czyli 3 kolumny i 3 wiersze)\n" +
            "nie mniejszą niż " + MIN_BOARD_SIZE + " ale nie większą niż " + MAX_BOARD_SIZE + ". Aplikacja będzie prosić Cię\n" +
            "o wpisanie ruchu za pomocą symboli wiersza (liczba) i kolumny (litera).\n" +
            "Wybieraj tak, żeby uzupełnić jednakowym symbolem wszystkie pola w rzędzie, kolumnie, lub po przekątnej\n" +
            "Dobrej zabawy!!!";
    public static final String HELLO = "Witaj";
    public static final String PLAYING_AS = "grasz jako";
    public static final String PLAYER = "Gracz";
    public static final String PROVIDE_BOARD_SIZE_TEXT = "Wpisz wielkość planszy >=" + MIN_BOARD_SIZE + " i <=" + MAX_BOARD_SIZE;
    public static final String WRONG_SIZE = "Wprowadzono wartość spoza zakresu, spróbuj jeszcze raz";
    public static final String WRONG_FORMAT = "Wprowadzona wartość w złym formacie";
    public static final String PROVIDE_ROW_NUMBER = "Podaj numer wiersza";
    public static final String SELECTED = "Wybrano";
    public static final String PLAY_AGAIN = "Czy chcesz zagrać jeszcze raz?";
    public static final String PROVIDE_COLUMN = "Podaj kolumnę (a, b, c...)";
    public static final String NOT_EMPTY_PLACE = "Wybrane miejsce jest już zajęte, spróbuj jeszcze raz";
    public static final String WINNER = "WYGRANA !!!, Wygrał gracz o imieniu";
    public static final String ROW = "wiersz";
    public static final String COLUMN = "kolumna";
    public static final String END_OF_THE_GAME = "Koniec rozgrywki";
    public static final String PROVIDE_YOUR_NAME = "Wprowadź swoje imię";
    public static final String DECISION_YES = "Tak";
    public static final String DECISION_NO = "Nie";
    public static final String TAKE_DECISION = "Proszę zdecyduj wybierając jedną z poniższych opcji";
    public static final String DRAWN_PLAYER = "Jako pierwszy gra";
}
