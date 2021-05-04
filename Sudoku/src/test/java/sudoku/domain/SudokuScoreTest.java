/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku.domain;


import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author sareetta
 */
public class SudokuScoreTest {
    SudokuScore score;
    
    public SudokuScoreTest() {
    }
    
    @Before
    public void setUp() {
        score = new SudokuScore(0, "test", "00:10");
    }
    
     @Test
    public void constructorSetstId() {
        assertTrue(score.getId() == 0);
    }
    
    @Test
    public void constructorSetsName() {
        assertTrue(score.getName().equals("test"));
    }
    
    @Test
    public void constructorSetsTime() {
        assertTrue(score.getTime().equals("00:10"));
    }
    
    @Test
    public void toStringReturnsCorrectText() {
        String value = score.toString();
        String expected = "test: 00:10";
        assertEquals(value, expected);
    }

}
