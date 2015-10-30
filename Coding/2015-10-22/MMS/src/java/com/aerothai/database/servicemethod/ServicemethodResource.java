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
public class ServicemethodResource {
    private String id;

    /**
     * Creates a new instance of ServicemethodResource
     */
    private ServicemethodResource(String id) {
        this.id = id;
    }

    /**
     * Get instance of the ServicemethodResource
     */
    public static ServicemethodResource getInstance(String id) {
        // The servicemethod may use some kind of persistence mechanism
        // to store and restore instances of ServicemethodResource class.
        return new ServicemethodResource(id);
    }

    /**
     * Retrieves representation of an instance of com.aerothai.ServicemethodResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String listServicemethodAt(@PathParam("id") int id) {
        String response = null;
        
        System.out.println("List Servicemethod At ID");
        try {
            JSONObject servicemethodData = null;
            ServicemethodService servicemethodService = new ServicemethodService();
            servicemethodData = servicemethodService.GetServicemethodAt(id);
            
            response = servicemethodData.toJSONString();
	} catch (Exception e) {
            System.out.println("error");
	}
        
        return response;
    }

    /**
     * PUT method for updating or creating an instance of ServicemethodResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces("application/json")
    public String updateServicemethod(@PathParam("id") int id,@FormParam("idservice_method") int idservice_method,
                @FormParam("details") String details,
                @Context HttpServletResponse servletResponse) throws IOException {
        
        String response="";
        ServicemethodService servicemethodService = new ServicemethodService();
        String query = "UPDATE service_method " + "SET"+
                            " details = '"+details+
                            "' WHERE idservice_method = "+id;
        try {
            //System.out.println("PUT Servicemethod 11 SQL :"+sqlUpdate);
            response = servicemethodService.updateServicemethod(query);
           
        } catch (Exception ex) {
            Logger.getLogger(ServicemethodResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }

    /**
     * DELETE method for resource ServicemethodResource
     */
    @DELETE
    @Produces("application/json")
    public String deleteServicemethod(@PathParam("id") int id) {
        
        String response="";
        ServicemethodService servicemethodService = new ServicemethodService();
        String query = "DELETE FROM service_method WHERE idservice_method = "+id;
        try {
            //System.out.println("PUT Servicemethod 11 SQL :"+sqlUpdate);
            response = servicemethodService.deleteServicemethod(query);
        } catch (Exception ex) {
            Logger.getLogger(ServicemethodResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }
}
