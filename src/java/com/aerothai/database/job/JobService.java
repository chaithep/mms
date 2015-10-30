/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerothai.database.job;

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
public class JobService {
    public JSONObject GetJobAll(int idunit,String opt)throws Exception {

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
            String query =  "SELECT * FROM job";
            
            if (idunit >0) {
                query =  query+" WHERE idunit ="+idunit;
                if (isNotNull(opt)) query =  query+" AND "+opt;
            } else {
                if (isNotNull(opt)) query =  "SELECT * FROM job WHERE"+opt;
            }    
            
            System.out.println(query);
            ResultSet rs = stmt.executeQuery(query);
            
            while (rs.next()) {
               
                JSONObject jsonData = new JSONObject();
                jsonData.put("id",rs.getInt("idjob"));
                jsonData.put("iduser",rs.getString("iduser"));
                jsonData.put("idunit",rs.getString("idunit"));
                jsonData.put("iddevice",rs.getString("iddevice"));
                jsonData.put("service_method",rs.getString("service_method"));
                jsonData.put("service_type",rs.getString("service_type"));
                jsonData.put("details",rs.getString("details"));
                jsonData.put("action",rs.getString("action"));
                jsonData.put("remark",rs.getString("remark"));
                jsonData.put("idstatus",rs.getString("idstatus"));
                jsonData.put("receive_staff",rs.getString("receive_staff"));
                jsonData.put("action_staff",rs.getString("action_staff"));
                jsonData.put("close_staff",rs.getString("close_staff"));
                jsonData.put("wait_date",rs.getString("wait_date"));
                jsonData.put("receive_date",rs.getString("receive_date"));
                jsonData.put("action_date",rs.getString("action_date"));
                jsonData.put("finish_date",rs.getString("finish_date"));
                jsonData.put("eva_date",rs.getString("eva_date"));
                jsonData.put("close_date",rs.getString("close_date"));;
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
    public JSONObject GetJobAt(int id)throws Exception {
        
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
            String query =  "SELECT * FROM job where idjob = "+id;
            System.out.println(query);
            ResultSet rs = stmt.executeQuery(query);
            
            while (rs.next()) {
                
                JSONObject jsonData = new JSONObject();
                jsonData.put("id",rs.getInt("idjob"));
                jsonData.put("iduser",rs.getString("iduser"));
                jsonData.put("idunit",rs.getString("idunit"));
                jsonData.put("iddevice",rs.getString("iddevice"));
                jsonData.put("service_method",rs.getString("service_method"));
                jsonData.put("service_type",rs.getString("service_type"));
                jsonData.put("details",rs.getString("details"));
                jsonData.put("action",rs.getString("action"));
                jsonData.put("remark",rs.getString("remark"));
                jsonData.put("idstatus",rs.getString("idstatus"));
                jsonData.put("receive_staff",rs.getString("receive_staff"));
                jsonData.put("action_staff",rs.getString("action_staff"));
                jsonData.put("close_staff",rs.getString("close_staff"));
                jsonData.put("wait_date",rs.getString("wait_date"));
                jsonData.put("receive_date",rs.getString("receive_date"));
                jsonData.put("action_date",rs.getString("action_date"));
                jsonData.put("finish_date",rs.getString("finish_date"));
                jsonData.put("eva_date",rs.getString("eva_date"));
                jsonData.put("close_date",rs.getString("close_date"));;
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
    public String createJob(String query) throws Exception {
	
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
    public String updateJob(String query) throws Exception {
	
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
    public String deleteJob(String query) throws Exception {
	
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
