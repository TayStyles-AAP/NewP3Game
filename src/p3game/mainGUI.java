/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p3game;

import javax.swing.*;
import java.awt.*;
//import java.awt.Color;

public class mainGUI{
    private JLayeredPane layeredPane;

    public JPanel gridArray (SavedData gameData){
        JPanel totalGUI = new JPanel();
        JLabel title = new JLabel("Test");   
        
        JPanel mainPanel = new JPanel(new GridLayout(10, 10, 0, 0));
        
        for (int i = 0; i < gameData.getArr().length; i++){
            for (int j = 0; j < gameData.getArr()[i].length; j++){
                switch(gameData.getArr()[i][j]){
                    case 0:
                        JLabel block1 = createSquareJLabel("img/Grass.png", 50);
                        mainPanel.add(block1);
                        break;
                    case 1:
                        JLabel block2 = createSquareJLabel("img/Player.png", 50);
                        mainPanel.add(block2);
                        break;
                    case 2:
                        JLabel block3 = createSquareJLabel("img/Enemy.png", 50);
                        mainPanel.add(block3);
                        break;
                    case 3:
                        JLabel block4 = createSquareJLabel("img/Object.png", 50);
                        mainPanel.add(block4);
                        break;
                }
            }
            System.out.println();
        }        
        totalGUI.add(mainPanel);
        totalGUI.setOpaque(false);
        return totalGUI;
    }
    
    // In this method, we create a square JPanel of a colour and set size
    // specified by the arguments.
    
    private JLabel createSquareJLabel(String img, int size){
        JLabel tempPanel = new JLabel();
        tempPanel.setIcon(new ImageIcon(img));
        tempPanel.setMinimumSize(new Dimension(size, size));
        //tempPanel.setMaximumSize(new Dimension(size, size));
        tempPanel.setPreferredSize(new Dimension(size, size));
        return tempPanel;
    }

    public static void createAndShowGUI(JFrame frame, mainGUI GUI, SavedData gameData, boolean isGameRunning) {
        if(isGameRunning == false){
            Container con = new Container();
            con.setBounds(0, 0, 0, 0);
            con.setLocation(300, 300);
            
            JLabel text = new JLabel("TITLE", JLabel.CENTER);
            text.setBounds(300, 200, 100, 100);
            text.setBackground(Color.white);
            text.setForeground(Color.black);
            
            con.add(text);
            frame.add(con);
            frame.add(text);
            frame.setVisible(true);
            //GUI = new mainGUI();
            //frame.setContentPane(GUI.gridArray(gameData));        

            //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            //frame.pack();
            //frame.setVisible(true);
        }else{
            
            //GUI = new mainGUI();
            frame.setContentPane(GUI.gridArray(gameData));  
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        }
    }
    
    public static void handleKeypress(char e, Gameboard gameboard, SavedData gameData, Statistics stats, JFrame frame, mainGUI GUI){
        switch (e) {
            case 'w':
                gameboard.updateGameboard(0,-1, gameData, stats, frame, GUI);
                break;
            case 's':
                gameboard.updateGameboard(0,1, gameData, stats, frame, GUI);
                break;
            case 'a':
                gameboard.updateGameboard(-1,0, gameData, stats, frame, GUI);
                break;
            case 'd':
                gameboard.updateGameboard(1,0, gameData, stats, frame, GUI);
                break;
            default:
                //lol
                break;
        }
    }
}

        
//    public static void createAndShowGUI(SavedData gameData, boolean isGameRunning) {
//        
//        JFrame.setDefaultLookAndFeelDecorated(false);
//        JFrame frame = new JFrame("MY GAMES GUI");
//        frame.setPreferredSize(new Dimension(600, 600));
//        GUIPractice GUI = new GUIPractice();
//        frame.setContentPane(GUI.gridArray(gameData));        
//        
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.pack();
//        frame.setVisible(true);
//    }