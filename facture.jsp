<%@ page import="java.sql.Connection" %>
<%@ page import="general.*" %>
<%@ page import="jirama.*" %>
<%@ page import="java.lang.Integer" %>
<%
    ConnectDB db = new ConnectDB();
    Connection conn = db.connect();
    Fonctions fonc = new Fonctions();
    String id = request.getParameter("id");
    String mois = request.getParameter("mois");
    String annee = request.getParameter("annee");
    int idCLient = new Integer(id).intValue();
    String aff = fonc.affFacture(conn,mois,annee,idCLient);
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
    <% out.print(aff); %>
</body>
<script src="js/jquery.min.js"></script> 
<script src="js/bootstrap.min.js"></script>
<% conn.close(); %>