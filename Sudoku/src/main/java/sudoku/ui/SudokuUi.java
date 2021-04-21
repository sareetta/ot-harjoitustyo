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
     
     @Override
    public void start(Stage stage) {
        sudoku = new SudokuGame();
        sudokuDisplay = new SudokuDisplay();
        
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
        
        BorderPane playLayout = new BorderPane();
        playLayout.setStyle("-fx-background-color: linear-gradient(to top, #e66465, #9198e5);");
        
        VBox playMenu = new VBox(30);
        playMenu.setPadding(new Insets(100));
        
        Label level = new Label("Choose difficulty:");
        Button easyPlay = new Button("Easy");
        Button back = new Button("Back to menu");
        
        level.setFont(Font.font("Lucida Sans Unicode", FontWeight.MEDIUM, 40));
        easyPlay.setFont(Font.font("Lucida Sans Unicode", 25));
        easyPlay.setMaxWidth(300);
        easyPlay.setStyle("-fx-background-color: #9198e5;" + "-fx-border-color: black");
        
        playMenu.setAlignment(Pos.CENTER);
        playMenu.getChildren().addAll(level, easyPlay);
        
        HBox backButton = new HBox(30);
        backButton.setPadding(new Insets(20));
        back.setFont(Font.font("Lucida Sans Unicode", 25));
        back.setMaxWidth(300);
        back.setStyle("-fx-background-color: #9198e5;" + "-fx-border-color: black");
       
        backButton.getChildren().add(back);
        
        playLayout.setCenter(playMenu);
        playLayout.setBottom(backButton);
        
        Scene playScene = new Scene(playLayout, 800, 600);
        
        HBox sudokuLayout = new HBox();
        sudokuLayout.setStyle("-fx-background-color: linear-gradient(to top, #e66465, #9198e5);");
        
        VBox buttons = new VBox(10);
        buttons.setPadding(new Insets(30));
        
        Button newSudoku = new Button("New game");
        Button check = new Button("Check");
        Button back2 = new Button("Back");
        
        newSudoku.setFont(Font.font("Lucida Sans Unicode", 20));
        newSudoku.setStyle("-fx-background-color: #9198e5;" + "-fx-border-color: black");
        newSudoku.setMaxWidth(200);
        check.setFont(Font.font("Lucida Sans Unicode", 20));
        check.setStyle("-fx-background-color: #9198e5;" + "-fx-border-color: black");
        check.setMaxWidth(200);
        back2.setFont(Font.font("Lucida Sans Unicode", 20));
        back2.setStyle("-fx-background-color: #9198e5;" + "-fx-border-color: black");
        back2.setMaxWidth(200);
        
        buttons.getChildren().addAll(newSudoku, check, back2);
        buttons.setAlignment(Pos.BOTTOM_LEFT);
        
        GridPane sudokuBoard = new GridPane();
        sudokuBoard.setPadding(new Insets(70));
        sudokuBoard.setAlignment(Pos.CENTER_RIGHT);  
         
        sudokuLayout.getChildren().addAll(buttons,sudokuBoard);
        sudokuLayout.setAlignment(Pos.CENTER);
        
        Scene sudokuScene = new Scene(sudokuLayout, 800, 600);
        
        play.setOnAction((event) -> {
            stage.setScene(playScene);
        });
        
        exit.setOnAction((event) -> {
            stage.close();
        });
        
        easyPlay.setOnAction((event) -> {
            sudoku.newSudoku();
            sudoku.setDifficulty(36);
            sudokuDisplay.show(sudoku, sudokuBoard);
            stage.setScene(sudokuScene);
        });
        
        back.setOnAction((event) -> {
            stage.setScene(menuScene);
        });
        
        back2.setOnAction((event) -> {
            stage.setScene(playScene);
        });
        
        stage.setScene(menuScene);
        stage.setTitle("Sudoku");
        stage.show();
       
    }

    public static void main(String[] args) {
        launch();
    }
    
}
