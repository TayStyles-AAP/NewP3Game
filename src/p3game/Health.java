/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p3game;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author taystyles
 */
public class Health {
    /*
    The health class/ printLivesStats prints the current enemy &/or player lives, it starts by printing the CURRENT lives status and then
    checking the MAX lives status for that entity type, then fills the rest with other icons.
    */
    public String printLivesStats(SavedData gameData, mainGUI GUI, Range_Attack range, int player, int enemy, JFrame frame){
        //Printing Enemy Lives
        String lives = "";
        if (enemy == 1){
            int eCount = 0;
            for(int i = 0; i < gameData.getCurrentEnemyLives(); i++){
                lives = lives + "♥";
                eCount++;
            }
            while(eCount < gameData.getMaxEnemyLives()){
                lives = lives + "♡";
                eCount++;
            }
            if (gameData.getCurrentEnemyLives() <= 0){
                //Gameboard.gameWon(gameData);
                //GUI.createAndShowGUI(frame, GUI, gameData, range, false);
                gameData.setRoundNumber(gameData.getRoundNumber()+1);
                gameData.setCurrentEnemyLives(gameData.getMaxEnemyLives());
            }
            else if (gameData.getCurrentPlayerLives() <= 0){
                //Gameboard.gameWon(gameData);
                //GUI.createAndShowGUI(frame, GUI, gameData, range, false);
                gameData.setInGame(false);
                gameData.setDisplayWinInfo(true);
            }   
        }
        
        //Player Lives/ statistics print below
        if (player ==1){    
            int pCount = 0;
            for(int i = 0; i < gameData.getCurrentPlayerLives(); i++){
                lives = lives + "♥";
                pCount++;
            }
            while(pCount < gameData.getMaxPlayerLives()){
                lives = lives + "♡";
                pCount++;
            }

            if (gameData.getCurrentEnemyLives() <= 0){
                //Gameboard.gameWon(gameData);
                //GUI.createAndShowGUI(frame, GUI, gameData, range, false);
                gameData.setRoundNumber(gameData.getRoundNumber()+1);
                gameData.setCurrentEnemyLives(gameData.getMaxEnemyLives());
            }
            else if (gameData.getCurrentPlayerLives() <= 0){
                //Gameboard.gameWon(gameData);
                //GUI.createAndShowGUI(frame, GUI, gameData, range, false);
                gameData.setRoundNumber(gameData.getRoundNumber()+1);
                gameData.setCurrentEnemyLives(gameData.getMaxEnemyLives());
                gameData.setInGame(false);
                gameData.setDisplayWinInfo(true);
            }   
        }
        return lives;
    }
}
