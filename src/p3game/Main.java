package p3game;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 * @author taystyles
 *
 */
public class Main {

    //Main method for setting up the game and creating initial instances
    public static void main(String[] args) {
        mainGUI GUI = new mainGUI(); // new instance of the GUI class
        SavedData gameData = new SavedData(); // new instance of the SavedData class
        Statistics stats = new Statistics();
        Gameboard gameboard = new Gameboard();
        Range_Attack range = new Range_Attack();
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        int screenHeight = d.height;
        int screenWidth = d.width;

        //Database code below
        Database database = new Database();
        database.establishConnection(); //Connect to the local database.
        database.createTable(); //Creates the GameStats table for the leaderboard.
        database.closeConnections(); //Close connection.

        //Create main frame for the game.
        JFrame frame = new JFrame("RPG game");
        frame.setBounds(0, 0, 750, 532);
        frame.setPreferredSize(new Dimension(750, 532));
        frame.setLocation(new Point((screenWidth / 2) - (frame.getWidth() / 2), (screenHeight / 2) - (frame.getHeight() / 2)));
        frame.setResizable(false);
        frame.setFocusable(true);
        frame.requestFocus();
        KeyPress.AddKeyListener(gameboard, gameData, stats, frame, GUI, range, database);
        GUI.DisplayGUI(frame, GUI, gameData, range, false, database);
    }
}
