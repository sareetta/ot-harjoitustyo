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
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import sudoku.domain.SudokuScore;


/**
 *
 * @author sareetta
 */
public class MediumDaoTest {
    DBHelper db;
    MediumDao medium;
    
    public MediumDaoTest() {
    }
    
   
    @Before
    public void setUp() {
        String dbUrl = "jdbc:sqlite:" + System.getProperty("user.dir") + System.getProperty("file.separator") + "test.db";
        db = new DBHelper(dbUrl, "Easy", "Medium");
        try {
            medium = new MediumDao(db);
        } catch (SQLException e) {
            assert false;
        }
        db.connect();
    }
    
    @Test
    public void methodSaveWorks() {
        try {
            medium.save(new SudokuScore(1,"rs","00:10"));
        } catch (SQLException e) {
            assert false;
        }
        db.connect();
        ResultSet rs = db.getResultSet("SELECT * FROM Medium");
        if (rs == null) assert false;
        try {
            assertTrue(rs.getString("name").equals("rs"));
        } catch (SQLException e) {
            assert false;
        }
    }
    @Test 
    public void methodListWorks() {
        try {
            medium.save(new SudokuScore(1,"rs","00:10"));
        } catch (SQLException e) {
            assert false;
        }
        assertTrue(medium.list().get(0).getName().equals("rs"));
        
    }
    
    @After
    public void tearDown() throws IOException {
        db.close();
        Files.deleteIfExists(Paths.get(System.getProperty("user.dir") + System.getProperty("file.separator") + "test.db"));
    }

   
}
