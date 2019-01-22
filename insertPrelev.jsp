<%@ page import="jirama.*" %>
<%@ page import="java.sql.Date" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="general.*" %>
<% 
    ConnectDB db = new ConnectDB();
    Connection conn = db.connect();
    String[] month = {"Janvier","Fevrier","Mars","Avril","Mai","Juin","Juillet","Aout","Septembre","Octobre","Novembre","Decembre"};
    Generalise gen = new Generalise();
    Compteur compt = new Compteur();
    Object[] obj = gen.selectOpt(conn, compt, null); 
    Compteur[] val = new Compteur[obj.length];
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
    <h3>Insertion prelevement</h3>
    <form action="traitePrelev.jsp" method="POST">
        <p><label for="date">Date de prelevement</label>
        <input name="date" type="text" required/></p>
        <p><label for="mois">Mois</label>
        <select name="mois">
        <% for(int i=0;i<month.length;i++) { %>
            <option value="<% out.print(month[i]); %>"><% out.print(month[i]); %></option>
        <% } %>
        </select></p>
        <p><label for="annee">Annee</label>
        <input name="annee" type="text" required/></p>
        <p><label for="valeur">Valeur</label>
        <input name="valeur" type="text" required/></p>
        <p><label for="numCompteur">Numero compteur</label>
        <select name="numCompteur">
        <% for(int i=0;i<obj.length;i++) { 
            val[i] = (Compteur)obj[i]; %>%>
            <option value="<% out.print(val[i].getNumCompteur()); %>"><% out.print(val[i].getNumCompteur()); %></option>
        <% } %>
        </select>
        <p><button type="submit">Inserer</button></p>
    </form>
</body>
<script src="js/jquery.min.js"></script> 
<script src="js/bootstrap.min.js"></script>
<% conn.close(); %>