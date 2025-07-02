/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lassogame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 *
 * @author turtl
 * https://www.youtube.com/watch?v=om59cwR7psI
 * 
 */


public class GameObject {
    private Point center; // The center of the object
    private int radius; // The radius of the object

    // Constructor to initialize the object at a given position and radius
    public GameObject(int x, int y, int radius) {
        this.center = new Point(x, y);
        this.radius = radius;
    }

    // Get the center point of the object
    public Point getCenter() {
        return center;
    }

    // Draw the object (a red dot) on the screen
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(center.x - radius, center.y - radius, radius * 2, radius * 2); // Draw the red dot
    }
}
