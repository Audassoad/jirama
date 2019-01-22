package jirama;

import general.*;
import jirama.Abonnement;
import jirama.Client;
import jirama.Compteur;
import jirama.CompteurPrelev;
import jirama.Facture;
import jirama.FactureDetaille;
import jirama.OffrePrepaye;
import jirama.Prelevement;
import jirama.Tarif;
import except.*;
import java.sql.Connection;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.sql.Date;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.concurrent.TimeUnit;
import java.lang.Integer;
public class Fonctions {
	//// --------------- Fonctions pour les temps -------------------------------
	public String[] getFRMonth() {
		String[] m = { "Janvier", "Fevrier", "Mars", "Avril", "Mai", "Juin", "Juillet", "Aout", "Septembre", "Octobre",
				"Novembre", "Decembre" };

		return m;
	}

	public String format(String dt) {
		// System.out.println("to format " + dt);
		String[] m = getFRMonth();
		String[] d = dt.split(" ");
		// System.out.println("length " + d.length);
		String res = "";

		for (int i = 0; i < m.length; i++) {
			if (d[1].compareToIgnoreCase(m[i]) == 0) {
				res += (i + 1);
				// System.out.println("res " + res);
				break;
			}
		}
		// System.out.println("res a " + res);
		res += "/" + d[0] + "/" + d[2];

		if (d.length > 3) {
			res += " " + d[3];
		}

		// System.out.println("res " + res);

		return res;
	}

	public String transformDate(String d) {
		String[] dt = d.split("/");
		String res = dt[1] + "/" + dt[0] + "/" + dt[2];

		return res;
	}

	public Timestamp controlDate(String date) throws DateException {
		Timestamp d = null;
		// System.out.println("date " + date);
		String dt = date.replace("-", "/");

		try {
			dt = transformDate(dt);
			// System.out.println("dt tra " + dt);
			d = new Timestamp(Timestamp.parse(dt));
			// System.out.println("d ---> " + d);
		} catch (Exception e) {
			try {
				d = new Timestamp(Timestamp.parse(date));
				// System.out.println("month d " + d.getMonth());
				// System.out.println("date d " + d.getDate());
			} catch (Exception ex) {
				try {
					dt = format(date);
					// System.out.println("format " + dt);
					d = new Timestamp(Timestamp.parse(dt));
				} catch (Exception exc) {
					// System.out.println("exc " + e.getMessage());
					throw new DateException("Date Invalide !");
				}
			}
		}

		return d;
	}

	public Date timestampToDate(Timestamp t) throws Exception {
		int date = t.getDate();
		int month = t.getMonth();
		int year = t.getYear();
		String vao = year + "/" + month + "/" + date;
		Date result = new java.sql.Date(year, month, date);
		return result;
	}

	public String transformDate2(Date t) {
		int annee = t.getYear() + 1900;
		String year = "" + t.getYear();
		String day = "" + t.getDate();
		String[] mois = { "Janvier", "Fevrier", "Mars", "Avril", "Mai", "Juin", "Juillet", "Aout", "Septembre",
				"Octobre", "Novembre", "Decembre" };
		String val = day + " " + mois[t.getMonth()] + " " + annee;
		return val;
	}

	public Date getDateNow(Connection conn)throws Exception{
		Statement stat = conn.createStatement();
		String sql = "SELECT SYSDATE FROM DUAL";
		ResultSet res = stat.executeQuery(sql);
		Date val = null;
		while(res.next()){
			val = res.getDate(1);
		}
		stat.close();
		return val;
	}

	public String getDateNowString(Connection conn)throws Exception{
		Statement stat = conn.createStatement();
		Date val = null;
		String sql = "SELECT SYSDATE FROM DUAL";
		ResultSet res = stat.executeQuery(sql);
		while(res.next()){
			val = res.getDate(1);
		}
		int j = val.getDate();
		int m = val.getMonth()+1;
		int y = val.getYear()+1900 ;
		String result = j+"/"+m+"/"+y;
		stat.close();
		return result;
	}

	public int getDiffDateEnJour(Date avant, Date apres){
		long diff = apres.getTime() - avant.getTime();
        long val = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        int result = (int)val;
        return result;
	}
	///// --------------------------------------------------------------------------------------------

	public String nb(int index) {
		String[] lettres = new String[3];
		lettres[0] = "mille";
		lettres[1] = "millions";
		lettres[2] = "milliards";

		return lettres[index];
	}

	public String nb1(int index) {
		String[] lettres = {"","un","deux","trois","quatre","cinq","six","sept","huit","neuf","dix","onze","douze","treize","quatorze","quinze","seize","dix sept","dix huit","dix neuf","vingt","vingt un","vingt deux","vingt trois","vingt quatre","vingt cinq","vingt six","vingt sept","vingt huit","vingt neuf","trente","trente un","trente deux","trente trois","trente quatre","trente cinq","trente six","trente sept","trente huit","trente neuf","quarante","quarante un","quarante deux","quarante trois","quarante quatre","quarante cinq","quarante six","quarante sept","quarante huit","quarante neuf","cinquante","cinquante un","cinquante deux","cinquante trois","cinquante quatre","cinquante cinq","cinquante six","cinquante sept","cinquante huit","cinquante neuf","soixante","soixante un","soixante deux","soixante trois","soixante quatre","soixante cinq","soixante six","soixante sept","soixante huit","soixante neuf","soixante dix","soixante onze","soixante douze","soixante treize","soixante quatorze","soixante quinze","soixante seize","soixante dix sept","soixante dix huit","soixante dix neuf","quatre vingt","quatre vingt un","quatre vingt deux","quatre vingt trois","quatre vingt quatre","quatre vingt cinq","quatre vingt six","quatre vingt sept","quatre vingt huit","quatre vingt neuf","quatre vingt dix","quatre vingt onze","quatre vingt douze","quatre vingt treize","quatre vingt quatorze","quatre vingt quinze","quatre vingt seize","quatre vingt dix sept","quatre vingt dix huit","quatre vingt dix neuf"};
		
		return lettres[index];
	}

	public String enLettre(int nb) {
		int d = nb;
		String res = "";
		int n = -1;
		System.out.println("nb" + nb);
		while(d > 0) {
			int r = d % 100;
			// System.out.println("r == " + r);
			// System.out.println("n ------> " + n);
			// System.out.println("d == " + d);
			// System.out.println("lettre r == " + nb1(r));

			if(n > -1 && d%1000 > 0) {
				// System.out.println("r" + r);
				res = nb(n) + " " + res;
			}
			// System.out.println(d/10);
			
			if((d%1000) > 1 || n != 0) {
				res = nb1(r) + " " + res;
			}
			
			d = d / 100;

			int r2 = d % 10;
			// System.out.println("r2 == " + r2);
			// System.out.println("lettre r2 == " + nb1(r2));
			if(r2 != 0) {
				res = "cent " + res;

				if(r2 != 1) {
					res = nb1(r2) + " " + res;
				}
			}

			// System.out.println("res avant mille " + res);
			
			d = d /10;

			if(n == 2) {
				n = -1;
			}

			n++;
		}

		return res;
	}

	public int doubleEnInt(double nbr){
		String car = ""+nbr;
		String[] vao = car.split(".");
		System.out.println(nbr);
		System.out.println("int "+vao[0]);
		System.out.println("dec "+vao[1]);
		String res = vao[0];
		int val = new Integer(res).intValue();
		return val;
	}


	public int getPrelevNum(Connection conn) throws Exception {
		Statement stat = conn.createStatement();
		String rqt = "SELECT prelevnum.nextval FROM dual";
		ResultSet res = stat.executeQuery(rqt);
		int val = 0;
		while (res.next()) {
			val = res.getInt(1);
		}
		stat.close();
		return val;
	}

	public int getFactureId(Connection conn)throws Exception{
		Statement stat = conn.createStatement();
		String rqt = "SELECT numfacture.nextval FROM dual";
		ResultSet res = stat.executeQuery(rqt);
		int val = 0;
		while (res.next()) {
			val = res.getInt(1);
		}
		stat.close();
		return val;
	}

	public String getAbonnId(Connection conn)throws Exception{
		Statement stat = conn.createStatement();
		String sql = "SELECT abonnementId.nextval FROM dual";
		ResultSet res = stat.executeQuery(sql);
		int val = 0;
		while (res.next()) {
			val = res.getInt(1);
		}
		stat.close();
		return  "Abonn"+val;

	}

	public String getIdFactAnnuler(Connection conn)throws Exception{
		Statement stat = conn.createStatement();
		String sql = "SELECT factureannule.nextval FROM dual";
		ResultSet res = stat.executeQuery(sql);
		int val = 0;
		while (res.next()) {
			val = res.getInt(1);
		}
		stat.close();
		return "FactureAvoir"+val;
	}

	public int getIdPrelevAnnuler(Connection conn)throws Exception{
		Statement stat = conn.createStatement();
		String sql = "SELECT prelevementannule.nextval FROM dual";
		ResultSet res = stat.executeQuery(sql);
		int val = 0;
		while (res.next()) {
			val = res.getInt(1);
		}
		stat.close();
		return val;
	}

	public void verifPrelev(Connection conn, String numCompteur, String mois, String annee, double valeur)throws Exception{
			Prelevement pr = new Prelevement();
			Generalise gen = new Generalise();
			String where = "NumCompteur = '"+numCompteur+"'";
			Object[] obj = gen.selectOpt(conn, pr, where);
			Prelevement[] liste = new Prelevement[obj.length];
			for(int i=0;i<obj.length;i++){
				liste[i] = (Prelevement)obj[i];
				String date = transformDate2(liste[i].getDatePrelev());
				if(liste[i].getAnnee().equals(annee) && liste[i].getMois().equals(mois))
				throw new Exception("Vous avez deja fait un prelevement sur ce compteur en mois de "+mois+" "+annee+" le "+date);
				if(valeur<=liste[i].getValeur())
				throw new Exception("La derniere valeur du prelevement est superieure a la valeur actuel");
			}
	
	}

	public void insertPrelev(Connection conn, Date datePrelev, String mois, String annee, double valeur, String numCompteur)throws Exception{
		String rqt = "INSERT INTO Prelevement VALUES("+getPrelevNum(conn)+",'"+datePrelev+"','"+mois+"','"+annee+"',"+valeur+",'"+numCompteur+"')";
		System.out.print(rqt);
		Statement stat = conn.createStatement();
		stat.execute("alter session set nls_date_format='YYYY-MM-DD'");
		stat.executeQuery(rqt);
		stat.executeQuery("COMMIT");
		stat.close();
	}
 
	public void insertFactureAvoir(Connection conn,int idFacture, double montant)throws Exception{
		Date date = getDateNow(conn);
		String id = getIdFactAnnuler(conn);
		String sql = "INSERT INTO FactureAvoir VALUES('"+id+"',"+idFacture+","+montant+",'"+date+"')";
		Statement stat = conn.createStatement();
		stat.execute("alter session set nls_date_format='YYYY-MM-DD'");
		stat.executeQuery(sql);
		stat.executeQuery("COMMIT");
		stat.close();
	}

	public void insertPrelevAnnule(Connection conn, int numPrelev)throws Exception{
		String sql = "";
		Statement stat = conn.createStatement();
		Date date = getDateNow(conn);
		int id = getIdPrelevAnnuler(conn);
		 sql = "INSERT INTO PrelevAnnule VALUES("+id+","+numPrelev+",'"+date+"')";
		stat.execute("alter session set nls_date_format='YYYY-MM-DD'");
		stat.executeQuery(sql);
		stat.executeQuery("COMMIT");
			stat.close();	
	}

	public Tarif[] getTarifs(Connection conn)throws Exception{
		Generalise gen = new Generalise();
		Tarif t = new Tarif();
		Object[] obj = gen.selectOpt(conn, t, null);
		Tarif[] val = new Tarif[obj.length];
		for(int i=0;i<obj.length;i++){
			val[i] = (Tarif)obj[i];
		}
		return val;
	}

	public double calculMontant(Connection conn,double consommation)throws Exception{
		double prix = 0;
		Tarif[] tarifs = getTarifs(conn);
		if(consommation<=tarifs[0].getQuantite()){
			prix = consommation*tarifs[0].getPrixU();
		}
		if(consommation>tarifs[0].getQuantite() && consommation<=tarifs[1].getQuantite()){
			double qttRest = consommation - tarifs[0].getQuantite();
			double prix1er = tarifs[0].getPrixU() * tarifs[0].getQuantite();
			double prix2e = qttRest * tarifs[1].getPrixU();
			prix = prix1er + prix2e;
		}
		if(consommation>tarifs[1].getQuantite() && consommation<=tarifs[2].getQuantite()){
			double qttRest = consommation - tarifs[1].getQuantite();
			double prix1er = tarifs[0].getPrixU() * tarifs[0].getQuantite();
			double prix2e = tarifs[1].getPrixU() * (tarifs[1].getQuantite() - tarifs[0].getQuantite());
			double prix3e = qttRest * tarifs[2].getPrixU();
			prix = prix1er + prix2e + prix3e;
		}
		if(consommation>tarifs[2].getQuantite()){
			double qttRest = consommation - tarifs[2].getQuantite();
			double prix1er = tarifs[0].getPrixU() * tarifs[0].getQuantite();
			double prix2e = tarifs[1].getPrixU() * (tarifs[1].getQuantite() - tarifs[0].getQuantite());
			double prix3e = tarifs[2].getPrixU() * (tarifs[2].getQuantite() - tarifs[1].getQuantite());
			double prixPlus = qttRest * tarifs[3].getPrixU();
			prix = prix1er + prix2e + prix3e + prixPlus;
		}
		return prix;
	}

	public Prelevement[] getPrelevOrder(Connection conn, String numCompteur)throws Exception{
		Vector vect = new Vector();
		Statement stat = conn.createStatement();
		String rqt = "SELECT * FROM Prelevement WHERE NumCompteur = '"+numCompteur+"' ORDER BY Valeur ASC";
		System.out.println(rqt);
		ResultSet res = stat.executeQuery(rqt);
		while(res.next()){
			Prelevement pr = new Prelevement(res.getInt(1),res.getDate(2),res.getString(3),res.getString(4),res.getDouble(5),res.getString(6));
			vect.add(pr);
		}
		Prelevement[] val = new Prelevement[vect.size()];
		Object[] obj = vect.toArray();
        for(int i=0;i<obj.length;i++){
            val[i] = (Prelevement)obj[i];
        }
        stat.close();
        return val;
	}

	public double calculConsommation(Connection conn, String numCompteur, int numPrelev)throws Exception{
		double consommation = 0;
		Generalise gen = new Generalise();
		Prelevement pr = new Prelevement();
		Prelevement[] liste = getPrelevOrder(conn, numCompteur);
		Prelevement dernier = new Prelevement();
		Prelevement avant = new Prelevement();
			for(int i=0;i<liste.length;i++){
				if(liste[i].getNumPrelev()==numPrelev && i!=0){
					dernier = liste[i];
					avant = liste[i-1];
					consommation = dernier.getValeur() - avant.getValeur();
					break;
				}
				if(liste[0].getNumPrelev()==numPrelev){
					consommation = liste[0].getValeur();
				}
			}
		return consommation;
	}

	public CompteurPrelev getCompteurPrelev(Connection conn, int idPrelev)throws Exception{
		Generalise gen = new Generalise();
		String where = "IdPrelev = "+idPrelev;
		Object[] obj = gen.selectOpt(conn, new CompteurPrelev(), where);
		CompteurPrelev val = (CompteurPrelev)obj[0];
		return val;
	}

	public int[] getClient(Connection conn, int[] idPrelev)throws Exception{
		Generalise gen = new Generalise();
		CompteurPrelev cpr = new CompteurPrelev();
		CompteurPrelev val = new CompteurPrelev();
		int[] idClient = new int[idPrelev.length];
		Object[] tab = new Object[0];
		for(int i=0;i<idPrelev.length;i++){
			String where = "IdPrelev ="+idPrelev[i];
			Object[] obj = gen.selectOpt(conn, cpr, where);
			val = (CompteurPrelev)obj[0];
			tab = gen.ajoutObj(tab, val.getClient());
			idClient[i] = (int)tab[i];
		}
		return idClient;
	}

	public int getOneClient(Connection conn, int idPrelev)throws Exception{
		Generalise gen = new Generalise();
		CompteurPrelev cpt = new CompteurPrelev();
		String where = "IdPrelev ="+idPrelev;
		Object[] obj = gen.selectOpt(conn,cpt, where);
		cpt = (CompteurPrelev)obj[0];
		int idClient = cpt.getClient();
		return idClient;
	}

	public double calculerEau(Connection conn, double consommation)throws Exception{
		double prix = 0;
		Tarif[] tarifs = getTarifs(conn);
		prix = tarifs[tarifs.length-1].getPrixU() * consommation;
		return prix;
	}

	public String getIdCompteur(Connection conn, int idPrelev) throws Exception{
		Generalise gen = new Generalise();
		CompteurPrelev cpt = new CompteurPrelev();
		String where = "IdPrelev = "+idPrelev;
		Object[] obj = gen.selectOpt(conn,cpt, where);
		CompteurPrelev res = (CompteurPrelev)obj[0];
		return res.getCompteur();
	}

	public String getCateg(Connection conn, String numCompteur) throws Exception{
		Compteur cpt = new Compteur();
		Generalise gen = new Generalise();
		String where = "NumCompteur ='"+numCompteur+"'";
		Object[] obj  = gen.selectOpt(conn, cpt, where);
		Compteur val = (Compteur)obj[0];
		return val.getCategorie();
	}

	public Date dateFin(Connection conn, String idOffre)throws Exception{
		Generalise gen = new Generalise();
		Date now = getDateNow(conn);
		int j = now.getDate();
		int m = now.getMonth();
		int y = now.getYear();
		OffrePrepaye offre = new OffrePrepaye();
		offre.setIdOffre(idOffre);
		Object[] obj = gen.selectOpt(conn, offre, null);
		offre = (OffrePrepaye)obj[0];
		int duree = offre.getDuree();
		int jourFin = j + duree;
		now.setDate(jourFin);
		return now;
	}

	public OffrePrepaye getOffre(Connection conn, String idOffre)throws Exception{
		Generalise gen = new Generalise();
		OffrePrepaye ofr = new OffrePrepaye();
		String where = "IdOffre='"+idOffre+"'";
		Object[] obj = gen.selectOpt(conn, ofr, where);
		OffrePrepaye result = (OffrePrepaye)obj[0];
		return result;
	}


	public boolean verifOffreExist(Connection conn, int idPrelev)throws Exception{
		Generalise gen = new Generalise();
		CompteurPrelev cptpr = getCompteurPrelev(conn, idPrelev);
		String numCompteur = cptpr.getCompteur();
		Date datePrelev = cptpr.getDatePrelev();
		String where = "NumCompteur='"+numCompteur+"' and '"+datePrelev+"' >= DateDebut and '"+datePrelev+"' <= DateFin";
		Object[] obj = gen.selectOpt(conn, new Abonnement(), where);
		if(obj.length > 0){
			return true;
		}
		return false;
	}

	public Abonnement getAbonnExist(Connection conn, int idPrelev)throws Exception{
		Generalise gen = new Generalise();
		CompteurPrelev cpt = getCompteurPrelev(conn, idPrelev);
		String numCompteur = cpt.getCompteur();
		Date datePrelev = cpt.getDatePrelev();
		String where = "NumCompteur='"+numCompteur+"' and '"+datePrelev+"' >= DateDebut and '"+datePrelev+"' <= DateFin";
		Object[] obj = gen.selectOpt(conn, new Abonnement(), where);
		Abonnement val = (Abonnement)obj[obj.length-1];
		return val;
	}

	public double calculRestAbonn(Connection conn, String numCompteur, String newOffre)throws Exception{
		double conso = 0;
		Generalise gen = new Generalise();
		String cond = "IdOffre = '"+newOffre+"'";
		Object[] of = gen.selectOpt(conn, new OffrePrepaye(), cond);
		OffrePrepaye vao = (OffrePrepaye)of[0];
		String where = "NumCompteur ='"+numCompteur+"' and DateFin > '"+getDateNowString(conn)+"' ORDER BY IdAbonnement";
		Object[] obj = gen.selectOpt(conn, new Abonnement(), where);
		if(obj.length>0){
			Abonnement abonn = (Abonnement)obj[obj.length-1];
			where = "IdOffre ='"+abonn.getIdOffre()+"' ORDER BY IdOffre";
			Object[] o = gen.selectOpt(conn, new OffrePrepaye(), where);
			int jRestant = getDiffDateEnJour(getDateNow(conn), abonn.getDateFin()) + 1;
			System.out.println(jRestant);
			if(jRestant <= 0){
				jRestant = 0;
			}
			OffrePrepaye offr = (OffrePrepaye)o[o.length-1];
			double pourcentConsom = offr.getOffreConsom() / offr.getDuree();
			conso = vao.getOffreConsom() + (jRestant * pourcentConsom) - 1;
		}
		if(obj.length==0)
		conso = vao.getOffreConsom();
		return conso;
	}

	public double calculNewQuantite(Connection conn, String numCompteur, String newOffre)throws Exception{
		double newQuantite = 0;

		Generalise gen = new Generalise();

		Date dateAchat = getDateNow(conn);
		String where = "NumCompteur = '"+numCompteur+"' and '"+getDateNowString(conn)+"' >= DateDebut and '"+getDateNowString(conn)+"' <= DateFin order by DateDebut";
		Object[] obj = gen.selectOpt(conn, new Abonnement(), where);
		String newCondition = "IdOffre = '"+newOffre+"'";
		Object[] obj2 = gen.selectOpt(conn, new OffrePrepaye(), newCondition);
		OffrePrepaye newOp = (OffrePrepaye)obj2[0];
		if(obj.length>0){
		Abonnement abonnement = (Abonnement)obj[obj.length-1];
		String idOffre = abonnement.getIdOffre();
		Date dateDebut = abonnement.getDateDebut();
		String condition = "IdOffre = '"+idOffre+"'";
		Object[] obj1 = gen.selectOpt(conn, new OffrePrepaye(), condition);
		OffrePrepaye op = (OffrePrepaye)obj1[0];

		double consomAcheter = op.getOffreConsom();
		int duree = op.getDuree();

		double newConsomAcheter = newOp.getOffreConsom();
		System.out.println("newconsom acheter "+newConsomAcheter);
		int newDuree = newOp.getDuree();
		System.out.println("newduree "+newDuree);

		int diffJour = getDiffDateEnJour(dateAchat, dateDebut);
		System.out.println("diffjour "+diffJour);
		double consomMoyenne = consomAcheter/duree;
		System.out.println("consomoyenne"+consomMoyenne);
		double resteOffre = diffJour * consomMoyenne;
		newQuantite = newConsomAcheter + resteOffre;
		System.out.println("newquantite "+newQuantite);
		} else
			newQuantite = newOp.getOffreConsom();

		return newQuantite;
	}

	public void acheterOffre(Connection conn, String idOffre, String numCompteur)throws Exception{
		Date fin = dateFin(conn, idOffre);
		Date debut = getDateNow(conn);
		Statement stat = conn.createStatement();
		String idAbonnement = getAbonnId(conn);
		double consom = calculNewQuantite(conn, numCompteur, idOffre);
		String sql = "INSERT INTO Abonnement VALUES('"+idAbonnement+"','"+numCompteur+"','"+idOffre+"',"+consom+",'"+debut+"','"+fin+"')";
		stat.execute("alter session set nls_date_format='YYYY-MM-DD'");
		stat.executeQuery(sql);
		stat.close();
	}

	public void facturerAvecOffre(Connection conn, int idPrelev)throws Exception{
		Statement stat = conn.createStatement();
		int client = getOneClient(conn, idPrelev);
		int numFacture = 0;
			String sql = "INSERT INTO PrelevementFacture VALUES("+idPrelev+","+client+")";
			System.out.println("1 -->"+sql);
			stat.executeQuery(sql);
			String numCompteur = getIdCompteur(conn, idPrelev);
			double consommation = calculConsommation(conn, numCompteur,idPrelev);
			Abonnement abonn = new Abonnement();
			abonn = getAbonnExist(conn, idPrelev);
			OffrePrepaye offr = new OffrePrepaye();
			offr = getOffre(conn, abonn.getIdOffre());
			double consoAbonn = abonn.getQuantite();
			double restConso = consommation - consoAbonn;
			String categ = getCateg(conn, numCompteur);
			double montant = 0, elec = 0, eau = 0;
			 numFacture = getFactureId(conn);
			 if(restConso<=0){
				 restConso = 0;
			 }
			if(categ.equals("Electricite")){
				elec = calculMontant(conn, restConso) * offr.getMajoration();
				sql = "INSERT INTO Facture VALUES("+numFacture+","+client+",'"+getDateNow(conn)+"',"+elec+",'Electricite')";
				System.out.println("2 -->"+sql);
				stat.execute("alter session set nls_date_format='YYYY-MM-DD'");
				stat.executeQuery(sql);
				String rqt = "INSERT INTO FactureDetaille VALUES("+idPrelev+","+numFacture+",'"+numCompteur+"','Electricite',"+consommation+")";
				System.out.println("3 --->"+rqt);
				stat.execute(rqt);
				elec = 0;
			}
			if(categ.equals("Eau")){
				eau = calculerEau(conn, restConso) * offr.getMajoration();
				sql = "INSERT INTO Facture VALUES("+numFacture+","+client+",'"+getDateNow(conn)+"',"+eau+",'Eau')";
				System.out.println("Eau  2 -->"+sql);
				stat.execute("alter session set nls_date_format='YYYY-MM-DD'");
				stat.executeQuery(sql);
				String rqt = "INSERT INTO FactureDetaille VALUES("+idPrelev+","+numFacture+",'"+numCompteur+"','Eau',"+consommation+")";
				System.out.println("Eau 3 --->"+rqt);
				stat.execute("alter session set nls_date_format='YYYY-MM-DD'");
				stat.execute(rqt);
				eau = 0;
			}
		stat.executeQuery("COMMIT");
		stat.close();
	}	

	public void facturerSansOffre(Connection conn, int idPrelev)throws Exception{
		Statement stat = conn.createStatement();
		int client = getOneClient(conn, idPrelev);
		int numFacture = 0;
		String sql = "INSERT INTO PrelevementFacture VALUES("+idPrelev+","+client+")";
			System.out.println("1 -->"+sql);
			stat.executeQuery(sql);
			String numCompteur = getIdCompteur(conn, idPrelev);
			double consommation = calculConsommation(conn, numCompteur,idPrelev);
			String categ = getCateg(conn, numCompteur);
			double montant = 0, elec = 0, eau = 0;
			 numFacture = getFactureId(conn);
			if(categ.equals("Electricite")){
				elec = calculMontant(conn, consommation);
				sql = "INSERT INTO Facture VALUES("+numFacture+","+client+",'"+getDateNow(conn)+"',"+elec+",'Electricite')";
				System.out.println("2 -->"+sql);
				stat.execute("alter session set nls_date_format='YYYY-MM-DD'");
				stat.executeQuery(sql);
				String rqt = "INSERT INTO FactureDetaille VALUES("+idPrelev+","+numFacture+",'"+numCompteur+"','Electricite',"+consommation+")";
				System.out.println("3 --->"+rqt);
				stat.execute(rqt);
				elec = 0;
			}
			if(categ.equals("Eau")){
				eau = calculerEau(conn, consommation);
				sql = "INSERT INTO Facture VALUES("+numFacture+","+client+",'"+getDateNow(conn)+"',"+eau+",'Eau')";
				System.out.println("Eau  2 -->"+sql);
				stat.execute("alter session set nls_date_format='YYYY-MM-DD'");
				stat.executeQuery(sql);
				String rqt = "INSERT INTO FactureDetaille VALUES("+idPrelev+","+numFacture+",'"+numCompteur+"','Eau',"+consommation+")";
				System.out.println("Eau 3 --->"+rqt);
				stat.execute("alter session set nls_date_format='YYYY-MM-DD'");
				stat.execute(rqt);
				eau = 0;
			}
		stat.executeQuery("COMMIT");
		stat.close();	
	}

	public void facturer(Connection conn, int[] idPrelev)throws Exception{
		for(int i=0; i<idPrelev.length; i++){
			boolean b = verifOffreExist(conn,idPrelev[i]);
			if(b){
				facturerAvecOffre(conn, idPrelev[i]);
			}
			else{
				facturerSansOffre(conn, idPrelev[i]);
			}
		}
	}

	public FactureDetaille getFactureDetaille(Connection conn, int numPrelev)throws Exception{
		Generalise gen = new Generalise();
		String where = "NumPrelev ="+numPrelev;
		Object[] obj = gen.selectOpt(conn, new FactureDetaille(), where);
		FactureDetaille factD = new FactureDetaille();
		System.out.print("T fact detail"+obj.length);
		factD = (FactureDetaille)obj[0];
		return factD; 
	}

	public Facture getFacture(Connection conn, int idFacture)throws Exception{
		Generalise gen = new Generalise();
		String where = "IdFacture = "+idFacture;
		Object[] obj = gen.selectOpt(conn, new Facture(), where);
		Facture facture = new Facture();
		facture = (Facture)obj[0];
		return facture;
	}

	public Client unClient(Connection conn, int idClient)throws Exception{
		Generalise gen = new Generalise();
		String where = "IdClient = "+idClient;
		Object[] obj = gen.selectOpt(conn, new Client(), where);
		Client val = (Client)obj[0];
		return val;
	}

	public double getMontantFacture(Connection conn, int[] idFacture)throws Exception{
		double montant = 0;
		Generalise gen = new Generalise();
		for(int i=0; i<idFacture.length; i++){
			String where = "IdFacture = "+idFacture[i];
			Object[] obj = gen.selectOpt(conn, new Facture(), where);
			Facture val = new Facture();
			val = (Facture)obj[0];
			montant += val.getMontant();
		}
		return montant;
	}

	

	public String affFacture(Connection conn, String mois, String annee, int numClient)throws Exception{
		String html = "";
		Client cl = new Client();
		cl = unClient(conn, numClient);
		Generalise gen = new Generalise();
		String where = "MoisPrelev = '"+mois+"' and AnneePrelev = '"+annee+"' and Client ="+numClient;
		Object[] obj = gen.selectOpt(conn, new CompteurPrelev(), where);
		if(obj.length>0){
			html += "<h3>Facture du client numero "+numClient+"</h3>";
			double montantTotal = 0;
			CompteurPrelev[] val = new CompteurPrelev[obj.length];
			int[] idPrelev = new int[obj.length];
			Facture[] factures = new Facture[obj.length];
			String idAnnul = "";
			FactureDetaille[] detailles = new FactureDetaille[obj.length];
			for(int i=0;i<obj.length;i++){
				val[i] = (CompteurPrelev)obj[i];
				idPrelev[i] = val[i].getIdPrelev();
				detailles[i] = getFactureDetaille(conn, idPrelev[i]);
				factures[i] = getFacture(conn, detailles[i].getIdFacture());
				montantTotal += factures[i].getMontant();
			}
			int total = (int)montantTotal;
			html += "<p>Nom : "+cl.getNom()+"</p>";
			html += "<p>Prenom : "+cl.getPrenom()+"</p>";
			html += "<p>Date de facture: "+transformDate2(factures[0].getDateFacture())+"</p>";
			html += "<p>Montant total : "+montantTotal+" Ariary</p>";
			html += "<p>Montant total en lettre : "+enLettre(total)+" ariary</p>";
			for(int i=0; i<obj.length; i++){
				html += "<p>Compteur numero : "+detailles[i].getNumCompteur()+"</p>";
				html += "<table border=\"1\">";
				html += "<tr>";
				html += "<th>Categorie</th>";
				html += "<th>Consommation</th>";
				html += "<th>Montant</th>";
				html += "</tr>";
				html += "<tr>";
				html += "<td>"+detailles[i].getCategorie()+"</td>";
				html += "<td>"+detailles[i].getConsommation()+"</td>";
				html += "<td>"+factures[i].getMontant()+"</td>";
				html += "</tr>";
				html += "</table>";
				html += "<br/>";
				html += "<form action=\"annuler.jsp\" action=\"POST\">";
				html += "<input type=\"hidden\" name=\"annuler\" value='"+factures[i].getIdFacture()+"'/>";
				html += "<input type=\"hidden\" name=\"prelev\" value='"+detailles[i].getNumPrelev()+"'/>";
				html += "<button type='submit'>Annuler la facture</button>";
				html += "</form>";
				html += "<hr>";
			}
		} else{
			html += "<p>Il n'y pas de facture liee a votre recherche</p>";
		}
		return html;
	}


}