/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku.dao;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import sudoku.domain.SudokuScore;

/**
 *
 * @author sareetta
 */
public class ScoreDaoTest {
    private ScoreDao test;
    
    public ScoreDaoTest() {
    }
    
    
    @Before
    public void setUp() throws SQLException {
        test = new ScoreDao("jdbc:sqlite:./test.db");
        test.createTable("easy");
    }
    
    @After
    public void tearDown() throws Exception{
        test.disconnect();
        Files.deleteIfExists(Paths.get("jdbc:sqlite:./test.db"));
    }
    
    @Test
    public void methodListWorks() {
        boolean list = test.list("easy") instanceof List;
        assertTrue(list);
    }
    
    @Test
    public void createAddsRowsToTheDatabase() {
        int start = test.list("easy").size();
        test.create(new SudokuScore(0, "test", "00:10"), "easy");
        int end = test.list("easy").size();
        assertTrue(end == start + 1);
    }
}
   
