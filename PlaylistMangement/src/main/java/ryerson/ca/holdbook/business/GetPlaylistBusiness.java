/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ryerson.ca.holdbook.business;

import java.util.ArrayList;
import ryerson.ca.holdbook.helper.Playlist;
import ryerson.ca.holdbook.helper.PlaylistXML;
import ryerson.ca.holdbook.persistence.GetPlaylists_CRUD;

/**
 *
 * @author student
 */
public class GetPlaylistBusiness {
    public PlaylistXML getPlaylist(String name){
        ArrayList<Playlist> listofPlaylist = GetPlaylists_CRUD.getPlaylist(name);
        PlaylistXML pl = new PlaylistXML();
        pl.setSong(listofPlaylist);
        return (pl);
    }
}
