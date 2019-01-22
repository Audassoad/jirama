package general;

import java.lang.reflect.Method;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Date;
import java.sql.Timestamp;

public class Generalise {
	public Method getFunction(Object objs, String att) throws Exception {
		// att = att.toLowerCase();
		String nomFonction = "get" + att.substring(0,1).toUpperCase() + att.substring(1);
		Class c = objs.getClass();
		Method m = c.getMethod(nomFonction);

		return m;
	}
		
	public Object[] findOpt(String column, String value, Object[] ob_tab) throws Exception {
		Object[] res = new Object[0];
		
		for(int e=0; e < ob_tab.length; e++) {
			if(ob_tab[e] != null) {
				Method m = getFunction(ob_tab[e],column);
				String str = m.invoke(ob_tab[e]).toString().toLowerCase();
				
				// System.out.println("str == " + str);
				// System.out.println("value == " + value);
				// System.out.println("comp == " + str.compareTo(value.toLowerCase()));
				
				if(str.indexOf(value.toLowerCase()) != -1) {
					res = ajoutObj(res,ob_tab[e]);
					ob_tab[e] = null;
					// System.out.println("trouve");
				}
			}
		}
		
		return res;
	}
	
	public Object[] ajoutObj(Object[] obj, Object o) {
		Object[] nouv = new Object[obj.length+1];
		int i = 0;
		
		while(i < obj.length) {
			nouv[i] = obj[i];
			
			i++;
		}
		
		nouv[i] = o;
		
		return nouv;
	}


	public Object[][] ajoutTable(Object[][] obj, Object[] o) {
		Object[][] nouv = new Object[obj.length+1][];
		int i = 0;
		
		while(i < obj.length) {
			nouv[i] = obj[i];
			
			i++;
		}
		
		nouv[i] = o;
		
		return nouv;
	}
	
	public String getTableFromClass(Class c) {
        String table_name = c.getName();
        int n = table_name.lastIndexOf('.') + 1;
        table_name = table_name.substring(n);
        
        return table_name;
    }
	
	public String FName(String type, String att) {
		String nomF = "";
		String[] table = att.split("\\.");
		att = table[table.length-1];

		if(type != null) {
			nomF += type;
		}	
		
		nomF += att.substring(0,1).toUpperCase() + att.substring(1);
		
		return nomF;
	}
	
	public Object[] select(Connection con, Object o, String where) throws Exception {
		Object[] res = new Object[0];
		Class c = o.getClass();
		String nt = getTableFromClass(c);
		String sql = "SELECT * FROM " + nt;
		
		if(where != null) {
			sql += " WHERE " + where;
		}
		
		System.out.println("general select sql " + sql);
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery(sql);
		Field[] f = c.getDeclaredFields();
		
		for(int p=0; rs.next(); p++) {
			Object obj = c.newInstance();
			
			for(int i=0; i < f.length; i++) {
				String att = f[i].getName();
				String nF = FName("set",att);
				Class type = f[i].getType();
				Method m = c.getMethod(nF,type);
				
				// System.out.println("type " + type.getName());
				
				String v = rs.getString(att);
				
				try {
					Object cast = type.cast(v);
					m.invoke(obj,cast);
				}
				catch(ClassCastException cce) {
					String parseF = "parse"+FName(null,type.getName());
					Method parseM = Generalise.class.getMethod(parseF,String.class);
					m.invoke(obj,parseM.invoke((new Generalise()),v));
				}
			}
			
			res = ajoutObj(res,obj);
		}
		
		rs.close();
		s.close();
		
		return res;
	}
	
	public Object[] selectOpt(Connection con, Object o, String where) throws Exception {
		Object[] res = new Object[0];
		Class c = o.getClass();
		String nt = getTableFromClass(c);
		String sql = "SELECT * FROM " + nt;
		
		if(where != null) {
			sql += " WHERE " + where;
		}
		
		System.out.println("general select sql " + sql);
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery(sql);
		Field[] f = c.getDeclaredFields();
		
		for(int p=0; rs.next(); p++) {
			Object obj = c.newInstance();
			
			for(int i=0; i < f.length; i++) {
				String att = f[i].getName();
				String nF = FName("set",att);
				Class type = f[i].getType();
				String dbGet = FName("get",type.getName());
				Method rSMethod = ResultSet.class.getMethod(dbGet,int.class);
				
				try {
					Method m = c.getMethod(nF,type);
					
					// System.out.println("type " + type.getName());
					
					
					Modifier mo = new Modifier();
					
					if(mo.isPublic(rSMethod.getModifiers())) {
						m.invoke(obj,rSMethod.invoke(rs,(i+1)));
					}
				}
				catch(Exception e) {
					throw new Exception("La fonction " + nF + "(" + FName(null,type.getName()) + " " + att +") n'existe pas dans la classe " + nt + " ou elle n'est pas public");
				}
			}
			
			res = ajoutObj(res,obj);
		}
		
		rs.close();
		s.close();
		
		return res;
	}
	
	public void setAll(Object o, String[] valeur) throws Exception {
        Class c = o.getClass();
        Field[] f = c.getDeclaredFields();

        for(int i=0; i < f.length; i++) {
			if(valeur[i] != null) {
				String att = f[i].getName();
				String nomFonction = FName("set",att);
				// System.out.println("fonction " + m);
				Method m = c.getMethod(nomFonction,String.class);

				try {
					m.invoke(o,valeur[i]);
				} catch(Exception e) {
					throw new Exception("La colonne " + att + " a pour valeur " + e.getMessage());
				}
			}
        }
    }

    public String getInsertSQL(Object o) throws Exception {
        Class c = o.getClass();
        
		String table_name = getTableFromClass(c);
        
        // System.out.println(table_name);
        String sql = "INSERT INTO " + table_name + " VALUES(";
        Field[] f = c.getDeclaredFields();
        
        // System.out.println("length " + f.length);
        for(int i=0; i < f.length; i++) {
            String att = f[i].getName();
            String nomFonction = FName("get",att);
            Method m = c.getMethod(nomFonction);
            Object obj = m.invoke(o);
            String res = "";
            
            if(obj == null) {
                res = "null";
            }
            else {
                res = obj.toString();
            }
			
			// System.out.println("to String : " + res);
            
			if(obj != null) {
				if(f[i].getType() == res.getClass() || f[i].getType() == Date.class || f[i].getType() == Timestamp.class) {
					res = "'" + res +"'";
				}
			}
			
			// if(f[i].getType() == res.getClass() && obj != null) {
            //     res = "'" + res +"'";
            // }
            
            sql += res;
            
            if(i != (f.length-1)) {
                sql += ",";
            }

            // System.out.println(sql);
        }
        
        sql += ")";
        
        return sql;
    }
	
	public int parse(double d) {
		int i = (new Integer((""+d).substring(0,(""+d).indexOf(".")))).intValue();
		
		return i;
	}
	
	public int parseInt(String s) {
		return (new Integer(s)).intValue();
	}
	
	public double parseDouble(String s) {
		return (new Double(s)).doubleValue();
	}
	
	public double parseLong(String s) {
		return (new Long(s)).longValue();
	}

	// public Timestamp parseTimestamp(String d)throws Exception{
		// Fonction fo = new Fonction();
		// System.out.println("date : "+d);
		// return fo.controllDate(d);		
	// }

    public int insert(Connection con, Object o) throws Exception {
        System.out.println("avant");
        String sql = getInsertSQL(o);
        System.out.println("SQL for insert : " + sql);
        Statement s = con.createStatement();
		int n = 0;
		s.execute("ALTER SESSION SET nls_Timestamp_format = 'YYYY-MM-DD HH24:MI:SS.ff'");
		s.execute("ALTER SESSION SET nls_Date_format = 'YYYY-MM-DD HH24:MI:SS'");
        n = s.executeUpdate(sql);
        s.execute("commit");
        s.close();
		
		return n;
    }
	
	public double getSum(Object[] o, String att) throws Exception {
        String nomFonction = "get" + att.substring(0,1).toUpperCase() + att.substring(1);
        double res = 0;
        System.out.println(nomFonction);
        
        for(int i=0; i < o.length; i++) {
            Class c = o[i].getClass();
            Method m = c.getMethod(nomFonction);
            System.out.println(m.invoke(o[i]).toString());
            double n = (new Double(m.invoke(o[i]).toString())).doubleValue();
            res  = res + n;
        }
        
        return res;
    }
}