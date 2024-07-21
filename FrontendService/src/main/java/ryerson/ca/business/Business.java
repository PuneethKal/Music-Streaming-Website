/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ryerson.ca.business;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.apache.commons.io.IOUtils;
import ryerson.ca.helper.UserInfo;
import ryerson.ca.helper.PlaylistXML;
import ryerson.ca.helper.SongsXML;
import ryerson.ca.persistence.Account_CRUD;

/**
 *
 * @author student
 */
public class Business {

    public static boolean isAuthenticated(String username, String passwrod, String typeOfAccount) {
        
        UserInfo user = Account_CRUD.FindUser(username, passwrod, typeOfAccount);

        if (user == null) {
            return false;
        }
        
        return true;
    }

    public static SongsXML getServicesAllSongs(String username, String token) throws IOException {
        Client searchclient = ClientBuilder.newClient();
        String songService= System.getenv("songService");
        WebTarget searchwebTarget
                = searchclient.target("http://"+songService+"/SongManagement/webresources/search/get");
        Form formData = new Form();
        formData.param("username",username);
        
        InputStream is
                = searchwebTarget.request(MediaType.APPLICATION_XML).post(Entity.entity(formData,MediaType.APPLICATION_XML),InputStream.class);
        String xml = IOUtils.toString(is, "utf-8");
        SongsXML songs = songxmltoObjects(xml);
        System.out.println(songs.getSongs().get(0).getSong_artist());
        return (songs);

    }
    
    private static SongsXML songxmltoObjects(String xml) {
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(SongsXML.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            SongsXML songs = (SongsXML) jaxbUnmarshaller.unmarshal(new StringReader(xml));
            return songs;

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    

    public static PlaylistXML getServicesPlaylists(String username,String token) throws IOException {
        System.out.println("----->>>>" + username);
        Client searchclient = ClientBuilder.newClient();
        String playlistService= System.getenv("playlistService");
        WebTarget searchwebTarget
                = searchclient.target("http://"+playlistService+"/PlaylistMangement/webresources/playlist/get");
        Form formData = new Form();
        formData.param("name",username);
        
        InputStream is
                = searchwebTarget.request(MediaType.APPLICATION_XML).post(Entity.entity(formData,MediaType.APPLICATION_XML),InputStream.class);
        String xml = IOUtils.toString(is, "utf-8");
        PlaylistXML playlists = playlistxmltoObjects(xml);
        System.out.println("Problem AREA BELO?");
        System.out.println(playlists.getPlaylists().get(0).getPlaylistCreator());
        return (playlists);

    }

    private static PlaylistXML playlistxmltoObjects(String xml) {
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(PlaylistXML.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            PlaylistXML playlist = (PlaylistXML) jaxbUnmarshaller.unmarshal(new StringReader(xml));
            return playlist;

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static void addsong(String username, String songTitle, String SongGenere){
        Client searchclient = ClientBuilder.newClient();
        String songService= System.getenv("songService");
        WebTarget searchwebTarget
                = searchclient.target("http://"+songService+"/SongManagement/webresources/search/add");
        Form formData = new Form();
        formData.param("username",username);
        formData.param("songTitle",songTitle);
        formData.param("SongGenere",SongGenere);
        InputStream is
                = searchwebTarget.request(MediaType.TEXT_HTML).post(Entity.entity(formData,MediaType.TEXT_HTML),InputStream.class);
    }
    
    public static void addplaylist(String username, String PlaylistName){
        Client searchclient = ClientBuilder.newClient();
        String playlistService= System.getenv("playlistService");
        WebTarget searchwebTarget
                = searchclient.target("http://"+playlistService+"/PlaylistMangement/webresources/playlist/add");
        Form formData = new Form();
        formData.param("name",username);
        formData.param("playlistName",PlaylistName);
        InputStream is
                = searchwebTarget.request(MediaType.TEXT_HTML).post(Entity.entity(formData,MediaType.TEXT_HTML),InputStream.class);
    }
}
