package p3game;

/**
 * @author taystyles
 */
public class Statistics {

    /*
    Stats class used by the database to create the leaderboard.
     */
    private static int numberOfMoves;
    private static String playerName;
    private static String playerType;
    private static int numberOfPAttacks;
    private static int numberOfEAttacks;

    public int getNumberOfMoves() {
        return numberOfMoves;
    }

    public int getNumberOfPAttacks() {
        return numberOfPAttacks;
    }

    public int getNumberOfEAttacks() {
        return numberOfEAttacks;
    }

    public String getPlayerType() {
        return playerType;
    }

    public static String getPlayerName() {
        System.out.println("Stats# Retreiving Playername");
        return playerName;
    }

    public static void setPlayerName(String playerName) {
        Statistics.playerName = playerName;
        System.out.println("Stats# Playername Set");
    }

    public static void setNumberOfMoves(int numberOfMoves) {
        Statistics.numberOfMoves = numberOfMoves;
    }

    public static void setNumberOfPAttacks(int numberOfPAttacks) {
        Statistics.numberOfPAttacks = numberOfPAttacks;
    }
}
