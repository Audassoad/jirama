<%@ page import="jirama.*" %>
<%@ page import="java.sql.Date" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="general.*" %>
<%
    ConnectDB db = new ConnectDB();
    Connection conn = db.connect();
    Generalise gen = new Generalise();
    Fonctions fonc = new Fonctions();
    PrelevNonFacture prelev = new PrelevNonFacture();
    Object[] obj = gen.selectOpt(conn,prelev,null);
    PrelevNonFacture[] ls = new PrelevNonFacture[obj.length];
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>JIRAMA</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"> 
    <link href="css/bootstrap.css" rel="stylesheet"> 
</head>
<body>
    <h3>Liste des prelevements non factures</h3>
    <table border="1">
        <tr>
            <th>Num Prelevement</th>
            <th>Date prelevement</th>
            <th>Mois</th>
            <th>Annee</th>
            <th>Client</th>
            <th>Facturer</th>
        </tr>
        <form action="traiteFacture.jsp" method="POST">
        <% for(int i=0;i<obj.length;i++) { 
            ls[i] = (PrelevNonFacture)obj[i];
            String date = fonc.transformDate2(ls[i].getDatePrelev());
        %>
        <tr>
            <td><% out.print(ls[i].getIdPrelev()); %></td>
            <td><% out.print(date); %></td>
            <td><% out.print(ls[i].getMoisPrelev()); %></td>
            <td><% out.print(ls[i].getAnneePrelev()); %></td>
            <td><% out.print(ls[i].getClient()); %></td>
            <td><input type="checkbox" name="<% out.print(ls[i].getIdPrelev()); %>"></input></td>
        </tr>
        <% } %>
    </table>
    <p><button type="submit">Facturer</button></p>
        </form>
</body>
<script src="js/jquery.min.js"></script> 
<script src="js/bootstrap.min.js"></script>
<% conn.close(); %>