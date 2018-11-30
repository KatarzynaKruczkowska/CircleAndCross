package com.company.tests;

import com.company.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.startsWith;
import static org.mockito.Mockito.*;

public class GameManagerTest {

    private IOManager mockIoManager;
    private GameManager gameManager;

    @Before
    public void onBefore() {
        mockIoManager = mock(IOManager.class);
        gameManager = new GameManager(mockIoManager);
    }

    @After
    public void onAfter() {

    }

    @Test
    public void initTwoPlayers() {

        //Given
        List<Player> result = null;
        when(mockIoManager.getName()).thenReturn("Kuba", "Robert");

        //When
        try {
            result = gameManager.initPlayers(2);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Then
        verify(mockIoManager).showMessage("Gracz 1 Wprowadź swoje imię \n");
        verify(mockIoManager).showMessage("Gracz 2 Wprowadź swoje imię \n");
        verify(mockIoManager).showMessage(startsWith("Witaj Kuba grasz jako"));
        verify(mockIoManager).showMessage(startsWith("Witaj Robert grasz jako"));
        verify(mockIoManager, times(2)).getName();
        verify(mockIoManager, times(4)).showMessage(any());


        assertNotNull(result);
        assertEquals("Kuba", result.get(0).getName());
        assertEquals("Robert", result.get(1).getName());
        assertEquals(2, result.size());
    }

    @Test(expected = TooManyPlayersException.class)
    public void initFivePlayers() throws TooManyPlayersException, TooLowPlayersException {
        //When
        gameManager.initPlayers(5);
    }

    @Test(expected = TooLowPlayersException.class)
    public void initZeroPlayers() throws TooManyPlayersException, TooLowPlayersException {
        //When
        gameManager.initPlayers(0);
    }

    @Test(expected = TooLowPlayersException.class)
    public void initNegativeNumberOfPlayers() throws TooManyPlayersException, TooLowPlayersException {
        //When
        gameManager.initPlayers(-1);
    }
}