/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerothai.database.user;

import com.aerothai.database.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author chaia_000
 */
public class UserService {
    public JSONObject GetUserAll(String role,String opt)throws Exception {
        
        ArrayList<UserObj> feeds = new ArrayList<UserObj>();
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
            String query =  "SELECT * ,"
                    +"(SELECT dept.name FROM dept where dept.iddept=user.iddept) as shdept,"
                    +"(SELECT unit.name FROM unit where unit.idunit=user.idunit) as shunit,"
                    +"(SELECT role.name FROM role where role.idrole=user.idrole) as shrole"
                    +" FROM user";
           
            if (!role.equals("0")) {
                query =  query+" WHERE idrole ="+role;
                if (isNotNull(opt)) query =  query+" AND "+opt;
            } else {
                if (isNotNull(opt)) query =  query+" WHERE "+opt;
            }
            System.out.println(query);
            ResultSet rs = stmt.executeQuery(query);
            
            while (rs.next()) {
                
                JSONObject jsonData = new JSONObject();
                jsonData.put("iduser",rs.getInt("iduser"));
                jsonData.put("name",rs.getString("name"));
		jsonData.put("lastname",rs.getString("lastname"));
                jsonData.put("idposition",rs.getInt("idposition"));
                jsonData.put("iddept",rs.getInt("iddept"));;
                jsonData.put("idunit",rs.getInt("idunit"));
                jsonData.put("idrole",rs.getInt("idrole"));
                jsonData.put("address",rs.getString("address"));
                jsonData.put("email",rs.getString("email"));
                jsonData.put("tel",rs.getString("tel"));
                jsonData.put("actunit",rs.getString("actunit"));
                jsonData.put("username",rs.getString("username"));
                jsonData.put("photo",rs.getString("photo"));
                jsonData.put("actcust",rs.getString("actcust"));
                //jsonData.put("namedept",rs.getString("dept.name"));
                //jsonData.put("shortdept",rs.getString("shortname"));
                jsonData.put("shdept",rs.getString("shdept"));
                jsonData.put("shunit",rs.getString("shunit"));
                jsonData.put("shrole",rs.getString("shrole"));
                
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
    public JSONObject GetUserAt(int id)throws Exception {
        
        ArrayList<UserObj> feeds = new ArrayList<UserObj>();
        Connection dbConn = null;
        JSONObject obj = new JSONObject();
        JSONArray objList = new JSONArray();
        
        int no =1;
        obj.put("tag","listat");
        obj.put("msg","error");
        obj.put("status", false);
	try {
            dbConn = DBConnection.createConnection();
            
            Statement stmt = dbConn.createStatement();
            String query =  "SELECT * FROM user,dept where user.iddept = dept.iddept AND user.iduser = "+id;
            System.out.println(query);
            ResultSet rs = stmt.executeQuery(query);
            
            while (rs.next()) {
                
                JSONObject jsonData = new JSONObject();
                jsonData.put("iduser",rs.getInt("iduser"));
                jsonData.put("name",rs.getString("name"));
		jsonData.put("lastname",rs.getString("lastname"));
                jsonData.put("idposition",rs.getInt("idposition"));
                jsonData.put("iddept",rs.getInt("iddept"));;
                jsonData.put("idunit",rs.getInt("idunit"));
                jsonData.put("idrole",rs.getInt("idrole"));
                jsonData.put("address",rs.getString("address"));
                jsonData.put("email",rs.getString("email"));
                jsonData.put("tel",rs.getString("tel"));
                jsonData.put("actunit",rs.getString("actunit"));
                jsonData.put("username",rs.getString("username"));
                jsonData.put("namedept",rs.getString("dept.name"));
                jsonData.put("shortdept",rs.getString("shortname"));
                jsonData.put("photo",rs.getString("photo"));
                jsonData.put("actcust",rs.getString("actcust"));
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
    public static String checkLogin(String uname, String pwd) throws Exception {
	boolean isUserAvailable = false;
	Connection dbConn = null;
        JSONObject obj = new JSONObject();
        JSONObject jsonData = new JSONObject();
        try {
            dbConn = DBConnection.createConnection();
            
            Statement stmt = dbConn.createStatement();
            String query = "SELECT * FROM user WHERE username = '" + uname
					+ "' AND pwd=" + "'" + pwd + "'";
            System.out.println(query);
            ResultSet rs = stmt.executeQuery(query);
		
            while (rs.next()) {
                    //System.out.println(rs.getString(1) + rs.getString(2) + rs.getString(3));
                isUserAvailable = true;
                //JSONObject jsonData = new JSONObject();
                jsonData.put("iduser",rs.getInt("iduser"));
                jsonData.put("name",rs.getString("name"));
		jsonData.put("lastname",rs.getString("lastname"));
                jsonData.put("idposition",rs.getInt("idposition"));
                jsonData.put("iddept",rs.getInt("iddept"));
                jsonData.put("idunit",rs.getInt("idunit"));
                jsonData.put("idrole",rs.getInt("idrole"));
                jsonData.put("address",rs.getString("address"));
                jsonData.put("email",rs.getString("email"));
                jsonData.put("tel",rs.getString("tel"));
                jsonData.put("actunit",rs.getString("actunit"));
                jsonData.put("username",rs.getString("username"));
                jsonData.put("photo",rs.getString("photo"));
                jsonData.put("actcust",rs.getString("actcust"));
                //objList.add(rs.getString("pwd"));
		
            }
            if (isUserAvailable) {
                
                obj.put("tag","login");
                obj.put("msg","done");
                obj.put("status", true);
                obj.put("data", jsonData.toJSONString());
            } else {
                obj.put("tag","login");
                obj.put("msg","Incorrect Email or Password");
                obj.put("status", false);
            }
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
    public String createUser(String query) throws Exception {
	
	Connection dbConn = null;
        JSONObject obj = new JSONObject();
        obj.put("tag","create");
        obj.put("msg","error");
        obj.put("status", false);
        try {
            dbConn = DBConnection.createConnection();
            
            Statement stmt = dbConn.createStatement();
            System.out.println(query);
            stmt.executeUpdate(query);
	
            //obj.put("tag","create");
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
    public String updateUser(String query) throws Exception {
	
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
    public String deleteUser(String query) throws Exception {
	
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
	public boolean isNotNull(String txt) {
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
