/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerothai.database.role;

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
public class RoleResource {
    private String id;

    /**
     * Creates a new instance of RoleResource
     */
    private RoleResource(String id) {
        this.id = id;
    }

    /**
     * Get instance of the RoleResource
     */
    public static RoleResource getInstance(String id) {
        // The role may use some kind of persistence mechanism
        // to store and restore instances of RoleResource class.
        return new RoleResource(id);
    }

    /**
     * Retrieves representation of an instance of com.aerothai.RoleResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String listRoleAt(@PathParam("id") int id) {
        String response = null;
        
        System.out.println("List Role At ID");
        try {
            JSONObject roleData = null;
            RoleService roleService = new RoleService();
            roleData = roleService.GetRoleAt(id);
            
            response = roleData.toJSONString();
	} catch (Exception e) {
            System.out.println("error");
	}
        
        return response;
    }

    /**
     * PUT method for updating or creating an instance of RoleResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces("application/json")
    public String updateRole(@PathParam("id") int id,@FormParam("idrole") int idrole,
                @FormParam("name") String name,
                @Context HttpServletResponse servletResponse) throws IOException {
        
        String response="";
        RoleService roleService = new RoleService();
        String query = "UPDATE role " + "SET"+
                            " name = '"+name+
                            "' WHERE idrole = "+id;
        try {
            //System.out.println("PUT Role 11 SQL :"+sqlUpdate);
            response = roleService.updateRole(query);
           
        } catch (Exception ex) {
            Logger.getLogger(RoleResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }

    /**
     * DELETE method for resource RoleResource
     */
    @DELETE
    @Produces("application/json")
    public String deleteRole(@PathParam("id") int id) {
        
        String response="";
        RoleService roleService = new RoleService();
        String query = "DELETE FROM role WHERE idrole = "+id;
        try {
            //System.out.println("PUT Role 11 SQL :"+sqlUpdate);
            response = roleService.deleteRole(query);
        } catch (Exception ex) {
            Logger.getLogger(RoleResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }
}
