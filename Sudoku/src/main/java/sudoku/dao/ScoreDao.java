/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku.dao;

import java.sql.*;
import java.util.*;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import sudoku.domain.SudokuScore;

/**
 * Class for handling the tables in the database.
 * @author sareetta
 */
public class ScoreDao implements SQLDao {
    private DBScore db;
    List<SudokuScore> scores;
    
    /**
     * Constructor. Finds easy scores from the database.
     * 
     * @param db             The database.
     * @param tableName      The table from database.
     * @throws SQLException  If exception occurs. 
     */
    public ScoreDao(DBScore db, String tableName) throws SQLException {
        this.db = db;
        scores = new ArrayList<>();
        db.connect();
        String strQuery = "SELECT * FROM $tableName";
        String query = strQuery.replace("$tableName", tableName);
        ResultSet rs = db.select(query);
        if (rs == null) {
            return;
        }
        db.close();
    }
    
    /**
     * Method for saving new scores to the database.
     * 
     * @param tableName     The table from the database.
     * @param score         The score to be added to the database.
     * @throws SQLException If exception occurs.
     */
    @Override
    public void save(SudokuScore score, String tableName) throws SQLException {
        db.connect();
        String strQuery = "INSERT INTO $tableName (id,name,time) VALUES (?,?,?)";
        String query = strQuery.replace("$tableName", tableName);
        db.update(query, score.getId(), score.getName(), score.getTime());
        db.close();
    }

    /**
     * Method for getting the list of the scores.
     * 
     * @param tableName     The table from the database.
     * @return              List of the scores.
     * @throws SQLException If exception occurs.
     */
    @Override
    public List<SudokuScore> list(String tableName) throws SQLException {
        db.connect();
        String strQuery = "SELECT * FROM $tableName ORDER BY time ASC;";
        String query = strQuery.replace("$tableName", tableName);
        ResultSet rs = db.select(query);
    
        while (rs.next()) {
            scores.add(new SudokuScore(rs.getInt("id"), rs.getString("name"), rs.getString("time")));
        } 
        db.close();
        
        return scores;
    }
    
    public void listScores(VBox scoreList) {
        if (scores.isEmpty()) {
                Label noScores = new Label("No scores!");
                noScores.setFont(Font.font("Lucida Sans Unicode", 20));
                scoreList.getChildren().add(noScores);
        } else {
            for (int i = 0; i < scores.size(); i++) {
                if ( i >= 10) {
                    break;
                }
            Label newRecord = new Label((i+1) + ". " + scores.get(i).toString());
            newRecord.setFont(Font.font("Lucida Sans Unicode", 20));
            scoreList.getChildren().add(newRecord);
            }
        }
    }
    
}
