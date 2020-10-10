/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p3game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
//import java.awt.Color;

public class mainGUI{
    private JLayeredPane layeredPane;
    private static JToggleButton jtbButton;

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
        Gameboard gameboard = new Gameboard();
        Statistics stats = new Statistics();
        
        if(isGameRunning == false){
            //main container for the titlescreen GUI
            Container titleCon = new Container();
            titleCon.setLocation(0, 0);
            titleCon.setBounds(0, 0, 600, 600);
            titleCon.setPreferredSize(new Dimension(600, 600));
            
            //Main title on the title screen
            JLabel title = new JLabel("RPG Game", JLabel.CENTER);
            title.setFont(new Font("Serif", Font.PLAIN, 48));
            title.setBounds(0, 50, 600, 50);
            title.setForeground(Color.gray);
            
            // "BY" on the title screen
            JLabel subTitle = new JLabel("by", JLabel.CENTER);
            subTitle.setFont(new Font("Serif", Font.BOLD, 24));
            subTitle.setBounds(0, 100, 600, 50);
            subTitle.setForeground(Color.gray);
            
            //My name on the title screen
            JLabel finalTitle = new JLabel("Taylor Styles", JLabel.CENTER);
            finalTitle.setFont(new Font("Arial", Font.PLAIN, 24));
            finalTitle.setBounds(0, 150, 600, 50);
            finalTitle.setForeground(Color.gray);            
            
            //Easy & Hard gamemode options.
            jtbButton = new JToggleButton("Easy");
            jtbButton.setBounds(250, 300, 100, 50);
            jtbButton.addItemListener((ItemEvent ev) -> {
                if("Easy".equals(jtbButton.getText())){
                    jtbButton.setText("Hard");
                    gameData.setDifficulty('H');
                    System.out.println("Difficulty set to: " + gameData.getDifficulty());
                }else{
                    jtbButton.setText("Easy");
                    gameData.setDifficulty('E');
                    System.out.println("Difficulty set to: " + gameData.getDifficulty());
                    
                }
            });
            
            // Radio boxes for player selection.
            JRadioButton jrb1 = new JRadioButton();
            jrb1.setBounds(185, 415, 50, 50);
            JRadioButton jrb2 = new JRadioButton();
            jrb2.setBounds(285, 415, 50, 50);
            JRadioButton jrb3 = new JRadioButton();
            jrb3.setBounds(385, 415, 50, 50);
            
            JLabel selectChar = new JLabel("Select a character", JLabel.CENTER);
            selectChar.setFont(new Font("Arial", Font.PLAIN, 18));
            selectChar.setBounds(0, 435, 600, 50);
            selectChar.setForeground(Color.gray);  
            
            jrb1.addItemListener(new ItemListener( ) {
                public void itemStateChanged(ItemEvent ev) { 
                    //JOptionPane.showMessageDialog(frame,"Witch Selected!\n7 x Lives\n1 x Damage");
                    jrb2.setSelected(false);
                    jrb3.setSelected(false);
                    selectChar.setText("Witch Selected");
                    gameData.setPlayerType(1);
                    System.out.println("Character Selected: "+gameData.getPlayerType());


                }
            });
            
            jrb2.addItemListener(new ItemListener( ) {
                public void itemStateChanged(ItemEvent ev) { 
                    //JOptionPane.showMessageDialog(frame,"God Selected!\n9 x Lives\n4 x Damage");
                    jrb1.setSelected(false);
                    jrb3.setSelected(false);
                    selectChar.setText("God Selected");
                    gameData.setPlayerType(2);
                    System.out.println("Character Selected: "+gameData.getPlayerType());

                }
            });
            jrb3.addItemListener(new ItemListener( ) {
                public void itemStateChanged(ItemEvent ev) { 
                    //JOptionPane.showMessageDialog(frame,"Warrier Selected!\n5 x Lives \n1 x Damage");
                    jrb1.setSelected(false);
                    jrb2.setSelected(false);
                    selectChar.setText("Warrior Selected");
                    gameData.setPlayerType(3);
                    System.out.println("Character Selected: "+gameData.getPlayerType());
                }
            });
            
            //Images which relate to the radio boxes above
            JLabel character1 = new JLabel();
            character1.setIcon(new ImageIcon("img/Witch.png"));
            character1.setMinimumSize(new Dimension(50, 50));
            character1.setMaximumSize(new Dimension(50, 50));
            character1.setPreferredSize(new Dimension(50, 50));
            character1.setBounds(175, 370, 50, 50);
            
            JLabel character2 = new JLabel();
            character2.setIcon(new ImageIcon("img/God.png"));
            character2.setMinimumSize(new Dimension(50, 50));
            character2.setMaximumSize(new Dimension(50, 50));
            character2.setPreferredSize(new Dimension(50, 50));
            character2.setBounds(275, 370, 50, 50);
            
            JLabel character3 = new JLabel();
            character3.setIcon(new ImageIcon("img/Warrior.png"));
            character3.setMinimumSize(new Dimension(50, 50));
            character3.setMaximumSize(new Dimension(50, 50));
            character3.setPreferredSize(new Dimension(50, 50));
            character3.setBounds(375, 370, 50, 50);
            
            //Start Game button below
            JButton startButton = new JButton("START GAME");
            startButton.setBounds(215, 500, 150, 50);
            startButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    //gameData.setInGame(true);
                    System.out.println("Start Game Pressed");
                    gameboard.generateBoard(gameData, stats, frame, GUI);
                    gameboard.startGame(0, gameData, stats, frame, GUI);
                }
            });
            
            // "BY" on the title screen
            JLabel copyright = new JLabel("© Taylor Styles. Student ID: 17998574", JLabel.CENTER);
            copyright.setFont(new Font("Serif", Font.BOLD, 12));
            copyright.setBounds(0, 540, 600, 50);
            copyright.setForeground(Color.gray);
            
            titleCon.add(startButton);
            titleCon.add(selectChar);
            titleCon.add(character1);
            titleCon.add(character2);
            titleCon.add(character3);
            titleCon.add(jrb1);
            titleCon.add(jrb2);
            titleCon.add(jrb3);
            titleCon.add(copyright);
            titleCon.add(jtbButton);
            titleCon.add(finalTitle);
            titleCon.add(subTitle);
            titleCon.add(title);
            frame.setContentPane(titleCon);
            frame.setVisible(true);
        } else{
            frame.remove(frame);
            frame.repaint();
            frame.setContentPane(GUI.gridArray(gameData));  
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        }
    }
    
    public static void handleKeypress(char e, Gameboard gameboard, SavedData gameData, Statistics stats, JFrame frame, mainGUI GUI){
        System.out.println("Handle Keypress entered with: "+e);
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