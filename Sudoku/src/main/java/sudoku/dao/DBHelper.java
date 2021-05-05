/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku.dao;

import java.sql.*;

/**
 *
 * @author sareetta
 */
public class DBHelper {
    private Connection db;
    private String dbUrl;
    private PreparedStatement ps;
    private ResultSet rs;

    public DBHelper(String dbUrl, String easyTable, String mediumTable) {
        this.dbUrl = dbUrl;
        connect();
        createTables(easyTable, mediumTable);
        close();
    }

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
            System.out.println("Exception in disconnect: " + e);;
        }
    }

    private void createTables(String easyTable, String mediumTable) {
        try {
            Statement s = db.createStatement();
            String tableString = "CREATE TABLE IF NOT EXISTS " + easyTable + " (id SERIAL, name STRING, time, STRING);";
            s.execute(tableString);
            tableString = "CREATE TABLE IF NOT EXISTS " + mediumTable + " (id SERIAL, name STRING, time, STRING)";
            s.execute(tableString);
            s.close();
        } catch (SQLException e) {
            System.out.println("Exception in createTables: " + e);
        }
    }

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
    
}
