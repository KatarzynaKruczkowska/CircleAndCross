package com.company.tests;

import com.company.Board;
import com.company.OnEndGameListener;
import com.company.PlayerSignType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class BoardTest {

    private Board mockBoard;
    private OnEndGameListener onEndGameMock;

    @Before
    public void onBefore() {
        onEndGameMock = mock(OnEndGameListener.class);
        mockBoard = mock(Board.class(3,onEndGameMock));
    }

    @After
    public void onAfter() {

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