/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p3game;

/**
 *
 * @author taystyles
 */
public class Health {
    /*
    The health class/ printLivesStats prints the current enemy &/or player lives, it starts by printing the CURRENT lives status and then
    checking the MAX lives status for that entity type, then fills the rest with other icons.
    */
    public static void printLivesStats(SavedData gameData, Statistics stats, int player, int enemy){
        //Printing Enemy Lives
        if (enemy == 1){
            int eCount = 0;
            System.out.print("Enemy: ");
            for(int i = 0; i < gameData.getCurrentEnemyLives(); i++){
                System.out.print(ColourPicker.RED +"♥ "+ ColourPicker.RESET);
                eCount++;
            }
            while(eCount < gameData.getMaxEnemyLives()){
                System.out.print(ColourPicker.RED + "♡ " + ColourPicker.RESET);
                eCount++;
            }

            if (gameData.isEnemyDD() == true) {
                System.out.print("POWERUP: Double Damage\n");
            } 

            else{
                System.out.print("POWERUP: None\n");
            }
            if (gameData.getCurrentEnemyLives() <= 0){
                Gameboard.gameWon(gameData, stats);
                System.exit(0);
            }
            else if (gameData.getCurrentPlayerLives() <= 0){
                Gameboard.gameWon(gameData, stats);
                System.exit(0);
            }   
        }
        
        //Player Lives/ statistics print below
        if (player ==1){    
            int pCount = 0;
            System.out.print("Player: ");
            for(int i = 0; i < gameData.getCurrentPlayerLives(); i++){
                System.out.print(ColourPicker.RED +"♥ "+ ColourPicker.RESET);
                pCount++;
            }
            while(pCount < gameData.getMaxPlayerLives()){
                System.out.print(ColourPicker.RED + "♡ " + ColourPicker.RESET);
                pCount++;
            }

            if (gameData.isPlayerDD() == true) {
                System.out.print("POWERUP: Double Damage\n");
            } 
            else{
                System.out.print("POWERUP: None\n");
            }
            if (gameData.getCurrentEnemyLives() <= 0){
                Gameboard.gameWon(gameData, stats);
                System.exit(0);
            }
            else if (gameData.getCurrentPlayerLives() <= 0){
                Gameboard.gameWon(gameData, stats);
                System.exit(0);
            }   
        }
    }
}
