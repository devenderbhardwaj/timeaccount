<%-- 
    Document   : errorPage
    Created on : 30-Mar-2023, 7:31:09 am
    Author     : Devender
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page isErrorPage = "true" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error</title>
    </head>
    <body>
        
        <h2>Something went wrong</h2>
        <%= exception.printStackTrace() %>
        <p><a href="index.jsp">Go to home</a></p>
    </body>
</html>
