/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku.ui;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import sudoku.domain.SudokuGame;

/**
 * Class for handling the Sudoku -board.
 * 
 * @author sareetta
 */
public class SudokuDisplay {
   public TextArea[][] board;
    
   /**
    * Constructor.
    * Initializes the board.
    */
    public SudokuDisplay() {
        board = new TextArea[9][9];
    }
    
    /**
     * Method takes care of the Sudoku -board (empty and number cells)
     * 
     * @param sudoku       The Sudoku game.
     * @param sudokuBoard  The board to make.
     */
    public void showSudoku(SudokuGame sudoku, GridPane sudokuBoard) {
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                if (sudoku.getValue(i-1, j-1) != 0) {
                    Label valueCell = new Label(" " + sudoku.getValue(i-1, j-1));
                        
                    valueCell.setFont(Font.font("Lucida Sans Unicode", 20));
                    valueCell.setMinSize(45,45);
                    valueCell.setMaxSize(45,45);
  
                    if ((i < 4 && j < 4) || (i > 6 && j > 6) || (i > 6 && j < 4) || (i < 4 && j > 6) || ((i > 3 && i < 7) && (j > 3 && j < 7))) {
                        valueCell.setStyle("-fx-background-color: #9198e5;" + "-fx-border-color: black");
                         
                    } else {
                        valueCell.setStyle("-fx-background-color: #e66465;" + "-fx-border-color: black");
                    } 
                       
                    valueCell.setTextAlignment(TextAlignment.CENTER);
                    sudokuBoard.add(valueCell, i, j);
                        
                    board[i-1][j-1] = new TextArea("" + sudoku.getValue(i-1, j-1));
                        
                    } else {
                        TextArea emptyCell = new TextArea();
            
                        emptyCell.setFont(Font.font("Lucida Sans Unicode", 20));
                        emptyCell.setStyle("-fx-border-color: black");
                        emptyCell.setMaxSize(45,45);
                        emptyCell.setMinSize(45,45);
                        
                        emptyCell.setTextFormatter(new TextFormatter<>(c -> {
                            if(c.getControlNewText().length() <= 1) {
                                return c;
                            } else {
                                return null;
                            }    
                        }));
                        
                        int row = i-1;
                        int column = j-1;
                        
                        board[row][column] = new TextArea();
                        
                        emptyCell.textProperty().addListener((c, from, to) -> {
                                board[row][column].setText("" + to);
                        });
                        sudokuBoard.add(emptyCell, i, j);
                    }                
                }
            }
    } 
    
}
