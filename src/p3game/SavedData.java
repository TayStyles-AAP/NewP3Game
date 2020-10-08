package p3game;

/*
This class stores all game data used within the game including player data, enemy data and special array tokens.
*/

public class SavedData{
    Statistics stats = new Statistics();
    
    private boolean debug; //Do i want debugger to print more debugging things?
    private int x; //Board X size
    private int y; //Board Y size
    private char difficulty; //Gameboard size (10/10 or 25/25)
    private int playerX; //Current player X pos
    private int playerY; //Current player Y pos
    private int enemyX; //Current enemy X pos
    private int enemyY; //Current enemy Y pos
    private int[][] arr; //Gameboard Array. Contains ALL attributes that are printed incl players, enemies, items and terrain.
    private boolean inGame; //If the game is currently running the Gameboard.StartGame();
    private final int playerToken = 1; //Player token ID. is used in the array to print a player
    private final int EnemyToken = 2; //Enemy token ID. is used in the array to print a enemy
    private int CurrentEnemyLives;
    private int MaxEnemyLives; // Number of enemy lives.
    private int MaxPlayerLives; // Number of enemy lives.
    private int CurrentPlayerLives;
    private int playerType; //Selects the player type to allow for other powers.
    private int oldPlayerX; //old player pos's for attacking purposes
    private int oldPlayerY; //old player pos's for attacking purposes
    private boolean enemyDD = false; //Enemy double damage
    private boolean playerDD = false; //Player double damage
    
    public boolean isDebug() {
        return debug;
    }

    public int getMaxEnemyLives() {
        return MaxEnemyLives;
    }
    
    public int getCurrentEnemyLives() {
        return CurrentEnemyLives;
    }
    
    public int getPlayerToken() {
        return playerToken;
    }
    
    public int getEnemyToken() {
        return EnemyToken;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return y;
    }

    public char getDifficulty() {
        return difficulty;
    }

    public int getPlayerX() {
        return playerX;
    }

    public int getPlayerY() {
        return playerY;
    }

    public int getEnemyX() {
        return enemyX;
    }

    public int getEnemyY() {
        return enemyY;
    }

    public int[][] getArr() {
        return arr;
    }

    public boolean isInGame() {
        return inGame;
    }
    
    public int getPlayerType() {
        return playerType;
    }

    public int getMaxPlayerLives() {
        return MaxPlayerLives;
    }

    public int getCurrentPlayerLives() {
        return CurrentPlayerLives;
    }

    public int getOldPlayerX() {
        return oldPlayerX;
    }

    public int getOldPlayerY() {
        return oldPlayerY;
    }

    public boolean isEnemyDD() {
        return this.enemyDD;
    }

    public boolean isPlayerDD() {
        return this.playerDD;
    }
    
    public void setMaxPlayerLives(int MaxPlayerLives) {
        this.MaxPlayerLives = MaxPlayerLives;
        CurrentPlayerLives = this.MaxPlayerLives;
    }
    
    public void setMaxEnemyLives(int MaxEnemyLives) {
        this.MaxEnemyLives = MaxEnemyLives;
        CurrentEnemyLives = this.MaxEnemyLives;
    }

    public void setCurrentPlayerLives(int CurrentPlayerLives) {
        this.CurrentPlayerLives = CurrentPlayerLives;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public void setDifficulty(char difficulty) {
        switch (difficulty) {
            case 'E':
            case 'e':
                this.x = 10;
                this.y = 10;
                setMaxEnemyLives(5);
                this.difficulty = difficulty;
                stats.setGameMode("Easy");
                break;
            case 'H':
            case 'h':
                this.x = 15;
                this.y = 15;
                setMaxEnemyLives(10);
                this.difficulty = difficulty;
                stats.setGameMode("Hard");
                break;
            default:
                System.out.println("That wasnt a valid difficulty. Easy selected.");
                this.x = 10;
                this.y = 10;
                setMaxEnemyLives(5);
                this.difficulty = 'E';
                stats.setGameMode("Easy");
                break;
        }
    }

    public void setPlayerX(int playerX) {
        this.playerX = playerX;
        stats.setNumberOfMoves(stats.getNumberOfMoves() + 1);
    }

    public void setPlayerY(int playerY) {
        this.playerY = playerY;
        stats.setNumberOfMoves(stats.getNumberOfMoves() + 1);
    }
    
    public void setEnemyX(int enemyX) {
        this.enemyX = enemyX;
    }

    public void setEnemyY(int enemyY) {
        this.enemyY = enemyY;
    }

    public void setArr(int[][] arr) {
        this.arr = arr;
    }

    public void setInGame(boolean inGame) {
        this.inGame = inGame;
    }
    
    public void setCurrentEnemyLives(int CurrentEnemyLives) {
        this.CurrentEnemyLives = CurrentEnemyLives;
    }
    
    public void setPlayerType(int playerType) {

        switch(playerType){
            case(1):
                stats.setPlayerType("Witch");
                setMaxPlayerLives(7);
                break;
            case(2):
                stats.setPlayerType("GOD");
                setMaxPlayerLives(10);
                break;
            case(3):
                stats.setPlayerType("Warrier");
                setMaxPlayerLives(5);
                break;
            default:
                stats.setPlayerType("nil");
                break;
        }
        this.playerType = playerType;
    }

    public void setOldPlayerX(int oldPlayerX) {
        this.oldPlayerX = oldPlayerX;
    }

    public void setOldPlayerY(int oldPlayerY) {
        this.oldPlayerY = oldPlayerY;
    }

    public void setEnemyDD(boolean enemyDD) {
        this.enemyDD = enemyDD;
        if(enemyDD ==true){
            stats.setNumOfEnemyPowerUps(stats.getNumOfEnemyPowerUps() + 1);
        }
    }

    public void setPlayerDD(boolean playerDD) {
        this.playerDD = playerDD;
        if (playerDD ==true){
            stats.setNumOfPlayerPowerUps(stats.getNumOfPlayerPowerUps() + 1);
        }
    }
}