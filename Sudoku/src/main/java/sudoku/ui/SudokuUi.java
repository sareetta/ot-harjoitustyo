/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku.ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import sudoku.domain.SudokuGame;

/**
 *
 * @author sareetta
 */
public class SudokuUi extends Application {
    private SudokuGame sudoku;
    private SudokuDisplay sudokuDisplay;
    private Timer time;
     
     @Override
    public void start(Stage stage) {
        sudoku = new SudokuGame();
        sudokuDisplay = new SudokuDisplay();
        time = new Timer();
        
        BorderPane menuLayout = new BorderPane();
        menuLayout.setStyle("-fx-background-color: linear-gradient(to top, #e66465, #9198e5);");
        
        VBox menu = new VBox(30);
        menu.setPadding(new Insets(100));
        
        Label welcome = new Label("Welcome to Sudoku!");
        Button play = new Button("Play");
        Button scores = new Button("Statistics");
        Button exit = new Button("Exit");
        
        welcome.setFont(Font.font("Lucida Sans Unicode", FontWeight.MEDIUM, 40));
        play.setFont(Font.font("Lucida Sans Unicode", 25));
        play.setMaxWidth(300);
        play.setStyle("-fx-background-color: #9198e5;" + "-fx-border-color: black");
        scores.setFont(Font.font("Lucida Sans Unicode", 25));
        scores.setMaxWidth(300);
        scores.setStyle("-fx-background-color: #9198e5;" + "-fx-border-color: black");
        exit.setFont(Font.font("Lucida Sans Unicode", 25));
        exit.setMaxWidth(300);
        exit.setStyle("-fx-background-color: #9198e5;" + "-fx-border-color: black");
        
        menu.setAlignment(Pos.CENTER);
        menu.getChildren().addAll(welcome, play, scores, exit);
        
        menuLayout.setCenter(menu);
        
        Scene menuScene = new Scene(menuLayout, 800, 600);
        
        BorderPane difficultyLayout = new BorderPane();
        difficultyLayout.setStyle("-fx-background-color: linear-gradient(to top, #e66465, #9198e5);");
        
        VBox difficultyMenu = new VBox(30);
        difficultyMenu.setPadding(new Insets(100));
        
        Label level = new Label("Choose difficulty:");
        Button easyPlay = new Button("Easy");
        Button mediumPlay = new Button("Medium");
        Button back = new Button("Back to menu");
        
        level.setFont(Font.font("Lucida Sans Unicode", FontWeight.MEDIUM, 40));
        easyPlay.setFont(Font.font("Lucida Sans Unicode", 25));
        easyPlay.setMaxWidth(300);
        easyPlay.setStyle("-fx-background-color: #9198e5;" + "-fx-border-color: black");
        mediumPlay.setFont(Font.font("Lucida Sans Unicode", 25));
        mediumPlay.setMaxWidth(300);
        mediumPlay.setStyle("-fx-background-color: #9198e5;" + "-fx-border-color: black");
        
        difficultyMenu.setAlignment(Pos.CENTER);
        difficultyMenu.getChildren().addAll(level, easyPlay, mediumPlay);
        
        HBox backButton = new HBox(30);
        backButton.setPadding(new Insets(20));
        back.setFont(Font.font("Lucida Sans Unicode", 20));
        back.setMaxWidth(200);
        back.setStyle("-fx-background-color: #9198e5;" + "-fx-border-color: black");
       
        backButton.getChildren().add(back);
        
        difficultyLayout.setCenter(difficultyMenu);
        difficultyLayout.setBottom(backButton);
        
        Scene difficultyScene = new Scene(difficultyLayout, 800, 600);
        
        BorderPane sudokuLayout = new BorderPane();
        sudokuLayout.setPadding(new Insets(25));
        sudokuLayout.setStyle("-fx-background-color: linear-gradient(to top, #e66465, #9198e5);");
        
        VBox buttons = new VBox(10);
        buttons.setPadding(new Insets(10));
        
        Button newSudoku = new Button("New game");
        Button check = new Button("Check");
        Button back2 = new Button("Back to menu");
        Button exit2 = new Button("Exit");
        
        newSudoku.setFont(Font.font("Lucida Sans Unicode", 20));
        newSudoku.setStyle("-fx-background-color: #9198e5;" + "-fx-border-color: black");
        newSudoku.setMaxWidth(170);
        newSudoku.setMinWidth(170);
        check.setFont(Font.font("Lucida Sans Unicode", 20));
        check.setStyle("-fx-background-color: #9198e5;" + "-fx-border-color: black");
        check.setMaxWidth(170);
        check.setMinWidth(170);
        back2.setFont(Font.font("Lucida Sans Unicode", 20));
        back2.setStyle("-fx-background-color: #9198e5;" + "-fx-border-color: black");
        back2.setMaxWidth(170);
        back2.setMinWidth(170);
        exit2.setFont(Font.font("Lucida Sans Unicode", 20));
        exit2.setStyle("-fx-background-color: #9198e5;" + "-fx-border-color: black");
        exit2.setMaxWidth(170);
        exit2.setMinWidth(170);
        
        buttons.getChildren().addAll(newSudoku, check, back2, exit2);
        buttons.setAlignment(Pos.BOTTOM_LEFT);
        
        VBox sudokuBottomView = new VBox(10);
        sudokuBottomView.setPadding(new Insets(20));
        
        Label solution = new Label();        
        solution.setFont(Font.font("Lucida Sans Unicode", 20));
        solution.setMaxWidth(310);
        solution.setMinWidth(310);
        solution.setMaxHeight(100);
        solution.setMinHeight(100);
       
        sudokuBottomView.getChildren().addAll(buttons, solution);
        sudokuBottomView.setAlignment(Pos.BOTTOM_LEFT);
        
        GridPane sudokuBoard = new GridPane();
        sudokuBoard.setAlignment(Pos.TOP_RIGHT);
         
        sudokuLayout.setCenter(sudokuBoard);
        sudokuLayout.setLeft(sudokuBottomView);
        sudokuLayout.setTop(time.getTime());
        
        Scene sudokuScene = new Scene(sudokuLayout, 850, 650);
        
        BorderPane exitLayout = new BorderPane();
        exitLayout.setStyle("-fx-background-color: linear-gradient(to top, #e66465, #9198e5);");
        
        HBox options = new HBox(10);
        options.setPadding(new Insets(10));
        
        Label exitGame = new Label("Are you sure you want to exit?\n"
                + "The Sudoku will not be saved.");
        Button yes = new Button("Yes");
        Button no = new Button("No");
        
        exitGame.setFont(Font.font("Lucida Sans Unicode", 25));
        yes.setFont(Font.font("Lucida Sans Unicode", 25));
        yes.setStyle("-fx-background-color: #9198e5;" + "-fx-border-color: black");
        yes.setMaxWidth(300);
        no.setFont(Font.font("Lucida Sans Unicode", 25));
        no.setStyle("-fx-background-color: #9198e5;" + "-fx-border-color: black");
        no.setMaxWidth(300);
        
        options.getChildren().addAll(yes, no);
        options.setAlignment(Pos.CENTER);
        
        VBox choose = new VBox(10);
        choose.setPadding(new Insets(10));
        
        choose.getChildren().addAll(exitGame, options);
        choose.setAlignment(Pos.CENTER);
        
        exitLayout.setCenter(choose);
        Scene exitScene = new Scene(exitLayout, 850, 650);
        
        play.setOnAction((event) -> {
            stage.setScene(difficultyScene);
        });
        
        exit.setOnAction((event) -> {
            stage.close();
        });
        
        easyPlay.setOnAction((event) -> {
            sudoku.setDifficulty(25);
            sudoku.newSudoku();
            sudokuDisplay.showSudoku(sudoku, sudokuBoard);
            stage.setScene(sudokuScene);
            time.start();
        });
        
        mediumPlay.setOnAction((event) -> {
            sudoku.setDifficulty(35);
            sudoku.newSudoku();
            sudokuDisplay.showSudoku(sudoku, sudokuBoard);
            stage.setScene(sudokuScene);
            time.start();
        });
        
        newSudoku.setOnAction((event) -> {
            time.reset();
            sudoku.newSudoku();
            sudokuDisplay.showSudoku(sudoku, sudokuBoard);
            solution.setText("");
            stage.setScene(sudokuScene);
            time.start();
        });
       
        check.setOnAction((event) -> {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    String value = sudokuDisplay.board[i][j].getText();
                    if(value.matches("1|2|3|4|5|6|7|8|9")) {
                       sudoku.setValue(i, j, Integer.parseInt(value));
                    }else {
                        break;
                    } 
                }
            }
           
            if(!sudoku.checkSudoku()) {
                solution.setText("Your Sudoku is incorrect :(\n"
                        + "Try again or start a new game.");
            }else {
                time.stop();
                solution.setText("Congratulations,\n"
                        + "you solved the Sudoku!\n"
                        + time.toString());
            }
            
        });
       
        back.setOnAction((event) -> {
            stage.setScene(menuScene);
            solution.setText("");
        });
        
        back2.setOnAction((event) -> {
            stage.setScene(difficultyScene);
            solution.setText("");
            time.reset();
        });
        
        exit2.setOnAction((event) -> {
            stage.setScene(exitScene);
            time.stop();
        });
        
        no.setOnAction((event) -> {
           stage.setScene(sudokuScene);
           time.continueTime();
        }); 
        
        yes.setOnAction((event) -> {
            stage.close();
        });
        
      
        stage.setScene(menuScene);
        stage.setTitle("Sudoku");
        stage.show();
        
    } 

    public static void main(String[] args) {
        launch();
    }
    
}
