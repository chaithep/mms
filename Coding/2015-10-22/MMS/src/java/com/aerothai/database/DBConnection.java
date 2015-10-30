/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerothai.database;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author chaia_000
 */
public class DBConnection {
    public static String dbClass = "com.mysql.jdbc.Driver";
    private static String dbName= "mms";
    private static String jdbcutf8 = "?useUnicode=true&characterEncoding=UTF-8";
    public static String dbUrl = "jdbc:mysql://172.16.70.100:3306/"+dbName+jdbcutf8;
    //public static String dbUrl = "jdbc:mysql://localhost:3306/"+dbName;
    public static String dbUser = "root";
    public static String dbPwd = "mmes";
    
        /**
	 * Method to create DB Connection
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("finally")
	public static Connection createConnection() throws Exception {
		Connection con = null;
		try {
			Class.forName(dbClass);
			con = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
		} catch (Exception e) {
			throw e;
		} finally {
			return con;
		}
	}  
}
