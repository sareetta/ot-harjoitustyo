/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku.ui;

import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import sudoku.dao.ScoreDao;
import sudoku.domain.SudokuGame;
import sudoku.domain.SudokuScore;

/**
 *
 * @author sareetta
 */
public class SudokuUi extends Application {
    SudokuGame sudoku;
    SudokuDisplay sudokuDisplay;
    ScoreDao scoreDao;
    Timer time;
    
    @Override
    public void init() throws Exception {
        Properties prop = new Properties();
        prop.load(new FileInputStream("config.properties"));
        String sudokuDB = prop.getProperty("sudokuDB");
        String easyTable = prop.getProperty("easyTable");
        String mediumTable = prop.getProperty("mediumTable");

        String userWorkingDir = System.getProperty("user.dir");
        String fileSeparator = System.getProperty("file.separator");
        String dbUrl = "jdbc:sqlite:" + userWorkingDir + fileSeparator + sudokuDB;

        scoreDao = new ScoreDao(dbUrl, easyTable, mediumTable);
    }
    
    @Override
    public void start(Stage stage) throws Exception {
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
        
        Scene menuScene = new Scene(menuLayout, 850, 650);
        
        BorderPane playLayout = new BorderPane();
        playLayout.setStyle("-fx-background-color: linear-gradient(to top, #e66465, #9198e5);");
        
        VBox playMenu = new VBox(30);
        playMenu.setPadding(new Insets(100));
        
        Label level = new Label("Choose difficulty:");
        Button easyPlay = new Button("Easy");
        Button mediumPlay = new Button("Medium");
        Button back = new Button("Return to menu");
        
        level.setFont(Font.font("Lucida Sans Unicode", FontWeight.MEDIUM, 40));
        easyPlay.setFont(Font.font("Lucida Sans Unicode", 25));
        easyPlay.setMaxWidth(300);
        easyPlay.setStyle("-fx-background-color: #9198e5;" + "-fx-border-color: black");
        mediumPlay.setFont(Font.font("Lucida Sans Unicode", 25));
        mediumPlay.setMaxWidth(300);
        mediumPlay.setStyle("-fx-background-color: #9198e5;" + "-fx-border-color: black");
        
        playMenu.setAlignment(Pos.CENTER);
        playMenu.getChildren().addAll(level, easyPlay, mediumPlay);
        
        HBox backButton = new HBox(10);
        backButton.setPadding(new Insets(10));
        back.setFont(Font.font("Lucida Sans Unicode", 20));
        back.setStyle("-fx-background-color: #9198e5;" + "-fx-border-color: black");
        back.setMaxWidth(200);
       
        backButton.getChildren().add(back);
        
        playLayout.setCenter(playMenu);
        playLayout.setBottom(backButton);
        
        Scene playScene = new Scene(playLayout, 850, 650);
        
        BorderPane sudokuLayout = new BorderPane();
        sudokuLayout.setPadding(new Insets(25));
        sudokuLayout.setStyle("-fx-background-color: linear-gradient(to top, #e66465, #9198e5);");
        
        VBox buttons = new VBox(10);
        buttons.setPadding(new Insets(10));
        
        Button newSudoku = new Button("New game");
        Button check = new Button("Check");
        Button back2 = new Button("Return to menu");
        Button exit2 = new Button("Exit");
        
        newSudoku.setFont(Font.font("Lucida Sans Unicode", 20));
        newSudoku.setStyle("-fx-background-color: #9198e5;" + "-fx-border-color: black");
        newSudoku.setMaxWidth(200);
        newSudoku.setMinWidth(200);
        check.setFont(Font.font("Lucida Sans Unicode", 20));
        check.setStyle("-fx-background-color: #9198e5;" + "-fx-border-color: black");
        check.setMaxWidth(200);
        check.setMinWidth(200);
        back2.setFont(Font.font("Lucida Sans Unicode", 20));
        back2.setStyle("-fx-background-color: #9198e5;" + "-fx-border-color: black");
        back2.setMaxWidth(200);
        back2.setMinWidth(200);
        exit2.setFont(Font.font("Lucida Sans Unicode", 20));
        exit2.setStyle("-fx-background-color: #9198e5;" + "-fx-border-color: black");
        exit2.setMaxWidth(200);
        exit2.setMinWidth(200);
        
        buttons.getChildren().addAll(newSudoku, check, back2, exit2);
        buttons.setAlignment(Pos.BOTTOM_LEFT);
        
        VBox sudokuBottomView = new VBox(10);
        sudokuBottomView.setPadding(new Insets(20));
        
        Label incorrect = new Label();        
        incorrect.setFont(Font.font("Lucida Sans Unicode", 20));
        incorrect.setMaxWidth(310);
        incorrect.setMinWidth(310);
        incorrect.setMaxHeight(100);
        incorrect.setMinHeight(100);
       
        sudokuBottomView.getChildren().addAll(buttons, incorrect);
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
        
        Label exitGame = new Label("The Sudoku will not be saved.\n"
                + "Are you sure you want to exit?");
        Button yes = new Button("Yes");
        Button no = new Button("No");
        
        exitGame.setFont(Font.font("Lucida Sans Unicode", 20));
        yes.setFont(Font.font("Lucida Sans Unicode", 20));
        yes.setStyle("-fx-background-color: #9198e5;" + "-fx-border-color: black");
        yes.setMaxWidth(200);
        no.setFont(Font.font("Lucida Sans Unicode", 20));
        no.setStyle("-fx-background-color: #9198e5;" + "-fx-border-color: black");
        no.setMaxWidth(200);
        
        options.getChildren().addAll(yes, no);
        options.setAlignment(Pos.CENTER);
        
        VBox choose = new VBox(10);
        choose.setPadding(new Insets(10));
        
        choose.getChildren().addAll(exitGame, options);
        choose.setAlignment(Pos.CENTER);
        
        exitLayout.setCenter(choose);
        Scene exitScene = new Scene(exitLayout, 850, 650);
        
        BorderPane scoresLayout = new BorderPane();
        scoresLayout.setStyle("-fx-background-color: linear-gradient(to top, #e66465, #9198e5);");
        scoresLayout.setPadding(new Insets(20));
        
        VBox highScores = new VBox(10);
        highScores.setPadding(new Insets(10));
        highScores.setAlignment(Pos.CENTER);
        
        Button back3 = new Button("Return to menu");
        back3.setFont(Font.font("Lucida Sans Unicode", 20));
        back3.setStyle("-fx-background-color: #9198e5;" + "-fx-border-color: black");
        back3.setMaxWidth(200);
        back3.setMinWidth(200);
        Label scoresTitle = new Label("High scores: ");
        scoresTitle.setFont(Font.font("Lucida Sans Unicode", FontWeight.BOLD, 30));
        highScores.getChildren().add(scoresTitle);
        
        HBox highScoresLists = new HBox(10);
        highScoresLists.setPadding(new Insets(10));
        
        VBox mediumScoresList = new VBox(10);
        mediumScoresList.setPadding(new Insets(10));
        VBox easyScoresList = new VBox(10);
        easyScoresList.setPadding(new Insets(10));
        
        Label mediumTitle = new Label("Medium Difficulty: ");
        mediumTitle.setFont(Font.font("Lucida Sans Unicode", FontWeight.BOLD, 25));
        Label easyTitle = new Label("Easy Difficulty: ");
        easyTitle.setFont(Font.font("Lucida Sans Unicode", FontWeight.BOLD, 25));
        
        mediumScoresList.getChildren().add(mediumTitle);
        easyScoresList.getChildren().add(easyTitle);
        
        highScoresLists.getChildren().addAll(mediumScoresList, easyScoresList);
        highScoresLists.setAlignment(Pos.CENTER);
        
        highScores.getChildren().add(highScoresLists);
        
        scoresLayout.setCenter(highScores);
        scoresLayout.setBottom(back3);
        
        Scene statisticsScene = new Scene(scoresLayout, 850, 650);
        
        BorderPane recordLayout = new BorderPane();
        recordLayout.setStyle("-fx-background-color: linear-gradient(to top, #e66465, #9198e5);");
        recordLayout.setPadding(new Insets(20));
        
        VBox record = new VBox(10);
        record.setPadding(new Insets(10));
        
        Label correct = new Label();        
        correct.setFont(Font.font("Lucida Sans Unicode", 20));
        correct.setMaxWidth(300);
        correct.setMinWidth(300);
        TextArea nameArea = new TextArea("");
        nameArea.setMaxSize(300, 60);
        nameArea.setMinSize(300, 60);
        Label name = new Label("Add username above.");
        name.setFont(Font.font("Lucida Sans Unicode", 20));
        name.setMaxWidth(300);
        name.setMinWidth(300);
        Button save = new Button("Save");
        save.setFont(Font.font("Lucida Sans Unicode", 20));
        save.setStyle("-fx-background-color: #9198e5;" + "-fx-border-color: black");
        save.setMaxWidth(300);
        save.setMinWidth(300);
        
        
        record.getChildren().addAll(correct, name, nameArea, save);
        record.setAlignment(Pos.CENTER);
        
        recordLayout.setCenter(record);
        Scene recordsScene = new Scene(recordLayout, 850, 650);  
       
        
        play.setOnAction((event) -> {
            stage.setScene(playScene);
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
            incorrect.setText("");
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
                incorrect.setText("Your Sudoku is incorrect :(\n"
                        + "Try again or start a new game.");
            }else {
                time.stop();
                correct.setText("Congratulations,\n"
                        + "you solved the Sudoku!\n"
                        + time.toString());
                stage.setScene(recordsScene);
            }
            
   
        });
        
        save.setOnAction((ActionEvent event) -> {
            if (nameArea.getText().length() < 16 && nameArea.getText().length() > 0) {
                
                if (sudoku.getDifficulty() == 35) {
                    scoreDao.create(new SudokuScore(0, nameArea.getText(), time.Time()), "Medium");
                } else if (sudoku.getDifficulty() == 25) {
                  //  scoreDao.create(new SudokuScore(0, nameArea.getText(), time.Time()), "Easy");
                }
                
                nameArea.setText("");
                name.setText("Add your username above.");
            } else {
                nameArea.setText("Your name needs to be 1 - 15 characters!");
            }
            
            stage.setScene(menuScene); 
            });
                
        
        scores.setOnAction((event) -> {
            mediumScoresList.getChildren().clear();
            easyScoresList.getChildren().clear();
            mediumScoresList.getChildren().add(mediumTitle);
            easyScoresList.getChildren().add(easyTitle);
            List<SudokuScore> scoresMedium = scoreDao.list("Medium");
          //  List<SudokuScore> scoresEasy = scoreDao.list("Easy");
            
            if (scoresMedium.isEmpty()) {
                Label noScores = new Label("No scores!");
                noScores.setFont(Font.font("Lucida Sans Unicode", 20));
                mediumScoresList.getChildren().add(noScores);
            } else {
                for (int i = 0; i < scoresMedium.size(); i++) {
                    if ( i >= 10) {
                        break;
                    }
                    Label newRecord = new Label((i+1) + ". " + scoresMedium.get(i).toString());
                    newRecord.setFont(Font.font("Lucida Sans Unicode", 20));
                    mediumScoresList.getChildren().add(newRecord);
                }
            }
            
       /*     if (scoresEasy.isEmpty()) {
                Label noScores = new Label("No scores!");
                noScores.setFont(Font.font("Lucida Sans Unicode", 20));
                easyScoresList.getChildren().add(noScores);
            } else {
                for (int i = 0; i < scoresEasy.size(); i++) {
                    if ( i >= 10) {
                        break;
                    }
                    Label newRecord = new Label((i+1) + ". " + scoresEasy.get(i).toString());
                    newRecord.setFont(Font.font("Lucida Sans Unicode", 20));
                    easyScoresList.getChildren().add(newRecord);
                }
            }
            */
            stage.setScene(statisticsScene);
        }); 
        
        back.setOnAction((event) -> {
            stage.setScene(menuScene);
            incorrect.setText("");
        });
        
        back2.setOnAction((event) -> {
            stage.setScene(playScene);
            incorrect.setText("");
        });
        
        back3.setOnAction((event) -> {
            stage.setScene(menuScene);
        }); 
        
        exit2.setOnAction((event) -> {
            stage.setScene(exitScene);
            time.stop();
        });
        
        no.setOnAction((event) -> {
           stage.setScene(sudokuScene);
           time.replay();
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
