<%-- 
    Document   : homepage
    Created on : Feb 4, 2024, 5:08:22 PM
    Author     : student
--%>    

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
            ArrayList<Song> musicCreated = (ArrayList) session.getAttribute("musicCreated"); 
            ArrayList<Song> Database = (ArrayList) session.getAttribute("Database");
            String uname = (String)request.getSession().getAttribute("uname");       
        %>
        
         
        
        <center><h1>Hello <%=uname%></h1></center>
        <center><h2>Music of the World</h2></center>
        
        <table border="2" align="center" cellpadding="5" cellspacing="5">

            <tr>

                <th> Song Name </th>
                <th> Author </th>
                
            </tr>

            <% for (Song a : Database) { %>
            <tr>
                <td> <%=a.getSong_title()%></td>
                <td> <%=a.getSong_artist()%></td>
                <td><%=a.getGenere()%></td> 
                <td><%=a.getRelease_date()%></td> 
            </tr>
            
            <% } %>
        </table>
        
        <%if(!"Guest".equals(uname)){%>
        <center><h1>Music Created by <%=uname%> </h1></center>
        
        <table border="2" align="center" cellpadding="5" cellspacing="5">

            <form method="post" action="FrontEnd">
                <tr>
                    <th> Enter Song Name <input type="text" name="song_title" size="30" required> </th>
                    <th> Enter Song Genre <input type="text" name="song_genre" size="30"required> </th>
                    <input type="hidden" name="artistname" value="<%=uname%>" />
                    <input type="hidden" name="pageName" value="addsong"/>
                    <th> <input name=add_to_song type="submit" value="add" > </th>                       
                </tr>
            </form>
        </table>
        
        <table border="2" align="center" cellpadding="5" cellspacing="5">
            
            <tr>

                <th> Song Name </th>
                <th> Author </th>
                
            </tr>

            <% for (Song a : musicCreated) { %>
            <tr>
                <td> <%=a.getSong_title()%></td>
                <td> <%=a.getSong_artist()%></td>
                <td><%=a.getGenere()%></td> 
                <td><%=a.getRelease_date()%></td> 
            </tr>
            <% } %>
        </table>
        <%} else{ %>
        <center><h1>Create an Account and Login First To See Your songs! </h1></center>
        <%}%>
        
        
        
    </body>
    
    
</html>
