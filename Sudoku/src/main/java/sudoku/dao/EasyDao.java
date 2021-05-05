/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku.dao;

import java.sql.*;
import java.util.*;
import sudoku.domain.SudokuScore;

/**
 * Class for handling the Easy table in the database.
 * @author sareetta
 */
public class EasyDao implements SQLDao {
    private List<SudokuScore> scores;
    private DBHelper db;
    
    /**
     * Constructor.
     * Finds easy scores from the database.
     * 
     * @param db             The database.
     * @throws SQLException  If exception occurs. 
     */
    public EasyDao(DBHelper db) throws SQLException {
        scores = new ArrayList<>();
        this.db = db;
        db.connect();
        ResultSet rs = db.getResultSet("SELECT * FROM Easy");
        if (rs == null) {
            return;
        }
        db.close();
    }
    
    /**
     * Method for saving new scores to the database.
     * 
     * @param score The score to be added to the database.
     * @throws SQLException If exception occurs.
     */
    @Override
    public void save(SudokuScore score) throws SQLException {
        db.connect();
        db.update("INSERT INTO Easy (id,name,time) VALUES (?,?,?)", score.getId(),
                score.getName(), score.getTime());
        db.close();
        scores.add(score);
    }

    /**
     * Method for getting the list of the scores.
     * 
     * @return List of the scores.
     */
    @Override
    public List<SudokuScore> list() {
        return scores;
    }
    
}
