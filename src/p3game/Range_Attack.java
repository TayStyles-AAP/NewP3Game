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
public class Range_Attack {
    //Checking if the enemy is in range for the player to attack him.
    public boolean isEnemyInRange(SavedData gameData) {
        System.out.println("# Checking player attack options");
        int tempArr[][] = gameData.getArr();

        //Basic attack options for all characters
        try {
            if ((tempArr[gameData.getPlayerY() + 1][gameData.getPlayerX()] == 2)) {
                System.out.println("# Enemy is in range to attack!");
                return true;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        try {
            if ((tempArr[gameData.getPlayerY() - 1][gameData.getPlayerX()] == 2)) {
                System.out.println("# Enemy is in range to attack!");
                return true;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        try {
            if ((tempArr[gameData.getPlayerY()][gameData.getPlayerX() + 1] == 2)) {
                System.out.println("# Enemy is in range to attack!");
                return true;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        try {
            if ((tempArr[gameData.getPlayerY()][gameData.getPlayerX() - 1] == 2)) {
                System.out.println("# Enemy is in range to attack!");
                return true;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }

        //If the selected character supports L shape attacking (Witch).
        if ((gameData.getPlayerType() == 1)) {
            try {
                if ((tempArr[gameData.getPlayerY() + 1][gameData.getPlayerX() - 1] == 2)) {
                    System.out.println("# Enemy is in range to attack!");
                    return true;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
            }
            try {
                if ((tempArr[gameData.getPlayerY() + 1][gameData.getPlayerX() + 1] == 2)) {
                    System.out.println("# Enemy is in range to attack!");
                    return true;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
            }
            try {
                if ((tempArr[gameData.getPlayerY() - 1][gameData.getPlayerX() + 1] == 2)) {
                    System.out.println("# Enemy is in range to attack!");
                    return true;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
            }
            try {
                if ((tempArr[gameData.getPlayerY() - 1][gameData.getPlayerX() - 1] == 2)) {
                    System.out.println("# Enemy is in range to attack!");
                    return true;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
            }
        }

        //Extra long attacking mode (God)
        if (gameData.getPlayerType() == 2) {
            try {
                if ((tempArr[gameData.getPlayerY() + 3][gameData.getPlayerX()] == 2)) {
                    System.out.println("# Enemy is in range to attack!");
                    return true;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
            }
            try {
                if ((tempArr[gameData.getPlayerY() - 3][gameData.getPlayerX()] == 2)) {
                    System.out.println("# Enemy is in range to attack!");
                    return true;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
            }
            try {
                if ((tempArr[gameData.getPlayerY()][gameData.getPlayerX() + 3] == 2)) {
                    System.out.println("# Enemy is in range to attack!");
                    return true;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
            }
            try {
                if ((tempArr[gameData.getPlayerY()][gameData.getPlayerX() - 3] == 2)) {
                    System.out.println("# Enemy is in range to attack!");
                    return true;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
            }
        }
        return false;
    }

    public boolean isPlayerInRange(SavedData gameData) {
        System.out.println("# Checking enemy attack options");
        int tempArr[][] = gameData.getArr();

        if (gameData.getPlayerType() == 1) {
            //forming directly next to player Detection below
            try {
                if ((tempArr[gameData.getPlayerY() + 1][gameData.getPlayerX()] == 2)) {
                    System.out.println("# Enemy is in range to attack!");
                    return true;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
            }
            try {
                if ((tempArr[gameData.getPlayerY() - 1][gameData.getPlayerX()] == 2)) {
                    System.out.println("# Enemy is in range to attack!");
                    return true;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
            }
            try {
                if ((tempArr[gameData.getPlayerY()][gameData.getPlayerX() + 1] == 2)) {
                    System.out.println("# Enemy is in range to attack!");
                    return true;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
            }
            try {
                if ((tempArr[gameData.getPlayerY()][gameData.getPlayerX() - 1] == 2)) {
                    System.out.println("# Enemy is in range to attack!");
                    return true;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
            }
        }
        return false;
    }
}
