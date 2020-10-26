package p3game;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.border.Border;

public class mainGUI {

    private static JToggleButton jtbButton;

    //displays the main array gameboard and returns a complete layed out container
    public Container gridArray(SavedData gameData, mainGUI GUI, Range_Attack range, JFrame frame) {
        Health health = new Health();
        Container totalGUI = new Container();
        totalGUI.removeAll();
        JPanel mainPanel = new JPanel(new GridLayout(gameData.getX(), gameData.getY(), 0, 0));
        JLabel pLives = new JLabel("Player Lives: " + health.printLivesStats(gameData, GUI, range, 1, 0, frame));
        JLabel eLives = new JLabel("EnemyLives: " + health.printLivesStats(gameData, GUI, range, 0, 1, frame));
        JLabel roundNumber = new JLabel("Round: " + gameData.getRoundNumber());
        System.out.println(gameData.getMaxPlayerLives());
        Border redBorder = BorderFactory.createLineBorder(Color.red, 3, true);
        JLabel enemyAttack = new JLabel("", JLabel.CENTER);

        if (gameData.isEnemyDD() == true) {
            mainPanel.setBorder(redBorder);
            enemyAttack.setText("Enemy has double damage!");
        }
        if (gameData.isPlayerDD() == true) {
            mainPanel.setBorder(redBorder);
        }
        if (gameData.isPlayerDD() == true && gameData.isEnemyDD() == true) {
            mainPanel.setBorder(redBorder);
        }
        if (gameData.isEnemyInRange() == true && gameData.isPlayerDD() == false) {
            enemyAttack.setText("Press SPACE to fight OR press ENTER for double damage during next attack!");
        } else if (gameData.isEnemyInRange() == true && gameData.isPlayerDD() == true) {
            enemyAttack.setText("Press SPACE for double damage fight!");
        } else {
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

            eLives.setBounds(425, 0, 200, 50);
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

        //the loop below is what decides what tile of the gridarray is populated with which image
        for (int[] arr : gameData.getArr()) {
            for (int j = 0; j < arr.length; j++) {
                switch (arr[j]) {
                    case 0:
                        mainPanel.add(new JLabel(gameData.getGrassIcon()));
                        break;
                    case 1:
                        mainPanel.add(new JLabel(gameData.getPlayerURL()));
                        break;
                    case 2:
                        mainPanel.add(new JLabel(gameData.getEnemyURL()));
                        break;
                    case 3:
                        mainPanel.add(new JLabel(gameData.getOjbectIcon()));
                        break;
                }
            }
        }
        totalGUI.add(roundNumber);
        totalGUI.add(mainPanel);
        totalGUI.add(pLives);
        totalGUI.add(eLives);
        totalGUI.add(enemyAttack);
        return totalGUI;
    }

    //MAIN GUI entery point 
    public void DisplayGUI(JFrame frame, mainGUI GUI, SavedData gameData, Range_Attack range, boolean isGameRunning, Database database) {
        Gameboard gameboard = new Gameboard();
        Statistics stats = new Statistics();
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        int screenHeight = d.height;
        int screenWidth = d.width;

        if (gameData.isInGame() == false) {
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

            //name input for the leaderboard
            JTextField nameInput = new JTextField("Name");
            nameInput.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent arg0) {
                    System.out.println("Title# Text area has focus");
                    nameInput.selectAll();
                }

                @Override
                public void focusLost(FocusEvent arg0) {
                    System.out.println("Title# Text area does not have focus");
                }
            });
            nameInput.setBounds(200, 300, 150, 25);
            nameInput.setPreferredSize(new Dimension(150, 25));

            //Instructions button
            JButton infoButton = new JButton("?");
            infoButton.setBounds(710, 10, 25, 25);
            infoButton.setPreferredSize(new Dimension(25, 25));

            infoButton.addActionListener((ActionEvent arg0) -> {
                JOptionPane.showMessageDialog(frame, "1) When your character is within range, you will see the attack options below the gameboard.\n"
                        + "2) When you attack, the enemy has a 1 in 3 odds of attacking you back \nand a 1 in 4 odds of aquiring double damage\n"
                        + "3) If you choose double damage, the enemy has a 1 in 3 odds of attacking you.\n"
                        + "4) Enemy -> Civilian: 1 damage, Enemy -> Witch/ God: 2 damage.\n\n"
                        + "Note) Each character has its own attacking properties. Pick your player accordingly.", "Game Instructions", 1);
            });

            //Start Game button below
            JButton startButton = new JButton("START GAME");
            startButton.setBounds(200, 390, 150, 50);
            startButton.setToolTipText("Select a charater to enable");
            startButton.setEnabled(false);
            startButton.addActionListener((ActionEvent arg0) -> {
                //gameData.setInGame(true);
                try {
                    System.out.println("Start Game Pressed");
                    if (nameInput.getText() != "Name") {
                        Statistics.setPlayerName(nameInput.getText());
                    } else {
                        Statistics.setPlayerName("Not Entered");
                    }
                    gameboard.generateBoard(gameData, stats, frame, GUI, range, database);
                    JOptionPane.showMessageDialog(frame, "1) When your character is within range, you will see the attack options below the gameboard.\n"
                            + "2) When you attack, the enemy has a 1 in 3 odds of attacking you back \nand a 1 in 4 odds of aquiring double damage\n"
                            + "3) If you choose double damage, the enemy has a 1 in 3 odds of attacking you.\n"
                            + "4) Civilian: 1 in 3 attacks miss, Witch: 1 in 4 attacks miss, God: 1 in 5 attacks miss.\n\n"
                            + "Note) Each character has its own attacking properties. Pick your player accordingly.", "Game Instructions", 1);
                    gameData.setInGame(true);
                } catch (IllegalArgumentException e) {
                }
            });

            //View leaderboard button
            JButton viewLeaderboard = new JButton("Leaderboard");
            viewLeaderboard.setBounds(400, 390, 150, 50);
            viewLeaderboard.addActionListener((ActionEvent arg0) -> {
                viewLeaderboard(frame, GUI, gameData, range, true, database);
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
                selectChar.setText("Witch Selected - Hover to see more info");
                gameData.setPlayerType(1);
                startButton.setEnabled(true);
                System.out.println("Character Selected: " + gameData.getPlayerType());
            });

            jrb2.addItemListener((ItemEvent ev) -> {
                selectChar.setText("God Selected - Hover to see more info");
                gameData.setPlayerType(2);
                startButton.setEnabled(true);
                System.out.println("Character Selected: " + gameData.getPlayerType());
            });

            jrb3.addItemListener((ItemEvent ev) -> {
                selectChar.setText("Civilian Selected - Hover to see more info");
                gameData.setPlayerType(3);
                startButton.setEnabled(true);
                System.out.println("Character Selected: " + gameData.getPlayerType());
            });

            //Images which relate to the radio boxes above
            JLabel character1 = new JLabel();
            character1.setIcon(new ImageIcon("img/WitchTra.png"));
            character1.setToolTipText("<html><p width=\"250\">~ 1 damage per hit. <br>~ 1 in 4 attacks miss. <br>~ Horizontal/Vertical & Diagonal attacking.</p></html>");
            character1.setPreferredSize(new Dimension(50, 50));
            character1.setBounds(400, 250, 50, 50);

            JLabel character2 = new JLabel();
            character2.setIcon(new ImageIcon("img/GodTra.png"));
            character2.setToolTipText("<html><p width=\"250\">~ 2 damage per hit. <br>~ 1 in 5 attacks miss. <br>~ Horizontal/Vertical (1 & 3 block away)</p></html>");
            character2.setPreferredSize(new Dimension(50, 50));
            character2.setBounds(500, 250, 50, 50);

            JLabel character3 = new JLabel();
            character3.setIcon(new ImageIcon("img/PlayerTra.png"));
            character3.setToolTipText("<html><p width=\"250\">~ 1 damage per hit. <br>~ 1 in 3 attacks miss. <br>~ Horizontal/Vertical (1 block away only)</p></html>");
            character3.setPreferredSize(new Dimension(50, 50));
            character3.setBounds(610, 250, 50, 50);

            // "BY" on the title screen
            JLabel copyright = new JLabel("© Taylor Styles. Student ID: 17998574", JLabel.CENTER);
            copyright.setFont(new Font("Serif", Font.BOLD, 12));
            copyright.setBounds(0, 470, 750, 50);
            copyright.setForeground(Color.gray);

            if (gameData.isDisplayWinInfo() == true) {
                title.setText("GAME OVER!");
                subTitle.setText("You Survived");
                database.establishConnection(); //Connect to the local database.
                database.insertTable(gameData); //Insert the session data into databse
                database.closeConnections();
                finalTitle.setText(gameData.getRoundNumber() + " round(s)");
                gameData.setDisplayWinInfo(false);
                gameData.setRoundNumber(1);
                gameData.setInGame(false);
                frame.setBounds(0, 0, 750, 532);
                frame.setPreferredSize(new Dimension(750, 532));
                frame.setLocation(new Point((screenWidth / 2) - (frame.getWidth() / 2), (screenHeight / 2) - (frame.getHeight() / 2)));
            }

            // add all of the elements into the container
            titleCon.add(nameInput);
            titleCon.add(viewLeaderboard);
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
            titleCon.add(finalTitle);
            titleCon.add(subTitle);
            titleCon.add(title);
            //add the container to the frame
            frame.setContentPane(titleCon);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);

            try {
                setImages(gameData);
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

    //set the single isntance of the images so im not having to always update the images
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

    // Changes the viree
    // 
    public void viewLeaderboard(JFrame frame, mainGUI GUI, SavedData gameData, Range_Attack range, boolean isGameRunning, Database database) {
        Container leaderCon = new Container();
        leaderCon.setLocation(0, 0);
        leaderCon.setBounds(0, 0, 750, 532);
        leaderCon.setPreferredSize(new Dimension(750, 532));

        //Main title on the title screen
        JLabel title = new JLabel("Leaderboard", JLabel.CENTER);
        title.setFont(new Font("Serif", Font.PLAIN, 32));
        title.setBounds(0, 20, 750, 50);
        title.setForeground(Color.gray);

        JLabel firstPlace = new JLabel("1st ->");
        firstPlace.setForeground(Color.green);
        firstPlace.setBounds(10, 75, 50, 25);
        firstPlace.setPreferredSize(new Dimension(50, 25));

        JLabel secondPlace = new JLabel("2nd ->");
        secondPlace.setForeground(Color.orange);
        secondPlace.setBounds(10, 175, 50, 25);
        firstPlace.setPreferredSize(new Dimension(50, 25));

        JLabel thirdPlace = new JLabel("3rd ->");
        thirdPlace.setForeground(Color.red);
        thirdPlace.setBounds(10, 275, 50, 25);
        thirdPlace.setPreferredSize(new Dimension(50, 25));

        JButton returnButton = new JButton("Return");
        returnButton.setBounds(25, 440, 100, 50);
        returnButton.addActionListener((ActionEvent arg0) -> {
            GUI.DisplayGUI(frame, GUI, gameData, range, false, database);
        });

        JTextArea results = new JTextArea("Loading...");
        database.establishConnection(); //Connect to the local database.
        results.setText(database.getQuery());
        database.closeConnections(); //close database connection
        results.setEditable(false);

        JButton ClearButton = new JButton("Clear Leaderboard");
        ClearButton.setBounds(600, 440, 125, 50);
        ClearButton.addActionListener((ActionEvent arg0) -> {
            database.establishConnection();
            database.clearDB();
            results.setText(database.getQuery());
            database.closeConnections();
        });

        JScrollPane scroll = new JScrollPane(results, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setBounds(70, 80, 600, 330);
        scroll.setBackground(frame.getBackground());
        Border redBorder = BorderFactory.createLineBorder(Color.lightGray, 3, true);
        scroll.setBorder(redBorder);

        javax.swing.SwingUtilities.invokeLater(() -> {
            scroll.getVerticalScrollBar().setValue(0);
        });

        leaderCon.add(ClearButton);
        leaderCon.add(thirdPlace);
        leaderCon.add(secondPlace);
        leaderCon.add(firstPlace);
        leaderCon.add(scroll);
        leaderCon.add(returnButton);
        leaderCon.add(title);
        frame.setContentPane(leaderCon);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
