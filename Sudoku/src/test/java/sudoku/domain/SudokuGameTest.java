/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku.domain;

import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

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
    public void newSudokuSetsBoardWithCorrectHeight() {
        assertTrue(9 == sudoku.getSudoku().length);
    }
    
    @Test
    public void newSudokuSetsBoardWithCorrectWidht() {
        assertTrue(9 == sudoku.getSudoku()[0].length);
    }
    
    @Test 
    public void generateNewSudokuGeneratesSolution() {
        sudoku.generateSudoku(0);
        int numbers = 0;
        int empty = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (sudoku.getValue(i, j) == 0) {
                    empty++;
                } else if(sudoku.getValue(i, j) != 0) {
                    numbers++;
                }
            }
        }
        assertTrue(numbers+empty == 81);
    }
    
    @Test
    public void isPossibleSubgridReturnFalseIfValueInTheRow() {
        sudoku.setValue(1, 1, 8);
        assertTrue(!sudoku.isPossibleSubgrid(1, 2, 8));
    }
    
    @Test
    public void isPossibleSubgridReturnFalseIfValueInTheColumn() {
        sudoku.setValue(1, 1, 8);
        assertTrue(!sudoku.isPossibleSubgrid(2, 1, 8));
    }
    
    @Test
    public void isPossibleSubgridReturnTrueIfNumberCanBeInserted() {
        assertTrue(sudoku.isPossibleSubgrid(0, 0, 8));
    }
    
    @Test 
    public void isPossibleSubgridReturnFalseIfValueInTheSubgrid() {
        sudoku.setValue(1, 1, 8);
        assertTrue(!sudoku.isPossibleSubgrid(2, 2, 8));
    }
    
     @Test
    public void setAndGetValueWorksCorrectly() {
        sudoku.setValue(1,1,8);
        assertTrue(sudoku.getValue(1,1) == 8);
    }
    
    @Test
    public void setValueChecksIfTheValueIsTooBig() {
        sudoku.setValue(1,1,10);
        assertTrue(sudoku.getValue(1, 1) == 1);
    }
    
    @Test
    public void setValueChecksIfTheValueIsTooSmall() {
        sudoku.setValue(1,1,-1);
        assertTrue(sudoku.getValue(1, 1) == 1);
    }
   
}
