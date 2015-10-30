/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerothai.database.status;

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
public class StatusResource {
    private String id;

    /**
     * Creates a new instance of StatusResource
     */
    private StatusResource(String id) {
        this.id = id;
    }

    /**
     * Get instance of the StatusResource
     */
    public static StatusResource getInstance(String id) {
        // The status may use some kind of persistence mechanism
        // to store and restore instances of StatusResource class.
        return new StatusResource(id);
    }

    /**
     * Retrieves representation of an instance of com.aerothai.StatusResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String listStatusAt(@PathParam("id") int id) {
        String response = null;
        
        System.out.println("List Status At ID");
        try {
            JSONObject statusData = null;
            StatusService statusService = new StatusService();
            statusData = statusService.GetStatusAt(id);
            
            response = statusData.toJSONString();
	} catch (Exception e) {
            System.out.println("error");
	}
        
        return response;
    }

    /**
     * PUT method for updating or creating an instance of StatusResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces("application/json")
    public String updateStatus(@PathParam("id") int id,@FormParam("idstatus") int idstatus,
                @FormParam("details") String details,
                @Context HttpServletResponse servletResponse) throws IOException {
        
        String response="";
        StatusService statusService = new StatusService();
        String query = "UPDATE status " + "SET"+
                            " details = '"+details+
                            "' WHERE idstatus = "+id;
        try {
            //System.out.println("PUT Status 11 SQL :"+sqlUpdate);
            response = statusService.updateStatus(query);
           
        } catch (Exception ex) {
            Logger.getLogger(StatusResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }

    /**
     * DELETE method for resource StatusResource
     */
    @DELETE
    @Produces("application/json")
    public String deleteStatus(@PathParam("id") int id) {
        
        String response="";
        StatusService statusService = new StatusService();
        String query = "DELETE FROM status WHERE idstatus = "+id;
        try {
            //System.out.println("PUT Status 11 SQL :"+sqlUpdate);
            response = statusService.deleteStatus(query);
        } catch (Exception ex) {
            Logger.getLogger(StatusResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }
}
