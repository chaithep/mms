/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerothai.database.accessory;

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

//Path: http://localhost/<appln-folder-name>/accessory
@Path("/accessory")
public class AccessorysResource {
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AccessorysResource
     */
    public AccessorysResource() {
    }
    
    /**
     * Retrieves representation of an instance of com.aerothai.AccessorysResource
     * @return an instance of java.lang.String
     */
    @GET
    // Produces JSON as response
    @Produces("application/json")
    //@Produces("application/xml")
    public String listAccessory(@QueryParam("idunit") int idunit,@QueryParam("column") String column, @QueryParam("value") String value) {
        String response = null;
        String opt = null;
        AccessoryService accessoryService = new AccessoryService();
        System.out.println("List Accessory All");
        if (accessoryService.isNotNull(column) && accessoryService.isNotNull(value) ){
            //System.out.println("column = :"+column+" v =:"+value);
            opt = " "+column+" = '"+value+"'";
        }
        
        try {
            JSONObject accessoryData = null;
            
            accessoryData = accessoryService.GetAccessoryAll(idunit,opt);
            
            response = accessoryData.toJSONString();
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
    public String createAccessory(@FormParam("idaccessory") int idaccessory,
                 @FormParam("details") String details,@FormParam("idunit") int idunit,
                @FormParam("iddevice_type") int iddevice_type,
                @Context HttpServletResponse servletResponse) throws IOException {
        String response="";
        AccessoryService accessoryService = new AccessoryService();
        String query = "INSERT INTO accessory " +
                            "(details,idunit,iddevice_type)"+
                            "VALUES"+    
                            "('"+details+"',"+idunit+","+iddevice_type+")";
        try {
            System.out.println("POST Accessory 1 SQL :"+query);
            response = accessoryService.createAccessory(query);
        } catch (Exception ex) {
            Logger.getLogger(AccessoryResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }
    
    /**
     * Sub-resource locator method for {id}
     */
    @Path("{id}")
    public AccessoryResource getAccessoryResource(@PathParam("id") String id) {
        return AccessoryResource.getInstance(id);
    }
}
