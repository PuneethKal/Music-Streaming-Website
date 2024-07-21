<%-- 
    Document   : ListenerHomePage
    Created on : Feb 22, 2024, 5:47:06 PM
    Author     : student
--%>

<%@page import="ryerson.ca.helper.Playlist"%>
<%@page import="java.io.*" %>

<%@page import="ryerson.ca.helper.Song" %>
<%@page import="ryerson.ca.helper.UserInfo" %> 
<!--not sure if needed here user import import-->

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dashboard</title>

    </head>
    <style>
        body { background-color: #D4F2F7 } 

        h1{
            background: #A8E5F0;
        }

        table, th, td, tr {
            border: 0px solid black;
            border-collapse: collapse;
        }

        th{
            padding: 10px;
            text-align: center;
        }
        td{
            border: 0px solid black;
            text-align: center;
        }

        tr{
            border: 2px solid black;
            text-align: center;
        }

        button{
            border: 2px solid black;
            text-align: center;
            padding: 20px 50px;
        }
        button:hover {background-color: #D6EEEE;}

    </style>
    <body>
        <%
            ArrayList<Playlist> playlist = (ArrayList) session.getAttribute("Playlist");
            ArrayList<Song> Database = (ArrayList) session.getAttribute("Database");
            String uname = (String) request.getSession().getAttribute("uname");
        %>



    <center><h1>Hello <%=uname%></h1></center>
    <center><h2>Music of the World</h2></center>

    <table border="2" align="center" cellpadding="5" cellspacing="5">

        <tr>

            <th> Song Name </th>
            <th> Author </th>

        </tr>

        <% for (Song a : Database) {%>
        <tr>
            <td> <%=a.getSong_title()%></td>
            <td> <%=a.getSong_artist()%></td>
            <td><%=a.getGenere()%></td> 
            <td><%=a.getRelease_date()%></td> 
        </tr>

        <% }%>
    </table>

    <center><h1>Playlists</h1></center>

    <h3></h3>
    <table border="2" align="center" cellpadding="5" cellspacing="5">
        <form method="post" action="FrontEnd">
            <tr>
                <th> Enter Playlist Name <input type="text" name="PlaylistName" size="30" required> </th>
                <input type="hidden" name="pageName" value="addplaylist"/>
                <input type="hidden" name="listenername" value="<%=uname%>" />
                <th> <button type="submit"> Create A Playlist </button> </th>                       
            </tr>
        </form>
    </table>
        

    <h3></h3>

    <%if (playlist != null && playlist.size() > 0) { %>
    <form form action="FrontEnd" method="post" >   
        <table border="2" align="center" cellpadding="5" cellspacing="5">

            <tr>

                <th> Name </th>
                <th> Playlist Creator </th>
                <th> Date Created </th>
                <th>  </th>

            </tr>

            <% for (Playlist a : playlist) {%>
            <tr>
                <td> <%=a.getName()%> </td>
                <td> <%=a.getPlaylistCreator()%></td>
                <td> <%=a.getDateCreated()%></td>
                <td align="center">
                    <button name="playlistname" type="submit" value="<%=a.getName()%>"> View Songs </button>
                    <input type="hidden" name="pageName" value="viewplaylist"/>
                </td>

            </tr>

            <% }%>


        </table>
    </form> 
    <%} else {%>
    <h2></h2>
    <center><h3>You have no playlists</h3></center>
        <%}%>

</body>


</html>