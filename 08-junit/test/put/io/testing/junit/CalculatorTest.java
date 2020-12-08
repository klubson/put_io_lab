package put.io.testing.junit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    private Calculator calculator;

    @BeforeEach//testy przestałyby działać gdyż obiekt typu Calculator nie jest statyczny
    public void setUp(){
        calculator = new Calculator();
    }

    @Test
    public void testAdd(){
        assertEquals(6, calculator.add(2,4));
        assertEquals(17, calculator.add(10,7));
    }

    @Test
    public void testMultiply(){
        assertEquals(15, calculator.multiply(3, 5));
        assertEquals(32, calculator.multiply(8,4));
    }

    @Test
    public void testAddPositiveNumbers(){
        assertThrows(IllegalArgumentException.class, () -> {calculator.addPositiveNumbers(-1, -6);});
    }

}