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
 * Class for handling the Medium table in the database.
 * 
 * @author sareetta
 */
public class MediumDao implements SQLDao {
    public List<SudokuScore> scores;
    private DBHelper db;
    
    /**
     * Constructor.
     * Finds medium scores from the database.
     * @param db             The database.
     * @throws SQLException  If exception occurs.
     */
    public MediumDao(DBHelper db) throws SQLException {
        scores = new ArrayList<>();
        this.db = db;
        db.connect();
        ResultSet rs = db.getResultSet("SELECT * FROM Medium");
        if (rs == null) {
            return;
        }
        db.close();
    }
    
    /**
     * Method for saving a score to the database.
     * 
     * @param score         The score to be saved.
     * @throws SQLException If exception occurs.
     */
    @Override
    public void save(SudokuScore score) throws SQLException {
        db.connect();
        db.update("INSERT INTO Medium (id,name,time) VALUES (?,?,?)", score.getId(),
                score.getName(), score.getTime());
        db.close();
        scores.add(score);
    }

    /**
     * Method for getting the list of the scores.
     * @return  List of the scores.
     */
    @Override
    public List<SudokuScore> list() {
        return scores;
    }
    
}
