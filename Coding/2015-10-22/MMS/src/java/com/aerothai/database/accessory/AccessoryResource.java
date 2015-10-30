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
public class AccessoryResource {
    private String id;

    /**
     * Creates a new instance of AccessoryResource
     */
    private AccessoryResource(String id) {
        this.id = id;
    }

    /**
     * Get instance of the AccessoryResource
     */
    public static AccessoryResource getInstance(String id) {
        // The accessory may use some kind of persistence mechanism
        // to store and restore instances of AccessoryResource class.
        return new AccessoryResource(id);
    }

    /**
     * Retrieves representation of an instance of com.aerothai.AccessoryResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String listAccessoryAt(@PathParam("id") int id) {
        String response = null;
        
        System.out.println("List Accessory At ID");
        try {
            JSONObject accessoryData = null;
            AccessoryService accessoryService = new AccessoryService();
            accessoryData = accessoryService.GetAccessoryAt(id);
            
            response = accessoryData.toJSONString();
	} catch (Exception e) {
            System.out.println("error");
	}
        
        return response;
    }

    /**
     * PUT method for updating or creating an instance of AccessoryResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces("application/json")
    public String updateAccessory(@PathParam("id") int id,@FormParam("idaccessory") int idaccessory,
                @FormParam("details") String details,@FormParam("idunit") int idunit,
                @FormParam("iddevice_type") int iddevice_type,
                @Context HttpServletResponse servletResponse) throws IOException {
        
        String response="";
        AccessoryService accessoryService = new AccessoryService();
        String query = "UPDATE accessory " + "SET"+
                            " details = '"+details+"', idunit = "+idunit+
                            ", iddevice_type = "+iddevice_type+
                            " WHERE idaccessory = "+id;
        try {
            //System.out.println("PUT Accessory 11 SQL :"+sqlUpdate);
            response = accessoryService.updateAccessory(query);
           
        } catch (Exception ex) {
            Logger.getLogger(AccessoryResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }

    /**
     * DELETE method for resource AccessoryResource
     */
    @DELETE
    @Produces("application/json")
    public String deleteAccessory(@PathParam("id") int id) {
        
        String response="";
        AccessoryService accessoryService = new AccessoryService();
        String query = "DELETE FROM accessory WHERE idaccessory = "+id;
        try {
            //System.out.println("PUT Accessory 11 SQL :"+sqlUpdate);
            response = accessoryService.deleteAccessory(query);
        } catch (Exception ex) {
            Logger.getLogger(AccessoryResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }
}
