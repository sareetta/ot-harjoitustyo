/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku.ui;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

/**
 * Class for the timer in the game.
 * 
 * @author sareetta
 */
public class Timer {
    private Timeline timeline;
    private long seconds;
    private long minutes;
    private Label time;

    /**
     * Constructor.
     * Initializes the parameters of the timer an the label.
     */
    public Timer() {
        this.timeline = new Timeline();
        this.seconds = 0;
        this.minutes = 0;
        this.time = new Label("00:00");
        time.setFont(new Font("Lucida Sans Unicode", 60));
        time.setTextFill(Color.BLACK);
        init();
    }

    /**
     * Method for initializing the timer.
     */
    private void init() {
        timeline = new Timeline(
            new KeyFrame(Duration.seconds(0),
                e -> increaseTime()),
            new KeyFrame(Duration.seconds(1)));

        timeline.setCycleCount(Animation.INDEFINITE);
    }

    /**
     * Method for increase the time.
     */
    private void increaseTime() {
        if (seconds < 59) {
            seconds++;
        } else {
            seconds = 0;
            minutes++;
        }
        if (seconds > 9 && minutes < 60) {
            this.time.setText(minutes + ":" + seconds);
        } else if (seconds < 10 && minutes < 60) {
            this.time.setText(minutes + ":0" + seconds);
        } else if (seconds < 10 && minutes > 60) {
            this.time.setText(minutes + ":0" + seconds);
        } else {
             this.time.setText(minutes + ":" + seconds);
        }
    }

    /**
     * Method for starting the timer.
     */
    public void start() {
        reset();
        timeline.play();
    }
    
    /**
     * Method for stopping the timer.
     */
    public void stop() {
        timeline.stop();
    }

    /**
     * Method for resetting the timer.
     */
    public void reset() {
        this.seconds = 0;
        this.minutes = 0;
    }
    
    /**
     * Method to continue the timer.
     */
    public void replay() {
        timeline.play();
    }

    /**
     * Method for getting the timer.
     * 
     * @return Label.
     */
    public Label getTime() {
        return this.time;
    }
    
    /**
     * Method for getting the time for a score.
     * 
     * @return Time.
     */
    public String Time() {
        return time.getText();
    }
    
    /**
     * Method for getting the time for correct Sudoku solution announcement.
     * @return Message.
     */
    public String toString() {
        return "Time used: " + time.getText();
    }
    
    
}
