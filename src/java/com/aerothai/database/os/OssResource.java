/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerothai.database.os;

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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import org.json.simple.JSONObject;

/**
 *
 * @author chaia_000
 */
//Path: http://localhost/<appln-folder-name>/os
@Path("/os")
public class OssResource {
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of OssResource
     */
    public OssResource() {
    }
    
    /**
     * Retrieves representation of an instance of com.aerothai.OssResource
     * @return an instance of java.lang.String
     */
    @GET
    // Produces JSON as response
    @Produces("application/json")
    //@Produces("application/xml")
    public String listOs() {
        String response = null;
        
        System.out.println("List Os All");
        try {
            JSONObject osData = null;
            OsService osService = new OsService();
            osData = osService.GetOsAll();
            
            response = osData.toJSONString();
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
    public String createOs(@FormParam("idos") int idos,
                @FormParam("name") String name,
                @Context HttpServletResponse servletResponse) throws IOException {
        String response="";
        OsService osService = new OsService();
        String query = "INSERT INTO os " +
                            "(name,shortname)"+
                            "VALUES"+    
                            "('"+name+
                            "')";
        try {
            System.out.println("POST Os 1 SQL :"+query);
            response = osService.createOs(query);
        } catch (Exception ex) {
            Logger.getLogger(OsResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }
    
      
    /**
     * Sub-resource locator method for {id}
     */
    @Path("{id}")
    public OsResource getOsResource(@PathParam("id") String id) {
        return OsResource.getInstance(id);
    }
}
