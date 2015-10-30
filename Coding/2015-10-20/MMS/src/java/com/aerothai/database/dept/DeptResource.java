/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerothai.database.dept;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import org.json.simple.JSONObject;

/**
 *
 * @author chaia_000
 */
public class DeptResource {
    private String id;

    /**
     * Creates a new instance of DeptResource
     */
    private DeptResource(String id) {
        this.id = id;
    }

    /**
     * Get instance of the DeptResource
     */
    public static DeptResource getInstance(String id) {
        // The dept may use some kind of persistence mechanism
        // to store and restore instances of DeptResource class.
        return new DeptResource(id);
    }

    /**
     * Retrieves representation of an instance of com.aerothai.DeptResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String listDeptAt(@PathParam("id") int id) {
        String response = null;
        
        System.out.println("List Dept At ID");
        try {
            JSONObject deptData = null;
            DeptService deptService = new DeptService();
            deptData = deptService.GetDeptAt(id);
            
            response = deptData.toJSONString();
	} catch (Exception e) {
            System.out.println("error");
	}
        
        return response;
    }

    /**
     * PUT method for updating or creating an instance of DeptResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces("application/json")
    public String updateDept(@PathParam("id") int id,@FormParam("iddept") int iddept,
                @FormParam("name") String name,@FormParam("shortname") String shortname,
                @Context HttpServletResponse servletResponse) throws IOException {
        
        String response="";
        DeptService deptService = new DeptService();
        String query = "UPDATE dept SET"+
                            " name = '"+name+"', shortname = '"+shortname+
                            "' WHERE iddept = "+id;
        try {
            //System.out.println("PUT Dept 11 SQL :"+sqlUpdate);
            response = deptService.updateDept(query);
        } catch (Exception ex) {
            Logger.getLogger(DeptResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }

    /**
     * DELETE method for resource DeptResource
     */
    @DELETE
    @Produces("application/json")
    public String deleteDept(@PathParam("id") int id) {
        
        String response="";
        DeptService deptService = new DeptService();
        String query = "DELETE FROM dept WHERE iddept = "+id;
        try {
            //System.out.println("PUT Dept 11 SQL :"+sqlUpdate);
            response = deptService.deleteDept(query);
        } catch (Exception ex) {
            Logger.getLogger(DeptResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }
}
