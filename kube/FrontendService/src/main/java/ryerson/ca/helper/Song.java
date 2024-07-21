/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ryerson.ca.helper;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author student
 */
@XmlRootElement(name = "song")
@XmlAccessorType(XmlAccessType.FIELD)
public class Song {
    
    private int SongID;
    private String song_title;
    private String song_artist;
    private String genere;
    private String release_date;
    
    public Song(){
        
    }
    
    public Song(int SongID, String song_title, String song_artist, String genere, String release_date) {
        this.SongID = SongID;
        this.song_title = song_title;
        this.song_artist = song_artist;
        this.genere = genere;
        this.release_date = release_date;
    }

    public int getSongID() {
        return SongID;
    }

    public String getSong_title() {
        return song_title;
    }

    public String getSong_artist() {
        return song_artist;
    }

    public String getGenere() {
        return genere;
    }

    public String getRelease_date() {
        return release_date;
    }
    
    
    
}
