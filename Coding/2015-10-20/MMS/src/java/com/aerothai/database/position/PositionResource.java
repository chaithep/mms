/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerothai.database.position;

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
public class PositionResource {
    private String id;

    /**
     * Creates a new instance of PositionResource
     */
    private PositionResource(String id) {
        this.id = id;
    }

    /**
     * Get instance of the PositionResource
     */
    public static PositionResource getInstance(String id) {
        // The position may use some kind of persistence mechanism
        // to store and restore instances of PositionResource class.
        return new PositionResource(id);
    }

    /**
     * Retrieves representation of an instance of com.aerothai.PositionResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String listPositionAt(@PathParam("id") int id) {
        String response = null;
        
        System.out.println("List Position At ID");
        try {
            JSONObject positionData = null;
            PositionService positionService = new PositionService();
            positionData = positionService.GetPositionAt(id);
            
            response = positionData.toJSONString();
	} catch (Exception e) {
            System.out.println("error");
	}
        
        return response;
    }

    /**
     * PUT method for updating or creating an instance of PositionResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces("application/json")
    public String updatePosition(@PathParam("id") int id,@FormParam("idposition") int idposition,
                @FormParam("name") String name,
                @Context HttpServletResponse servletResponse) throws IOException {
        
        String response="";
        PositionService positionService = new PositionService();
        String query = "UPDATE position " + "SET"+
                            " name = '"+name+
                            "' WHERE idposition = "+id;
        try {
            //System.out.println("PUT Position 11 SQL :"+sqlUpdate);
            response = positionService.updatePosition(query);
           
        } catch (Exception ex) {
            Logger.getLogger(PositionResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }

    /**
     * DELETE method for resource PositionResource
     */
    @DELETE
    @Produces("application/json")
    public String deletePosition(@PathParam("id") int id) {
        
        String response="";
        PositionService positionService = new PositionService();
        String query = "DELETE FROM position WHERE idposition = "+id;
        try {
            //System.out.println("PUT Position 11 SQL :"+sqlUpdate);
            response = positionService.deletePosition(query);
        } catch (Exception ex) {
            Logger.getLogger(PositionResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }
}
