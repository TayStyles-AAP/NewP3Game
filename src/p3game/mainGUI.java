/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p3game;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.border.Border;

public class mainGUI {
    private static JToggleButton jtbButton;

    public Container gridArray(SavedData gameData, mainGUI GUI, Range_Attack range, JFrame frame) {
        Health health = new Health();
        Container totalGUI = new Container();
        JPanel mainPanel = new JPanel(new GridLayout(gameData.getX(), gameData.getY(), 0, 0));
        JLabel pLives = new JLabel("Player Lives: " + health.printLivesStats(gameData, GUI, range, 1, 0, frame));
        JLabel eLives = new JLabel("EnemyLives: " + health.printLivesStats(gameData, GUI, range, 0, 1, frame));
        JLabel roundNumber = new JLabel("Round: "+gameData.getRoundNumber());

        Border redBorder = BorderFactory.createLineBorder(Color.red, 3, true);
        JLabel enemyAttack = new JLabel("", JLabel.CENTER);
        
        if (gameData.isEnemyDD() == true){
            mainPanel.setBorder(redBorder);
            enemyAttack.setText("Enemy has double damage!");
        }
        if (gameData.isPlayerDD() == true){
            mainPanel.setBorder(redBorder);
        }
        if (gameData.isPlayerDD() == true && gameData.isEnemyDD() == true){
            mainPanel.setBorder(redBorder);
        }
        if(gameData.isEnemyInRange() == true && gameData.isPlayerDD() == false){
            enemyAttack.setText("Press SPACE to fight OR press ENTER for double damage during next attack!");
        }
        else if(gameData.isEnemyInRange() == true && gameData.isPlayerDD() == true){
            enemyAttack.setText("Press SPACE for double damage fight!");
        }
        else{
            enemyAttack.setText("");
        }

        if (gameData.getDifficulty() == 'H') {
            totalGUI.setBounds(0, 0, 950, 785);
            totalGUI.setPreferredSize(new Dimension(950, 785));
            mainPanel.setBounds(50, 50, 750, 750);
        } else {
            totalGUI.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()));
            totalGUI.setBounds(0, 0, frame.getWidth(), frame.getHeight());
            
            mainPanel.setBounds(80, 32, 500, 500);
            
            pLives.setBounds(80, 0, 250, 50);
            pLives.setPreferredSize(new Dimension(200, 50));
            pLives.setForeground(Color.red);
            
            eLives.setBounds(425,0, 200, 50);
            eLives.setPreferredSize(new Dimension(200, 50));
            eLives.setForeground(Color.DARK_GRAY);
            
            roundNumber.setBounds(320, 0, 200, 50);
            roundNumber.setPreferredSize(new Dimension(100, 50));
            roundNumber.setForeground(Color.blue);
            
            enemyAttack.setBounds(0, 530, totalGUI.getWidth(), 50);
            enemyAttack.setPreferredSize(new Dimension(200, 50));
            enemyAttack.setForeground(Color.red);
        }
        
        mainPanel.removeAll();
        
        for (int i = 0; i < gameData.getArr().length; i++) {
            for (int j = 0; j < gameData.getArr()[i].length; j++) {
                switch (gameData.getArr()[i][j]) {
                    case 0:
                        //JLabel block1 = createSquareJLabel(gameData.getGrassIcon(), 50);
                        mainPanel.add(new JLabel(gameData.getGrassIcon()));
                        //mainPanel.add(block1);
                        break;
                    case 1:
                        //JLabel block2 = createSquareJLabel(img, 50);
                        mainPanel.add(new JLabel(gameData.getPlayerURL()));
                        //mainPanel.add(block2);
                        break;
                    case 2:
                        //img = gameData.getEnemyURL();
                        //JLabel block3 = createSquareJLabel(img, 50);
                        mainPanel.add(new JLabel(gameData.getEnemyURL()));
                        //mainPanel.add(block3);
                        break;
                    case 3:
                        //JLabel block4 = createSquareJLabel("img/Object.png", 50);
                        mainPanel.add(new JLabel(gameData.getOjbectIcon()));
                        //mainPanel.add(block4);
                        break;
                }
            }
        }

        totalGUI.add(roundNumber);
        totalGUI.add(mainPanel);
        totalGUI.add(pLives);
        totalGUI.add(eLives);
        totalGUI.add(enemyAttack);
        //totalGUI.setOpaque(true);
        return totalGUI;
    }

    // In this method, we create a square JPanel of a colour and set size
    // specified by the arguments.
    private JLabel createSquareJLabel(String img, int size) {
        JLabel tempPanel = new JLabel();
        tempPanel.setIcon(new ImageIcon(img));
        tempPanel.setMinimumSize(new Dimension(size, size));
        //tempPanel.setMaximumSize(new Dimension(size, size));
        tempPanel.setPreferredSize(new Dimension(size, size));
        return tempPanel;
    }

    public void createAndShowGUI(JFrame frame, mainGUI GUI, SavedData gameData, Range_Attack range, boolean isGameRunning) {
        Gameboard gameboard = new Gameboard();
        Statistics stats = new Statistics();
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        int screenHeight = d.height;
        int screenWidth = d.width;

        if(gameData.isInGame() == false){
            isGameRunning = false;
        }
        
        if (isGameRunning == false) {
            gameData.setDifficulty('E');
            //main container for the titlescreen GUI
            Container titleCon = new Container();
            titleCon.setLocation(0, 0);
            titleCon.setBounds(0, 0, 750, 532);
            titleCon.setPreferredSize(new Dimension(750, 532));

            //Main title on the title screen
            JLabel title = new JLabel("RPG Game", JLabel.CENTER);
            title.setFont(new Font("Serif", Font.PLAIN, 48));
            title.setBounds(0, 50, 750, 50);
            title.setForeground(Color.gray);

            // "BY" on the title screen
            JLabel subTitle = new JLabel("by", JLabel.CENTER);
            subTitle.setFont(new Font("Serif", Font.BOLD, 24));
            subTitle.setBounds(0, 100, 750, 50);
            subTitle.setForeground(Color.gray);

            //My name on the title screen
            JLabel finalTitle = new JLabel("Taylor Styles", JLabel.CENTER);
            finalTitle.setFont(new Font("Arial", Font.PLAIN, 24));
            finalTitle.setBounds(0, 140, 750, 50);
            finalTitle.setForeground(Color.gray);

            JButton infoButton = new JButton("?");
            infoButton.setBounds(710, 10, 25, 25);
            infoButton.setPreferredSize(new Dimension(25, 25));
            
            infoButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    JOptionPane.showMessageDialog(frame, "1) When your character is within range, you will see the attack options below the gameboard.\n"
                + "2) If the enemy steps over you to your prevous position, the enemy has the ability to attack you. (1/4 odds)\n"
                + "3) For each attack, You can either attack now for standard damage, or continue for double damage next time.\n"
                + "4) Rocks are a barrier. Use them as an advantage with certain characters to attack the enemy (Witch & God).\n"
                + "NOTE: The enemy will actively rocks where possible.\n"
                ,"Game Instructions", 1);

                }
            });
            
            //Easy & Hard gamemode options.
            jtbButton = new JToggleButton("Easy");
            jtbButton.setBounds(100, 270, 150, 50);
            jtbButton.addItemListener((ItemEvent ev) -> {
                if ("Easy".equals(jtbButton.getText())) {
                    jtbButton.setText("Hard");
                    gameData.setDifficulty('H');
                    System.out.println("Difficulty set to: " + gameData.getDifficulty());
                } else {
                    jtbButton.setText("Easy");
                    gameData.setDifficulty('E');
                    System.out.println("Difficulty set to: " + gameData.getDifficulty());

                }
            });
            
            //Start Game button below
            JButton startButton = new JButton("START GAME");
            startButton.setBounds(300, 390, 150, 50);
            startButton.setEnabled(false);
            startButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    //gameData.setInGame(true);
                    try{
                        System.out.println("Start Game Pressed");
                        gameboard.generateBoard(gameData, stats, frame, GUI, range);
                        JOptionPane.showMessageDialog(frame, "1) When your character is within range, you will see the attack options below the gameboard.\n"
                            + "2) When you attack, the enemy has a 1 in 3 odds of attacking you back \nand a 1 in 4 odds of aquiring double damage\n"
                            + "3) If you choose double damage, the enemy has a 1 in 3 odds of attacking you.\n\n"
                            + "Note) Each character has its own attacking properties. Pick your player accordingly.","Game Instructions", 1);
                        gameData.setInGame(true);
                    }catch(IllegalArgumentException e){}
                }
            });

            ButtonGroup BGroup = new ButtonGroup();
            // Radio boxes for player selection.
            JRadioButton jrb1 = new JRadioButton();
            jrb1.setBounds(410, 290, 50, 50);
            JRadioButton jrb2 = new JRadioButton();
            jrb2.setBounds(510, 290, 50, 50);
            JRadioButton jrb3 = new JRadioButton();
            jrb3.setBounds(606, 290, 50, 50);

            BGroup.add(jrb1);
            BGroup.add(jrb2);
            BGroup.add(jrb3);

            //Character Selection
            JLabel selectChar = new JLabel("Hold mouse on character for more info", JLabel.CENTER);
            selectChar.setFont(new Font("Arial", Font.PLAIN, 12));
            selectChar.setBounds(400, 310, 250, 50);
            selectChar.setForeground(Color.gray);

            jrb1.addItemListener((ItemEvent ev) -> {
                selectChar.setText("Witch Selected");
                gameData.setPlayerType(1);
                startButton.setEnabled(true);
                System.out.println("Character Selected: " + gameData.getPlayerType());
            });

            jrb2.addItemListener((ItemEvent ev) -> {
                selectChar.setText("God Selected");
                gameData.setPlayerType(2);
                startButton.setEnabled(true);
                System.out.println("Character Selected: " + gameData.getPlayerType());
            });

            jrb3.addItemListener((ItemEvent ev) -> {
                selectChar.setText("Civilian Selected");
                gameData.setPlayerType(3);
                startButton.setEnabled(true);
                System.out.println("Character Selected: " + gameData.getPlayerType());
            });

            //Images which relate to the radio boxes above
            JLabel character1 = new JLabel();
            character1.setIcon(new ImageIcon("img/WitchTra.png"));
            
            character1.setToolTipText("<html><p width=\"250\">~ 1 damage per hit. <br>~ 1 in 3 attacks miss. <br>~ Horizontal/Vertical & Diagonal attacking.</p></html>");
            
            character1.setMinimumSize(new Dimension(50, 50));
            character1.setMaximumSize(new Dimension(50, 50));
            character1.setPreferredSize(new Dimension(50, 50));
            character1.setBounds(400, 250, 50, 50);

            JLabel character2 = new JLabel();
            character2.setIcon(new ImageIcon("img/GodTra.png"));
            
            character2.setToolTipText("<html><p width=\"250\">~ 2 damage per hit. <br>~ 1 in 5 attacks miss. <br>~ Horizontal/Vertical (1 & 3 block away)</p></html>");
            
            character2.setMinimumSize(new Dimension(50, 50));
            character2.setMaximumSize(new Dimension(50, 50));
            character2.setPreferredSize(new Dimension(50, 50));
            character2.setBounds(500, 250, 50, 50);

            JLabel character3 = new JLabel();
            character3.setIcon(new ImageIcon("img/PlayerTra.png"));
            
            character3.setToolTipText("<html><p width=\"250\">~ 1 damage per hit. <br>~ 1 in 3 attacks miss. <br>~ Horizontal/Vertical (1 block away only)</p></html>");
            
            character3.setMinimumSize(new Dimension(50, 50));
            character3.setMaximumSize(new Dimension(50, 50));
            character3.setPreferredSize(new Dimension(50, 50));
            character3.setBounds(610, 250, 50, 50);

            // "BY" on the title screen
            JLabel copyright = new JLabel("© Taylor Styles. Student ID: 17998574", JLabel.CENTER);
            copyright.setFont(new Font("Serif", Font.BOLD, 12));
            copyright.setBounds(0, 470, 750, 50);
            copyright.setForeground(Color.gray);
            
            if(gameData.isDisplayWinInfo() == true){
                title.setText("GAME OVER!");
                subTitle.setText("You Survived");
                finalTitle.setText(gameData.getRoundNumber()+" round(s)");
                gameData.setDisplayWinInfo(false);
                gameData.setInGame(false);
                frame.setBounds(0, 0, 750, 532);
                frame.setPreferredSize(new Dimension(750, 532));
                frame.setLocation(new Point((screenWidth/2) - (frame.getWidth()/2),(screenHeight/2) - (frame.getHeight()/2)));
            }
            
            titleCon.add(infoButton);
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
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);

            try {
                GUI.setImages(gameData);
            } catch (IOException ex) {
                Logger.getLogger(mainGUI.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            if (gameData.getDifficulty() == 'H') {
                frame.setPreferredSize(new Dimension(950, 785));
                frame.setLocation(new Point((screenWidth / 3) - (frame.getWidth() / 3), (screenHeight / 5) - (frame.getHeight() / 5)));
            } else {
                frame.setPreferredSize(new Dimension(650, 590));
            }
            frame.setContentPane(GUI.gridArray(gameData, GUI, range, frame));
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
            frame.requestFocus();
        }
    }

    public static void setImages(SavedData gameData) throws IOException {
        gameData.setGrassIcon();
        gameData.setObjectIcon();
        
        gameData.setEnemyIcon();
        gameData.setPlayerIcon();
        gameData.setWitchIcon();
        gameData.setGodIcon();

        gameData.setEnemyDDIcon();
        gameData.setWitchDDIcon();
        gameData.setPlayerDDIcon();
        gameData.setGodDDIcon();
    }
}
