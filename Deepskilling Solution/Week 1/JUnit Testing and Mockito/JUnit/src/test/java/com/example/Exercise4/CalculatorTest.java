package com.example.Exercise4;

import com.example.Calculator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {
    private Calculator calculator;

    @BeforeEach
    public void setUp() {
        // Setup: runs before each test
        calculator = new Calculator();
        System.out.println("Test setup completed.");
    }

    @AfterEach
    public void tearDown() {
        // Teardown: runs after each test
        calculator = null;
        System.out.println("Test cleanup completed.");
    }

    @Test
    public void testAddition() {
        // Arrange
        int a = 5;
        int b = 3;

        // Act
        int result = calculator.add(a, b);

        // Assert
        assertEquals(8, result);
    }
}
