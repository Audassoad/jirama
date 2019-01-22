<%@ page import="java.sql.Connection" %>
<%@ page import="general.*" %>
<%@ page import="jirama.*" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.lang.Double" %>
<%@ page import="java.sql.Date" %>
<%
    ConnectDB db = new ConnectDB();
    Connection conn = db.connect();
    Fonctions fonc = new Fonctions();
    String date = request.getParameter("date");
    String mois = request.getParameter("mois");
    String annee = request.getParameter("annee");
    String value = request.getParameter("valeur");
    String numCompteur = request.getParameter("numCompteur");
    Double valeur = Double.parseDouble(value);
    Timestamp tmp = fonc.controlDate(date);
    Date dt = fonc.timestampToDate(tmp);
    fonc.verifPrelev(conn,numCompteur,mois,annee,valeur);
    fonc.insertPrelev(conn,dt,mois,annee,valeur,numCompteur);
    String redirectURL = "index.jsp?message=Prelevement mis a jour avec succes";
    response.sendRedirect(redirectURL);
    conn.close();
%>