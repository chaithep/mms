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
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import org.json.simple.JSONObject;

/**
 *
 * @author chaia_000
 */
//Path: http://localhost/<appln-folder-name>/role
@Path("/role")
public class RolesResource {
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of RolesResource
     */
    public RolesResource() {
    }
    
    /**
     * Retrieves representation of an instance of com.aerothai.RolesResource
     * @return an instance of java.lang.String
     */
    @GET
    // Produces JSON as response
    @Produces("application/json")
    //@Produces("application/xml")
    public String listRole(@QueryParam("column") String column, @QueryParam("value") String value) {
        String response = null;
        String opt = null;
        RoleService roleService = new RoleService();
        System.out.println("List Role All");
        if (roleService.isNotNull(column) && roleService.isNotNull(value) ){
            //System.out.println("column = :"+column+" v =:"+value);
            opt = " "+column+" = '"+value+"'";
        }
        try {
            JSONObject roleData = null;
            
            roleData = roleService.GetRoleAll(opt);
            
            response = roleData.toJSONString();
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
    public String createRole(@FormParam("idrole") int idrole,
                @FormParam("name") String name,
                @Context HttpServletResponse servletResponse) throws IOException {
        String response="";
        RoleService roleService = new RoleService();
        String query = "INSERT INTO role " +
                            "(name)"+
                            "VALUES"+    
                            "('"+name+"')";
        try {
            System.out.println("POST Role 1 SQL :"+query);
            response = roleService.createRole(query);
        } catch (Exception ex) {
            Logger.getLogger(RoleResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }
    
    /**
     * Sub-resource locator method for {id}
     */
    @Path("{id}")
    public RoleResource getRoleResource(@PathParam("id") String id) {
        return RoleResource.getInstance(id);
    }
}
