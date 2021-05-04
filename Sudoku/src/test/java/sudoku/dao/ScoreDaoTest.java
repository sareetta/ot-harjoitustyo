/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku.dao;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
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
        test = new ScoreDao("jdbc:sqlite:./test.db", "Easy", "Medium");
    }
    
    @After
    public void tearDown() throws Exception{
        test.disconnect();
        Files.deleteIfExists(Paths.get("jdbc:sqlite:./test.db"));
    }
    
    @Test
    public void methodListWorks() {
        boolean list = test.list("Easy") instanceof List;
        assertTrue(list);
    }
    
    @Test
    public void createAddsRowsToTheDatabase() {
        int start = test.list("Easy").size();
        test.create(new SudokuScore(0, "test", "00:10"), "Easy");
        int end = test.list("Easy").size();
        assertTrue(end == start + 1);
    }
}
