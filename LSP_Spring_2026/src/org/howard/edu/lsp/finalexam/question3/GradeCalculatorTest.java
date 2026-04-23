package org.howard.edu.lsp.finalexam.question3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class GradeCalculatorTest {

    private final GradeCalculator calculator = new GradeCalculator();

    @Test
    public void testAverageCalculation() {
        double avg = calculator.average(80, 90, 100);
        assertEquals(90.0, avg);
    }

    @Test
    public void testLetterGrade() {
        assertEquals("B", calculator.letterGrade(85.0));
    }

    @Test
    public void testIsPassing() {
        assertTrue(calculator.isPassing(60.0));
    }

    @Test
    public void testBoundaryValueAtPassingThreshold() {
        assertEquals("D", calculator.letterGrade(60.0));
        assertTrue(calculator.isPassing(60.0));
    }

    @Test
    public void testBoundaryValueAtFailingThreshold() {
        assertEquals("F", calculator.letterGrade(59.9));
        assertFalse(calculator.isPassing(59.9));
    }

    @Test
    public void testExceptionForNegativeScore() {
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.average(-1, 50, 60);
        });
    }

    @Test
    public void testExceptionForScoreAbove100() {
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.average(90, 101, 80);
        });
    }
}