/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lassogame;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.NoSuchElementException;

/**
 *https://stackoverflow.com/questions/5071040/java-convert-integer-to-string
 * https://www.w3schools.com/java/java_files_read.asp
 * https://www.w3schools.com/js/js_errors.asp
 * https://www.w3schools.com/java/java_files_read.asp
 * https://www.geeksforgeeks.org/different-ways-reading-text-file-java/
 * https://stackoverflow.com/questions/12180041/not-reading-a-file
 * 
 */
public class HighScorer {
    // NOTE: PLEASE CHANGE FILE PATH TO THE ONE ON YOUR COMPUTER
    private static final String fName = "C:\\Users\\turtl\\OneDrive\\Documents\\NetBeansProjects\\com.mycompany_LassoGame_jar_1.0-SNAPSHOT_1\\src\\main\\java\\com\\mycompany\\lassogame\\Score.txt";
            
        public static int getHighScore() {
            
        try{
            File fileHiSco = new File(fName);
            Scanner myReader = new Scanner(fileHiSco);
            String data = myReader.nextLine();
            int score = Integer.parseInt(data);
            return score;
            
        }
        catch(FileNotFoundException | NoSuchElementException | NumberFormatException e){
            System.out.println(e.getMessage());
        }
        return 0;
    }
        
        public static void saveHighScore(int score)
        {
            try
            {
                FileWriter newfileHiSco = new FileWriter(fName);
                newfileHiSco.write(Integer.toString(score));
                newfileHiSco.close();
            }
            catch(IOException e)
            {
                System.out.println(e.getMessage());
            }
        }
        
}
