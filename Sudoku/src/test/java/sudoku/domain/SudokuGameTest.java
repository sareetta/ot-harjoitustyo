/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sareetta
 */
public class SudokuGameTest {
    SudokuGame sudoku;
    
    @Before
    public void setUp() {
        sudoku = new SudokuGame();
    }
    
    @Test
    public void constructorCreatesNewSudoku() {
        assertTrue(sudoku != null);
    }
    
    @Test
    public void newSudokuSetsBoardWithCorrectHight() {
        assertTrue(9 == sudoku.getSudoku().length);
    }
    
    @Test
    public void newSudokuSetsBoardWithCorrectWidht() {
        assertTrue(9 == sudoku.getSudoku()[0].length);
    }
    
   
}
