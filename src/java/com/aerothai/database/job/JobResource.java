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
public class JobResource {
private String id;

    /**
     * Creates a new instance of JobResource
     */
    private JobResource(String id) {
        this.id = id;
    }

    /**
     * Get instance of the JobResource
     */
    public static JobResource getInstance(String id) {
        // The job may use some kind of persistence mechanism
        // to store and restore instances of JobResource class.
        return new JobResource(id);
    }

    /**
     * Retrieves representation of an instance of com.aerothai.JobResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String listJobAt(@PathParam("id") int id) {
        String response = null;
        
        System.out.println("List Job At ID");
        try {
            JSONObject jobData = null;
            JobService jobService = new JobService();
            jobData = jobService.GetJobAt(id);
            
            response = jobData.toJSONString();
	} catch (Exception e) {
            System.out.println("error");
	}
        
        return response;
    }

    /**
     * PUT method for updating or creating an instance of JobResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces("application/json")
    public String updateJob(@PathParam("id") int id,@FormParam("idjob") int idjob,
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
        System.out.println("Put job At ID :"+id);
        JobService jobService = new JobService();
        int i =0;
        String query = "UPDATE job " + "SET";
        if (iduser > 0) {
            if (i>0) query += ",";
            i++;
            query += " iduser = "+iduser;
        }
         if (idunit > 0) {
            if (i>0) query += ",";
            i++;
            query += " idunit = "+idunit;
        }
        if (iddevice > 0) {
            if (i>0) query += ",";
            i++;
            query += " iddevice = "+iddevice;
        }
        if (service_method > 0) {
            if (i>0) query += ",";
            i++;
            query += " service_method = "+service_method;
        }
        if (service_type > 0) {
            if (i>0) query += ",";
            i++;
            query += " service_type = "+service_type;
        }
        if (idstatus > 0) {
            if (i>0) query += ",";
            i++;
            query += " idstatus = "+idstatus;
        }
        if (jobService.isNotNull(details) && (!details.isEmpty())) { 
            query += " details = '"+details+"'";
            i++;
        }
        if (jobService.isNotNull(action) && (!action.isEmpty())) { 
            query += " action = '"+action+"'";
            i++;
        }
        if (jobService.isNotNull(remark) && (!remark.isEmpty())) { 
            query += " remark = '"+remark+"'";
            i++;
        }
        if (jobService.isNotNull(receive_staff) && (!receive_staff.isEmpty())) { 
            query += " receive_staff = '"+receive_staff+"'";
            i++;
        }
        if (jobService.isNotNull(action_staff) && (!action_staff.isEmpty())) { 
            query += " action_staff = '"+action_staff+"'";
            i++;
        }
        if (jobService.isNotNull(close_staff) && (!close_staff.isEmpty())) { 
            query += " close_staff = '"+close_staff+"'";
            i++;
        }
        if (jobService.isNotNull(wait_date) && (!wait_date.isEmpty())) { 
            query += " wait_date = '"+wait_date+"'";
            i++;
        }
        if (jobService.isNotNull(receive_date) && (!receive_date.isEmpty())) { 
            query += " receive_date = '"+receive_date+"'";
            i++;
        }
        if (jobService.isNotNull(action_date) && (!action_date.isEmpty())) { 
            query += " action_date = '"+action_date+"'";
            i++;
        }
        if (jobService.isNotNull(finish_date) && (!finish_date.isEmpty())) { 
            query += " finish_date = '"+finish_date+"'";
            i++;
        }
        if (jobService.isNotNull(eva_date) && (!eva_date.isEmpty())) { 
            query += " eva_date = '"+eva_date+"'";
            i++;
        }
        if (jobService.isNotNull(close_date) && (!close_date.isEmpty())) { 
            query += " close_date = '"+close_date+"'";
            i++;
        }
        query += " WHERE idjob = "+id;    
        System.out.println("PUT job 10 SQL :"+query);
        try {
            //System.out.println("PUT Job 11 SQL :"+sqlUpdate);
            response = jobService.updateJob(query);
           
        } catch (Exception ex) {
            Logger.getLogger(JobResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }

    /**
     * DELETE method for resource JobResource
     */
    @DELETE
    @Produces("application/json")
    public String deleteJob(@PathParam("id") int id) {
        
        String response="";
        JobService jobService = new JobService();
        String query = "DELETE FROM job WHERE idjob = "+id;
        try {
            //System.out.println("PUT Job 11 SQL :"+sqlUpdate);
            response = jobService.deleteJob(query);
        } catch (Exception ex) {
            Logger.getLogger(JobResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }
}
