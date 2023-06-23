package academy.learnprogramming;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class GameImplTest {

    @Mock
    private NumberGenerator numberGenerator;

    private GameImpl game;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        game = new GameImpl(numberGenerator, 10);
    }

    @Test
    void reset() {
        int minNumber = 1;
        int maxNumber = 100;
        when(numberGenerator.getMinNumber()).thenReturn(minNumber);
        when(numberGenerator.getMaxNumber()).thenReturn(maxNumber);
        when(numberGenerator.next()).thenReturn(42);

        // Act
        game.reset();

        // Assert
        assertEquals(minNumber, game.getSmallest());
        assertEquals(minNumber, game.getGuess());
        assertEquals(10, game.getRemainingGuesses());
        assertEquals(maxNumber, game.getBiggest());
        assertEquals(42, game.getNumber());
    }

    @Test
    void checkValidNumberRange() {
        game.setGuess(50);
        game.setSmallest(1);
        game.setBiggest(100);

        game.check();

        assertTrue(game.isValidNumberRange());
        assertEquals(49, game.getBiggest());
        assertNotEquals(9, game.getRemainingGuesses());
    }

    @Test
    void invalidNumberRange() {
        game.setGuess(150);
        game.setSmallest(1);
        game.setBiggest(100);

        game.check();

        assertFalse(game.isValidNumberRange());
        assertEquals(100, game.getBiggest());
        assertNotEquals(9, game.getRemainingGuesses());
    }

    @Test
    void gameWonTrue() {
        game.setGuess(20);
        game.setNumber(20);

        assertTrue(game.isGameWon());
    }

    @Test
    void gameWonFalse() {
        game.setGuess(15);
        game.setNumber(25);

        assertFalse(game.isGameWon());
    }

    @Test
    void numberEqualsGuessAndNoRemainingGuesses() {
        game.setGuess(20);
        game.setNumber(20);
        game.setRemainingGuesses(0);

        assertFalse(game.isGameLost());
    }

    @Test
    void numberEqualsGuessAndRemainingGuesses() {
        game.setGuess(20);
        game.setNumber(20);
        game.setRemainingGuesses(5);

        assertFalse(game.isGameLost());
    }

    @Test
    void numberNotEqualsGuessAndRemainingGuesses() {
        game.setGuess(20);
        game.setNumber(15);
        game.setRemainingGuesses(5);

        assertFalse(game.isGameLost());
    }

    @Test
    void numberNotEqualsGuessAndNoRemainingGuess() {
        game.setGuess(20);
        game.setNumber(15);
        game.setRemainingGuesses(0);

        assertTrue(game.isGameLost());
    }


}