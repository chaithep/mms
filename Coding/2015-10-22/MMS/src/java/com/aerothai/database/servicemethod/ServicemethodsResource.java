/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerothai.database.servicemethod;

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

//Path: http://localhost/<appln-folder-name>/servicemethod
@Path("/servicemethod")
public class ServicemethodsResource {
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ServicemethodsResource
     */
    public ServicemethodsResource() {
    }
    
    /**
     * Retrieves representation of an instance of com.aerothai.ServicemethodsResource
     * @return an instance of java.lang.String
     */
    @GET
    // Produces JSON as response
    @Produces("application/json")
    //@Produces("application/xml")
    public String listServicemethod(@QueryParam("column") String column, @QueryParam("value") String value) {
        String response = null;
        String opt = null;
        ServicemethodService servicemethodService = new ServicemethodService();
        System.out.println("List Servicemethod All");
        if (servicemethodService.isNotNull(column) && servicemethodService.isNotNull(value) ){
            //System.out.println("column = :"+column+" v =:"+value);
            opt = " "+column+" = '"+value+"'";
        }
        
        try {
            JSONObject servicemethodData = null;
            
            servicemethodData = servicemethodService.GetServicemethodAll(opt);
            
            response = servicemethodData.toJSONString();
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
    public String createServicemethod(@FormParam("idservice_method") int idservice_method,
                 @FormParam("details") String details,
                @Context HttpServletResponse servletResponse) throws IOException {
        String response="";
        ServicemethodService servicemethodService = new ServicemethodService();
        String query = "INSERT INTO service_method " +
                            "(details)"+
                            "VALUES"+    
                            "('"+details+"')";
        try {
            System.out.println("POST Servicemethod 1 SQL :"+query);
            response = servicemethodService.createServicemethod(query);
        } catch (Exception ex) {
            Logger.getLogger(ServicemethodResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }
    
    /**
     * Sub-resource locator method for {id}
     */
    @Path("{id}")
    public ServicemethodResource getServicemethodResource(@PathParam("id") String id) {
        return ServicemethodResource.getInstance(id);
    }
}
