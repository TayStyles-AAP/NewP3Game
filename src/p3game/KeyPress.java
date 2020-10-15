/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p3game;

import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author taystyles
 */
public class KeyPress {
    public static void AddKeyListener(Gameboard gameboard, SavedData gameData, Statistics stats, JFrame frame, mainGUI GUI, Range_Attack range, Database database){
                    //Add the key listener for the whole game.
            frame.addKeyListener(new KeyListener() {
                boolean minimise = false;
                @Override
                public void keyTyped(KeyEvent e) {}
                @Override
                public void keyPressed(KeyEvent e) {}
                @Override
                public void keyReleased(KeyEvent e) {
                    //System.out.println("'"+ e.getKeyChar() +", "+e.getKeyCode()+ "' Pressed");
                    minimise = false;
                    try{
                        KeyPress.handleKeypress(e.getKeyCode(), gameboard, gameData, stats, frame, GUI, range, database);
                    }catch(NullPointerException k){
                        System.out.println("# No key commands available from the start game screen!");
                    }

                }
            });
    }
    public static void handleKeypress(int e, Gameboard gameboard, SavedData gameData, Statistics stats, JFrame frame, mainGUI GUI, Range_Attack range, Database database) {
        Generator generator = new Generator();
        System.out.println("Keys# Key: " + e + " Pressed");
        boolean minimise = false;
        
        if (e == 91 || e == 93) {
            minimise = true;
        }

        switch (e) {
            case 87: //Up key
                if (gameboard.checkValidMove('w', gameData) == true) {
                    gameboard.updateGameboard(0, -1, gameData, stats, frame, GUI, range, database);
                }
                break;
            case 83: //down key
                if (gameboard.checkValidMove('s', gameData) == true) {
                    gameboard.updateGameboard(0, 1, gameData, stats, frame, GUI, range, database);
                }
                break;
            case 65://left key
                if (gameboard.checkValidMove('a', gameData) == true) {
                    gameboard.updateGameboard(-1, 0, gameData, stats, frame, GUI, range, database);
                }
                break;
            case (68): //right key
                if (gameboard.checkValidMove('d', gameData) == true) {
                    gameboard.updateGameboard(1, 0, gameData, stats, frame, GUI, range, database);
                }
                break;
            case 38://up arrow key --> UP
                if (gameboard.checkValidMove('w', gameData) == true) {
                    gameboard.updateGameboard(0, -1, gameData, stats, frame, GUI, range, database);
                }
                break;
            case 40: //down arrow key --> DOWN
                if (gameboard.checkValidMove('s', gameData) == true) {
                    gameboard.updateGameboard(0, 1, gameData, stats, frame, GUI, range, database);
                }
                break;
            case 37: //left arrow key --> LEFT
                if (gameboard.checkValidMove('a', gameData) == true) {
                    gameboard.updateGameboard(-1, 0, gameData, stats, frame, GUI, range, database);
                }
                break;
            case (39): //right arrow key --> RIGHT
                if (gameboard.checkValidMove('d', gameData) == true) {
                    gameboard.updateGameboard(1, 0, gameData, stats, frame, GUI, range, database);
                }
                break;
            case (77): //Minimise (bound just to the M key for now, cant figure out multikey commands with MacOS.
                frame.setState(Frame.ICONIFIED);
                break;
            case (32):// Space key -> attack
                if (gameData.isEnemyInRange() == true) {
                    gameData.setEnemyInRange(false);
                    CalculateAttack(gameboard, gameData, stats, frame, GUI, range, generator, true, database);
                }
                break;
            case (13): //windows enter key -> Double damage
                if (gameData.isEnemyInRange() == true && gameData.isPlayerDD() == false) {
                    int attackOdds = ThreadLocalRandom.current().nextInt(0, 3); //1 in 3 odds
                    System.out.println("Attack# Attack odds: "+attackOdds);
                    switch(attackOdds){
                        case(0):
                            CalculateAttack(gameboard, gameData, stats, frame, GUI, range, generator, false, database);
                            break;
                        default:
                            break;
                    }
                    gameData.setPlayerDD(true);
                    gameData.setEnemyInRange(false);
                    
                    generator.genEntitys('E', gameData); //generate new enemy
                    gameboard.generateBoard(gameData, stats, frame, GUI, range, database);

                }
                break;
            case (10): //Mac enter key -> Double damage
                if (gameData.isEnemyInRange() == true && gameData.isPlayerDD() == false) {
                    int attackOdds = ThreadLocalRandom.current().nextInt(0, 3); //1 in 3 odds
                    System.out.println("Attack# Attack odds: "+ attackOdds);
                    switch(attackOdds){
                        case(0):
                            CalculateAttack(gameboard, gameData, stats, frame, GUI, range, generator, false, database);
                            break;
                        default:
                            break;
                    }
                    gameData.setPlayerDD(true);
                    gameData.setEnemyInRange(false);
                    //generator.genEntitys('P', gameData); //generate new player
                    generator.genEntitys('E', gameData); //generate new enemy
                    //generator.genTerrain(gameData);
                    gameboard.generateBoard(gameData, stats, frame, GUI, range, database);
                }
                break;
            default:
                break;
        }
    }
    
    public static void CalculateAttack(Gameboard gameboard, SavedData gameData, Statistics stats, JFrame frame, mainGUI GUI, Range_Attack range, Generator generator, boolean playerAttack, Database database){
        int willEnemyAttack = ThreadLocalRandom.current().nextInt(0, 3);
        int willPlayerAttack = ThreadLocalRandom.current().nextInt(0, 2);
        int oldEnemyLives = gameData.getCurrentEnemyLives();
        int oldPlayerLives = gameData.getCurrentPlayerLives();
        
        //calculate player to enemy damage
        if (playerAttack == true){
            switch(willPlayerAttack){
            case(0):
                gameData.setCurrentEnemyLives(gameData.getCurrentEnemyLives() + generator.genAttackCalculation("p", gameData));
                System.out.println("Attack# Player Attacked Enemy!");
                if (gameData.isPlayerDD() == true){
                    gameData.setPlayerDD(false);
                }
                break;
            default:
                System.out.println("Attack# Player Missed Enemy");
                break;
            }
        }
        
        System.out.println("Attack# Enemy Generated attack option: " + willEnemyAttack);
        
        if (willEnemyAttack == 1 && gameData.isEnemyDD() == true){
            willEnemyAttack = 0;
            System.out.println("Attack# Enemy has double damage applied.");
        }
        switch (willEnemyAttack) {
            case (0):
                System.out.println("Attack# Enemy Attacked you back!");
                //calculate enemy to player damage
                gameData.setCurrentPlayerLives(gameData.getCurrentPlayerLives() + generator.genAttackCalculation("e", gameData));
                if (gameData.isEnemyDD() == true){
                    gameData.setEnemyDD(false);
                }
                break;
            case (1):
                if (gameData.isEnemyDD() == false) {
                    System.out.println("Attack# Enemy did not attack. Double damage applied.");
                    gameData.setEnemyDD(true);
                }
                break;
            case (2):
                break;
            default:
                break;
        }
        generator.genEntitys('P', gameData); //generate new player
        generator.genEntitys('E', gameData); //generate new enemy
        //generator.genTerrain(gameData);
        
        JOptionPane.showMessageDialog(frame, getAttackReport(gameData, oldEnemyLives, oldPlayerLives, willEnemyAttack, willPlayerAttack), "Attack Report", 2);
        
        if(gameData.getCurrentPlayerLives() <= 0){
            gameData.setDisplayWinInfo(true);
            GUI.createAndShowGUI(frame, GUI, gameData, range, false, database);
        }else{
            gameboard.generateBoard(gameData, stats, frame, GUI, range, database);
        }
    }
    
    public static String getAttackReport(SavedData gameData, int OEL, int OPL, int enemyAttack, int willPlayerAttack){
        String report = "";
        int EnemyDamage = gameData.getCurrentEnemyLives() - OEL;
        int PlayerDamage = gameData.getCurrentPlayerLives() - OPL;
        
        if (EnemyDamage != 0){
            report+= "- You inflicted "+ EnemyDamage+" damage to the enemy.\n";
        }
        if (PlayerDamage != 0){
            report+= "- Enemy inflicted "+ PlayerDamage+" damage to you.\n";
        }
        if (enemyAttack == 2){
            report+= "- Enemy did not attack you.\n";
        }
        if (gameData.isEnemyDD() == true && enemyAttack != 2){
            report+= "- Enemy did not attack & applied double damage.\n";
        }
        if (willPlayerAttack != 0){
            report+= "- You missed! Better luck next time.\n";
        }
        return report;
    }
}
//frame,"You attacked the enemy and gave "+(oldEnemyLives-gameData.getCurrentEnemyLives())+" damage.\n"
//                + "The enemy attacked you and took "+(oldPlayerLives-gameData.getCurrentPlayerLives())+" damage."
