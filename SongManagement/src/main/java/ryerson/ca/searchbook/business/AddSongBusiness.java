/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ryerson.ca.searchbook.business;

import java.util.ArrayList;
import java.util.Random;
import java.time.LocalDate;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ryerson.ca.searchbook.helper.Song;
import ryerson.ca.searchbook.persistence.AddSong_CRUD;
import ryerson.ca.searchbook.persistence.Song_CRUD;

/**
 *
 * @author student
 */
public class AddSongBusiness {

    public AddSongBusiness(){}
    
    public boolean AddSong(String username, String songTitle, String songGenre){
        
        int songID = Integer.parseInt("100"+getID()); //song id is a 7 digit where the first 100 eg. 1001234
        LocalDate today = LocalDate.now();
        String releaseDate = today.toString();

        return AddSong_CRUD.addSong(new Song(songID,songTitle, username, songGenre, releaseDate));
    }
    
    private int getID(){
        Random random = new Random();
        int rand = 0;
        while (true) {
            rand = random.nextInt(10000);
            if (rand != 0 && verifyRandomNumber(rand)) {
                break;
            }
        }
        return rand;
    }
    
    private boolean verifyRandomNumber(int num){
        
        ArrayList<Song> allmusic = Song_CRUD.getAllMusic();
        
        for (Song a : allmusic){
            if ( a.getSongID() == num)
                return false;
        }
        return true;
    }
}
