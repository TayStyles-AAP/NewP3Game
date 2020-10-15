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
public class Statistics {
    /*
    Stats class used by the fileIO class to get the relevent data required
    */
    private static int numberOfMoves; 
    //private static String gameMode; 
    private static String playerName; 
    private static String playerType;
    private static int numberOfPAttacks; 
    private static int numberOfEAttacks; 
    private static int numberOfHotSpringLives; 
    private static long overallTime; 
    private static String winner; 
    private static String loser; 
    private static int numOfPlayerPowerUps; 
    private static int numOfEnemyPowerUps;
    private static long startTime;
    private static long endTime;

    public int getNumberOfMoves() {
        return numberOfMoves;
    }

    public int getNumberOfPAttacks() {
        return numberOfPAttacks;
    }

    public int getNumberOfEAttacks() {
        return numberOfEAttacks;
    }

    public long getOverallTime() {
        return overallTime;
    }
    
    public String getLoser() {
        return loser;
    }
    
    public String getWinner() {
        return winner;
    }

    public int getNumOfPlayerPowerUps() {
        return numOfPlayerPowerUps;
    }

    public int getNumOfEnemyPowerUps() {
        return numOfEnemyPowerUps;
    }

    public String getPlayerType() {
        return playerType;
    }

    public int getNumberOfHotSpringLives() {
        return numberOfHotSpringLives;
    }

    public static String getPlayerName() {
        return playerName;
    }

    public static void setPlayerName(String playerName) {
        Statistics.playerName = playerName;
    }
    
    public void setNumberOfHotSpringLives(int numberOfHotSpringLives) {
        this.numberOfHotSpringLives = numberOfHotSpringLives;
    }
    
    public void setPlayerType(String playerType) {
        this.playerType = playerType;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
        setOverallTime(this.startTime, this.endTime);
    }
    
    public void setNumberOfMoves(int numberOfMoves) {
        this.numberOfMoves = numberOfMoves;
    }

    public void setNumberOfPAttacks(int numberOfPAttacks) {
        this.numberOfPAttacks = numberOfPAttacks;
    }

    public void setNumberOfEAttacks(int numberOfEAttacks) {
        this.numberOfEAttacks = numberOfEAttacks;
    }

    public void setOverallTime(long start, long end) {
        this.overallTime = start - end;
    }

    public void setWinner(String winner) {
        if (winner == "Player WON!"){
            this.loser = "Enemy LOST!";
        }
        else{
            this.loser = "Player LOST!";
        }
        this.winner = winner;
    }

    public void setNumOfPlayerPowerUps(int numOfPlayerPowerUps) {
        this.numOfPlayerPowerUps = numOfPlayerPowerUps;
    }

    public void setNumOfEnemyPowerUps(int numOfEnemyPowerUps) {
        this.numOfEnemyPowerUps = numOfEnemyPowerUps;
    }
}
