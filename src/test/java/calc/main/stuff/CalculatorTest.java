package calc.main.stuff;

import calc.main.stuff.Calculator;
import calc.main.stuff.ValueValidation;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class CalculatorTest {
    private Calculator actualCalculator;
    ValueValidation valueValidation;

    @Before
    public void setUp() {
        valueValidation = new ValueValidation();
       actualCalculator = new Calculator(valueValidation);
    }

    @Test
    public void testNotNull() {
        assertNotNull(actualCalculator);
    }


    @Test
    public void testiDividedByNonzeroEasy() {
        String divisionRes = actualCalculator.divide("28", "7");
        assertEquals("4", divisionRes);
    }
    @Test(expected = ArithmeticException.class)
    public void testiDividedByZero() {
        String divisionRes = actualCalculator.divide("28", "0");
        assertEquals("4", divisionRes);
        assertFalse(true); // should be an exception
    }

    public void testAddTwoPositiveValues() {
        assertEquals(2, actualCalculator.add(1,1));
    }

    @Test
    public void testAddTwoOtherPositiveValues() {
        assertEquals(21, actualCalculator.add(17,4));
    }

    @Test
    public void testAddTwoNegativeValues() {
        assertEquals(-1, actualCalculator.add(1,-2));
    }

    @Test
    public void testAddTwoStringsAndReturnaValidDecimal() {
        String addCurrrentResult = actualCalculator.add("1.11", "1.12");
        assertEquals("2.23", addCurrrentResult);
    }

    @Test(expected = NumberFormatException.class)
    public void testAddTwoInvalidStringsAndExpectAnException() {
        String addCurrrentResult = actualCalculator.add("1...11", "1....12");
        assertTrue("An Exception was expected herer", false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddTwoInvalidStringsAndExpectAnException_2() {
        String addCurrrentResult = actualCalculator.add("1.113", "1.125");
        assertTrue("An Exception was expected here", false);
    }

    @Test
    public void testSubtractTwoPositiveValues() {
        assertEquals(1,actualCalculator.subtract(2,1));
    }

    @Test
    public void testMutiplyTwoStringsWithDecimalValuesAndReturnTheCorrectAnswer() {
        assertEquals("2.5953", actualCalculator.multiply("2.11", "1.23"));

    }

    @Test
    public void sendAValidBigDecimalWithScaleSetToTwoAndGetGoodReturn() {
        Boolean isValidScale = actualCalculator.isValidScale("3.33");
        assertNotNull("Function should return a bool val", isValidScale);
        assertTrue(isValidScale);
    }
    @Test
    public void sendInvalidBigDecimalWithScaleSetToTwoAndGetFalse() {
        Boolean isValidScale = actualCalculator.isValidScale("2.333");
        assertNotNull("Function should return a bool val", isValidScale);
        assertFalse(isValidScale);
    }

    @Test
    public void sendAnInvalidScaleAndReturnFalse() {
        assertFalse(actualCalculator.isValidScale("2.333"));
    }
}
