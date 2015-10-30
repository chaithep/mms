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

//Path: http://localhost/<appln-folder-name>/jobevaluate
@Path("/jobevaluate")
public class JobevaluatesResource {
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of JobevaluatesResource
     */
    public JobevaluatesResource() {
    }
    
    /**
     * Retrieves representation of an instance of com.aerothai.JobevaluatesResource
     * @return an instance of java.lang.String
     */
    @GET
    // Produces JSON as response
    @Produces("application/json")
    //@Produces("application/xml")
    public String listJobevaluate(@QueryParam("idjob") int idjob,@QueryParam("column") String column, @QueryParam("value") String value) {
        String response = null;
        String opt = null;
        JobevaluateService jobevaluateService = new JobevaluateService();
        System.out.println("List Jobevaluate All");
        if (jobevaluateService.isNotNull(column) && jobevaluateService.isNotNull(value) ){
            //System.out.println("column = :"+column+" v =:"+value);
            opt = " "+column+" = '"+value+"'";
        }
        
        try {
            JSONObject jobevaluateData = null;
            
            jobevaluateData = jobevaluateService.GetJobevaluateAll(idjob,opt);
            
            response = jobevaluateData.toJSONString();
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
    public String createJobevaluate(@FormParam("idjob_evaluate") int idjob_evaluate,
                @FormParam("idjob") int idjob,@FormParam("staff") int staff,
                @FormParam("service") int service,@FormParam("comment") String comment,
                @Context HttpServletResponse servletResponse) throws IOException {
        String response="";
        JobevaluateService jobevaluateService = new JobevaluateService();
        String query = "INSERT INTO job_evaluate " +
                            "(idjob,staff,filename,menu,action,addjob)"+
                            "VALUES"+    
                            "("+idjob+","+staff+","+service+",'"+comment+
                            "')";
        try {
            System.out.println("POST Jobevaluate 1 SQL :"+query);
            response = jobevaluateService.createJobevaluate(query);
        } catch (Exception ex) {
            Logger.getLogger(JobevaluateResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }
    
    /**
     * Sub-resource locator method for {id}
     */
    @Path("{id}")
    public JobevaluateResource getJobevaluateResource(@PathParam("id") String id) {
        return JobevaluateResource.getInstance(id);
    }
}
