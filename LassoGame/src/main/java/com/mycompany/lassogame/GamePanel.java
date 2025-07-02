/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lassogame;

import java.awt.BasicStroke;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;
import javax.swing.Timer;

/**
 *
 * @author turtl
 */

public class GamePanel extends JPanel{
    
    // Game Loop and Screen setup from https://youtu.be/om59cwR7psI?si=RUgomvbiiM_APbtF
    
    //Where you set up other classes that hold game mechanical stuff like the Lasso class
    public Lasso lasso;
    public GameObject object;
    private int score = 0;
    private int highScore = 0;
    private boolean gameOver = false;
    private int remainingTime = 10;
    private Timer timer;
    
    
    Thread gameThread;
    
    public GamePanel()
    {
        // Sets the size of this JPanel
        this.setPreferredSize(new Dimension(1024,768));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        lasso = new Lasso();
        object = new GameObject(512, 384, 20);
        highScore = HighScorer.getHighScore();
        
        // Create the mouse listener and add it to the panel
        MouseListener mouseListener = new MouseListener(this);
        addMouseMotionListener(mouseListener);
        addMouseListener(mouseListener);
        


        // Start the countdown timer
        startTimer();
    }
    

    // In order to check for a game over
    public boolean isGameOver() {
        return gameOver;
    }
    
    public void reObj()
    {
        Random rand = new Random();
        object = new GameObject(rand.nextInt(700)+100,rand.nextInt(400)+100,20);
    }
    
    public void incrementScore() {
        score++;
    }
    
    public void incementTimer()
    {
        remainingTime++;
    }
    
    private void highScoreUpdater()
    {
        if (score > highScore)
        {
           highScore = score;
           HighScorer.saveHighScore(highScore);
        }
    }
    
    // Start the 10-second countdown timer
    // https://docs.oracle.com/javase/tutorial/uiswing/misc/timer.html
    private void startTimer() {
        timer = new Timer(1000, e -> {
            remainingTime--;  // Decrement remaining time
            if (remainingTime <= 0) {
                gameOver = true;  // End the game when the timer reaches 0
                timer.stop();  // Stop the timer
                highScoreUpdater();
            }
            repaint();  // Repaint the screen to update the timer
        });
        timer.start();  // Start the timer
    }
   
    
    // This is a built in Java method for drawing on JPanel
    // https://youtu.be/VpH33Uw-_0E?si=vVQPYT7fTxW5DX4O
    // After testing I realized I didn't need a game loop (like 60fps) since nothing is actually "moving" on screen (there isn't any animations)
    
    public void paintComponent(Graphics g)
    {
        // This is so we can make the paintCompo Obj from JPanel
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        // https://stackoverflow.com/questions/30005331/java-how-to-clear-graphics-from-a-jpanel
        g2d.setStroke(new BasicStroke(6.0F));

        // Draw the object (dot in the center)
        object.draw(g2d);

        // Draw the user's drawn loop
        lasso.draw(g2d);

        // Display score when loop is closed and surrounds the object
        g.setColor(Color.WHITE);
        g.drawString("Score: " + score, 10, 20);
        g.setColor(Color.RED);
        g.drawString("Draw a loop around the red dot!", 10, 40);
        g.setColor(Color.WHITE);
        g.drawString("HighScore: " + highScore,10,80);
        
        // Display remaining time
        g.drawString("Time remaining: " + remainingTime + "s", 10, 60);

        // Display game over message
        if (gameOver) {
            g.setColor(Color.RED);
            g.drawString("Game Over!", 480, 360);
        }
        
        // Disposes of this graphics context and reelase any sys resources using it
        g2d.dispose();
    }   
}
