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
public class ScoreDao implements SQLDao {
    private String database;
    private Connection db;
    private PreparedStatement p;
    private ResultSet rs;
    
    /**
     * Constructor method. Calls createTable to create a table if it doesn't exist to avoid errors.
     * @throws SQLException if an error occurs.
     */
    public ScoreDao(String database) throws SQLException {
        this.database = database;
        connect();
        createTable();
        disconnect();
    }
    
    /**
     * Method connects to the database.
     */
    public void connect() {
        try {
            if (db == null) {
                db = DriverManager.getConnection(this.database);
            } else {
                disconnect();
                db = DriverManager.getConnection(this.database);
            }
        } catch (SQLException e) {
            System.out.println("Exception in connect: " + e);
        }
    }

    /**
     * Method closes the connection to the database.
     */
    public void disconnect() {
        try {
            db.close();
            if (p != null) {
                p.close();
            }
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            System.out.println("Exception in disconnect: " + e);;
        }
    }
    
    /**
     * Method adds a new score to the database.
     * @param score The name and time will be used for the new row to be added to the database.
     */
    @Override
    public void create(SudokuScore score, String tableName) {
        try {
            connect();
            String strQuery = "INSERT INTO $tableName (name, time)"
                  + " VALUES (?, ?)";
            String query = strQuery.replace("$tableName", tableName);
            p = db.prepareStatement(query);
            p.setString(1, score.getName());
            p.setString(2, score.getTime());
        
            p.executeUpdate();
            disconnect();
        
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Method used to list the scores in the table, sorted by time in an ascending order.
     * @return Returns a list of the scores in the database sorted by time in an ascending order.
     */
    @Override
    public List<SudokuScore> list(String tableName) {
        List<SudokuScore> scores = new ArrayList<>();
        try {
            connect();
            String strQuery = "SELECT * FROM $tableName"
                  + " ORDER BY time ASC;";
            String query = strQuery.replace("$tableName", tableName);
            p = db.prepareStatement(query);
            
            rs = p.executeQuery();
        
            while (rs.next()) {
                scores.add(new SudokuScore(rs.getInt("id"), rs.getString("name"), rs.getString("time")));
            }
        
            disconnect();
        
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
        Statement s = db.createStatement();
        String strQuery = ""
                + "CREATE TABLE IF NOT EXISTS Easy"
                + " (id SERIAL,"
                + " name STRING,"
                + " time STRING)";
        s.execute(strQuery);
        
        strQuery = ""
                + "CREATE TABLE IF NOT EXISTS Medium"
                + " (id SERIAL,"
                + " name STRING,"
                + " time STRING)";
        s.execute(strQuery);
        s.close();
    }
    
}
