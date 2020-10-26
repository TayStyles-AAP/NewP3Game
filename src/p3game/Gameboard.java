package p3game;

import java.util.concurrent.*;
import java.util.*;
import javax.swing.JFrame;

public class Gameboard {

    Scanner scan = new Scanner(System.in);
    Generator generator = new Generator() {
    };

    /*
    The generate board method is a core part of this program and is in charge of printing the array based on the values contained within the array
    if there is a 0, it prints the ground char. if its a 1, it prints the player icon, if its a 2 it prints the enemy icon, and finally if its a 3 it
    prints the hot springs and is called everytime the game needs to print the board
     */
    public void generateBoard(SavedData gameData, Statistics stats, JFrame frame, mainGUI GUI, Range_Attack range, Database database) {
        if (gameData.isInGame() == false) {
            gameData.setArr(new int[gameData.getX()][gameData.getY()]);
            System.out.println("~~~~~~~~~~~~ | GUI Generated | ~~~~~~~~~~~~");
            generator.genTerrain(gameData);
            generator.genEntitys('P', gameData);
            generator.genEntitys('E', gameData);
            gameData.setInGame(true);
        }
        GUI.DisplayGUI(frame, GUI, gameData, range, true, database);
    }

    /*
    This updateGameboard method is in charge of updating the board with the most up-to-date user/ enemy information/ locations by updating the array 
    which is printed in the generate gameboard function. 
    Also works out weather to take damage or not.
     */
    public void updateGameboard(int changePlayerX, int changePlayerY, SavedData gameData, Statistics stats, JFrame frame, mainGUI GUI, Range_Attack range, Database database) {
        System.out.println("~~~~~~~~~~~~ | GUI UPDATED | ~~~~~~~~~~~~");
        int tempArr[][] = gameData.getArr();

        gameData.setOldPlayerX(gameData.getPlayerX());
        gameData.setOldPlayerY(gameData.getPlayerY());

        if (changePlayerY != 0) {
            int newPos = gameData.getPlayerY() + changePlayerY;       // Create new int and assign it the players current YPos -1, this is to move the player up the array.
            if (newPos >= 0 && newPos <= gameData.getY() - 1) {       // Checking if the newPos value is not out of bounds.
                if (gameData.getEnemyX() == gameData.getPlayerX() && gameData.getEnemyY() == gameData.getPlayerY()) {
                    tempArr[gameData.getPlayerY()][gameData.getPlayerX()] = 2;     // Append the tempArray to remove the current players marker.
                } 
                else {
                    tempArr[gameData.getPlayerY()][gameData.getPlayerX()] = 0;
                }
                tempArr[newPos][gameData.getPlayerX()] = gameData.getPlayerToken(); // Keeps players X the same and applies newPos to players Y.
                gameData.setPlayerY(newPos);               // Set the gamedata to reflect the new player Y position.
            }
            stats.setNumberOfMoves(stats.getNumberOfMoves() + 1);
        } 
        else if (changePlayerX != 0) {
            int newPos = gameData.getPlayerX() + changePlayerX;
            if (newPos >= 0 && newPos <= gameData.getX() - 1) {
                if (gameData.getEnemyX() == gameData.getPlayerX() && gameData.getEnemyY() == gameData.getPlayerY()) {
                    tempArr[gameData.getPlayerY()][gameData.getPlayerX()] = 2;                      // Append the tempArray to remove the current players marker.
                } 
                else {
                    tempArr[gameData.getPlayerY()][gameData.getPlayerX()] = 0;
                }
                tempArr[gameData.getPlayerY()][newPos] = gameData.getPlayerToken();
                gameData.setPlayerX(newPos);
            }
            stats.setNumberOfMoves(stats.getNumberOfMoves() + 1);

        } 
        else {
            System.err.println("Error in the Gameboard.java class, in the UpdateGameboard() function.");
        }

        //<<--ENEMY MOVEMENTS BELOW --//>>
        boolean enemyLoop = true;
        while (enemyLoop == true) {
            int randMove;

            if (gameData.getPlayerType() == 3 || gameData.getPlayerType() == 2) {
                randMove = ThreadLocalRandom.current().nextInt(0, 5);
            } 
            else {
                randMove = ThreadLocalRandom.current().nextInt(0, 4);
            }
            switch (randMove) {
                case 0: {
                    //Enemy up
                    int newPos = gameData.getEnemyY() - 1;
                    try {
                        if (tempArr[newPos][gameData.getEnemyX()] == 1 || tempArr[newPos][gameData.getEnemyX()] == 3) {
                            break;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        break;
                    }
                    if (newPos > 0 && newPos <= gameData.getY() - 1) { // Checking if the newPos value is not out of bounds.
                        tempArr[gameData.getEnemyY()][gameData.getEnemyX()] = 0;
                        tempArr[newPos][gameData.getEnemyX()] = gameData.getEnemyToken(); // Keeps players X the same and applies newPos to players Y.
                        gameData.setEnemyY(newPos); // Set the gamedata to reflect the new player Y position.
                        enemyLoop = false;
                        break;
                    } 
                    else {
                        break;
                    }
                }
                case 1: {
                    //Enemy Down
                    int newPos = gameData.getEnemyY() + 1;
                    try {
                        if (tempArr[newPos][gameData.getEnemyX()] == 1 || tempArr[newPos][gameData.getEnemyX()] == 3) {
                            break;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        break;
                    }

                    if (newPos >= 0 && newPos <= gameData.getY() - 1) {
                        tempArr[gameData.getEnemyY()][gameData.getEnemyX()] = 0;
                        tempArr[newPos][gameData.getEnemyX()] = gameData.getEnemyToken();
                        gameData.setEnemyY(newPos);
                        enemyLoop = false;
                        break;
                    } 
                    else {
                        break;
                    }
                }
                case 2: {
                    //Enemy Left
                    int newPos = gameData.getEnemyX() - 1;
                    try {
                        if (tempArr[gameData.getEnemyY()][newPos] == 1 || tempArr[gameData.getEnemyY()][newPos] == 3) {
                            break;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        break;
                    }
                    if (newPos >= 0 && newPos <= gameData.getX() - 1) {
                        tempArr[gameData.getEnemyY()][gameData.getEnemyX()] = 0;
                        tempArr[gameData.getEnemyY()][newPos] = gameData.getEnemyToken();
                        gameData.setEnemyX(newPos);
                        enemyLoop = false;
                        break;
                    } 
                    else {
                        break;
                    }
                }
                case 3: {
                    //Enemy Right
                    int newPos = gameData.getEnemyX() + 1;
                    try {
                        if (tempArr[gameData.getEnemyY()][newPos] == 1 || tempArr[gameData.getEnemyY()][newPos] == 3) {
                            break;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        break;
                    }
                    if (newPos >= 0 && newPos <= gameData.getX() - 1) {
                        tempArr[gameData.getEnemyY()][gameData.getEnemyX()] = 0;
                        tempArr[gameData.getEnemyY()][newPos] = gameData.getEnemyToken();
                        gameData.setEnemyX(newPos);
                        enemyLoop = false;
                        break;
                    } 
                    else {
                        break;
                    }
                }
                case 4: {
                    enemyLoop = false;
                    break;
                }
                default: {
                    System.err.println("ERROR... WRONG NUMBER GENERATED: " + randMove);
                    break;
                }
            }
        }
        // </--Enemy movments end --/>

        //small bit of error handling within the array below incase of weird movements.
        if (tempArr[gameData.getPlayerY()][gameData.getPlayerX()] != 1) {
            tempArr[gameData.getPlayerY()][gameData.getPlayerX()] = 1;
        }

        gameData.setArr(tempArr);

        //Player Attack Below
        if (range.isEnemyInRange(gameData) == true) {
            gameData.setEnemyInRange(true);
        } else {
            gameData.setEnemyInRange(false);
        }
        generateBoard(gameData, stats, frame, GUI, range, database);
    }

    public boolean checkValidMove(char movement, SavedData gameData) {
        switch (movement) {
            case 'W':
            case 'w':
            try {
                return gameData.getArr()[gameData.getPlayerY() - 1][gameData.getPlayerX()] != 3;
            } catch (ArrayIndexOutOfBoundsException e) {
                return false;
            }
            case 'S':
            case 's':
            try {
                return gameData.getArr()[gameData.getPlayerY() + 1][gameData.getPlayerX()] != 3;
            } catch (ArrayIndexOutOfBoundsException e) {
                return false;
            }
            case 'A':
            case 'a':
            try {
                return gameData.getArr()[gameData.getPlayerY()][gameData.getPlayerX() - 1] != 3;
            } catch (ArrayIndexOutOfBoundsException e) {
                return false;
            }
            case 'D':
            case 'd':
            try {
                return gameData.getArr()[gameData.getPlayerY()][gameData.getPlayerX() + 1] != 3;
            } catch (ArrayIndexOutOfBoundsException e) {
                return false;
            }
            case 'Q':
            case 'q':
                System.exit(0);
                break;
            default:
                System.out.println("Invalid movement");
                break;
        }
        return false;
    }
}
