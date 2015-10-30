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
public class BuildingResource {
    private String id;

    /**
     * Creates a new instance of BuildingResource
     */
    private BuildingResource(String id) {
        this.id = id;
    }

    /**
     * Get instance of the BuildingResource
     */
    public static BuildingResource getInstance(String id) {
        // The building may use some kind of persistence mechanism
        // to store and restore instances of BuildingResource class.
        return new BuildingResource(id);
    }

    /**
     * Retrieves representation of an instance of com.aerothai.BuildingResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String listBuildingAt(@PathParam("id") int id) {
        String response = null;
        
        System.out.println("List Building At ID");
        try {
            JSONObject buildingData = null;
            BuildingService buildingService = new BuildingService();
            buildingData = buildingService.GetBuildingAt(id);
            
            response = buildingData.toJSONString();
	} catch (Exception e) {
            System.out.println("error");
	}
        
        return response;
    }

    /**
     * PUT method for updating or creating an instance of BuildingResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces("application/json")
    public String updateBuilding(@PathParam("id") int id,@FormParam("idbuilding") int idbuilding,
                @FormParam("name") String name,
                @Context HttpServletResponse servletResponse) throws IOException {
        
        String response="";
        BuildingService buildingService = new BuildingService();
        String query = "UPDATE building SET"+
                            " name = '"+name+
                            "' WHERE idbuilding = "+id;
        try {
            //System.out.println("PUT Building 11 SQL :"+sqlUpdate);
            response = buildingService.updateBuilding(query);
        } catch (Exception ex) {
            Logger.getLogger(BuildingResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }

    /**
     * DELETE method for resource BuildingResource
     */
    @DELETE
    @Produces("application/json")
    public String deleteBuilding(@PathParam("id") int id) {
        
        String response="";
        BuildingService buildingService = new BuildingService();
        String query = "DELETE FROM building WHERE idbuilding = "+id;
        try {
            //System.out.println("PUT Building 11 SQL :"+sqlUpdate);
            response = buildingService.deleteBuilding(query);
        } catch (Exception ex) {
            Logger.getLogger(BuildingResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }
}

