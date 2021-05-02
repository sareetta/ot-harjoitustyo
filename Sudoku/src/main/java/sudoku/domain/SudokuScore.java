/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku.domain;

/**
 * Class for scores
 * @author sareetta
 */
public class SudokuScore {
    private int id;
    private String name;
    private String time;

    /**
     * The constructor.
     * Sets the id, name and time variables of the score.
     * @param id   Unique number used to identify scores. When creating a score and adding it to the database, 
     * @param name The name of the user.
     * @param time Time spent solving the Sudoku.
     */
    public SudokuScore(int id, String name, String time) {
        this.id = id;
        this.name = name;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }
    
    public int getId() {
        return id;
    }
    
    @Override
    public String toString() {
        return this.getName() + ": " + time;
    }
}
