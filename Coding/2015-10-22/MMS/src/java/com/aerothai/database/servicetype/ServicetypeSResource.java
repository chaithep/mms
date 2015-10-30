/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerothai.database.servicetype;

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

//Path: http://localhost/<appln-folder-name>/servicetype
@Path("/servicetype")
public class ServicetypeSResource {
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ServicetypeSResource
     */
    public ServicetypeSResource() {
    }
    
    /**
     * Retrieves representation of an instance of com.aerothai.ServicetypeSResource
     * @return an instance of java.lang.String
     */
    @GET
    // Produces JSON as response
    @Produces("application/json")
    //@Produces("application/xml")
    public String listServicetype(@QueryParam("column") String column, @QueryParam("value") String value) {
        String response = null;
        String opt = null;
        ServicetypeService servicetypeService = new ServicetypeService();
        System.out.println("List Servicetype All");
        if (servicetypeService.isNotNull(column) && servicetypeService.isNotNull(value) ){
            //System.out.println("column = :"+column+" v =:"+value);
            opt = " "+column+" = '"+value+"'";
        }
        
        try {
            JSONObject servicetypeData = null;
            
            servicetypeData = servicetypeService.GetServicetypeAll(opt);
            
            response = servicetypeData.toJSONString();
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
    public String createServicetype(@FormParam("idservice_type") int idservicetype,
                 @FormParam("details") String details,
                @Context HttpServletResponse servletResponse) throws IOException {
        String response="";
        ServicetypeService servicetypeService = new ServicetypeService();
        String query = "INSERT INTO service_type " +
                            "(details)"+
                            "VALUES"+    
                            "('"+details+"')";
        try {
            System.out.println("POST Servicetype 1 SQL :"+query);
            response = servicetypeService.createServicetype(query);
        } catch (Exception ex) {
            Logger.getLogger(ServicetypeResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }
    
    /**
     * Sub-resource locator method for {id}
     */
    @Path("{id}")
    public ServicetypeResource getServicetypeResource(@PathParam("id") String id) {
        return ServicetypeResource.getInstance(id);
    }
}
