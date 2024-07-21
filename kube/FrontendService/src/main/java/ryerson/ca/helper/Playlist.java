/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ryerson.ca.helper;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author student
 */
 @XmlRootElement(name = "playlist")
 @XmlAccessorType(XmlAccessType.FIELD)
public class Playlist {
    private int playlistID;
    private String name;
    private String playlistCreator;
    private String dateCreated;
    private ArrayList<Integer> songs;

    public Playlist(){}    
    
    public Playlist(int playlistID, String name, String playlistCreator, String dateCreated, ArrayList<Integer> songs) {
        this.playlistID = playlistID;
        this.name = name;
        this.playlistCreator = playlistCreator;
        this.dateCreated = dateCreated;
        this.songs = songs;
    }

    public ArrayList<Integer> getSongs() {
        return songs;
    }

    public int getPlaylistID() {
        return playlistID;
    }

    public String getName() {
        return name;
    }

    public String getPlaylistCreator() {
        return playlistCreator;
    }

    public String getDateCreated() {
        return dateCreated;
    }
    
}
