package com.company.tests;

import com.company.GameManager;
import com.company.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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
    public void insertSignWithNegativeCoordinatesTest() {
        Player[] result = gameManager.initPlayers(5);

        assertEquals(5, result.length);
    }
}