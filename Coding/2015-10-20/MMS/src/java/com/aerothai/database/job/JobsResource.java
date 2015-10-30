/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerothai.database.job;

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

//Path: http://localhost/<appln-folder-name>/job
@Path("/job")
public class JobsResource {
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of JobsResource
     */
    public JobsResource() {
    }
    
    /**
     * Retrieves representation of an instance of com.aerothai.JobsResource
     * @return an instance of java.lang.String
     */
    @GET
    // Produces JSON as response
    @Produces("application/json")
    //@Produces("application/xml")
    public String listJob(@QueryParam("idunit") int idunit,@QueryParam("column") String column, @QueryParam("value") String value) {
        String response = null;
        String opt = null;
        JobService jobService = new JobService();
        System.out.println("List Job All");
        if (jobService.isNotNull(column) && jobService.isNotNull(value) ){
            //System.out.println("column = :"+column+" v =:"+value);
            opt = " "+column+" = '"+value+"'";
        }
        
        try {
            JSONObject jobData = null;
            
            jobData = jobService.GetJobAll(idunit,opt);
            
            response = jobData.toJSONString();
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
    public String createJob(@FormParam("idjob") int idjob,
                @FormParam("iduser") int iduser,@FormParam("idunit") int idunit,
                @FormParam("iddevice") int iddevice,@FormParam("service_method") int service_method,
                @FormParam("service_type") int service_type,@FormParam("details") String details,
                @FormParam("action") String action,@FormParam("remark") String remark,
                @FormParam("idstatus") int idstatus,@FormParam("receive_staff") String receive_staff,
                @FormParam("action_staff") String action_staff,@FormParam("close_staff") String close_staff,
                @FormParam("wait_date") String wait_date,@FormParam("receive_date") String receive_date,
                @FormParam("action_date") String action_date,@FormParam("finish_date") String finish_date,
                @FormParam("eva_date") String eva_date,@FormParam("close_date") String close_date,
                @Context HttpServletResponse servletResponse) throws IOException {
        String response="";
        JobService jobService = new JobService();
        String query = "INSERT INTO job " +
                            "(iduser,idunit,iddevice,service_method,service_type,idstatus,"+
                            "details,action,remark,receive_staff,action_staff,close_staff,"+
                            "wait_date,receive_date,action_date,finish_date,eva_date,close_date)"+
                            "VALUES"+    
                            "("+iduser+","+idunit+","+iddevice+","+service_method+","+service_type+","+idstatus+
                            ",'"+details+"','"+action+"','"+remark+"','"+receive_staff+
                            "','"+action_staff+"','"+close_staff+"','"+wait_date+"','"+receive_date+
                            "','"+action_date+"','"+finish_date+"','"+eva_date+"','"+close_date+"')";
        try {
            System.out.println("POST Job 1 SQL :"+query);
            response = jobService.createJob(query);
        } catch (Exception ex) {
            Logger.getLogger(JobResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }
    
    /**
     * Sub-resource locator method for {id}
     */
    @Path("{id}")
    public JobResource getJobResource(@PathParam("id") String id) {
        return JobResource.getInstance(id);
    }
}
