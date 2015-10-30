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
//Path: http://localhost/<appln-folder-name>/position
@Path("/position")
public class PositionsResource {
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of PositionsResource
     */
    public PositionsResource() {
    }
    
    /**
     * Retrieves representation of an instance of com.aerothai.PositionsResource
     * @return an instance of java.lang.String
     */
    @GET
    // Produces JSON as response
    @Produces("application/json")
    //@Produces("application/xml")
    public String listPosition(@QueryParam("column") String column, @QueryParam("value") String value) {
        String response = null;
        String opt = null;
        PositionService positionService = new PositionService();
        System.out.println("List Position All");
        if (positionService.isNotNull(column) && positionService.isNotNull(value) ){
            //System.out.println("column = :"+column+" v =:"+value);
            opt = " "+column+" = '"+value+"'";
        }
        try {
            JSONObject positionData = null;
            
            positionData = positionService.GetPositionAll(opt);
            
            response = positionData.toJSONString();
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
    public String createPosition(@FormParam("idposition") int idposition,
                @FormParam("name") String name,
                @Context HttpServletResponse servletResponse) throws IOException {
        String response="";
        PositionService positionService = new PositionService();
        String query = "INSERT INTO position " +
                            "(name)"+
                            "VALUES"+    
                            "('"+name+"')";
        try {
            System.out.println("POST Position 1 SQL :"+query);
            response = positionService.createPosition(query);
        } catch (Exception ex) {
            Logger.getLogger(PositionResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }
    
    /**
     * Sub-resource locator method for {id}
     */
    @Path("{id}")
    public PositionResource getPositionResource(@PathParam("id") String id) {
        return PositionResource.getInstance(id);
    }
}
