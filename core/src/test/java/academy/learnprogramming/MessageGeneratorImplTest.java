package academy.learnprogramming;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MessageGeneratorImplTest {

    @Mock
    private NumberGenerator numberGenerator;

    @Mock
    private MessageGenerator messageGenerator;

    private GameImpl game;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        game = new GameImpl(numberGenerator, 10);
        messageGenerator = new MessageGeneratorImpl(game);
    }

    @Test
    void getMainMessage() {
        game.setSmallest(5);
        game.setBiggest(70);

        String mainMessage = messageGenerator.getMainMessage();
        String expectedMessage = "Number is between " +
                game.getSmallest() + " and " +
                game.getBiggest() +
                ". Can you guess it?";

        assertEquals(expectedMessage, mainMessage);
    }

    @Test
    void youGuessIt() {
        game.setGuess(10);
        game.setNumber(10);

        String resultMessage = messageGenerator.getResultMessage();
        String expectedMessage = "You guess it! The number was " + game.getNumber();

        assertEquals(expectedMessage, resultMessage);
    }

    @Test
    void youLost() {
        game.setGuess(5);
        game.setNumber(10);

        String resultMessage = messageGenerator.getResultMessage();
        String expectedMessage = "You lost. The number was " + game.getNumber();

        assertEquals(expectedMessage, resultMessage);
    }

    @Test
    void invalidNumberRange() {
        game.setValidNumberRange(false);
        game.setNumber(80);
        game.setGuess(50);
        game.setSmallest(10);
        game.setBiggest(70);
        game.setRemainingGuesses(9);

        String resultMessage = messageGenerator.getResultMessage();
        String expectedMessage = "Invalid number range!";

        assertEquals(expectedMessage, resultMessage);
    }

    @Test
    void whatIsYourFirstGuess() {
        game.setNumber(50);
        game.setRemainingGuesses(10);

        String resultMessage = messageGenerator.getResultMessage();
        String expectedMessage = "What is your first guess?";

        assertEquals(expectedMessage, resultMessage);
    }

    @Test
    void guessNumberIsLower() {
        game.setRemainingGuesses(9);
        game.setNumber(50);
        game.setGuess(60);

        String resultMessage = messageGenerator.getResultMessage();
        String expectedMessage = "Lower! You have " + game.getRemainingGuesses() + " guesses left";

        assertEquals(expectedMessage, resultMessage);
    }

    @Test
    void guessNumberIsHigher() {
        game.setRemainingGuesses(9);
        game.setNumber(50);
        game.setGuess(40);

        String resultMessage = messageGenerator.getResultMessage();
        String expectedMessage = "Higher! You have " + game.getRemainingGuesses() + " guesses left";

        assertEquals(expectedMessage, resultMessage);
    }

}