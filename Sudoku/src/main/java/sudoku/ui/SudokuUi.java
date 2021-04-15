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
    
     @Override
    public void start(Stage stage) {
       SudokuGame sudoku = new SudokuGame();
       sudoku.print();
        
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
        play.setStyle("-fx-background-color: #9198e5");
        scores.setFont(Font.font("Lucida Sans Unicode", 25));
        scores.setMaxWidth(300);
        scores.setStyle("-fx-background-color: #9198e5");
        exit.setFont(Font.font("Lucida Sans Unicode", 25));
        exit.setMaxWidth(300);
        exit.setStyle("-fx-background-color: #9198e5");
        

        menu.setAlignment(Pos.CENTER);
        
        menu.getChildren().addAll(welcome, play, scores, exit);
        menuLayout.setCenter(menu);
        
        
        Scene menuScene = new Scene(menuLayout, 800, 600);
        stage.setScene(menuScene);
        stage.setTitle("Sudoku");
        stage.show();
       
    }

    public static void main(String[] args) {
        launch();
    }
    
}
