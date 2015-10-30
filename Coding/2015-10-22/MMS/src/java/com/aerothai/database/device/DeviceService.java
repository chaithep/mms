/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerothai.database.device;

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
public class DeviceService {
    public JSONObject GetDeviceAll(int idunit,String opt)throws Exception {

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
            String query =  "SELECT * FROM device";
            
            if (idunit >0) {
                query =  query+" WHERE idunit ="+idunit;
                if (isNotNull(opt)) query =  query+" AND "+opt;
            } else {
                if (isNotNull(opt)) query =  "SELECT * FROM device WHERE"+opt;
            }    
            
            System.out.println(query);
            ResultSet rs = stmt.executeQuery(query);
            
            while (rs.next()) {
               
                JSONObject jsonData = new JSONObject();
                jsonData.put("id",rs.getInt("iddevice"));
                jsonData.put("idunit",rs.getString("idunit"));
                jsonData.put("iddevice_type",rs.getString("iddevice_type"));
                jsonData.put("idmodel",rs.getString("idmodel"));
                jsonData.put("idaccessory",rs.getString("idaccessory"));
                jsonData.put("asset_no",rs.getString("asset_no"));
                jsonData.put("serial_no",rs.getString("serial_no"));
                jsonData.put("ip_address",rs.getString("ip_address"));
                jsonData.put("mac_address",rs.getString("mac_address"));
                jsonData.put("computer_name",rs.getString("computer_name"));
                jsonData.put("os",rs.getString("os"));
                jsonData.put("firmware",rs.getString("firmware"));
                jsonData.put("vendor",rs.getString("vendor"));
                jsonData.put("customer",rs.getString("customer"));
                jsonData.put("address",rs.getString("address"));
                jsonData.put("location",rs.getString("location"));
                jsonData.put("insert_date",rs.getString("insert_date"));
                jsonData.put("receive_date",rs.getString("receive_date"));
                jsonData.put("exp_date",rs.getString("exp_date"));
                jsonData.put("image",rs.getString("image"));
                jsonData.put("id_door",rs.getString("id_door"));
                jsonData.put("in_out_side",rs.getString("in_out_side"));
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
    public JSONObject GetDeviceAt(int id)throws Exception {
        
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
            String query =  "SELECT * FROM device where iddevice = "+id;
            System.out.println(query);
            ResultSet rs = stmt.executeQuery(query);
            
            while (rs.next()) {
                
                JSONObject jsonData = new JSONObject();
                jsonData.put("id",rs.getInt("iddevice"));
                jsonData.put("idunit",rs.getString("idunit"));
                jsonData.put("iddevice_type",rs.getString("iddevice_type"));
                jsonData.put("idmodel",rs.getString("idmodel"));
                jsonData.put("idaccessory",rs.getString("idaccessory"));
                jsonData.put("asset_no",rs.getString("asset_no"));
                jsonData.put("serial_no",rs.getString("serial_no"));
                jsonData.put("ip_address",rs.getString("ip_address"));
                jsonData.put("mac_address",rs.getString("mac_address"));
                jsonData.put("computer_name",rs.getString("computer_name"));
                jsonData.put("os",rs.getString("os"));
                jsonData.put("firmware",rs.getString("firmware"));
                jsonData.put("vendor",rs.getString("vendor"));
                jsonData.put("customer",rs.getString("customer"));
                jsonData.put("address",rs.getString("address"));
                jsonData.put("location",rs.getString("location"));
                jsonData.put("insert_date",rs.getString("insert_date"));
                jsonData.put("receive_date",rs.getString("receive_date"));
                jsonData.put("exp_date",rs.getString("exp_date"));
                jsonData.put("image",rs.getString("image"));
                jsonData.put("id_door",rs.getString("id_door"));
                jsonData.put("in_out_side",rs.getString("in_out_side"));
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
    public String createDevice(String query) throws Exception {
	
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
    public String updateDevice(String query) throws Exception {
	
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
    public String deleteDevice(String query) throws Exception {
	
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
        /**
	 * Method to construct JSON with Error Msg
	 * 
	 * @param tag
	 * @param status
	 * @param err_msg
	 * @return
	 */
	public static String constructJSON(String tag, boolean status,String msg) {
		JSONObject obj = new JSONObject();
		
			obj.put("tag", tag);
			obj.put("status", new Boolean(status));
			obj.put("msg", msg);
		
		return obj.toString(); 
	}
}
