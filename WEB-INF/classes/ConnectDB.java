package jirama;

import java.sql.*;
import java.sql.DriverManager;

public class ConnectDB {    
    public Connection connect() throws Exception{
        Class.forName("oracle.jdbc.driver.OracleDriver");
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String user = "jirama";
        String mdp = "jirama";
        Connection conn = DriverManager.getConnection(url,user,mdp);
        return conn;
    }
}
