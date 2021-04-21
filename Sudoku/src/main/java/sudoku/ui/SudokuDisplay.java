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
 *
 * @author sareetta
 */
public class SudokuDisplay {
   private TextArea[][] board;
    
    public SudokuDisplay() {
        board = new TextArea[9][9];
    }
    
    public void show(SudokuGame sudoku, GridPane sudokuBoard) {
            
            for (int i=1; i <= 9; i++) {
                for (int j=1; j <= 9; j++) {
                    if (sudoku.getValue(i-1, j-1) != 0) {
                        Label valueSquare = new Label(" " + sudoku.getValue(i-1, j-1));
                        
                        valueSquare.setFont(Font.font("Lucida Sans Unicode", 20));
                        valueSquare.setMaxSize(45,45);
                        valueSquare.setMinSize(45,45);
  
                        if ((i < 4 && j < 4) || (i > 6 && j > 6) || (i > 6 && j < 4) || (i < 4 && j > 6) || ((i > 3 && i < 7) && (j > 3 && j < 7))) {
                            valueSquare.setStyle("-fx-background-color: #9198e5;" + "-fx-border-color: black");
                         
                        } else {
                            valueSquare.setStyle("-fx-background-color: #e66465;" + "-fx-border-color: black");
                        } 
                       
                        valueSquare.setTextAlignment(TextAlignment.CENTER);
                        valueSquare.setWrapText(true);
                        sudokuBoard.add(valueSquare, i, j);
                        
                        board[i-1][j-1] = new TextArea(" " + sudoku.getValue(i-1, j-1));
                        
                    } else {
                        TextArea emptySquare = new TextArea();
            
                        emptySquare.setFont(Font.font("Lucida Sans Unicode", 20));
                        emptySquare.setStyle("-fx-border-color: black");
                        emptySquare.setMaxSize(45,45);
                        emptySquare.setMinSize(45,45);
                        
                        emptySquare.setTextFormatter(new TextFormatter<>(c -> {
                            if(c.getControlNewText().length() <= 1) {
                                return c;
                            } else {
                                return null;
                            }    
                        }));
                        
                        int row = i-1;
                        int column = j-1;
                        
                        board[column][row] = new TextArea();
                        
                        emptySquare.textProperty().addListener((c, from, to) -> {
                                board[row][column].setText(" " + to);
                        });
                        
                        sudokuBoard.add(emptySquare, i, j);
                    }                
                }
            }
    } 
    
}
