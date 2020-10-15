
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p3game;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import javax.swing.JFrame;

/**
 * @author taystyles
**/

public class Main {
    
    public static void main(String[] args) throws java.lang.Exception{
        mainGUI GUI = new mainGUI();
        SavedData gameData = new SavedData();  
        Statistics stats = new Statistics();  
        Gameboard gameboard = new Gameboard();
        Range_Attack range = new Range_Attack();
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        int screenHeight = d.height;
        int screenWidth = d.width;
        
        //Database code below
        Database database = new Database();
        database.establishConnection(); //Connect to the local database.
        database.createTable(); //Creates the GameStats table for the leaderboard.
        //database.insertTable(); //Insert the game statistics into the database.
        //System.out.println(database.getQuery()); //Get the infomration from the database.
        //database.closeConnections();
       
        //Create main frame for the game
        JFrame frame = new JFrame("RPG game");
        frame.setBounds(0, 0, 750, 532);
        frame.setPreferredSize(new Dimension(750, 532));
        frame.setLocation(new Point((screenWidth/2) - (frame.getWidth()/2),(screenHeight/2) - (frame.getHeight()/2)));
        frame.setResizable(false);
        frame.setFocusable(true);
        frame.requestFocus();
        KeyPress.AddKeyListener(gameboard, gameData, stats, frame, GUI, range, database);
        GUI.createAndShowGUI(frame, GUI, gameData, range, false, database);
    }
}