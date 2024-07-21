/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ryerson.ca.helper;

/**
 *
 * @author student
 */
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "songs")
@XmlAccessorType(XmlAccessType.FIELD)

public class SongsXML {

    @XmlElement(name = "song")
    private ArrayList<Song>songs;

    public SongsXML() {
    }
    
    public ArrayList<Song> getSongs() {
        return songs;
    }

    

    public void setSong(ArrayList<Song> bs) {
        songs = bs;
    }

}
