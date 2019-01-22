<%@ page import="java.sql.Connection" %>
<%@ page import="general.*" %>
<%@ page import="jirama.*" %>
<%@ page import="java.lang.Integer" %>
<%@ page import="java.lang.Double" %>
<%
    ConnectDB db = new ConnectDB();
    Connection conn = db.connect();
    Fonctions fonc = new Fonctions();
    String id = request.getParameter("annuler");
    String pr = request.getParameter("prelev");
    String m = request.getParameter("montant");
    Double montant = Double.parseDouble(m);
    int idFacture = new Integer(id).intValue();
    int numPrelev = new Integer(pr).intValue();
    Generalise gen = new Generalise();
    String where = "IdFacture ="+idFacture;
    Object[] obj = gen.selectOpt(conn,new Facture(),where);
    Facture val = (Facture)obj[0];
    if(val.getMontant() < montant){
        throw new Exception("Le montant saisi est superieur au montant du facture");
    }
    fonc.insertFactureAvoir(conn,idFacture,montant);
    if(val.getMontant() == montant){
        fonc.insertPrelevAnnule(conn,numPrelev);
        response.sendRedirect("insertNewPrelev.jsp?numprelev="+numPrelev);
    }else
    response.sendRedirect("index.jsp?message=Modification reussie");
    conn.close();
    /* catch(Exception e)
    {
        e.printStackTrace(new java.io.PrintWriter(out))
    }*/
%>

