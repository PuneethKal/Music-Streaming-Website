/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ryerson.ca.holdbook.business;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import ryerson.ca.holdbook.helper.Playlist;
import ryerson.ca.holdbook.persistence.AddPlaylist_CRUD;
import ryerson.ca.holdbook.persistence.GetPlaylists_CRUD;

/**
 *
 * @author student
 */
public class AddPlaylistBusiness {
    
    public AddPlaylistBusiness(){}
    
    public boolean AddPlaylist(String name, String PlaylistName){
        
        ArrayList<Playlist> allplaylist = GetPlaylists_CRUD.getPlaylist(name);
        
        int PlaylistID = Integer.parseInt("200"+getID(allplaylist)); //song id is a 7 digit where the first 200 eg. 2001234
        LocalDate today = LocalDate.now();
        String DateCreated = today.toString();
        ArrayList<Integer> songs = new ArrayList<>(); 

        return AddPlaylist_CRUD.addPlaylist(new Playlist(PlaylistID,PlaylistName, name, DateCreated,songs));
    }
    
    private int getID(ArrayList<Playlist> p){
        Random random = new Random();
        int rand = 0;
        while (true) {
            rand = random.nextInt(10000);
            if (rand != 0 && verifyRandomNumber(rand,p)) {
                break;
            }
        }
        return rand;
    }
    
    private boolean verifyRandomNumber(int num, ArrayList<Playlist> p){
        
        for (Playlist a : p){
            if ( a.getPlaylistID() == num)
                return false;   
        }
        return true;
    }
}
