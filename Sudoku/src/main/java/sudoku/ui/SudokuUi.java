/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku.ui;

import java.io.FileInputStream;
import java.sql.SQLException;
import java.util.ArrayList;
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
import sudoku.dao.DBScore;
import sudoku.dao.ScoreDao;
import sudoku.domain.SudokuGame;
import sudoku.domain.SudokuScore;

/**
 * Class for user interface.
 * 
 * @author sareetta
 */
public class SudokuUi extends Application {
    SudokuGame sudoku;
    SudokuDisplay sudokuDisplay;
    ScoreDao easyDao;
    ScoreDao mediumDao;
    ScoreDao hardDao;
    Timer time;
    
    private GridPane sudokuBoard;
    
    private Label incorrect;
    private Label correct;
    private Label easyTitle;
    private Label mediumTitle;
    private Label hardTitle;
    
    private VBox easyScoresList;
    private VBox mediumScoresList;
    private VBox hardScoresList;
   
    private Scene menuScene;
    private Scene difficultyScene;
    private Scene sudokuScene;
    private Scene recordScene;
    private Scene statisticsScene;
    private Scene exitScene;
    
    
    /**
     * Database initialization.
     * 
     * @throws Exception If exception occurs.
     */
    @Override
    public void init() throws Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream("config.properties"));
        String sudokuDB = properties.getProperty("sudokuDB");
        String easyTable = properties.getProperty("easyTable");
        String mediumTable = properties.getProperty("mediumTable");
        String hardTable = properties.getProperty("hardTable");

        String ud = System.getProperty("user.dir");
        String fs = System.getProperty("file.separator");
        String dbUrl = "jdbc:sqlite:" + ud + fs + sudokuDB;

        DBScore db = new DBScore(dbUrl, easyTable, mediumTable, hardTable);
        easyDao = new ScoreDao(db, easyTable);
        mediumDao = new ScoreDao(db, mediumTable);
        hardDao = new ScoreDao(db, hardTable);
        
    }
    
    /**
     * Creates the start scene.
     * 
     * @param stage The window.
     */
    private void menuScene(Stage stage) {
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
        
        menuScene = new Scene(menuLayout, 850, 650);
        
        play.setOnAction((event) -> {
            stage.setScene(difficultyScene);
        });
        
        scores.setOnAction((event) -> {
            listScores(easyDao, "Easy", easyScoresList, easyTitle);
            listScores(mediumDao, "Medium", mediumScoresList, mediumTitle);
            listScores(hardDao, "Hard", hardScoresList, hardTitle);
        
            stage.setScene(statisticsScene);
        }); 
        
        exit.setOnAction((event) -> {
            stage.close();
        });
    }
    
    /**
     * Creates the scene where player chooses the difficulty.
     * 
     * @param stage The window.
     */
    private void difficultyScene(Stage stage) {
        BorderPane difficultyLayout = new BorderPane();
        difficultyLayout.setStyle("-fx-background-color: linear-gradient(to top, #e66465, #9198e5);");
        
        VBox playMenu = new VBox(30);
        playMenu.setPadding(new Insets(100));
        
        Label level = new Label("Choose difficulty:");
        Button easyPlay = new Button("Easy");
        Button mediumPlay = new Button("Medium");
        Button hardPlay = new Button("Hard");
        Button back = new Button("Return to menu");
        
        level.setFont(Font.font("Lucida Sans Unicode", FontWeight.MEDIUM, 40));
        easyPlay.setFont(Font.font("Lucida Sans Unicode", 25));
        easyPlay.setMaxWidth(300);
        easyPlay.setStyle("-fx-background-color: #9198e5;" + "-fx-border-color: black");
        mediumPlay.setFont(Font.font("Lucida Sans Unicode", 25));
        mediumPlay.setMaxWidth(300);
        mediumPlay.setStyle("-fx-background-color: #9198e5;" + "-fx-border-color: black");
        hardPlay.setFont(Font.font("Lucida Sans Unicode", 25));
        hardPlay.setMaxWidth(300);
        hardPlay.setStyle("-fx-background-color: #9198e5;" + "-fx-border-color: black");
        
        playMenu.setAlignment(Pos.CENTER);
        playMenu.getChildren().addAll(level, easyPlay, mediumPlay, hardPlay);
        
        HBox backButton = new HBox(10);
        backButton.setPadding(new Insets(10));
        back.setFont(Font.font("Lucida Sans Unicode", 20));
        back.setStyle("-fx-background-color: #9198e5;" + "-fx-border-color: black");
        back.setMaxWidth(200);
       
        backButton.getChildren().add(back);
        
        difficultyLayout.setCenter(playMenu);
        difficultyLayout.setBottom(backButton);
        
        difficultyScene = new Scene(difficultyLayout, 850, 650);
        
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
        
        hardPlay.setOnAction((event) -> {
            sudoku.setDifficulty(45);
            sudoku.newSudoku();
            sudokuDisplay.showSudoku(sudoku, sudokuBoard);
            stage.setScene(sudokuScene);
            time.start();
        });
        
        back.setOnAction((event) -> {
            stage.setScene(menuScene);
            incorrect.setText("");
        });
    }
    
    /**
     * Creates the Sudoku scene.
     * 
     * @param stage The window.
     */
    private void sudokuScene(Stage stage) {
        sudoku = new SudokuGame();
        sudokuDisplay = new SudokuDisplay();
        
        BorderPane sudokuLayout = new BorderPane();
        sudokuLayout.setPadding(new Insets(25));
        sudokuLayout.setStyle("-fx-background-color: linear-gradient(to top, #e66465, #9198e5);");
        
        VBox buttons = new VBox(10);
        buttons.setPadding(new Insets(10));
        
        Button newSudoku = new Button("New game");
        Button check = new Button("Check");
        Button back = new Button("Return to menu");
        Button exit = new Button("Exit");
        
        newSudoku.setFont(Font.font("Lucida Sans Unicode", 20));
        newSudoku.setStyle("-fx-background-color: #9198e5;" + "-fx-border-color: black");
        newSudoku.setMaxWidth(200);
        newSudoku.setMinWidth(200);
        check.setFont(Font.font("Lucida Sans Unicode", 20));
        check.setStyle("-fx-background-color: #9198e5;" + "-fx-border-color: black");
        check.setMaxWidth(200);
        check.setMinWidth(200);
        back.setFont(Font.font("Lucida Sans Unicode", 20));
        back.setStyle("-fx-background-color: #9198e5;" + "-fx-border-color: black");
        back.setMaxWidth(200);
        back.setMinWidth(200);
        exit.setFont(Font.font("Lucida Sans Unicode", 20));
        exit.setStyle("-fx-background-color: #9198e5;" + "-fx-border-color: black");
        exit.setMaxWidth(200);
        exit.setMinWidth(200);
        
        buttons.getChildren().addAll(newSudoku, check, back, exit);
        buttons.setAlignment(Pos.BOTTOM_LEFT);
        
        VBox sudokuBottomView = new VBox(10);
        sudokuBottomView.setPadding(new Insets(20));
        
        incorrect = new Label();        
        incorrect.setFont(Font.font("Lucida Sans Unicode", 20));
        incorrect.setMaxWidth(310);
        incorrect.setMinWidth(310);
        incorrect.setMaxHeight(100);
        incorrect.setMinHeight(100);
       
        sudokuBottomView.getChildren().addAll(buttons, incorrect);
        sudokuBottomView.setAlignment(Pos.BOTTOM_LEFT);
        
        sudokuBoard = new GridPane();
        sudokuBoard.setAlignment(Pos.TOP_RIGHT);
         
        sudokuLayout.setCenter(sudokuBoard);
        sudokuLayout.setLeft(sudokuBottomView);
        sudokuLayout.setTop(time.getTime());
        
        sudokuScene = new Scene(sudokuLayout, 850, 650);
        
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
                if (value.matches("1|2|3|4|5|6|7|8|9")) {
                    sudoku.setValue(i, j, Integer.parseInt(value));
                }else {
                    break;
                } 
            }
        }
            if(!sudoku.checkSudoku()) {
                incorrect.setText("Your Sudoku is incorrect :(\n"
                        + "Try again or start a new game.");
            } else {
                time.stop();
                correct.setText("Congratulations,\n"
                        + "you solved the Sudoku!\n"
                        + time.toString());
                stage.setScene(recordScene);
            }
        });
        
        back.setOnAction((event) -> {
            stage.setScene(difficultyScene);
            incorrect.setText("");
        });
        
        exit.setOnAction((event) -> {
            stage.setScene(exitScene);
            time.stop();
        });
    }
    
    /**
     * Creates the score -saving scene.
     * 
     * @param stage The window.
     */
    private void recordScene(Stage stage) {
        BorderPane recordLayout = new BorderPane();
        recordLayout.setStyle("-fx-background-color: linear-gradient(to top, #e66465, #9198e5);");
        recordLayout.setPadding(new Insets(20));
        
        VBox record = new VBox(10);
        record.setPadding(new Insets(10));
        
        correct = new Label();        
        correct.setFont(Font.font("Lucida Sans Unicode", 20));
        correct.setMaxWidth(300);
        correct.setMinWidth(300);
        TextArea nameArea = new TextArea("");
        Font font1 = new Font("Lucida Sans Unicode", 20);
        nameArea.setFont(font1);
        nameArea.setMaxHeight(50);
        nameArea.setMinHeight(50);
        nameArea.setMinWidth(300);
        nameArea.setMaxWidth(300);
        Label name = new Label("Enter your username.");
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
        recordScene = new Scene(recordLayout, 850, 650);  
        
        save.setOnAction((ActionEvent event) -> {
            if (nameArea.getText().length() < 11 && nameArea.getText().length() > 0) {
                switch (sudoku.getDifficulty()) {
                    case 25:
                        saveScores(easyDao, "Easy", nameArea);
                        break;
                    case 35:
                        saveScores(mediumDao, "Medium", nameArea);
                        break;
                    default:
                        saveScores(hardDao, "Hard", nameArea);
                        break;
                }
               
               name.setText("Enter your username.");
               stage.setScene(menuScene);
            } else {
                Font font2 = new Font("Lucida Sans Unicode", 13);
                nameArea.setFont(font2);
                nameArea.setText("Your name needs to be 1 - 10 characters!");
            }
        });
    }
    
    /**
     * Creates the exit scene.
     * 
     * @param stage The window.
     */
    private void exitScene(Stage stage) {
        BorderPane exitLayout = new BorderPane();
        exitLayout.setStyle("-fx-background-color: linear-gradient(to top, #e66465, #9198e5);");
        
        HBox options = new HBox(10);
        options.setPadding(new Insets(10));
        
        Label exitGame = new Label("Are you sure you want to exit?\n"
                + "The Sudoku will not be saved.");
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
        exitScene = new Scene(exitLayout, 850, 650);
        
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
    
    /**
     * Creates the statistics scene.
     * 
     * @param stage The window.
     */
    private void statisticsScene(Stage stage) {
        BorderPane scoresLayout = new BorderPane();
        scoresLayout.setStyle("-fx-background-color: linear-gradient(to top, #e66465, #9198e5);");
        scoresLayout.setPadding(new Insets(20));
        
        VBox highScores = new VBox(10);
        highScores.setPadding(new Insets(10));
        highScores.setAlignment(Pos.CENTER);
        
        Button back = new Button("Return to menu");
        back.setFont(Font.font("Lucida Sans Unicode", 20));
        back.setStyle("-fx-background-color: #9198e5;" + "-fx-border-color: black");
        back.setMaxWidth(200);
        back.setMinWidth(200);
        Label scoresTitle = new Label("High scores: ");
        scoresTitle.setFont(Font.font("Lucida Sans Unicode", FontWeight.BOLD, 30));
        highScores.getChildren().add(scoresTitle);
        
        HBox highScoresLists = new HBox(10);
        highScoresLists.setPadding(new Insets(5));
        
        easyScoresList = new VBox(10);
        easyScoresList.setPadding(new Insets(10));
        mediumScoresList = new VBox(10);
        mediumScoresList.setPadding(new Insets(10));
        hardScoresList = new VBox(10);
        hardScoresList.setPadding(new Insets(10));
        
        easyTitle = new Label("Easy Difficulty: ");
        easyTitle.setFont(Font.font("Lucida Sans Unicode", FontWeight.BOLD, 20));
        mediumTitle = new Label("Medium Difficulty: ");
        mediumTitle.setFont(Font.font("Lucida Sans Unicode", FontWeight.BOLD, 20));
        hardTitle = new Label("Hard Difficulty: ");
        hardTitle.setFont(Font.font("Lucida Sans Unicode", FontWeight.BOLD, 20));
        
        easyScoresList.getChildren().add(easyTitle);
        mediumScoresList.getChildren().add(mediumTitle);
        hardScoresList.getChildren().add(hardTitle);
        
        highScoresLists.getChildren().addAll(easyScoresList, mediumScoresList, hardScoresList);
        highScoresLists.setAlignment(Pos.CENTER);
        
        highScores.getChildren().add(highScoresLists);
        
        scoresLayout.setCenter(highScores);
        scoresLayout.setBottom(back);
        
        statisticsScene = new Scene(scoresLayout, 850, 650);
        
        back.setOnAction((event) -> {
            stage.setScene(menuScene);
        }); 
    }
    
    /**
     * Creates the user interface and opens the application.
     * @param stage       The application window.
     * @throws Exception  If exception occurs.
     */
    @Override
    public void start(Stage stage) throws Exception {
        time = new Timer();
 
        menuScene(stage);
        difficultyScene(stage);
        sudokuScene(stage);
        recordScene(stage);
        exitScene(stage);
        statisticsScene(stage);
    }
   
    /**
     * Method lists scores to the Statistics view.
     * @param dao        The table to be listed.
     * @param difficulty The difficulty of the game.
     * @param scoreList The VBox for the scores.
     */
    public void listScores(ScoreDao dao, String difficulty, VBox scoreList, Label title) {
        scoreList.getChildren().clear();
        scoreList.getChildren().add(title);
        List<SudokuScore> list = new ArrayList<>();
        try {
            list = dao.list(difficulty);
            } catch (SQLException e) {
                System.out.println("Exception in listing: " + e);
        }
        if (list.isEmpty()) {
                Label noScores = new Label("No scores!");
                noScores.setFont(Font.font("Lucida Sans Unicode", 20));
                scoreList.getChildren().add(noScores);
        } else {
            for (int i = 0; i < list.size(); i++) {
                if ( i >= 10) {
                    break;
                }
            Label newRecord = new Label((i+1) + ". " + list.get(i).toString());
            newRecord.setFont(Font.font("Lucida Sans Unicode", 20));
            scoreList.getChildren().add(newRecord);
            }
        }
        list.clear();
    }
 
    /**
     * 
     * Method for saving Sudoku -scores.
     * 
     * @param dao         The table where the score will be saved.
     * @param difficulty  The difficulty of the game.
     * @param nameArea    The text area for username input.
     */
    public void saveScores(ScoreDao dao, String difficulty, TextArea nameArea) {
        try {
            dao.save(new SudokuScore(0, nameArea.getText(), time.Time()), difficulty);
        } catch (SQLException e) {
            System.out.println("Exception in saving: " + e);
        } 
        
        nameArea.setText("");
    }
    
    /**
     * Method for launching the application.
     * @param args 
     */
    public static void main(String[] args) {
        launch();
    }
    
}
