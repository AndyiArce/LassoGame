/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lassogame;

/**
 *
 * https://www.youtube.com/watch?v=M6DcY6dQxYw
 * https://docs.oracle.com/javase/7/docs/api/java/awt/event/MouseEvent.html
 * 
 * while you could MouseListener, MouseMotionListener implements, we don't need all of those functions
 * therefore we will just extend the MouseAdapter for it to look nicer
 * Cool Thing I found out, The MouseEvent Class has a getPoint method
 */
import java.awt.event.*;

public class MouseListener extends MouseAdapter implements MouseMotionListener {
    private GamePanel gamePanel;  // The main game panel

    // Constructor to accept the main game panel
    public MouseListener(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (!gamePanel.isGameOver() && gamePanel.lasso.isSurroundingObject(gamePanel.object)) {
            gamePanel.incrementScore();  // Increment score if loop surrounds the object
            gamePanel.reObj();
            gamePanel.incementTimer();
        }
        gamePanel.lasso.clear();
        gamePanel.repaint();  // Repaint once the loop is completed
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (!gamePanel.isGameOver()) {  // Only allow drawing if the game is not over
            gamePanel.lasso.addPoint(e.getPoint());
            gamePanel.repaint();  // Repaint every time the mouse is dragged
        }
    }
}
