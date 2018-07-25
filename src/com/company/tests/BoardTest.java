package com.company.tests;

import com.company.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class BoardTest {
    private OnEndGameListener mockOnEndGameListener;
    private Board board;


    @Before
    public void onBefore() {
        mockOnEndGameListener = mock(OnEndGameListener.class);
        board = new Board(2, mockOnEndGameListener);
    }

    @After
    public void onAfter() {

    }

    @Test
    public void getSignTextTest() {
        //Given
        board.insertSign(PlayerSignType.X, 1, 1);
        //When
        boolean result = board.getSignText(1,1) == "X";

        //Then
        assertTrue(result);
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