/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku.domain;

import java.util.*;

/**
 *
 * @author sareetta
 */
public class SudokuGame {
    private int[][] sudoku;
    private int difficulty;
    private List<Integer> values;
    
    public SudokuGame() {
        difficulty = 25;
        newSudoku();
        values = new ArrayList<>();
        for (int i = 1; i <= 9; i++) { 
            values.add(i);
        }
    }
    
    public void newSudoku() {
        sudoku = new int[9][9];
        generateSudoku(0);
        addDifficultyLevel(this.difficulty);
        Collections.shuffle(values);
    }
   
    public int[][] generateSudoku(int index) {
        if (index > 80) { 
            return sudoku;
        }
        int row = index / 9;
        int column = index % 9;
        
        while (values.size() > 0) {
            int value = getNextPossibleValue(column, row, values);
            if (value == -1) { 
                return null;
            }
            sudoku[row][column] = value;
            int[][] temp = generateSudoku(index + 1);
            if (temp != null) { 
                return temp;
            }
            sudoku[row][column] = 0;
        }
        return null;
    }
        
    public int getNextPossibleValue(int column, int row, List<Integer> values) {
        while (values.size() > 0) {
            int value = values.remove(0);
            if (isPossibleColumn(row, value) && isPossibleRow(column, value) && isPossibleSubgrid(column, row, value)) {
                return value;
            }
        }
        return -1;
    }

    public boolean isPossibleColumn(int row, int value) {
        for (int column = 0; column < 9; column++) {
            if (sudoku[row][column] == value) {
                return false;
            }
        }
        return true;
    }
    
    public boolean isPossibleRow(int column, int value) {
        for (int row = 0; row < 9; row++) {
            if (sudoku[row][column] == value) {
                return false;
            }
        }
        return true;
    }

    public boolean isPossibleSubgrid(int column, int row, int value) {
        int x = row < 3 ? 0 : row < 6 ? 3 : 6;
        int y = column < 3 ? 0 : column < 6 ? 3 : 6;
       
        for (int i = x; i < x + 3; i++) {
            for (int j = y; j < y + 3; j++) {
                if (sudoku[i][j] == value) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public void addDifficultyLevel(int difficulty) {
        int amountToReplace = difficulty;
        Random random = new Random();

        while (amountToReplace > 0) {
            int number = random.nextInt(81);
            
            int row = number / 9;
            int column = number % 9;

            if (sudoku[row][column] == 0) {
                continue;
            }

            sudoku[row][column] = 0;
            amountToReplace--;
        }
    }
    
    public void setValue(int column, int row, int value) {
        if (value < 1 || value > 9) {
            sudoku[row][column] = 1;
            return;
        }
        sudoku[row][column] = value;
    }
    
    public int getValue(int column, int row) {
        return sudoku[row][column];
    }
    
    public int[][] getSudoku() {
        return this.sudoku;
    }
    
    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
    
    public int getDifficulty() {
        return this.difficulty;
    }
    
    public void print() {
        System.out.println();
        for (int column = 0; column < 9; column++) {
            for (int row = 0; row < 9; row++) {
                System.out.print(" " + sudoku[row][column]);
                System.out.println();
            }
        }
    }

    
        
  
    
}
