/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerothai.database.jobevaluate;

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
public class JobevaluateResource {
    private String id;

    /**
     * Creates a new instance of JobevaluateResource
     */
    private JobevaluateResource(String id) {
        this.id = id;
    }

    /**
     * Get instance of the JobevaluateResource
     */
    public static JobevaluateResource getInstance(String id) {
        // The jobevaluate may use some kind of persistence mechanism
        // to store and restore instances of JobevaluateResource class.
        return new JobevaluateResource(id);
    }

    /**
     * Retrieves representation of an instance of com.aerothai.JobevaluateResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String listJobevaluateAt(@PathParam("id") int id) {
        String response = null;
        
        System.out.println("List Jobevaluate At ID");
        try {
            JSONObject jobevaluateData = null;
            JobevaluateService jobevaluateService = new JobevaluateService();
            jobevaluateData = jobevaluateService.GetJobevaluateAt(id);
            
            response = jobevaluateData.toJSONString();
	} catch (Exception e) {
            System.out.println("error");
	}
        
        return response;
    }

    /**
     * PUT method for updating or creating an instance of JobevaluateResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces("application/json")
    public String updateJobevaluate(@PathParam("id") int id,@FormParam("idjob_evaluate") int idjob_evaluate,
                @FormParam("idjob") int idjob,@FormParam("staff") int staff,
                @FormParam("service") int service,@FormParam("comment") String comment,
                @Context HttpServletResponse servletResponse) throws IOException {
        
        String response="";
        JobevaluateService jobevaluateService = new JobevaluateService();
        String query = "UPDATE jobevaluate " + "SET"+
                            " idjob = "+idjob+", staff = "+staff+
                            ", service = "+service+", comment = '"+comment+
                            "' WHERE idjob_evaluate = "+id;
        try {
            //System.out.println("PUT Jobevaluate 11 SQL :"+sqlUpdate);
            response = jobevaluateService.updateJobevaluate(query);
           
        } catch (Exception ex) {
            Logger.getLogger(JobevaluateResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }

    /**
     * DELETE method for resource JobevaluateResource
     */
    @DELETE
    @Produces("application/json")
    public String deleteJobevaluate(@PathParam("id") int id) {
        
        String response="";
        JobevaluateService jobevaluateService = new JobevaluateService();
        String query = "DELETE FROM jobevaluate WHERE idjob_evaluate = "+id;
        try {
            //System.out.println("PUT Jobevaluate 11 SQL :"+sqlUpdate);
            response = jobevaluateService.deleteJobevaluate(query);
        } catch (Exception ex) {
            Logger.getLogger(JobevaluateResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }
}
