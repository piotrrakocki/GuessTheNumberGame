package academy.learnprogramming;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class NumberGeneratorImplTest {

    @Mock
    private NumberGeneratorImpl numberGenerator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        numberGenerator = new NumberGeneratorImpl(20, 5);
    }

    @Test
    void next() {
        for (int i=0; i < 1000; i++) {
            int randomNumber = numberGenerator.next();
            Assertions.assertTrue(randomNumber >= 5 && randomNumber <= 20);
        }
    }

}