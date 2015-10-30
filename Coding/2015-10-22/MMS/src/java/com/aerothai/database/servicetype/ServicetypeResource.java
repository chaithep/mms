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
public class ServicetypeResource {
    private String id;

    /**
     * Creates a new instance of ServicetypeResource
     */
    private ServicetypeResource(String id) {
        this.id = id;
    }

    /**
     * Get instance of the ServicetypeResource
     */
    public static ServicetypeResource getInstance(String id) {
        // The servicetype may use some kind of persistence mechanism
        // to store and restore instances of ServicetypeResource class.
        return new ServicetypeResource(id);
    }

    /**
     * Retrieves representation of an instance of com.aerothai.ServicetypeResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String listServicetypeAt(@PathParam("id") int id) {
        String response = null;
        
        System.out.println("List Servicetype At ID");
        try {
            JSONObject servicetypeData = null;
            ServicetypeService servicetypeService = new ServicetypeService();
            servicetypeData = servicetypeService.GetServicetypeAt(id);
            
            response = servicetypeData.toJSONString();
	} catch (Exception e) {
            System.out.println("error");
	}
        
        return response;
    }

    /**
     * PUT method for updating or creating an instance of ServicetypeResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces("application/json")
    public String updateServicetype(@PathParam("id") int id,@FormParam("idservice_type") int idservicetype,
                @FormParam("details") String details,
                @Context HttpServletResponse servletResponse) throws IOException {
        
        String response="";
        ServicetypeService servicetypeService = new ServicetypeService();
        String query = "UPDATE service_type " + "SET"+
                            " details = '"+details+
                            "' WHERE idservice_type = "+id;
        try {
            //System.out.println("PUT Servicetype 11 SQL :"+sqlUpdate);
            response = servicetypeService.updateServicetype(query);
           
        } catch (Exception ex) {
            Logger.getLogger(ServicetypeResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }

    /**
     * DELETE method for resource ServicetypeResource
     */
    @DELETE
    @Produces("application/json")
    public String deleteServicetype(@PathParam("id") int id) {
        
        String response="";
        ServicetypeService servicetypeService = new ServicetypeService();
        String query = "DELETE FROM service_type WHERE idservice_type = "+id;
        try {
            //System.out.println("PUT Servicetype 11 SQL :"+sqlUpdate);
            response = servicetypeService.deleteServicetype(query);
        } catch (Exception ex) {
            Logger.getLogger(ServicetypeResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }
}
