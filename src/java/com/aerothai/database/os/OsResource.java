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
public class OsResource {
    private String id;

    /**
     * Creates a new instance of OsResource
     */
    private OsResource(String id) {
        this.id = id;
    }

    /**
     * Get instance of the OsResource
     */
    public static OsResource getInstance(String id) {
        // The os may use some kind of persistence mechanism
        // to store and restore instances of OsResource class.
        return new OsResource(id);
    }

    /**
     * Retrieves representation of an instance of com.aerothai.OsResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String listOsAt(@PathParam("id") int id) {
        String response = null;
        
        System.out.println("List Os At ID");
        try {
            JSONObject osData = null;
            OsService osService = new OsService();
            osData = osService.GetOsAt(id);
            
            response = osData.toJSONString();
	} catch (Exception e) {
            System.out.println("error");
	}
        
        return response;
    }

    /**
     * PUT method for updating or creating an instance of OsResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces("application/json")
    public String updateOs(@PathParam("id") int id,@FormParam("idos") int idos,
                @FormParam("name") String name,
                @Context HttpServletResponse servletResponse) throws IOException {
        
        String response="";
        OsService osService = new OsService();
        String query = "UPDATE os SET"+
                            " name = '"+name+
                            "' WHERE idos = "+id;
        try {
            //System.out.println("PUT Os 11 SQL :"+sqlUpdate);
            response = osService.updateOs(query);
        } catch (Exception ex) {
            Logger.getLogger(OsResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }

    /**
     * DELETE method for resource OsResource
     */
    @DELETE
    @Produces("application/json")
    public String deleteOs(@PathParam("id") int id) {
        
        String response="";
        OsService osService = new OsService();
        String query = "DELETE FROM os WHERE idos = "+id;
        try {
            //System.out.println("PUT Os 11 SQL :"+sqlUpdate);
            response = osService.deleteOs(query);
        } catch (Exception ex) {
            Logger.getLogger(OsResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }
}
