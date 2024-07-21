<%-- 
    Document   : PlaylistView
    Created on : Feb 22, 2024, 8:49:50 PM
    Author     : student
--%>

<%@page import="ryerson.ca.helper.Playlist"%>
<%@page import="ryerson.ca.helper.Song" %>
<%@page import="ryerson.ca.helper.UserInfo" %> 
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Playlist View</title>
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
            Playlist playlist = (Playlist) request.getAttribute("playlistsongs");
        %>
    <center><h1>Songs In Playlist</h1></center>

    <center><h2> There are no songs in playlist</h2></center>
    
    
    <h2> </h2>
    <h2> </h2>

    <form action="FrontEnd" method="post">
        <input type="hidden" name="pageName" value="listenerhomepage"/>
        <center> <button type="submit"> Back </button> </center>
    </form>



</body>
</html>
