/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ryerson.ca.holdbook.helper;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author student
 */
 @XmlRootElement(name = "playlists")
 @XmlAccessorType(XmlAccessType.FIELD)
public class PlaylistXML {
    @XmlElement(name = "playlist")
    private ArrayList<Playlist>playlists;

    public PlaylistXML() {
    }
    
    public List<Playlist> getPlaylists() {
        return playlists;
    }

    

    public void setSong(ArrayList<Playlist> bs) {
        playlists = bs;
    }
}
