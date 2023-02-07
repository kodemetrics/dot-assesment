package com.dot.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private static  boolean isDriverLoaded = false;
    private final static String url="jdbc:mysql://localhost:3306/REQ_LIMIT?useSSL=false";
    private final static String user="root";
    private final static String password="";

    static{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver Loaded");
            isDriverLoaded = true;
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        Connection con  = DriverManager.getConnection(url,user,password);
        System.out.println("Connection established");
        return con;
    }

    public static void closeConnection(Connection con) throws SQLException{
        if(con!=null){
            con.close();
            System.out.println("connection closed");
        }
    }
}
