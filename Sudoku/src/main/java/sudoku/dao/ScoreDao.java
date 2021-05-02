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
 * Class responsible for the database
 * 
 * @author sareetta
 */
public class ScoreDao implements SQLDao{
    private String database;
    private String tableName;
    
    /**
     * Constructor method. Calls createTable to create a table if it doesn't exist to avoid errors.
     * @throws SQLException if an error occurs.
     */
    public ScoreDao(String database, String tableName) throws SQLException {
        this.database = database;
        this.tableName = tableName;
        createTable();
    }
    
    /**
     * Method adds a new score to the database.
     * @param score The name and time will be used for the new row to be added to the database.
     */
    @Override
    public void create(SudokuScore score) {
        try {
            Connection connection = DriverManager.getConnection(this.database);
            String strQuery = "INSERT INTO $tableName (name, time)"
                  + " VALUES (?, ?)";
            String query = strQuery.replace("$tableName",this.tableName);
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, score.getName());
            stmt.setString(2, score.getTime());
        
            stmt.executeUpdate();
            stmt.close();
            connection.close();
        
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Method used to list the scores in the table, sorted by time in an ascending order.
     * @return Returns a list of the scores in the database sorted by time in an ascending order.
     */
    @Override
    public List<SudokuScore> list() {
        List<SudokuScore> scores = new ArrayList<>();
        
        try {
            Connection connection = DriverManager.getConnection(this.database);
        
            String strQuery = "SELECT * FROM $tableName"
                  + " ORDER BY time ASC;";
            String query = strQuery.replace("$tableName",this.tableName);
            PreparedStatement stmt = connection.prepareStatement(query);
            
        
            ResultSet rs = stmt.executeQuery();
        
            while (rs.next()) {
                scores.add(new SudokuScore(rs.getInt("id"), rs.getString("name"), rs.getString("time")));
            }
        
            stmt.close();
            connection.close();
        
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return scores;
    }
    
    /**
     * Method that creates the table into the database if it does not yet exist.
     * @throws SQLException if an error occurs.
     */
    @Override
    public void createTable() throws SQLException {
        Connection connection = DriverManager.getConnection(this.database);
        
        String strQuery = ""
                + "CREATE TABLE IF NOT EXISTS $tableName"
                + " (id SERIAL,"
                + " name STRING,"
                + " time STRING)";
            String query = strQuery.replace("$tableName",this.tableName);
            PreparedStatement stmt = connection.prepareStatement(query);
      
        stmt.executeUpdate();
        stmt.close();
        connection.close();
    }
    
    /**
     * Method used to set path to the database. Used for testing the class.
     * @param database Path to the database to be used.
     */
    @Override
    public void setDatabase(String database) {
        this.database = database;
    }
    
    
}
