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
 * Interface for the database
 * 
 * @author sareetta
 */
public interface SQLDao {
    /**
     * Method that creates the table into the database if it does not yet exist.
     * @param tableName
     * @throws SQLException if an error occurs.
    */
    public void createTable(String tableName) throws SQLException;
    
    /**
     * Method adds a new score to the database table.
     * @param score The name and time will be used for the new row to be added to the database.
     * @param tableName
     */
    public void create(SudokuScore score, String tableName);
    
    /**
     * Method used to list the scores in the database table, sorted by time in an ascending order.
     * @param tableName
     * @return Returns a list of the scores in the database sorted by time.
     */
    public List<SudokuScore> list(String tableName);
    
    
}
