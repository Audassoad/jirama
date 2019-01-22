<%@ page import="jirama.*" %>
<%@ page import="java.sql.Date" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="general.*" %>
<%@ page import="java.lang.Integer" %>
<% 
    ConnectDB db = new ConnectDB();
    Connection conn = db.connect();
    Generalise gen = new Generalise();
    Compteur compt = new Compteur();
    Object[] obj = gen.selectOpt(conn, compt, null); 
    Compteur[] val = new Compteur[obj.length];
    String num = request.getParameter("numprelev");
    int numPrelev = new Integer(num).intValue();
    String where = "NumPrelev ="+numPrelev;
    Object[] o = gen.selectOpt(conn, new Prelevement(), where);
    Prelevement pr = (Prelevement)o[0];
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
    <h3>Insertion d'un nouveau prelevement</h3>
    <form action="traiteNewPrelev.jsp" method="POST">
        <p><label for="date">Date de prelevement</label>
        <input name="date" type="text" required/></p>
        <p><label for="valeur">Valeur</label>
        <input name="valeur" type="text" required/></p>
        <input type="hidden" name="mois" value="<% out.print(pr.getMois()); %>"/>
        <input type="hidden" name="annee" value="<% out.print(pr.getAnnee()); %>"/>
        <input type="hidden" name="numCompteur" value="<% out.print(pr.getNumCompteur()); %>"
        <p><button type="submit">Inserer</button></p>
    </form>
</body>
<script src="js/jquery.min.js"></script> 
<script src="js/bootstrap.min.js"></script>
<% conn.close(); %>