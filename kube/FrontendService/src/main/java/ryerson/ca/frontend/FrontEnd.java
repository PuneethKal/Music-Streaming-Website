/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ryerson.ca.frontend;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.NewCookie;
import ryerson.ca.business.Business;
import ryerson.ca.helper.Playlist;
import ryerson.ca.helper.PlaylistXML;
import ryerson.ca.helper.Song;
import ryerson.ca.helper.SongsXML;

/**
 *
 * @author student
 */
@WebServlet(name = "FrontEnd", urlPatterns = {"/FrontEnd"})
public class FrontEnd extends HttpServlet {

    Authenticate autho;

    public FrontEnd() {
        autho = new Authenticate();
    }
    private final String authenticationCookieName = "login_token";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private Map.Entry<String, String> isAuthenticated(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String token = "";

        System.out.println("TOKEN IS");
        try {
            for (Cookie cookie : cookies) {
                System.out.println(cookie.getName());
                if (cookie.getName().equals(authenticationCookieName)) {
                    token = cookie.getValue();
                }
            }
        } catch (Exception e) {

        }
        if (!token.isEmpty())
           try {
            if (this.autho.verify(token).getKey()) {
                Map.Entry entry = new AbstractMap.SimpleEntry<String, String>(token, this.autho.verify(token).getValue());
                return entry;

            } else {
                Map.Entry entry = new AbstractMap.SimpleEntry<String, String>("", "");
                return entry;
            }
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(FrontEnd.class.getName()).log(Level.SEVERE, null, ex);
        }

        Map.Entry entry = new AbstractMap.SimpleEntry<String, String>("", "");
        return entry;

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String hiddenParam = request.getParameter("pageName");
        String token;
        String uname;
        System.out.println(hiddenParam);
        if (!(hiddenParam.equals("wanttobrowse") || hiddenParam.equals("wanttocreateaccount") || hiddenParam.equals("wanttologin"))) {
            token = isAuthenticated(request).getKey();
            uname = isAuthenticated(request).getValue();
        }
        RequestDispatcher rd;
        switch (hiddenParam) {
            case "wanttobrowse":
                rd = request.getRequestDispatcher("LoginFailed.jsp");
                rd.forward(request, response);
                break;
            case "wanttocreateaccount":
                rd = request.getRequestDispatcher("LoginFailed.jsp");
                rd.forward(request, response);
                break;
            case "wanttologin":
                rd = request.getRequestDispatcher("loginpage.html");
                rd.forward(request, response);
                break;
            case "Listener":
                String Listenerusername = request.getParameter("username");
                String Listenerpasswrod = request.getParameter("passwrod");
                boolean ListenerisAuthenticated = Business.isAuthenticated(Listenerusername, Listenerpasswrod, hiddenParam);
                if (ListenerisAuthenticated) {

                    

                    token = autho.createJWT("FrontEnd", Listenerusername, 100000);

                    Cookie newCookie = new Cookie(authenticationCookieName, token);
                    response.addCookie(newCookie);

                    ArrayList<Song> Database = getSongData(token).getSongs();
                    ArrayList<Playlist> playlist = new ArrayList<>();
                    try {
                        playlist = getPlaylistData(Listenerusername, token).getPlaylists();
                    } catch (Exception e) {
                    }
                    
                    request.getSession().setAttribute("uname", Listenerusername);
                    request.getSession().setAttribute("Database", Database);
                    request.getSession().setAttribute("Playlist", playlist);

                    RequestDispatcher requestDispatcher = request.
                            getRequestDispatcher("ListenerHomePage.jsp");

                    requestDispatcher.forward(request, response);

                } else {
                    rd = request.getRequestDispatcher("LoginFailed.jsp");
                    rd.forward(request, response);
                }
                break;
            case "Artist":
                String Artistusername = request.getParameter("username");
                String Artistpasswrod = request.getParameter("passwrod");
                boolean ArtistisAuthenticated = Business.isAuthenticated(Artistusername, Artistpasswrod, hiddenParam);
                if (ArtistisAuthenticated) {
                    request.setAttribute("username", Artistusername);
                    token = autho.createJWT("FrontEnd", Artistusername, 100000);

                    Cookie newCookie = new Cookie(authenticationCookieName, token);
                    response.addCookie(newCookie);

                    ArrayList<Song> Database = getSongData(token).getSongs();
                    ArrayList<Song> musicCreated = new ArrayList<>();
                    musicCreated = getUserSongData(Artistusername, token).getSongs();

                    request.getSession().setAttribute("uname", Artistusername);
                    request.getSession().setAttribute("Database", Database);
                    request.getSession().setAttribute("musicCreated", musicCreated);

                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("ArtistHomePage.jsp");
                    requestDispatcher.forward(request, response);

                } else {
                    rd = request.getRequestDispatcher("LoginFailed.jsp");
                    rd.forward(request, response);
                }
                break;
            case "addsong":

                String username = request.getParameter("artistname");
                String song_title = request.getParameter("song_title");
                String song_genre = request.getParameter("song_genre");

                Business.addsong(username, song_title, song_genre);

                token = autho.createJWT("FrontEnd", username, 100000);

                Cookie newCookie = new Cookie(authenticationCookieName, token);
                response.addCookie(newCookie);

                ArrayList<Song> Database = getSongData(token).getSongs();
                ArrayList<Song> musicCreated = new ArrayList<>();
                musicCreated = getUserSongData(username, token).getSongs();

                request.getSession().setAttribute("uname", username);
                request.getSession().setAttribute("Database", Database);
                request.getSession().setAttribute("musicCreated", musicCreated);

                RequestDispatcher requestDispatcher = request.getRequestDispatcher("ArtistHomePage.jsp");
                requestDispatcher.forward(request, response);
                break;
            case "addplaylist":

                String listernuame = request.getParameter("listenername");
                String PlaylistName = request.getParameter("PlaylistName");

                Business.addplaylist(listernuame,PlaylistName);

//                request.setAttribute("uname", Listenerusername);

                token = autho.createJWT("FrontEnd", listernuame, 100000);

                Cookie newCookies = new Cookie(authenticationCookieName, token);
                response.addCookie(newCookies);

                ArrayList<Song> Databases = getSongData(token).getSongs();
                ArrayList<Playlist> playlist = new ArrayList<>();
                try {
                    playlist = getPlaylistData(listernuame, token).getPlaylists();
                } catch (Exception e) {
                }

                request.getSession().setAttribute("Database", Databases);
                request.getSession().setAttribute("Playlist", playlist);

                RequestDispatcher requestDispatchers = request.
                        getRequestDispatcher("ListenerHomePage.jsp");

                requestDispatchers.forward(request, response);

                break;
            case "viewplaylist":
                
                RequestDispatcher rq = request.
                        getRequestDispatcher("PlaylistView.jsp");

                rq.forward(request, response);
                
                
                
                break;
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

//    private BooksXML retreiveServicesFromBackend(String query, String token) {
//        try {
//            return (Business.getServices(query, token));
//        } catch (IOException ex) {
//            Logger.getLogger(FrontEnd.class.getName()).log(Level.SEVERE, null, ex);
//            return (null);
//        }
//
//    }
    private SongsXML getSongData(String token) {
        try {
            return (Business.getServicesAllSongs("", token));
        } catch (IOException ex) {
            Logger.getLogger(FrontEnd.class.getName()).log(Level.SEVERE, null, ex);
            return (null);
        }
    }

    private SongsXML getUserSongData(String username, String token) {
        try {
            return (Business.getServicesAllSongs(username, token));
        } catch (IOException ex) {
            Logger.getLogger(FrontEnd.class.getName()).log(Level.SEVERE, null, ex);
            return (null);
        }
    }

    private PlaylistXML getPlaylistData(String username, String token) {
        try {
            return (Business.getServicesPlaylists(username, token));
        } catch (IOException ex) {
            Logger.getLogger(FrontEnd.class.getName()).log(Level.SEVERE, null, ex);
            return (null);
        }
    }

}
