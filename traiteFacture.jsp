<%@ page import="java.sql.Connection" %>
<%@ page import="general.*" %>
<%@ page import="jirama.*" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.lang.Double" %>
<%@ page import="java.sql.Date" %>
<%@ page import="java.lang.String" %>
<%
    ConnectDB db = new ConnectDB();
    Connection conn = db.connect();
    Generalise gen = new Generalise();
    Fonctions fonc = new Fonctions();
    PrelevNonFacture prelev = new PrelevNonFacture();
    Object[] obj = gen.selectOpt(conn,prelev,null);
    Object[] tab = new Object[0];
    PrelevNonFacture[] ls = new PrelevNonFacture[obj.length];
    for(int i=0;i<obj.length;i++){
        ls[i] = (PrelevNonFacture)obj[i];
        String id = String.valueOf(ls[i].getIdPrelev());
        if(request.getParameter(id)!=null)
        tab = gen.ajoutObj(tab,ls[i].getIdPrelev());
    }
    int[] idListe = new int[tab.length];
    for(int i=0;i<tab.length;i++){
        idListe[i] = (int)tab[i];
    }
    fonc.facturer(conn, idListe);
    String redirectURL = "index.jsp";
    response.sendRedirect(redirectURL);
    conn.close();
%>