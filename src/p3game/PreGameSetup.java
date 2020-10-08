/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p3game;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Taylor
 */
public class PreGameSetup {
    public static char init(SavedData gameData){
        Scanner scan = new Scanner(System.in);
        System.out.println("/******************************************************/\n/   ***   "+ColourPicker.RED +"WELCOME TO RPG GAME BY TAYLOR STYLES"+ColourPicker.RESET+"   ***   /\n/******************************************************/\n");
        System.out.println("E - Easy 10/10 board size, enemy damage = 1, enemy lives = 5 \nH - Hard 15/15 board size, double enenmy damage, enemy lives = 10");
        while(true){
            try{
                char difficulty = scan.next().charAt(0);
                difficulty = Character.toUpperCase(difficulty);
                if (difficulty == 'E' || difficulty == 'H'){
                    gameData.setDifficulty(difficulty);
                    break;
                }
            }catch(InputMismatchException e){
                scan.nextLine();
            }
        }
        System.out.println("\nYou selected "+ gameData.getDifficulty() +". Now select your player type;\n1 = Witch        | Lives + 2 (7 total)\n2 = God          | Lives + 5 (10 total) 4 x damage (same applies for enemy)\n3 = Warrier      | Damage x 2 (Double Damage)");
        
        while (true){
            try{
                int playerType = scan.nextInt();
                if (playerType == 1 || playerType == 2 || playerType == 3){
                    gameData.setPlayerType(playerType);
                    break;
                }
            }catch(InputMismatchException e){
                scan.nextLine();
            }
        }
        System.out.println("\nReady to play?\n"+ColourPicker.BLUE+"READ THE RULES: \n"+ColourPicker.RESET
                + "1) If you correctly guess the enemy's next move and move onto the same position you can attack. (1/4 odds)\n"
                + "2) If the enemy steps over you to your prevous position, the enemy has the ability to attack you. (1/4 odds)\n"
                + "3) For each player attack, You can either attack now for standard damage, or continue for double damage next time.\n"
                + "4) You are NOT able to move through hot springs. The first time you will be warned, the next you will lose 1 life.\n"
                + ColourPicker.RED+"NOTE)"+ColourPicker.RESET+" The enemy will actively avoid hot springs and gameboard edges, USE THIS TO YOUR ADVANTAGE TO PREDICT HIS NEXT MOVE \n"
                + "Key; ♿ = You, ☠ = Enemy, ♨ = Hot Springs\n\n");
        System.out.println("\u001b[41mPress Y to accept you've read the rules."+ColourPicker.RESET);
        scan.nextLine();
        char start = scan.next().charAt(0);
        return start;
    }
}
