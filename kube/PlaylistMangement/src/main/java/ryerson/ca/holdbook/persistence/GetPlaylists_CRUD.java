/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ryerson.ca.holdbook.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import ryerson.ca.holdbook.helper.Playlist;

/**
 *
 * @author student
 */
public class GetPlaylists_CRUD {
    
    private static Connection getCon() throws ClassNotFoundException, SQLException{
    Connection con=null;

         //String connection="localhost:3306";
         String connection = System.getenv("DB_URL");
         System.out.println(connection);
         
         Class.forName("com.mysql.cj.jdbc.Driver");
       
         con=DriverManager.getConnection("jdbc:mysql://"+connection+"/PLAYLIST_WMP?allowPublicKeyRetrieval=true&useSSL=false", "root", "student" );
       System.out.println("Connection established.");
    return con;
    }
    
    public static ArrayList<Playlist> getPlaylist(String username) {
        ArrayList<Playlist> bean = new ArrayList<>();
        try {
            Connection con = getCon();
            String q = "SELECT * FROM Playlist WHERE PlaylistCreatorUsername = '" + username + "'";
            
            PreparedStatement ps = con.prepareStatement(q);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                //been= new UserInfo();
                int playlistID = rs.getInt("PlaylistID");
                String playlistName = rs.getString("PlaylistName");
                String PlaylistCreator = rs.getString("PlaylistCreatorUsername");
                String dateCreated = rs.getString("DateCreated");
                ArrayList<Integer> playlistSongs = getSongID(playlistID);
                
                bean.add(new Playlist(playlistID,playlistName,PlaylistCreator,dateCreated,playlistSongs));
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return bean;
    }
    
    private static ArrayList<Integer> getSongID(int PlaylistID){
        
        ArrayList<Integer> SongIDs = new ArrayList<>();
        
        try {
            Connection con1 = getCon();
            String q = "SELECT  * \n"
                    + "FROM PlaylistContainsMusic\n"
                    + "WHERE PlaylistID = '" + PlaylistID + "'";
            
            
            PreparedStatement ps = con1.prepareStatement(q);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SongIDs.add( rs.getInt("SongID") );                
            }
            con1.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return SongIDs; 
    }
    
}
