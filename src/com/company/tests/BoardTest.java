package com.company.tests;

import com.company.Board;
import com.company.OnEndGameListener;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.company.PlayerSignType.O;
import static com.company.PlayerSignType.X;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class BoardTest {
    private OnEndGameListener mockOnEndGameListener;
    private Board board;
    private int size = 2;


    @Before
    public void onBefore() {
        mockOnEndGameListener = mock(OnEndGameListener.class);
        board = new Board(size, mockOnEndGameListener);
    }

    @After
    public void onAfter() {

    }

    @Test
    public void getSignTextTest() {
        //Given
        board.insertSign(X, size - 1, size - 1);

        //When
        String result = board.getSignText(size - 1, size - 1);

        //Then
        assertEquals("X", result);

    }

    @Test
    public void getSignTextTestXchangedToO() {
        //Given
        board.insertSign(X, size - 1, size - 1);

        //When
        boolean result = board.insertSign(O, size - 1, size - 1);

        //Then
        assertFalse(result);
        assertEquals("X", board.getSignText(size - 1, size - 1));
    }

    @Test
    public void TestListenerWhenWin() {
        //Given

        //When
        for (int i = 0; i < size; i++) {
            board.insertSign(X, i, size - 1);
        }

        //Then
        verify(mockOnEndGameListener).onEndGame(X);
        verifyNoMoreInteractions(mockOnEndGameListener);
    }

    @Test
    public void TestListenerWhenTie() {
        //Given
        board = new Board(3, mockOnEndGameListener);

        //When
        board.insertSign(X, 0, 0);
        board.insertSign(O, 0, 1);
        board.insertSign(O, 0, 2);

        board.insertSign(O, 1, 0);
        board.insertSign(X, 1, 1);
        board.insertSign(X, 1, 2);

        board.insertSign(X, 2, 0);
        board.insertSign(O, 2, 1);
        board.insertSign(O, 2, 2);

        //Then
        verify(mockOnEndGameListener).onEndGame(null);
        verifyNoMoreInteractions(mockOnEndGameListener);
    }



    @Test
    public void insertSignWithNegativeCoordinatesTest() {
        boolean result = board.insertSign(X, -5, -7);

        assertFalse(result);
    }

    @Test
    public void insertSignWithTooBigCoordinatesTest() {
        boolean result = board.insertSign(X, 5, 7);

        assertFalse(result);
    }

    @Test
    public void insertSignWithProperCoordinatesTest() {
        boolean result = board.insertSign(X, size - 1, size - 1);

        assertTrue(result);
    }
}