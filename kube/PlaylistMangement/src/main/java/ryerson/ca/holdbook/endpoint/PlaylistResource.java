/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ryerson.ca.holdbook.endpoint;

import java.io.StringWriter;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import ryerson.ca.holdbook.business.AddPlaylistBusiness;
import ryerson.ca.holdbook.business.GetPlaylistBusiness;

import ryerson.ca.holdbook.helper.PlaylistXML;

/**
 * REST Web Service
 *
 * @author student
 */
@Path("playlist")
public class PlaylistResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of SearchResource
     */
    public PlaylistResource() {
    }

    /**
     * Retrieves representation of an instance of
     * ryerson.ca.searchbook.endpoint.SearchResource
     *
     * @return an instance of java.lang.String
     */
    @POST
    @Produces(MediaType.APPLICATION_XML)
    @Path("get")
    public String getXml(@FormParam("name") String name) {
        System.out.println(name);
        
        GetPlaylistBusiness playlists = new GetPlaylistBusiness();
        PlaylistXML playlist = playlists.getPlaylist(name);
        
        if (playlist == null) {
            return("");
        }
        
        
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(PlaylistXML.class);

            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(playlist, sw);

            return (sw.toString());

        } catch (JAXBException ex) {
            Logger.getLogger(PlaylistResource.class.getName()).log(Level.SEVERE, null, ex);
            return ("error happened");
        }
    }

    @POST
    @Produces(MediaType.TEXT_HTML)
    @Path("add")
    public String addNewPlaylist(@FormParam("name") String name, @FormParam("playlistName") String playlistName) 
    {
          AddPlaylistBusiness hold = new AddPlaylistBusiness();
          boolean bs = hold.AddPlaylist(name, playlistName);
          if(bs)
              return("Inserted");
          else
              return("Not inserted");
          
    }
}
