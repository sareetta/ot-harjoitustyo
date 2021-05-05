/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku.dao;

import java.sql.*;

/**
 * Class for handling the database.
 * 
 * @author sareetta
 */
public class DBHelper {
    private String dbUrl;
    private Connection db;
    private PreparedStatement ps;
    private ResultSet rs;

    /**
     * Constructor. 
     * Sets the database address and calls method create.
     * @param dbUrl       The address of the database.
     * @param easyTable   The name of first table.
     * @param mediumTable The name of the second table.
     */
    public DBHelper(String dbUrl, String easyTable, String mediumTable) {
        this.dbUrl = dbUrl;
        connect();
        init(easyTable, mediumTable);
        close();
    }

    /**
     * Method connects to the database.
     */
    public void connect() {
        try {
            if (db == null) {
                db = DriverManager.getConnection(dbUrl);
            } else {
                close();
                db = DriverManager.getConnection(dbUrl);
            }
        } catch (SQLException e) {
            System.out.println("Exception in connect: " + e);
        }
    }
   
    /**
     * Method creates the tables in the database if they don't exist.
     * 
     * @param easyTable    The table foe easy scores.
     * @param mediumTable  The table for medium scores.
     */
    public void init(String easyTable, String mediumTable) {
        try {
            Statement s = db.createStatement();
            String tableString = "CREATE TABLE IF NOT EXISTS " + easyTable + " (id SERIAL, name STRING, time, STRING);";
            s.execute(tableString);
            tableString = "CREATE TABLE IF NOT EXISTS " + mediumTable + " (id SERIAL, name STRING, time, STRING)";
            s.execute(tableString);
            s.close();
        } catch (SQLException e) {
            System.out.println("Exception in create: " + e);
        }
    }

    /**
     * Method for ResultSet.
     * 
     * @param statement Statement object.
     * @return  returns the ResultSet.
     */
    public ResultSet getResultSet(String statement) {
        rs = null;
        try {
            ps = db.prepareStatement(statement);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            System.out.println("Exception in getResultSet: " + e);
        }
        return rs;
    }
    
    /**
     * Method updates the database.
     * 
     * @param statement Wanted statement.
     * @param value1    First variable is the id.
     * @param value2    Second variable is the name.
     * @param value3    Third variable is the time.
     */
    public void update(String statement, int value1, String value2, String value3) {
        try {
            ps = db.prepareStatement(statement);
            ps.setInt(1, value1);
            ps.setString(2, value2);
            ps.setString(3, value3);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Exception in update: " + e);
        }
    }
    
    /**
     * Method closes connection to the database.
     */
    public void close() {
        try {
            db.close();
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            System.out.println("Exception in close: " + e);;
        }
    }
    
}
