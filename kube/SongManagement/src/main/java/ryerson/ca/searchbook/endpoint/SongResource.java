/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ryerson.ca.searchbook.endpoint;

import java.io.StringWriter;
import java.util.Collection;
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
import ryerson.ca.searchbook.business.AddSongBusiness;
import ryerson.ca.searchbook.business.SongBusiness;
import ryerson.ca.searchbook.helper.Song;
import ryerson.ca.searchbook.helper.SongsXML;

/**
 * REST Web Service
 *
 * @author student
 */
@Path("search")
public class SongResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of SearchResource
     */
    public SongResource() {
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
    public String getXml(@FormParam("username") String username) {

        SongBusiness search = new SongBusiness();
        SongsXML listofsongs;
        if (!(username == null || username.isEmpty())) {
            listofsongs = search.getArtistSongs(username);
        } else {
            listofsongs = search.getAllSongs();
        }

        System.out.println(">>>>>>>>>>>>>>>>>>" + listofsongs);

        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(SongsXML.class);

            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(listofsongs, sw);

            return (sw.toString());

        } catch (JAXBException ex) {
            Logger.getLogger(SongResource.class.getName()).log(Level.SEVERE, null, ex);
            return ("error happened");
        }
    }

    @POST
    @Produces(MediaType.TEXT_HTML)
    @Path("add")
    public String  addNewSong(@FormParam("username") String username, 
            @FormParam("songTitle") String songTitle, @FormParam("SongGenre") String SongGenre) 
    {
          AddSongBusiness add = new AddSongBusiness();
          boolean bs = add.AddSong(username, songTitle, SongGenre);
          if(bs)
              return("Inserted");
          else
              return("Not inserted");
          
    }
}
