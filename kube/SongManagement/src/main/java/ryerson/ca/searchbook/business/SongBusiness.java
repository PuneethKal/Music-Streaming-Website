/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ryerson.ca.searchbook.business;

import static java.lang.System.in;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import ryerson.ca.searchbook.helper.Song;
import ryerson.ca.searchbook.helper.SongsXML;
import ryerson.ca.searchbook.persistence.Song_CRUD;

/**
 *
 * @author student
 */
public class SongBusiness {
    
    public SongsXML getArtistSongs(String username){
        ArrayList<Song> listofsongs = Song_CRUD.getArtistMusic(username);
        SongsXML sl = new SongsXML();
        sl.setSong(listofsongs);
        return (sl);
    }
    
    public SongsXML getAllSongs(){
        ArrayList<Song> listofsongs = Song_CRUD.getAllMusic();
        SongsXML sl = new SongsXML();
        sl.setSong(listofsongs);
        return (sl);
    }
    
      
}
