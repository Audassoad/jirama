<%@ page import="java.sql.Connection" %>
<%@ page import="general.*" %>
<%@ page import="jirama.*" %>
<%
    ConnectDB db = new ConnectDB();
    Connection conn = db.connect();
    Generalise gen = new Generalise();
    Client cl = new Client();
    Object[] obj = gen.selectOpt(conn, cl, null);
    Client[] val = new Client[obj.length];
    String[] month = {"Janvier","Fevrier","Mars","Avril","Mai","Juin","Juillet","Aout","Septembre","Octobre","Novembre","Decembre"}; 
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
    <h3>Afficher facture</h3>
    <br/>
    <br/>
    <form action="facture.jsp" method="POST">
    <p><label for="id">Id du client</label>
    <select name="id">
    <% for(int i=0;i<val.length;i++) { 
         val[i] = (Client)obj[i]; %>
        <option value="<% out.print(val[i].getIdClient()); %>"><% out.print(val[i].getIdClient()); %></option>
        <% } %>
    </select>
    </p>
    <p><label for="mois">Mois</label>
    <select name="mois">
    <% for(int i=0;i<month.length;i++) { %>
        <option value="<% out.print(month[i]); %>"><% out.print(month[i]); %></option>
    <% } %>
    </select>
    &nbsp &nbsp &nbsp &nbsp
    <label for="annee">Annee</label>
    <select name="annee">
        <option value="2015">2015</option>
        <option value="2016">2016</option>
        <option value="2017">2017</option>
        <option value="2018">2018</option>
        <option value="2019">2019</option>
    </select></p>
    <p><button type="submit">Afficher</button></p>
    </form>
    <br/>
    <br/>
    <a href="prepaye.jsp">Voir les tarifs prepayes</a>
    <br/>
    <br/>
    <a href="insertPrelev.jsp">Inserer prelevement</a> &nbsp &nbsp &nbsp &nbsp <a href="listePrelev.jsp">Voir la liste des prelevement non facture</a>
    <br/>
    <br/>
    <% if(request.getParameter("message")!=null)
    out.print(request.getParameter("message"));
    %>
    
</body>
<script src="js/jquery.min.js"></script> 
<script src="js/bootstrap.min.js"></script>
<% conn.close(); %>