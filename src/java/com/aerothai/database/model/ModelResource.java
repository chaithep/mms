/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerothai.database.model;

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
public class ModelResource {
    private String id;

    /**
     * Creates a new instance of ModelResource
     */
    private ModelResource(String id) {
        this.id = id;
    }

    /**
     * Get instance of the ModelResource
     */
    public static ModelResource getInstance(String id) {
        // The model may use some kind of persistence mechanism
        // to store and restore instances of ModelResource class.
        return new ModelResource(id);
    }

    /**
     * Retrieves representation of an instance of com.aerothai.ModelResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String listModelAt(@PathParam("id") int id) {
        String response = null;
        
        System.out.println("List Model At ID");
        try {
            JSONObject modelData = null;
            ModelService modelService = new ModelService();
            modelData = modelService.GetModelAt(id);
            
            response = modelData.toJSONString();
	} catch (Exception e) {
            System.out.println("error");
	}
        
        return response;
    }

    /**
     * PUT method for updating or creating an instance of ModelResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces("application/json")
    public String updateModel(@PathParam("id") int id,@FormParam("idmodel") int idmodel,
                @FormParam("details") String details,@FormParam("idunit") int idunit,
                @FormParam("iddevice_type") int iddevice_type,
                @Context HttpServletResponse servletResponse) throws IOException {
        
        String response="";
        ModelService modelService = new ModelService();
        String query = "UPDATE model " + "SET"+
                            " details = '"+details+"', idunit = "+idunit+
                            ", iddevice_type = "+iddevice_type+
                            " WHERE idmodel = "+id;
        try {
            //System.out.println("PUT Model 11 SQL :"+sqlUpdate);
            response = modelService.updateModel(query);
           
        } catch (Exception ex) {
            Logger.getLogger(ModelResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }

    /**
     * DELETE method for resource ModelResource
     */
    @DELETE
    @Produces("application/json")
    public String deleteModel(@PathParam("id") int id) {
        
        String response="";
        ModelService modelService = new ModelService();
        String query = "DELETE FROM model WHERE idmodel = "+id;
        try {
            //System.out.println("PUT Model 11 SQL :"+sqlUpdate);
            response = modelService.deleteModel(query);
        } catch (Exception ex) {
            Logger.getLogger(ModelResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }
}
