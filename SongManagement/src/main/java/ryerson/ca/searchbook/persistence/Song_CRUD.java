/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ryerson.ca.searchbook.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import ryerson.ca.searchbook.helper.Song;

/**
 *
 * @author student
 */
public class Song_CRUD {
    
    private static Connection getCon() throws ClassNotFoundException, SQLException{
    Connection con=null;

         //String connection="localhost:3306";
         String connection = System.getenv("DB_URL");
         System.out.println(connection);
         
         Class.forName("com.mysql.cj.jdbc.Driver");
       
         con=DriverManager.getConnection("jdbc:mysql://"+connection+"/SONG_WMP?allowPublicKeyRetrieval=true&useSSL=false", "root", "student" );
       System.out.println("Connection established.");
    return con;
    }
    
    public static ArrayList<Song> getArtistMusic(String username) {
        ArrayList<Song> ArtistSongs = new ArrayList<>();
        try {
            Connection con = getCon();
            String q = "SELECT * FROM Song WHERE SongArtist LIKE '" + username + "'";
            
System.out.println(q);

            PreparedStatement ps = con.prepareStatement(q);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                int MusicID = rs.getInt("SongID");
                String SongTitle = rs.getString("SongTitle");
                String SongArtistUsername = rs.getString("SongArtist");
                String Genre = rs.getString("Genre");
                String ReleaseDate = rs.getString("ReleaseDate");
                
                ArtistSongs.add(new Song(MusicID,SongTitle,SongArtistUsername,Genre,ReleaseDate));
            }
            con.close();
        }catch(Exception e){System.out.println(e);}
        return ArtistSongs;
    }
    
    public static ArrayList<Song> getAllMusic(){
        ArrayList<Song> ArtistSongs = new ArrayList<>();
        try {
            Connection con = getCon();
            String q = "SELECT * FROM Song";
            
System.out.println(q);
            
            PreparedStatement ps = con.prepareStatement(q);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                int SongID = rs.getInt("SongID");
                String SongTitle = rs.getString("SongTitle");
                String SongArtistUsername = rs.getString("SongArtist");
                String Genre = rs.getString("Genre");
                String ReleaseDate = rs.getString("ReleaseDate");
                
                ArtistSongs.add(new Song(SongID,SongTitle,SongArtistUsername,Genre,ReleaseDate));
            }
            con.close();
        }catch(Exception e){System.out.println(e);}
        return ArtistSongs;
    }
}
