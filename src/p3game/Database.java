package p3game;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author taystyles
 *
 */
public class Database {

    Statistics stats = new Statistics();
    Connection conn;
    String TableName = "GameLeaderboard";
    String url = "jdbc:derby:RPGLeaderDB;create=true";

    //called prior to anything i interact with the databse, to re-open the DB connection. used to connect to the imbedded database
    public void establishConnection() {
        try {
            System.out.println("DB# Attempting to connect to database");
            this.conn = DriverManager.getConnection(url);
            if (this.conn != null) {
                System.out.println("DB# Connected to the database");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
/*
    Create table is called on each query incase there is no table there, i then check the error code and if the 
    error code is 3000, then i ignore the attempt to create the table as i only want to create a new table if 
    there is not one already
*/
    public void createTable() {
        try {
            System.out.println("DB# Attempting to create new table");
            conn.createStatement().execute("CREATE TABLE " + TableName + "(PlayerName varchar(50), Rounds int, Player varchar(10), Moves int, NumOfAttacks int)");
            System.out.println("DB# Successfully created new table");
        } catch (SQLException ex) {
            if (ex.getErrorCode() == 30000) { //check for the 3000 error code to see if the error is the "database exists" error.
                System.out.println("DB# Table Already Exists. Appending to database");
            } 
            else {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    //Is called after each game ends, and uploads all data captured from the game into the database.
    public void insertTable(SavedData gameData) {
        String Player;
        switch (gameData.getPlayerType()) {
            case (1):
                Player = "Witch";
                break;
            case (2):
                Player = "God";
                break;
            case (3):
                Player = "Civilian";
                break;
            default:
                Player = "Unable to fetch playerType";
                break;
        }

        try {
            System.out.println("DB# Attempting to insert into database...");
            conn.createStatement().execute("INSERT INTO " + TableName + " (PlayerName, Rounds, Player, Moves, NumOfAttacks) values ('" + stats.getPlayerName() + "'," + gameData.getRoundNumber() + ", '" + Player + "', " + stats.getNumberOfMoves() + ", " + stats.getNumberOfPAttacks() + ")");
            System.out.println("DB# Successfully inserted into database!");
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Called when the user clicks on the "view leaderboard" button and it queries the database.
    public String getQuery() {
        ResultSet rs;
        String results = "";
        try {
            System.out.println("DB# Querying database");
            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            rs = statement.executeQuery("SELECT * FROM GAMELEADERBOARD order by rounds DESC FETCH FIRST 3 ROWS ONLY");
            if (rs != null) {
                System.out.println("DB# Successfully got data from database");
            }

            while (rs.next()) {
                String playerName = rs.getString("PlayerName");
                int rounds = rs.getInt("Rounds");
                String playerType = rs.getString("Player");
                int moves = rs.getInt("Moves");
                int attackNum = rs.getInt("NumOfAttacks");
                results += "Player Name: " + playerName + "\nNumber of rounds: " + rounds + "\nChosen Character: " + playerType + "\nNumber Of Moves: " + moves + "\nNumber of successful attacks: " + attackNum + "\n----------------------------------------\n";
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        System.out.println("DB# Posting results to leaderboard");
        return results;
    }

    //Clearing the database. is called from the leaderboard page and drops the whole table.
    public void clearDB() {
        try {
            System.out.println("# Trying to drop table");
            conn.createStatement().execute("DROP TABLE " + TableName);
        } catch (SQLException ex) {
            System.out.println("# Failed to drop table");
        }
        System.out.println("# Successfully dropped table");
    }

    public void closeConnections() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
    }
}
