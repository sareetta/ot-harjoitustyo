/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku.dao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


/**
 *
 * @author sareetta
 */
public class DBScoreTest {
    DBScore db;
    
    public DBScoreTest() {
    }
    
    @Before
    public void setUp() {
        String dbUrl = "jdbc:sqlite:" + System.getProperty("user.dir") + System.getProperty("file.separator") + "test.db";
        db = new DBScore(dbUrl, "Easy", "Medium");
        db.connect();
    }
  
    @Test
    public void creatingTableWorks() {
        db.init("Test1", "Test2");
        ResultSet rs = db.select("SELECT * FROM Test1");
        assertTrue(rs != null);
        
    }
    @Test
    public void updatingDatabaseWorks() throws SQLException {
        db.update("INSERT INTO Medium (id,name,time) VALUES (?,?,?)", 1, "rs", "00:10");
        assertEquals("rs", db.select("SELECT * FROM Medium").getString(2));
    }
    @Test
    public void getResultSetreturnsResultSet() {
        ResultSet rs = db.select("SELECT * FROM Easy");
        assertTrue(rs instanceof ResultSet);
    }
    
    @After
    public void tearDown() throws IOException {
        db.close();
        Files.deleteIfExists(Paths.get(System.getProperty("user.dir") + System.getProperty("file.separator") + "test.db"));
    }

}
