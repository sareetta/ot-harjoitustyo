/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku.dao;

import java.sql.SQLException;
import java.util.List;
import sudoku.domain.SudokuScore;

/**
 *
 * @author sareetta
 */
public interface SQLDao {
    void save(SudokuScore score) throws SQLException;

    List<SudokuScore> list();
}
