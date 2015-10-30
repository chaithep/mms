/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerothai.database.devicetype;

import com.aerothai.database.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author chaia_000
 */
public class DeviceTypeService {
    public JSONObject GetDeviceTypeAll(String opt)throws Exception {

        Connection dbConn = null;
        JSONObject obj = new JSONObject();
        JSONArray objList = new JSONArray();
        
        int no =1;
        //obj.put("draw", 2);
        obj.put("tag","list");
        obj.put("msg","error");
        obj.put("status", false);
	try {
            dbConn = DBConnection.createConnection();
            
            Statement stmt = dbConn.createStatement();
            String query =  "SELECT * FROM device_type";
            if (isNotNull(opt)){
                query =  "SELECT * FROM device_type WHERE"+opt;
            }
            System.out.println(query);
            ResultSet rs = stmt.executeQuery(query);
            
            while (rs.next()) {
               
                JSONObject jsonData = new JSONObject();
                jsonData.put("id",rs.getInt("iddevice_type"));
                jsonData.put("details",rs.getString("details"));
                jsonData.put("idunit",rs.getString("idunit"));
                jsonData.put("no",no);
                objList.add(jsonData);
                no++;
                
            }
            obj.put("msg","done");
            obj.put("status", true);
            obj.put("data", objList);
	} catch (SQLException sqle) {
            throw sqle;
	} catch (Exception e) {
            // TODO Auto-generated catch block
            if (dbConn != null) {
		dbConn.close();
            }
            throw e;
	} finally {
            if (dbConn != null) {
		dbConn.close();
            }
	}
	return obj;
    }
    public JSONObject GetDeviceTypeAt(int id)throws Exception {
        
        Connection dbConn = null;
        JSONObject obj = new JSONObject();
        JSONArray objList = new JSONArray();
        
        int no =1;
        //obj.put("draw", 2);
        obj.put("tag","listat");
        obj.put("msg","error");
        obj.put("status", false);
	try {
            dbConn = DBConnection.createConnection();
            
            Statement stmt = dbConn.createStatement();
            String query =  "SELECT * FROM device_type where iddevice_type = "+id;
            System.out.println(query);
            ResultSet rs = stmt.executeQuery(query);
            
            while (rs.next()) {
                
                JSONObject jsonData = new JSONObject();
                jsonData.put("id",rs.getInt("iddevice_type"));
                jsonData.put("details",rs.getString("details"));
                jsonData.put("idunit",rs.getString("idunit"));
                jsonData.put("no",no);
                objList.add(jsonData);
                no++;
            }
            obj.put("msg","done");
            obj.put("status", true);
            obj.put("data", objList);
	} catch (SQLException sqle) {
            throw sqle;
	} catch (Exception e) {
            // TODO Auto-generated catch block
            if (dbConn != null) {
		dbConn.close();
            }
            throw e;
	} finally {
            if (dbConn != null) {
		dbConn.close();
            }
	}
	return obj;
    }
    
    /**
     * Method to check whether uname and pwd combination are correct
     * 
     * @param uname
     * @param pwd
     * @return
     * @throws Exception
     */
    public boolean updateSql(String query) throws Exception {
	boolean isDone = false;
	Connection dbConn = null;
                
        try {
            dbConn = DBConnection.createConnection();
            
            Statement stmt = dbConn.createStatement();
            System.out.println(query);
            stmt.executeUpdate(query);
            isDone = true;
	                
	} catch (SQLException sqle) {
			throw sqle;
	} catch (Exception e) {
            // TODO Auto-generated catch block
            if (dbConn != null) {
		dbConn.close();
            }
            throw e;
	} finally {
            if (dbConn != null) {
		dbConn.close();
            }
	}
	return isDone;
    }
    /**
     * Method to check whether uname and pwd combination are correct
     * 
     * @param uname
     * @param pwd
     * @return
     * @throws Exception
     */
    public String createDeviceType(String query) throws Exception {
	
	Connection dbConn = null;
        JSONObject obj = new JSONObject();
        obj.put("msg","error");
        obj.put("status", false);
        try {
            dbConn = DBConnection.createConnection();
            
            Statement stmt = dbConn.createStatement();
            System.out.println(query);
            stmt.executeUpdate(query);
	
            obj.put("tag","create");
            obj.put("msg","done");
            obj.put("status", true);
    
	} catch (SQLException sqle) {
			throw sqle;
	} catch (Exception e) {
            // TODO Auto-generated catch block
            if (dbConn != null) {
		dbConn.close();
            }
            throw e;
	} finally {
            if (dbConn != null) {
		dbConn.close();
            }
	}
	return obj.toJSONString();
    }
     /**
     * Method to check whether uname and pwd combination are correct
     * 
     * @param uname
     * @param pwd
     * @return
     * @throws Exception
     */
    public String updateDeviceType(String query) throws Exception {
	
	Connection dbConn = null;
        JSONObject obj = new JSONObject();
        
        try {
            dbConn = DBConnection.createConnection();
            
            Statement stmt = dbConn.createStatement();
            System.out.println(query);
            stmt.executeUpdate(query);
	
            obj.put("tag","update");
            obj.put("msg","done");
            obj.put("status", true);
    
	} catch (SQLException sqle) {
			throw sqle;
	} catch (Exception e) {
            // TODO Auto-generated catch block
            if (dbConn != null) {
		dbConn.close();
            }
            throw e;
	} finally {
            if (dbConn != null) {
		dbConn.close();
            }
	}
	return obj.toJSONString();
    }
    /**
     * Method to check whether uname and pwd combination are correct
     * 
     * @param uname
     * @param pwd
     * @return
     * @throws Exception
     */
    public String deleteDeviceType(String query) throws Exception {
	
	Connection dbConn = null;
        JSONObject obj = new JSONObject();
        
        try {
            dbConn = DBConnection.createConnection();
            
            Statement stmt = dbConn.createStatement();
            System.out.println(query);
            stmt.executeUpdate(query);
	
            obj.put("tag","delete");
            obj.put("msg","done");
            obj.put("status", true);
    
	} catch (SQLException sqle) {
			throw sqle;
	} catch (Exception e) {
            // TODO Auto-generated catch block
            if (dbConn != null) {
		dbConn.close();
            }
            throw e;
	} finally {
            if (dbConn != null) {
		dbConn.close();
            }
	}
	return obj.toJSONString();
    }
        /**
	 * Null check Method
	 * 
	 * @param txt
	 * @return
	 */
	public static boolean isNotNull(String txt) {
		// System.out.println("Inside isNotNull");
		return txt != null && txt.trim().length() >= 0 ? true : false;
	}
        

}
