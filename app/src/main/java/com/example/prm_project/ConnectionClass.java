package com.example.prm_project;

import android.os.StrictMode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionClass {
    String classes = "net.sourceforge.jtds.jdbc.Driver";
    protected static String ip = "192.168.0.101";
    protected static String port = "1433";
    protected static String db = "Restaurant";
    protected static String un = "sa";
    protected static String password = "123456";
    public Connection Con(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection con = null;
        try {
            Class.forName(classes);
            String conUrl = "jdbc:jtds:sqlserver://"+ip + ":" +port + ";" + db;
            con = DriverManager.getConnection(conUrl, un, password);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return con;
    }
}
