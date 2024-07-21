<%-- 
    Document   : LoginFailed
    Created on : Feb 17, 2024, 3:52:27 PM
    Author     : student
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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

        th,tr,td {
            padding: 20px;
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
    <center><h1>LOGIN FAILED!</h1></center>
    
    <h3> </h3>
    
    <table border="2" align="center" cellpadding="10">
        <tr> 
            <td>  
                <form action="index.html" method="post">
                    <button name="buttonvalue" type="submit" value="browse"> Back to Start </button>
                </form>
            </td> 
        </tr>
    </table>
</body>
</html>
