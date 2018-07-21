package com.company.tests;

import com.company.Board;
import com.company.GameManager;
import com.company.OnEndGameListener;
import com.company.PlayerSignType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class BoardTest {
    private int size = 2;
    private OnEndGameListener mockOnEndGameListener;
    private Board mockBoard;


    @Before
    public void onBefore() {
        mockOnEndGameListener = mock(OnEndGameListener.class);
        mockBoard = mock(Board.class(size,mockOnEndGameListener));

    }

    @After
    public void onAfter() {

    }
    @Test
    public void getSignTextTest(){
        when(mockBoard.getSignText(2,2).thenReturn("X"));

        verify(mockBoard.getSignText(2,3,)
    }

    @Test
    public void insertSignWithNegativeCoordinatesTest() {
        boolean result = mockBoard.insertSign(PlayerSignType.X, -5, -7);

        assertFalse(result);
    }

    @Test
    public void insertSignWithTooBigCoordinatesTest() {
        boolean result = mockBoard.insertSign(PlayerSignType.X, 5, 7);

        assertFalse(result);
    }

    @Test
    public void insertSignWithProperCoordinatesTest() {
        boolean result = mockBoard.insertSign(PlayerSignType.X, 2, 1);

        assertTrue(result);
    }
}