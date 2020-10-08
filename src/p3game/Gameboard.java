/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p3game;

import java.util.concurrent.*;
import java.util.*;
import javax.swing.JFrame;
/**
 *
 * @author taystyles
 */

/*

*/
public class Gameboard{
    Scanner scan = new Scanner(System.in);
    Generator generator = new Generator() {};
    
    /*
    The generate board method is a core part of this program and is in charge of printing the array based on the values contained within the array
    if there is a 0, it prints the ground char. if its a 1, it prints the player icon, if its a 2 it prints the enemy icon, and finally if its a 3 it
    prints the hot springs and is called everytime the game needs to print the board
    */
    public void generateBoard(SavedData gameData, Statistics stats, JFrame frame, mainGUI GUI){
        
        if (gameData.isInGame() == false){
            gameData.setArr(new int[gameData.getX()][gameData.getY()]);
            System.out.println("~~~~~~~~~~~~ | Generated Gameboard | ~~~~~~~~~~~~");
            generator.genTerrain(gameData);
            generator.genEntitys('P', gameData);
            generator.genEntitys('E', gameData);
        }
        
        mainGUI.createAndShowGUI(frame, GUI, gameData, true);
        
        for (int i = 0; i < gameData.getArr().length; i++){
            for (int j = 0; j < gameData.getArr()[i].length; j++){
                switch(gameData.getArr()[i][j]){
                    case 0:
                        System.out.print(ColourPicker.GREEN + "- " + ColourPicker.RESET);
                        break;
                    case 1:
                        System.out.print(ColourPicker.MAGENTA+"♿ "+ColourPicker.RESET);
                        break;//
                    case 2:
                        System.out.print(ColourPicker.RED+"☠ "+ColourPicker.RESET);
                        break;
                    case 3:
                        System.out.print(ColourPicker.BLUE+"♨ "+ColourPicker.RESET);
                        break;
                }
            }
            System.out.println();
        }
    }

    /*
    This Start Game is the loop that starts the game/ is the workspace for the rest of the game.
    is in charge of printing the player movements options as well as calling the update gameboard depending on the direction they want to move
    as well as a lot of invalid entry and error handling.
    
    also includes detection for hitting a hotspring
    */
    public void startGame(int f, SavedData gameData, Statistics stats, mainGUI gui, JFrame frame, mainGUI GUI){
        gameData.setInGame(true);
        boolean errorState = false;
        boolean hotSpring = false;
        while (gameData.isInGame() == true){
            if (errorState == false){
                Health.printLivesStats(gameData, stats, 1, 1);
                System.out.println("[W] Up . [S] Down . [A] Left . [D] Right . [Q] Quit");
            }
            char movement = scan.next().charAt(0);

            switch (movement) {
                case 'W':
                case 'w':
                    try{
                        if (gameData.getArr()[gameData.getPlayerY()-1][gameData.getPlayerX()] == 3){
                            if (hotSpring == true){
                                gameData.setCurrentPlayerLives(gameData.getCurrentPlayerLives()-1);
                                System.out.println("** Sizling Sound **");
                                stats.setNumberOfHotSpringLives(stats.getNumberOfHotSpringLives()+1);
                                Health.printLivesStats(gameData, stats, 1, 0);
                                errorState = true;
                                break;
                            }
                            else{
                                System.out.println("Theres a hot springs in your way! "+ColourPicker.RED+"first warning!"+ColourPicker.RESET);
                                hotSpring = true;
                                errorState = true;
                                break;
                            }
                        }
                        else{
                            updateGameboard(0,-1, gameData, stats, frame, GUI); //ARR x = Same, y = -1.
                            errorState = false;
                            break;
                        }
                    }catch(ArrayIndexOutOfBoundsException e){
                        System.out.println("You CAN hide but you CAN'T run away ;)");
                        errorState = true;
                        break;
                    }
                case 'S':
                case 's':
                    try{
                        if (gameData.getArr()[gameData.getPlayerY()+1][gameData.getPlayerX()] == 3){
                            if (hotSpring == true){
                                gameData.setCurrentPlayerLives(gameData.getCurrentPlayerLives()-1);
                                System.out.println("** Sizling Sound **");
                                stats.setNumberOfHotSpringLives(stats.getNumberOfHotSpringLives()+1);
                                Health.printLivesStats(gameData, stats, 1, 0);
                                errorState = true;
                                break;
                            }
                            else{
                                System.out.println("Theres a hot springs in your way! "+ColourPicker.RED+"first warning!"+ColourPicker.RESET);
                                hotSpring = true;
                                errorState = true;
                                break;
                            }
                        }
                        else{
                            updateGameboard(0,1, gameData, stats, frame, GUI);
                            errorState = false;
                            break;
                        }
                    }catch(ArrayIndexOutOfBoundsException e){
                        System.out.println("You CAN hide but you CAN'T run away ;)");
                        errorState = true;
                        break;
                    }
                case 'A':
                case 'a':
                    try{
                        if (gameData.getArr()[gameData.getPlayerY()][gameData.getPlayerX()-1] == 3){
                            if (hotSpring == true){
                                gameData.setCurrentPlayerLives(gameData.getCurrentPlayerLives()-1);
                                System.out.println("** Sizling Sound **");
                                stats.setNumberOfHotSpringLives(stats.getNumberOfHotSpringLives()+1);
                                Health.printLivesStats(gameData, stats, 1, 0);
                                errorState = true;
                                break;
                            }
                            else{
                                System.out.println("Theres a hot springs in your way! "+ColourPicker.RED+"first warning!"+ColourPicker.RESET);
                                hotSpring = true;
                                errorState = true;
                                break;
                            } 
                        }
                        else{
                            updateGameboard(-1,0, gameData, stats, frame, GUI);
                            errorState = false;
                            break;
                        }
                    }catch(ArrayIndexOutOfBoundsException e){
                        System.out.println("You CAN hide but you CAN'T run away ;)");
                        errorState = true;
                        break;
                    }
                case 'D':
                case 'd':
                    try{
                        if (gameData.getArr()[gameData.getPlayerY()][gameData.getPlayerX()+1] == 3){
                            if (hotSpring == true){
                                gameData.setCurrentPlayerLives(gameData.getCurrentPlayerLives()-1);
                                System.out.println("** Sizling Sound **");
                                stats.setNumberOfHotSpringLives(stats.getNumberOfHotSpringLives()+1);
                                Health.printLivesStats(gameData, stats, 1, 0);
                                errorState = true;
                                break;
                            }
                            else{
                                System.out.println("Theres a hot springs in your way! "+ColourPicker.RED+"first warning!"+ColourPicker.RESET);
                                hotSpring = true;
                                errorState = true;
                                break;
                            }
                        }
                        else{
                            updateGameboard(1,0, gameData, stats, frame, GUI);
                            errorState = false;
                            break;
                        }
                    }catch(ArrayIndexOutOfBoundsException e){
                        System.out.println("You CAN hide but you CAN'T run away ;)");
                        errorState = true;
                        break;
                    }
                case 'Q':
                case 'q':
                    System.exit(0);
                    break;
                default:
                    System.out.println(ColourPicker.RED+"~~Error~~ Enter a valid key."+ColourPicker.RESET);
                    break;
            }
        }
    }
    
    /*
    This updateGameboard method is in charge of updating the board with the most up-to-date user/ enemy information/ locations by updating the array 
    which is printed in the generate gameboard function. 
    Also works out weather to take damage or not.
    */
    public void updateGameboard(int changePlayerX, int changePlayerY, SavedData gameData, Statistics stats, JFrame frame, mainGUI GUI){
        System.out.println("~~~~~~~~~~~~ | UPDATED Gameboard | ~~~~~~~~~~~~");
        
        //<<--ENEMY MOVEMENTS BELOW --//>>
        int enemyTemp[][] = gameData.getArr();
        boolean enemyLoop = true;
        while (enemyLoop == true){
            int randMove = ThreadLocalRandom.current().nextInt(0,4);
            switch (randMove) {
                case 0:{
                    //Enemy up
                    int newPos = gameData.getEnemyY() - 1;
                    try{
                        if (enemyTemp[newPos][gameData.getEnemyX()] == 3 || enemyTemp[newPos][gameData.getEnemyX()] == 4){
                          break;
                        }
                    }catch(ArrayIndexOutOfBoundsException e){
                        break;
                    }
                    if (newPos > 0 && newPos <= gameData.getY()-1){ // Checking if the newPos value is not out of bounds.
                        enemyTemp[gameData.getEnemyY()][gameData.getEnemyX()] = 0;
                        enemyTemp[newPos][gameData.getEnemyX()] = gameData.getEnemyToken(); // Keeps players X the same and applies newPos to players Y.
                        gameData.setEnemyY(newPos); // Set the gamedata to reflect the new player Y position.
                        enemyLoop = false;
                        break;
                    }   
                    else{
                        break;
                    }
                }
                case 1:{
                    //Enemy Down
                    int newPos = gameData.getEnemyY() + 1;
                    try{
                        if (enemyTemp[newPos][gameData.getEnemyX()] == 3 || enemyTemp[newPos][gameData.getEnemyX()] == 4){
                            break;
                        }
                    }catch(ArrayIndexOutOfBoundsException e){
                        break;
                    }
                    
                    if (newPos >= 0 && newPos <= gameData.getY()-1){
                        enemyTemp[gameData.getEnemyY()][gameData.getEnemyX()] = 0;
                        enemyTemp[newPos][gameData.getEnemyX()] = gameData.getEnemyToken();
                        gameData.setEnemyY(newPos);   
                        enemyLoop = false;
                        break;
                    }
                    else{
                        break;
                    }
                }
                case 2:{
                    //Enemy Left
                    int newPos = gameData.getEnemyX() - 1;
                    try{
                        if (enemyTemp[gameData.getEnemyY()][newPos] == 3 || enemyTemp[gameData.getEnemyY()][newPos] == 4){
                            break;
                        }
                    }catch(ArrayIndexOutOfBoundsException e){
                        break;
                    }
                    if (newPos >= 0 && newPos <= gameData.getX()-1){
                        enemyTemp[gameData.getEnemyY()][gameData.getEnemyX()] = 0;
                        enemyTemp[gameData.getEnemyY()][newPos] = gameData.getEnemyToken();
                        gameData.setEnemyX(newPos);
                        enemyLoop = false;
                        break;
                    }  
                    else{
                        break;
                    }
                }
                case 3:{
                    //Enemy Right
                    int newPos = gameData.getEnemyX() + 1;
                    try{
                        if (enemyTemp[gameData.getEnemyY()][newPos] == 3 || enemyTemp[gameData.getEnemyY()][newPos] == 4){
                            break;
                        }
                    }catch(ArrayIndexOutOfBoundsException e){
                        break;
                    }
                    if (newPos >= 0 && newPos <= gameData.getX()-1){
                        enemyTemp[gameData.getEnemyY()][gameData.getEnemyX()] = 0;
                        enemyTemp[gameData.getEnemyY()][newPos] = gameData.getEnemyToken();
                        gameData.setEnemyX(newPos);
                        enemyLoop = false;
                        break;
                    }   
                    else{
                        break;
                    }
                }
                default:{
                    System.err.println("ERROR... WRONG NUMBER GENERATED: " + randMove);
                    break;
                }
            }
        }
        gameData.setArr(enemyTemp);
        // </--Enemy movments end --/>
        
        // <-- Player movments start -->
        int playerTemp[][] = gameData.getArr();
        
        gameData.setOldPlayerX(gameData.getPlayerX());
        gameData.setOldPlayerY(gameData.getPlayerY());

        if (changePlayerY != 0){
            int newPos = gameData.getPlayerY() + changePlayerY;                                     // Create new int and assign it the players current YPos -1, this is to move the player up the array.
            if (newPos >= 0 && newPos <= gameData.getY()-1){                                // Checking if the newPos value is not out of bounds.
                if (gameData.getEnemyX() == gameData.getPlayerX() && gameData.getEnemyY() == gameData.getPlayerY()){
                    playerTemp[gameData.getPlayerY()][gameData.getPlayerX()] = 2;                      // Append the tempArray to remove the current players marker.
                }
                else{
                    playerTemp[gameData.getPlayerY()][gameData.getPlayerX()] = 0; 
                }
                playerTemp[newPos][gameData.getPlayerX()] = gameData.getPlayerToken();         // Keeps players X the same and applies newPos to players Y.
                gameData.setPlayerY(newPos);                                            // Set the gamedata to reflect the new player Y position.
            }
        }
        else if (changePlayerX != 0){
            int newPos = gameData.getPlayerX() + changePlayerX;
            if (newPos >= 0 && newPos <= gameData.getX()-1){
                if (gameData.getEnemyX() == gameData.getPlayerX() && gameData.getEnemyY() == gameData.getPlayerY()){
                    playerTemp[gameData.getPlayerY()][gameData.getPlayerX()] = 2;                      // Append the tempArray to remove the current players marker.
                }
                else{
                    playerTemp[gameData.getPlayerY()][gameData.getPlayerX()] = 0; 
                }
                playerTemp[gameData.getPlayerY()][newPos] = gameData.getPlayerToken();
                gameData.setPlayerX(newPos);
            }
        }
        else{
            System.err.println("Error in the Gameboard.java class, in the UpdateGameboard() function.");
        }
        gameData.setArr(playerTemp);
        
        // </-- Player movments end --/>
        
        if ((gameData.getEnemyX() == gameData.getOldPlayerX()) && (gameData.getEnemyY() == gameData.getOldPlayerY())){
            try{
                System.out.print("NOTIFICATION");
                int count = 0;
                while (count <5){
                    Thread.sleep(500);
                    System.out.print(".");
                    count++;
                }
                System.out.println();
            }catch(InterruptedException e){
                System.err.println(e);
            }
            Scanner scan = new Scanner(System.in);
            
            if (ThreadLocalRandom.current().nextInt(0,2) == 0 || gameData.isEnemyDD() == true){
                gameData.setCurrentPlayerLives(gameData.getCurrentPlayerLives() + generator.genAttackCalculation("e", gameData));

                stats.setNumberOfEAttacks(stats.getNumberOfEAttacks()+ 1);
                gameData.setEnemyDD(false);
                generator.genEntitys('P', gameData);
                generator.genEntitys('E', gameData);
                generator.genTerrain(gameData);
            }
            else{
                System.out.println(ColourPicker.RED+"# ENEMY #"+ColourPicker.RESET+" Decided not to attack and will apply double damage next time");
                gameData.setEnemyDD(true);
                generator.genEntitys('P', gameData);
                generator.genEntitys('E', gameData);
                generator.genTerrain(gameData);
            }
        }
        
        if (gameData.getEnemyX() == gameData.getPlayerX() && gameData.getEnemyY() == gameData.getPlayerY()){
            try{ //nice looking loading screen for letting the player know that something has happened.
                System.out.print("NOTIFICATION");
                int count = 0;
                while (count <5){
                    Thread.sleep(500);
                    System.out.print("."); //prints 5 dots totalling 2.5 second wait.
                    count++;
                }
                System.out.println();
            }catch(InterruptedException e){
                System.err.println(e);
            }
            
            if (gameData.isPlayerDD() == false){ //if the player doesnt have double damage enabled & shows attack options.
                System.out.println("You are about to contact the enemy!");
                int attackOpt;
                boolean loop = true;
                while (loop == true){
                    System.out.println("1 - to attack\n2 - to continue (double damage next attack)");
                    try{
                        attackOpt = scan.nextInt();
                        if (attackOpt == 1){
                            stats.setNumberOfPAttacks(stats.getNumberOfPAttacks()+ 1);
                            gameData.setCurrentEnemyLives(gameData.getCurrentEnemyLives() + generator.genAttackCalculation("p", gameData));
                            generator.genEntitys('P', gameData); //generate new player
                            generator.genEntitys('E', gameData); //generate new enemy
                            generator.genTerrain(gameData);

                        }
                        else if (attackOpt == 2){
                            gameData.setPlayerDD(true);
                            generator.genEntitys('P', gameData); //generate new player
                            generator.genEntitys('E', gameData); //generate new enemy
                            generator.genTerrain(gameData);
                        }
                        loop = false;
                    }catch(InputMismatchException e){ /*if they didnt enter an int above. Had to impliemnt as this happens mid game
                        and can cause the user to enter a char/ string when the program is waiting for an int.
                        */
                        System.out.println(ColourPicker.RED+"!!! INCORRECT INPUT !!!"+ColourPicker.RESET);
                        scan.nextLine();
                    }
                }
            }
            else{ //is called if the player has double damage enabled and allows for other attack options
                System.out.println("You are about to contact the enemy!\n1 - Attack with normal damage\n2 - Attack with double damage");
                boolean loop = true;
                while (loop == true) {
                    try{
                        int attackOpt = scan.nextInt();
                        if (attackOpt == 1){ //if the user selected attack with normal damage, switch case below determines how many lives to take.
                            switch (gameData.getPlayerType()) {
                                case 3:
                                    gameData.setCurrentEnemyLives(gameData.getCurrentEnemyLives()-2);
                                    stats.setNumberOfPAttacks(stats.getNumberOfPAttacks()+ 1);
                                    break;
                                case 2:
                                    gameData.setCurrentEnemyLives(gameData.getCurrentEnemyLives()-4);
                                    stats.setNumberOfPAttacks(stats.getNumberOfPAttacks()+ 1);
                                    break;
                                case 1:
                                    gameData.setCurrentEnemyLives(gameData.getCurrentEnemyLives()-1);
                                    stats.setNumberOfPAttacks(stats.getNumberOfPAttacks()+ 1);
                                    break;
                                default:
                                    System.err.println("error with attact option in updategameboard class.");
                            }
                            generator.genEntitys('P', gameData); //new player
                            generator.genEntitys('E', gameData); //new enemy
                            generator.genTerrain(gameData); //generates new terrain after each death.
                        }
                        else if (attackOpt == 2){
                            gameData.setPlayerDD(false);
                            switch (gameData.getPlayerType()) {
                                case 3:
                                    gameData.setCurrentEnemyLives(gameData.getCurrentEnemyLives()-4);
                                    stats.setNumberOfPAttacks(stats.getNumberOfPAttacks()+ 1);
                                    break;
                                case 2:
                                    gameData.setCurrentEnemyLives(gameData.getCurrentEnemyLives()-8);
                                    stats.setNumberOfPAttacks(stats.getNumberOfPAttacks()+ 1);
                                    break;
                                case 1:
                                    gameData.setCurrentEnemyLives(gameData.getCurrentEnemyLives()-2);
                                    stats.setNumberOfPAttacks(stats.getNumberOfPAttacks()+ 1);
                                    break;
                                default:
                                    System.err.println("error with attact option in updategameboard class.");
                            }
                            generator.genEntitys('P', gameData);
                            generator.genEntitys('E', gameData);
                            generator.genTerrain(gameData);
                        }
                        loop = false;
                    }catch(InputMismatchException e){
                        System.out.println(ColourPicker.RED+"!!! INCORRECT INPUT !!!"+ColourPicker.RESET);
                        System.out.println("1 - Attack with normal damage\n2 - Attack with double damage");
                        scan.nextLine();
                    }
                }
            }
            scan.nextLine();
        }
        generateBoard(gameData, stats, frame, GUI);
    }
    
    /*
    Game won method is called once either the player or enemy has no lives left and prints an option to either save or discard the 
    game data. it then calls the file io function if nessescary to save the data.
    */
    public static void gameWon(SavedData gameData, Statistics stats){
        Scanner scan = new Scanner(System.in);
        if (gameData.getCurrentEnemyLives() <= 0) {
            System.out.println("\u001b[41m /****** \u001b[42m YOU WON! \u001b[41m ******/");
            stats.setWinner("Player WON!");
        }
        else{
            System.out.println("\u001b[41m /****** \u001b[42m ENEMY WON! \u001b[41m ******/");
            stats.setWinner("Enemy WON!");
        }
        System.out.println("THANK YOU FOR PLAYING");
        scan.close();
    }
}