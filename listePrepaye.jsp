<%@ page import="jirama.*" %>
<%@ page import="general.*" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.lang.Integer" %>
<%
    ConnectDB db = new ConnectDB();
    Connection conn = db.connect();
    Generalise gen = new Generalise();
    String id = request.getParameter("id");
    int idCLient = new Integer(id).intValue();
    Client cl = new Client();
    String message = "";
    String where = "IdClient ="+idCLient;
    Object[] obj = gen.selectOpt(conn,cl,where);
    Client val = new Client();
    val = (Client)obj[0];
    OffrePrepaye off = new OffrePrepaye();
    Object[] o = gen.selectOpt(conn,off,null);
    OffrePrepaye[] offres = new OffrePrepaye[o.length];
    for(int i=0;i<o.length;i++){
        offres[i] = (OffrePrepaye)o[i];
    }
    Compteur cpt = new Compteur();
    String cond = "IdClient="+idCLient;
    Object[] objCpt = gen.selectOpt(conn,cpt,cond);
    Compteur[] lsCpt = new Compteur[objCpt.length];
    for(int j=0;j<objCpt.length;j++){
        lsCpt[j] = (Compteur)objCpt[j];
    }
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
    <h3>Bienvenue <% out.print(val.getPrenom()); %></h3>
     <form action="traiteAchat.jsp" method="POST">
     <input type="hidden" name="id" value="<% out.print(id); %>">
    <% if(lsCpt.length!=1){ %>
     <h4>Votre compteur</h4>
        <select name="idCompt">
        <% for(int l=0;l<lsCpt.length;l++){ %>
            <option value="<% out.print(lsCpt[l].getNumCompteur()); %>"><% out.print(lsCpt[l].getNumCompteur()); %> (<% out.print(lsCpt[l].getCategorie()); %>)</option>
        <% } %>
        </select>
    <% } if(lsCpt.length==1) { %>
        <input type = "hidden" name ="idCompt" value = "<% out.print(lsCpt[0].getNumCompteur()); %>"/>
    <% } %>
  
    <br/>
    <br/>
    <table border="1">
        <tr>
            <th>Offre</th>
            <th>Quantite</th>
            <th>Validite (en jours)</th>
            <th>Majoration</th>
            <th>Type de l'offre</th>
            <th>Prix</th>
            <th>Achat</th>
        </tr>
        <% for(int i=0;i<o.length;i++) { %>
       
        <tr>
            <td><% out.print(offres[i].getIdOffre()); %></td>
            <td><% out.print(offres[i].getOffreConsom()); %></td>
            <td><% out.print(offres[i].getDuree()); %></td>
            <td><% out.print(offres[i].getMajoration()); %></td>
            <td><% out.print(offres[i].getTypeOffre()); %></td>
            <td><% out.print(offres[i].getPrix()); %></td>
            <td><input type="radio" name="idOffre" value="<% out.print(offres[i].getIdOffre()); %>"/></td>
        </tr>
         
        <% } %>
    </table>
    <br/>
    <br/>
     <p><button type="submit">Acheter</button></p>
    </form>
    <br/>
    <br/>
    <%
        if(request.getParameter("message")!=null){
            message = request.getParameter("message");
        }
        out.print("<b>"+message+"</b>");
    %>
</body>
<script src="js/jquery.min.js"></script> 
    <script src="js/bootstrap.min.js"></script>
<% conn.close(); %>