/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku.domain;

import java.util.*;

/**
 * Class for application logic.
 *
 * @author sareetta
 */
public class SudokuGame {
    private int[][] sudoku;
    protected int[][] solution;
    private int difficulty;
    
    /**
     * Constructor.
     * Initializes the Sudoku -board and the difficulty level.
     */
    public SudokuGame() {
        sudoku = new int[9][9];
        difficulty = 25;
    }
     
    /**
     * Method creates a new Sudoku game.
     * Generates a new Sudoku .
     * Based on the chosen difficulty it replaces some numbers with zeros.
     */
    public void newSudoku() {
        sudoku = new int[9][9];
        generateSudoku(0);
        solution = copy(sudoku);
        addDifficultyLevel(this.difficulty);
    }

    /**
     * Generates a new Sudoku -board.
     * Fills the entire board (solution).
     * @param index  Fills the board starting from this index.
     * @return       Return the generated board (solution).
     */
    public int[][] generateSudoku(int index) {
        if (index > 80) { 
            return sudoku;
        }
        int row = index / 9;
        int column = index % 9;
        List<Integer> values = new ArrayList<Integer>();
        for (int i = 1; i <= 9; i++) {
            values.add(i);
        }
        Collections.shuffle(values);
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
   
    /**
     * Checks if the given number can be inserted in the 
     * 3x3 -subgrid in the given coordinates.
     * 
     * @param row       The row to be checked.
     * @param column       The column to be checked.
     * @param value  Given number to be placed.
     * @return        Return true if can, false otherwise.
     */
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
    
    /**
     * Method to check if the given number is already in the column.
     * 
     * @param column       The column to be checked.
     * @param value  The given number.
     * @return        Returns true if it is not, false otherwise.
     */
    public boolean isPossibleRow(int column, int value) {
        for (int row = 0; row < 9; row++) {
            if (sudoku[row][column] == value) {
                return false;
            }
        }
        return true;
    }

    
    /**
     * Method to check if the given number is already in the row.
     * 
     * @param row    The row to be checked.
     * @param value  Given number.
     * @return       Returns true if it is not, false otherwise.
     */
    public boolean isPossibleColumn(int row, int value) {
        for (int column = 0; column < 9; column++) {
            if (sudoku[row][column] == value) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Method returns next possible number from list for given coordinates or -1 when list
     * is empty.
     * 
     * @param row      The current row.
     * @param column   The current column.
     * @param values   Possible candidates.
     * @return         Returns the next possible number or -1 if there is no numbers left.
     */
    public int getNextPossibleValue(int column, int row, List<Integer> values) {
        while (values.size() > 0) {
            int value = values.remove(0);
            if (isPossibleColumn(row, value) && isPossibleRow(column, value) && isPossibleSubgrid(column, row, value)) {
                return value;
            }
        }
        return -1;
    }
  
    /**
     * Method generates the difficulty level of the game based on what user chose.
     * Adds zeros to the board.
     * 
     * @param difficulty  Chosen difficulty level.
     */
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
    
    /**
     * Method checks if the solution for Sudoku is correct or not.
     * 
     * @return Return true if correct, otherwise false
     */
    public boolean checkSudoku() {
        boolean check = true;
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                if (solution[row][column] != sudoku[row][column]) {
                    check = false;
                }
            }
        }
        return check;
    }
    
    /**
     * Method copies the solution of the Sudoku for checking.
     * 
     * @param sudoku The generated Sudoku solution.
     * @return     Returns copy of the solution.
     */
    public int[][] copy(int[][] sudoku) {
        int[][] copy = new int[9][9];
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                copy[row][column] = sudoku[row][column];
            }
        }
        return copy;
    }
    
     /**
     * Method sets the given number in the given coordinates to the Sudoku.
     * If number is not valid, 1 is set to given coordinates.
     * @param row      The row where the number is to be set
     * @param column   The column where the number is to be set
     * @param value    The number that is put into the Sudoku
     */
    public void setValue(int column, int row, int value) {
        if (value < 1 || value > 9) {
            sudoku[row][column] = 1;
            return;
        }
        sudoku[row][column] = value;
    }

    /**
     * Method returns the number in given coordinates.
     * @param row  The row of the cell
     * @param column  The column of the cell
     * @return   The number in the given coordinates of the Sudoku
     */
    public int getValue(int column, int row) {
        return sudoku[row][column];
    }
    
    /**
     * Sets the difficulty level (number of empty cells).
     * @param difficulty The number of spaces to be blank at first.
     */
    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
    
    /**
     * Returns the difficulty level (number of empty cells).
     * @return The number of empty cells in the board
     */
    public int getDifficulty() {
        return this.difficulty;
    }

    /**
     * Method returns the Sudoku.
     * @return Returns the Sudoku
     */
    public int[][] getSudoku() {
        return this.sudoku;
    }
    
    /**
     * Method prints the Sudoku.
     * Used for testing.
     */
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
