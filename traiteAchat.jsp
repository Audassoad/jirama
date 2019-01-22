<%@ page import="java.sql.Connection" %>
<%@ page import="general.*" %>
<%@ page import="jirama.*" %>
<%
    ConnectDB db = new ConnectDB();
    Connection conn = db.connect();
    String idCompteur = request.getParameter("idCompt");
    String idOffre = request.getParameter("idOffre");
    String id = request.getParameter("id");
    Fonctions fonc = new Fonctions();
    String aff = "Veuillez choisir un offre!";
    if(idOffre!=null){
        fonc.acheterOffre(conn,idOffre,idCompteur); 
        aff = "Achat effectue";
    }
    

%>
<form name="achatOffre" method="POST" action="listePrepaye.jsp">
    <input type="hidden" name="message" value="<% out.print(aff); %>"/>
    <input type="hidden" name="id" value="<% out.print(id); %>">
</form>
<script>
    achatOffre.submit();
</script>