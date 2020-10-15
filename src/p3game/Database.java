/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p3game;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author taystyles
 */
public class Database {

    Statistics stats = new Statistics();
    Connection conn;
    String TableName = "GameLeaderboard";
    String url = "jdbc:derby:RPGLeaderDB;create=true";

    public void establishConnection() {
        try {
            System.out.println("DB# Attempting to connect to database");
            this.conn = DriverManager.getConnection(url);
            if (this.conn != null){
                System.out.println("DB# Connected to the database");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void createTable() {
        try {
            System.out.println("DB# Attempting to create new table");
            conn.createStatement().execute("CREATE TABLE "+ TableName +" (ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), PlayerName varchar(50), Rounds int, Player varchar(10), Moves int, NumOfAttacks int)");
            System.out.println("DB# Successfully created new table");
        } catch (SQLException ex) {
            if(ex.getErrorCode() == 30000){
                System.out.println("DB# Table Already Exists. Appending to database");
            }else{
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void insertTable() {
        String PlayerName = "Taylor";
        int rounds = 5;
        String Player = "Witch";
        int Moves = 80;
        int NumOfAttacks = 15;
        try {
            System.out.println("DB# Attempting to insert into database...");
            conn.createStatement().execute("INSERT INTO "+TableName+" (PlayerName, Rounds, Player, Moves, NumOfAttacks) values ('"+PlayerName+"',"+rounds+", '"+Player+"', "+Moves+", "+NumOfAttacks+")");
            System.out.println("DB# Successfully inserted into database!");
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getQuery() {
        ResultSet rs = null;
        String results = "";
        try {
            System.out.println("DB# Querying database");
            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            
            rs = statement.executeQuery("SELECT * FROM "+TableName);
            if (rs != null){
                System.out.println("DB# Successfully got data from database");
            }
            //rs.beforeFirst();
            
            while (rs.next()) {
                int ID = rs.getInt("ID");
                String playerName = rs.getString("PlayerName");
                int rounds = rs.getInt("Rounds");
                String playerType = rs.getString("Player");
                int moves = rs.getInt("Moves");
                int attackNum = rs.getInt("NumOfAttacks");
                results+="Game Number: "+ID+"\nPlayer Name: "+playerName+"\nNumber of rounds: "+rounds+"\nChosen Character: "+playerType+"\nNumber Of Moves: "+moves+"\nNumber of attacks: "+attackNum+"\n\n----------------------------------------\n\n";
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        System.out.println("DB# Posting results to leaderboard");
        return results;
    }
    
    public int getNumOfRows() {
        ResultSet rs = null;
        int rows = 0;
        try {
            System.out.println("Getting number of rows....");
            Statement statement = conn.createStatement(
            ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            String sqlQuery = "select count(*) from "+TableName;

            rs = statement.executeQuery(sqlQuery);
            rs.beforeFirst();
            
            while (rs.next()) {
                rows = rs.getInt(0);
                return rows;
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return rows;
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
