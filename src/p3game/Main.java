
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p3game;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import javax.swing.JFrame;

/**
 *
 * @author taystyles
 */

public class Main {
    public static void main(String[] args) throws java.lang.Exception{
        mainGUI gui = new mainGUI();
        SavedData gameData = new SavedData();  
        Statistics stats = new Statistics();  
        Gameboard gameboard = new Gameboard();
        
        JFrame frame = new JFrame("RPG game");
        frame.setBounds(0, 0, 600, 600);
        frame.setPreferredSize(new Dimension(600, 600));
        frame.setResizable(false);
        mainGUI.createAndShowGUI(frame, gui, gameData, false);
        
        frame.addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {
                System.out.println("'"+ e.getKeyChar() + "' Pressed");
                mainGUI.handleKeypress(e.getKeyChar(), gameboard, gameData, stats, frame, gui);
            }
        });
        
//        char start = PreGameSetup.init(gameData);
//        while (gameData.isInGame() == false){
//            start = Character.toUpperCase(start);
//            if (start == 'Y'){
//                gameboard.generateBoard(gameData, stats, frame, gui);
//                gameboard.startGame(0, gameData, stats, frame, gui);
//            }
//        }
    }
}