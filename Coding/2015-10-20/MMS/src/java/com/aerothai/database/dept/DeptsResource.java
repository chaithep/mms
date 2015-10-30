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
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import org.json.simple.JSONObject;

/**
 *
 * @author chaia_000
 */
//Path: http://localhost/<appln-folder-name>/dept
@Path("/dept")
public class DeptsResource {
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of DeptsResource
     */
    public DeptsResource() {
    }
    
    /**
     * Retrieves representation of an instance of com.aerothai.DeptsResource
     * @return an instance of java.lang.String
     */
    @GET
    // Produces JSON as response
    @Produces("application/json")
    //@Produces("application/xml")
    public String listDept() {
        String response = null;
        
        System.out.println("List Dept All");
        try {
            JSONObject deptData = null;
            DeptService deptService = new DeptService();
            deptData = deptService.GetDeptAll();
            
            response = deptData.toJSONString();
	} catch (Exception e) {
            System.out.println("error");
	}
        
        return response;
    }

    /**
     * POST method for creating an instance of ItemResource
     * @param content representation for the new resource
     * @return an HTTP response with content of the created resource
     */
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces("application/json")
    public String createDept(@FormParam("iddept") int iddept,
                @FormParam("name") String name,@FormParam("shortname") String shortname,
                @Context HttpServletResponse servletResponse) throws IOException {
        String response="";
        DeptService deptService = new DeptService();
        String query = "INSERT INTO dept " +
                            "(name,shortname)"+
                            "VALUES"+    
                            "('"+name+"','"+shortname+
                            "')";
        try {
            System.out.println("POST Dept 1 SQL :"+query);
            response = deptService.createDept(query);
        } catch (Exception ex) {
            Logger.getLogger(DeptResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }
    
      
    /**
     * Sub-resource locator method for {id}
     */
    @Path("{id}")
    public DeptResource getDeptResource(@PathParam("id") String id) {
        return DeptResource.getInstance(id);
    }
}
