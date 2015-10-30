/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerothai.database.building;

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
//Path: http://localhost/<appln-folder-name>/building
@Path("/building")
public class BuildingsResource {
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of BuildingsResource
     */
    public BuildingsResource() {
    }
    
    /**
     * Retrieves representation of an instance of com.aerothai.BuildingsResource
     * @return an instance of java.lang.String
     */
    @GET
    // Produces JSON as response
    @Produces("application/json")
    //@Produces("application/xml")
    public String listBuilding() {
        String response = null;
        
        System.out.println("List Building All");
        try {
            JSONObject buildingData = null;
            BuildingService buildingService = new BuildingService();
            buildingData = buildingService.GetBuildingAll();
            
            response = buildingData.toJSONString();
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
    public String createBuilding(@FormParam("idbuilding") int idbuilding,
                @FormParam("name") String name,
                @Context HttpServletResponse servletResponse) throws IOException {
        String response="";
        BuildingService buildingService = new BuildingService();
        String query = "INSERT INTO building " +
                            "(name,shortname)"+
                            "VALUES"+    
                            "('"+name+
                            "')";
        try {
            System.out.println("POST Building 1 SQL :"+query);
            response = buildingService.createBuilding(query);
        } catch (Exception ex) {
            Logger.getLogger(BuildingResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }
    
      
    /**
     * Sub-resource locator method for {id}
     */
    @Path("{id}")
    public BuildingResource getBuildingResource(@PathParam("id") String id) {
        return BuildingResource.getInstance(id);
    }
}
