package sn.groupeisi;

import org.junit.Test;
import sn.groupeisi.Calculator;

import static org.junit.Assert.assertEquals;

public class CalculatorTest {

    @Test
    public void testAdd(){

        Calculator calc = new Calculator();
        int result = calc.add(4,9);

        assertEquals(13,result);
    }

    @Test
    public void testSubstract(){

        Calculator calc = new Calculator();
        int result = calc.substract(27,11);

        assertEquals(16,result);
    }

    @Test
    public void testMultiplication(){

        Calculator calc = new Calculator();
        int result = calc.multiplication(16,2);

        assertEquals(32,result);
    }

    @Test
    public void testDivide(){

        Calculator calc = new Calculator();

        int result = calc.divide(25,5);

        assertEquals(5,result);
        
    }

    @Test
    public void testDivideByZero() {
        Calculator calc = new Calculator();
        int result = calc.divide(5,0);

        assertEquals(-1,result);
    }
}
