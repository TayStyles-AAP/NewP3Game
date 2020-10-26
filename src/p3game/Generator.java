package p3game;

import java.util.concurrent.ThreadLocalRandom;

/*
 * @author Taylor
 */
public class Generator {

    /*
    The generate entities method takes a player type as a parameter and uses that to decide which entity to re-spawn.
    for each attack, both entities cleared are re-spawned every time.
     */
    public void genEntitys(char playerType, SavedData gameData) {
        int randX = genNumbers(gameData);
        int randY = genNumbers(gameData);
        int tempArr[][] = gameData.getArr(); //Make new temp array and assign it the value of the main array
        int randLoop = 0;

        while (randLoop == 0) {
            if ((tempArr[randY][randX] == 0) && (tempArr[randY + 1][randX] == 0) && (tempArr[randY - 1][randX] == 0) && (tempArr[randY][randX + 1] == 0) && (tempArr[randY][randX - 1] == 0) && (tempArr[randY + 1][randX + 1] == 0) && (tempArr[randY + 1][randX - 1]) == 0 && (tempArr[randY - 1][randX + 1]) == 0 && (tempArr[randY - 1][randX - 1]) == 0) {
                switch (playerType) {
                    case ('P'):
                        if (gameData.isInGame() == true) { //finds any existing player identifiers in the array and sets them to 0 (ground piece)
                            for (int i = 0; i < gameData.getArr().length; i++) {
                                for (int j = 0; j < gameData.getArr()[i].length; j++) {
                                    if (gameData.getArr()[i][j] == 1) {
                                        tempArr[i][j] = 0;
                                    }
                                }
                            }
                        }
                        gameData.setPlayerX(randX); //Initilise Enemy X to the prevously random number
                        gameData.setPlayerY(randY); //Initilise Enemy Y to the prevously random number
                        tempArr[randY][randX] = gameData.getPlayerToken(); //sets the random location in the array to the player int.
                        System.out.println("GEN# Player Generated");
                        break;
                    case ('E'):
                        if (gameData.isInGame() == true) {//finds any existing enemies identifiers in the array and sets them to 0 (ground piece)
                            for (int i = 0; i < gameData.getArr().length; i++) {
                                for (int j = 0; j < gameData.getArr()[i].length; j++) {
                                    if (gameData.getArr()[i][j] == 2) {
                                        tempArr[i][j] = 0;
                                    }
                                }
                            }
                        }
                        gameData.setEnemyX(randX); //Initilise Enemy X to the prevously generated random number
                        gameData.setEnemyY(randY); //Initilise Enemy Y to the prevously generated random number
                        tempArr[randY][randX] = gameData.getEnemyToken();
                        System.out.println("GEN# Enemy Generated");
                }
                randLoop = 1;
            } else {
                randX = genNumbers(gameData);
                randY = genNumbers(gameData);
            }
        }
        gameData.setArr(tempArr); //assign main array the values of tempoaray
    }

    /*
     takes information from my SavedData class and uses them to workout how much damage to apply to either the player or
    enemy upon an attack.
     */
    public int genAttackCalculation(String playerType, SavedData gameData) {
        if (playerType == "p") {
            if (gameData.isPlayerDD() == false && gameData.getPlayerType() == 3) { //player health damage
                return -1;
            } else if (gameData.isPlayerDD() == true && gameData.getPlayerType() == 3) {
                return -2;
            } else if (gameData.isPlayerDD() == false && gameData.getPlayerType() == 2) { //god health damage
                return -2;
            } else if (gameData.isPlayerDD() == true && gameData.getPlayerType() == 2) {
                return -4;
            } else if (gameData.isPlayerDD() == false && gameData.getPlayerType() == 1) { //witch health damage
                return -1;
            } else if (gameData.isPlayerDD() == true && gameData.getPlayerType() == 1) {
                return -2;
            }
        } else {
            if (gameData.getDifficulty() == 'E' && gameData.isEnemyDD() == false) {
                return -1;
            } else if (gameData.getDifficulty() == 'E' && gameData.isEnemyDD() == true) {
                return -2;
            }
        }
        return 0;
    }

    /*
    The genTerrian function is designed to firstly clear all & any exiting terrain (due to re-generating the terrain after each life)
    then depending on the hardness, either places 6 or 10 hot springs in any location that has a 0 in the array, indicating that the
    space is clear to place an object.
     */
    public void genTerrain(SavedData gameData) {
        int tempArr[][] = gameData.getArr();
        if (gameData.isInGame() == true) {
            for (int i = 0; i < gameData.getArr().length; i++) {
                for (int j = 0; j < gameData.getArr()[i].length; j++) {
                    if (gameData.getArr()[i][j] == 3 || gameData.getArr()[i][j] == 4) {
                        tempArr[i][j] = 0;
                    }
                }
            }
        }
        //Genrate springs easy difficulty below (6 springs)
        int easyRockLoop = 0;
        if (gameData.getDifficulty() == 'E') {
            while (easyRockLoop < 10) {
                int randX = genNumbers(gameData);
                int randY = genNumbers(gameData);
                if (tempArr[randX][randY] == 0) {
                    tempArr[randX][randY] = 3;
                    easyRockLoop++;
                }
            }
        }
        //Genrate springs easy difficulty below (10 springs)
        int hardRockLoop = 0;
        if (gameData.getDifficulty() == 'H') {
            while (hardRockLoop < 23) {
                int randX = genNumbers(gameData);
                int randY = genNumbers(gameData);
                if (tempArr[randX][randY] == 0) {
                    tempArr[randX][randY] = 3;
                    hardRockLoop++;
                }
            }
        }
        System.out.println("GEN# Terrain Generated");
        gameData.setArr(tempArr);
    }

    //Simply a random number generator to clean up code above.
    public int genNumbers(SavedData gameData) {
        return ThreadLocalRandom.current().nextInt(1, gameData.getX() - 1);
    }
}
