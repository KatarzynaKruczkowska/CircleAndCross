package com.company.tests;

import com.company.Board;
import com.company.PlayerSignType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BoardTest {

    Board board;

    @Before
    public void onBefore() {
//        final OnEndGameListener onEndGameMock = new OnEndGameListener() {
//            @Override
//            public void onEndGame(PlayerSignType sign) {
//
//            }
//        };
//        board = new Board(3, onEndGameMock);

        board = new Board(3, sign -> {
        });
    }

    @After
    public void onAfter() {

    }

    @Test
    public void insertSignWithNegativeCoordinatesTest() {
        boolean result = board.insertSign(PlayerSignType.X, -5, -7);

        assertFalse(result);
    }

    @Test
    public void insertSignWithTooBigCoordinatesTest() {
        boolean result = board.insertSign(PlayerSignType.X, 5, 7);

        assertFalse(result);
    }

    @Test
    public void insertSignWithProperCoordinatesTest() {
        boolean result = board.insertSign(PlayerSignType.X, 2, 1);

        assertTrue(result);
    }
}