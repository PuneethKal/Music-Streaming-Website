/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ryerson.ca.holdbook.persistence;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import ryerson.ca.holdbook.helper.Playlist;

/**
 *
 * @author student
 */
public class AddPlaylist_CRUD {

    private static Connection getCon() throws ClassNotFoundException, SQLException {
        Connection con = null;

        //String connection="localhost:3306";
        String connection = System.getenv("DB_URL");
        System.out.println(connection);

        Class.forName("com.mysql.cj.jdbc.Driver");

        con = DriverManager.getConnection("jdbc:mysql://" + connection + "/PLAYLIST_WMP?allowPublicKeyRetrieval=true&useSSL=false", "root", "student");
        System.out.println("Connection established.");
        return con;
    }

    public static boolean addPlaylist(Playlist AddThisPlaylist) {
        try {
            Connection con = getCon();

            int PlaylistID = AddThisPlaylist.getPlaylistID();
            String PlaylistName = AddThisPlaylist.getName();
            String PlaylistCreatorUsername = AddThisPlaylist.getPlaylistCreator();
            String DateCreated = AddThisPlaylist.getDateCreated();
            String q = "INSERT INTO Playlist VALUES("
                    + "'" + PlaylistID + "',"
                    + "'" + PlaylistName + "',"
                    + "'" + PlaylistCreatorUsername + "',"
                    + "'" + DateCreated + "');"; //format 2023-02-01

            PreparedStatement ps = con.prepareStatement(q);
            ps.executeUpdate();

            con.close();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public static boolean addSongToPlaylist(String Name, String Author, String Genre, String Date) {
        //implement
        return false;
    }
}
