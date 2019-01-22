<%@ page import="java.sql.Connection" %>
<%@ page import="general.*" %>
<%@ page import="jirama.*" %>
<%@ page import="java.lang.Integer" %>
<%
    String id = request.getParameter("annuler");
    String prelev = request.getParameter("prelev");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0"> 
    <link href="css/bootstrap.css" rel="stylesheet"> 
    <title>JIRAMA</title>
</head>
<body>
   <form action="traiteAnnuler.jsp" method="POST">
    <h3>Entrer le vrai montant</h3>
        <input name="montant" type="text" required/></p>
        <input type="hidden" name="annuler" value="<% out.print(id); %>"/>
        <input type="hidden" name="prelev" value="<% out.print(prelev); %>"/>
        <button type="submit">Valider</button>
   </form>
   <br/>
   <br/>
   <% if(request.getParameter("message")!=null){
       out.print(request.getParameter("message"));
   }

   %>
</body>
<script src="js/jquery.min.js"></script> 
<script src="js/bootstrap.min.js"></script>