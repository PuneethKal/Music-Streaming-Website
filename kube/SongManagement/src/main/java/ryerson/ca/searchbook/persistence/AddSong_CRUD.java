/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ryerson.ca.searchbook.persistence;


import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import ryerson.ca.searchbook.helper.Song;

/**
 *
 * @author student
 */
public class AddSong_CRUD {
    
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
    
    public static boolean addSong(Song AddThisSong){
        try {
            Connection con = getCon();
            
            int SongID = AddThisSong.getSongID();
            String SongTitle = AddThisSong.getSong_title();
            String SongArtistUsername = AddThisSong.getSong_artist();
            String Genre = AddThisSong.getGenere();
            String ReleaseDate = AddThisSong.getRelease_date();
            String q = "INSERT INTO Song(SongID,SongTitle,SongArtist,Genre,ReleaseDate) VALUES("
                    + "'" + SongID + "',"
                    + "'" + SongTitle + "',"
                    + "'" + SongArtistUsername + "',"
                    + "'" + Genre +"',"
                    + "'" + ReleaseDate + "');"; //format 2023-02-01
            
            PreparedStatement ps = con.prepareStatement(q);
            ps.executeUpdate();

            con.close();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
    
}
