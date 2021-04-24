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
 *
 * @author sareetta
 */
public class Timer {
    private Timeline timeline;
    private long seconds;
    private long minutes;
    private Label time;

    public Timer() {
        this.timeline = new Timeline();
        this.seconds = 0;
        this.minutes = 0;
        this.time = new Label("00:00");
        time.setFont(new Font("Lucida Sans Unicode", 60));
        time.setTextFill(Color.BLACK);
        init();
    }

    private void init() {
        timeline = new Timeline(
                new KeyFrame(Duration.seconds(0),
                        e -> increaseTime()),
                new KeyFrame(Duration.seconds(1)));

        timeline.setCycleCount(Animation.INDEFINITE);
    }

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

    public void start() {
        reset();
        timeline.play();
    }
    
    public void stop() {
        timeline.stop();
    }

    public void reset() {
        this.seconds = 0;
        this.minutes = 0;
    }
    
    public void continueTime() {
        timeline.play();
    }

    public Label getTime() {
        return this.time;
    }
    
    public String toString() {
        return "Time used: " + time.getText();
    }
    
    
}
