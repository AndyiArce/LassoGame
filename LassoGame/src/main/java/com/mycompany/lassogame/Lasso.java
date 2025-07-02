/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lassogame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;


/**
 *
 * This method can be used to get the point of where the mouse is
 * Therefore we can store it in an arraylist of points and check if 
 * https://docs.oracle.com/javase/6/docs/api/java/awt/Point.html
 * https://docs.oracle.com/javase/6/docs/api/java/awt/Point.html
 * https://docs.oracle.com/javase/8/docs/api/index.html?java/awt/Polygon.html
 */
public class Lasso{
    private ArrayList<Point> points; // We get a list of points that our Mouse has Traversed Through
    
    public Lasso() {
        points = new ArrayList<>();
    }
    
    // A Method that would add a Point to the List
    public void addPoint(Point p) {
        points.add(p);
    }
    
    // A Method to clear the List
    public void clear() {
        points.clear();
    }
    
    // A Method that checks if our loop is closed
    public boolean isClosed() {
        if (points.size() < 50) { // Just to be sure that the mouse standing by iself doesn't count
            return false;
        }
        return points.get(0).distance(points.get(points.size() - 1)) < 40; // Distance threshold to consider the loop closed
    }
    
    
    // Method for drawing our line (Base Case)

    public void draw(Graphics g)
    {
        g.setColor(Color.WHITE);
        drawRecursive(g,0);
        // If the loop is closed, draw the closing point with a green circle
        if (isClosed()) {
            Point p = points.get(points.size() - 1);
            g.setColor(Color.GREEN);
            g.fillOval(p.x - 5, p.y - 5, 15, 15);
        }
      
    }
    
    // Method for drawing our line (Recursive Case)

    private void drawRecursive(Graphics g, int index)
    {
        // we are getting the points from the spot our first mouse spot and next spot as we draw a line
        if (index >= points.size() - 1)
        {
            return;
        }
        
        Point p1 = points.get(index);
        Point p2 = points.get(index + 1);
        
        g.drawLine(p1.x, p1.y, p2.x, p2.y);
        
        drawRecursive(g, index + 1);
    }
    
    // Method for checking if our drawn line is surrounding our GameObject
    public boolean isSurroundingObject(GameObject object) {
        if (!isClosed()) return false;
        // We will use the polygon class to essentially create a polygon with the points we have
        // Then with these points we will have if the polygon contains the object in question
        
        // with both this and the isClosed method, we can check if the object is surrounded
        // and that our mouse created a closed loop
        
        Polygon loopPolygon = new Polygon();
        for (Point p : points) {
            loopPolygon.addPoint(p.x, p.y);
        }

        return loopPolygon.contains(object.getCenter());
    }  
    
}
