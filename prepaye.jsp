<%@ page import="jirama.*" %>
<%@ page import="general.*" %>
<%@ page import="java.sql.Connection" %>
<%
    ConnectDB db = new ConnectDB();
    Connection conn = db.connect();
    Generalise gen = new Generalise();
    Client cl = new Client();
    Object[] obj = gen.selectOpt(conn,cl,null);
    Client[] val = new Client[obj.length];
    for(int i=0;i<obj.length;i++){
        val[i] = (Client)obj[i];
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
    <h3>Les tarifs prepayes</h3>
    <p>Continuer en tant que : </p>
    <form action="listePrepaye.jsp" method="POST">
    <select name="id">
    <% for(int i=0;i<val.length;i++) { %>
        <option value="<% out.print(val[i].getIdClient()); %>"><% out.print(val[i].getPrenom()); %></option>
        <% } %>
    </select>
    <button type="submit">Afficher</button>
    </form>
</body>
<script src="js/jquery.min.js"></script> 
    <script src="js/bootstrap.min.js"></script>
<% conn.close(); %>