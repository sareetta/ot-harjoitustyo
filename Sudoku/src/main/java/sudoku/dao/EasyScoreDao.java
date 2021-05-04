/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku.dao;

import java.sql.SQLException;

/**
 * Class for creating an easy score
 * 
 * @author sareetta
 */
public class EasyScoreDao extends ScoreDao {
    /**
     * Constructor method.
     * @param database   The used database.
     * @param tableName  The wanted table.
     * @throws SQLException if an error occurs.
     */
    public EasyScoreDao(String database, String tableName) throws SQLException {
        super(database, tableName);
    }
    
}
