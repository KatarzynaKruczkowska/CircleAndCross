package com.company.tests;

import com.company.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class BoardTest {
    //private OnEndGameListener mockOnEndGameListener;
    private OnEndGameListener onEndGameListener;
    private Board board;
    private int size = 2;


    @Before
    public void onBefore() {
        //mockOnEndGameListener = mock(OnEndGameListener.class);
        board = new Board(size, onEndGameListener);
    }

    @After
    public void onAfter() {

    }

    @Test
    public void getSignTextTest() {
        //Given
        board.insertSign(PlayerSignType.X, size - 1, size - 1);

        //When
        String result = board.getSignText(size - 1, size - 1);

        //Then
        assertEquals("X", result);

    }

    @Test
    public void getSignTextTestXchangedToO() {
        //Given
        board.insertSign(PlayerSignType.X, size - 1, size - 1);

        //When
        board.insertSign(PlayerSignType.O, size - 1, size - 1);
        String result = board.getSignText(size - 1, size - 1);

        //Then
        assertEquals("X", result);

    }

    @Test
    public void TestListenerWhenWin() {
        //Given
        for (int i = 0; i < size - 1; i++) {
            board.insertSign(PlayerSignType.X, i, size - 1);
        }

        //When
        Boolean winner = board.insertSign(PlayerSignType.X, size - 1, size - 1);
        String result = onEndGameListener.toString();

        //Then
        assertEquals("X", result);
        assertTrue(winner);
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
        boolean result = board.insertSign(PlayerSignType.X, size - 1, size - 1);

        assertTrue(result);
    }
}