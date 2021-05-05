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
 *
 * @author sareetta
 */
public class DBMediumDao implements SQLDao{
    public List<SudokuScore> scores;
    private DBHelper db;
    
    public DBMediumDao(DBHelper db) throws SQLException {
        scores = new ArrayList<>();
        this.db = db;
        db.connect();
        ResultSet rs = db.getResultSet("SELECT * FROM Medium");
        if (rs == null) {
            return;
        }
        db.close();
    }
    
    @Override
    public void save(SudokuScore score) throws SQLException {
        db.connect();
        db.update("INSERT INTO Medium (id,name,time) VALUES (?,?,?)", score.getId(),
                score.getName(), score.getTime());
        db.close();
        scores.add(score);
    }

    @Override
    public List<SudokuScore> list() {
        return scores;
    }
    
}
