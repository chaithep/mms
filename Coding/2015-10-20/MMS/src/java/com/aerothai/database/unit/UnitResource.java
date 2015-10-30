/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerothai.database.unit;

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
public class UnitResource {
    private String id;

    /**
     * Creates a new instance of UnitResource
     */
    private UnitResource(String id) {
        this.id = id;
    }

    /**
     * Get instance of the UnitResource
     */
    public static UnitResource getInstance(String id) {
        // The unit may use some kind of persistence mechanism
        // to store and restore instances of UnitResource class.
        return new UnitResource(id);
    }

    /**
     * Retrieves representation of an instance of com.aerothai.UnitResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String listUnitAt(@PathParam("id") int id) {
        String response = null;
        
        System.out.println("List Unit At ID");
        try {
            JSONObject unitData = null;
            UnitService unitService = new UnitService();
            unitData = unitService.GetUnitAt(id);
            
            response = unitData.toJSONString();
	} catch (Exception e) {
            System.out.println("error");
	}
        
        return response;
    }

    /**
     * PUT method for updating or creating an instance of UnitResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces("application/json")
    public String updateUnit(@PathParam("id") int id,@FormParam("idunit") int idunit,
                @FormParam("name") String name,@FormParam("shortname") String shortname,
                @FormParam("filename") String filename,@FormParam("menu") String menu,
                @FormParam("action") String action,@FormParam("addjob") String addjob,
                @Context HttpServletResponse servletResponse) throws IOException {
        
        String response="";
        UnitService unitService = new UnitService();
        String query = "UPDATE unit " + "SET"+
                            " name = '"+name+"', shortname = '"+shortname+
                            "', filename = '"+filename+"', menu = '"+menu+
                            "', action = '"+filename+"', addjob = '"+addjob+
                            "' WHERE idunit = "+id;
        try {
            //System.out.println("PUT Unit 11 SQL :"+sqlUpdate);
            response = unitService.updateUnit(query);
           
        } catch (Exception ex) {
            Logger.getLogger(UnitResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }

    /**
     * DELETE method for resource UnitResource
     */
    @DELETE
    @Produces("application/json")
    public String deleteUnit(@PathParam("id") int id) {
        
        String response="";
        UnitService unitService = new UnitService();
        String query = "DELETE FROM unit WHERE idunit = "+id;
        try {
            //System.out.println("PUT Unit 11 SQL :"+sqlUpdate);
            response = unitService.deleteUnit(query);
        } catch (Exception ex) {
            Logger.getLogger(UnitResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }
}
