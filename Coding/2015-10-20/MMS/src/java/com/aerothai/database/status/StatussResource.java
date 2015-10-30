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

//Path: http://localhost/<appln-folder-name>/status
@Path("/status")
public class StatussResource {
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of StatussResource
     */
    public StatussResource() {
    }
    
    /**
     * Retrieves representation of an instance of com.aerothai.StatussResource
     * @return an instance of java.lang.String
     */
    @GET
    // Produces JSON as response
    @Produces("application/json")
    //@Produces("application/xml")
    public String listStatus(@QueryParam("column") String column, @QueryParam("value") String value) {
        String response = null;
        String opt = null;
        StatusService statusService = new StatusService();
        System.out.println("List Status All");
        if (statusService.isNotNull(column) && statusService.isNotNull(value) ){
            //System.out.println("column = :"+column+" v =:"+value);
            opt = " "+column+" = '"+value+"'";
        }
        try {
            JSONObject statusData = null;
            
            statusData = statusService.GetStatusAll(opt);
            
            response = statusData.toJSONString();
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
    public String createStatus(@FormParam("idstatus") int idstatus,
                @FormParam("details") String details,
                @Context HttpServletResponse servletResponse) throws IOException {
        String response="";
        StatusService statusService = new StatusService();
        String query = "INSERT INTO status " +
                            "(details)"+
                            "VALUES"+    
                            "('"+details+"')";
        try {
            System.out.println("POST Status 1 SQL :"+query);
            response = statusService.createStatus(query);
        } catch (Exception ex) {
            Logger.getLogger(StatusResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }
    
    /**
     * Sub-resource locator method for {id}
     */
    @Path("{id}")
    public StatusResource getStatusResource(@PathParam("id") String id) {
        return StatusResource.getInstance(id);
    }
}
