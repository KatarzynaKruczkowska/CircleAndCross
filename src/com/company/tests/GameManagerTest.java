package com.company.tests;

import com.company.GameManager;
import com.company.Player;
import com.company.TooManyPlayersException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito.*;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class GameManagerTest {

    private GameManager gameManager;

    @Before
    public void onBefore() {
        gameManager = new GameManager(new IOManagerForTests());
    }


    @After
    public void onAfter() {

    }

    @Test
    public void initTwoPlayers() {

        //Given
        List<Player> result = null;

        //When
        try {
            result = gameManager.initPlayers(2);
        } catch (TooManyPlayersException e) {
            e.printStackTrace();
        }

        //Then
        assertEquals(2, result.size());
    }

    @Test(expected = TooManyPlayersException.class)
    public void initFivePlayers() throws TooManyPlayersException {
        //When
        gameManager.initPlayers(5);
    }
}