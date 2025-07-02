/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.lassogame;

import javax.swing.*;

/**
 *
 * @author turtl
 */
public class LassoGame {

    public static void main(String[] args) {
        // Window setup is from https://youtu.be/om59cwR7psI?si=LB7t_8BQuKzrBn3P
        JFrame window = new JFrame();
        
        // Lets the window close on pressing (x)
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        window.setResizable(false); // Prevents Resizing
        window.setTitle("Lasso");
        
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        
        window.pack();
        
        window.setLocationRelativeTo(null);
        window.setVisible(true);        
        
    }
}
