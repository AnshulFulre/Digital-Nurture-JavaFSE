package com.example.Exercise1.Exercise1;

import com.example.Calculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculatorTest {

    private Calculator calculator = new Calculator();

    @Test
    void testAdd(){
        int result = calculator.add(3,2);
        assertEquals(result, 5);
    }
}
