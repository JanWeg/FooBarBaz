package calc.main.stuff;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ValueVaidationTest {

    private ValueValidation validation;

    @Before
    public void setUp() {
        validation = new ValueValidation();
    }

    @Test
    public void testNonNullOnCreation() {
        assertNotNull(validation);
    }

    @Test
    public void testIfValidScalesProvided() {
        List<String> errors = validation.getErrors("1.11", "2.22");
        assertEquals(0, errors.size());
    }


    @Test
    public void shouldReturnTheErrournousValue() {

        List<String> errors = validation.getErrors("1.11", "1.334", "1.22");
        assertEquals(1, errors.size());
        assertEquals("The value 1.334 has to many digits after the dot.", errors.get(0));
    }

    @Test
    public void shouldReturnMultipleErrorsWhennMutliplePassedIn() {

        String preparedValues[] = {"1.333", "3.4444", "4.333", "1.333"};

        List<String> errors = validation.getErrors("1.333", "2.22", "3.4444", "4.333", "1.333");

        assertEquals(4, errors.size());
        int i = 0;
        for (String v : preparedValues ) {
            assertEquals(String.format("The value %s has to many digits after the dot.", preparedValues[i]), errors.get(i++));
        }
    }

    @Test
    public void exceptionWhenNonNumbersAreGiven() {
        String preparedInputValue = "1xxx2";
        String expected_outcome = String.format("The value %s passed in was not a valid number.", preparedInputValue);

        List<String> actual_res = validation.getErrors(preparedInputValue);

        assertEquals(1, actual_res.size());
        assertEquals(expected_outcome, actual_res.get(0));

    }


    @Test
    public void acceptWhenEValueIsGiven() {
        String preparedInputValue = "1E2";
        String expected_outcome = String.format("The value %s passed in was not a valid number.", preparedInputValue);

        List<String> actual_res = validation.getErrors(preparedInputValue);

        assertEquals(0, actual_res.size());
    }
}
