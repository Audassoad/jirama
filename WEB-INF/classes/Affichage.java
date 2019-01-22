package affiche;

import jirama.*;
import general.*;
import java.sql.Connection;
import java.sql.*;
import java.util.concurrent.TimeUnit;

public class Affichage {
    
	public static int getDiffDateEnJour(Date avant, Date apres){
		long diff = apres.getTime() - avant.getTime();
        long val = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        int result = (int)val;
        return result;
	}
    public static void main(String[] args) throws Exception{
        ConnectDB db = new ConnectDB();
        Connection conn = db.connect();
        /*Generalise gen = new Generalise();
        Client cl = new Client();
        Object[] obj = gen.selectOpt(conn, cl, null);
        Client[] val = new Client[obj.length];
        for(int i=0;i<obj.length;i++){
            val[i] = (Client)obj[i];
            System.out.println(val[i].getIdClient());
        }
        */
        Fonctions fonc = new Fonctions();
        // String num = "56010";
        // double cons = fonc.calculConsommation(conn,num);
        // double prix = fonc.calculMontant(conn,cons);
        // System.out.println("Consommation: "+cons);
        // System.out.println("Prix "+prix);
        // System.out.println(getDateNow(conn));
        // Date dt1 = null;
        // dt1 = new Date(19,00,11);
        // Date dt2 = new Date(18,11,23);
        // long diff = getDiffDateEnJour(dt2, dt1);
        // System.out.println("Diff "+diff);
        double nbr = 10000.0;
        int chr = fonc.doubleEnInt(nbr);
        System.out.println(chr);
        conn.close();
        
    }

    
}