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
//Path: http://localhost/<appln-folder-name>/unit
@Path("/unit")
public class UnitsResource {
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of UnitsResource
     */
    public UnitsResource() {
    }
    
    /**
     * Retrieves representation of an instance of com.aerothai.UnitsResource
     * @return an instance of java.lang.String
     */
    @GET
    // Produces JSON as response
    @Produces("application/json")
    //@Produces("application/xml")
    public String listUnit(@QueryParam("action") String action,@QueryParam("column") String column, @QueryParam("value") String value) {
        String response = null;
        String opt = null;
        UnitService unitService = new UnitService();
        System.out.println("List Unit All");
        if (unitService.isNotNull(column) && unitService.isNotNull(value) ){
            //System.out.println("column = :"+column+" v =:"+value);
            opt = " "+column+" = '"+value+"'";
        }
        if (!unitService.isNotNull(action)){
            action = "0";    
        }
        try {
            JSONObject unitData = null;
            
            unitData = unitService.GetUnitAll(action,opt);
            
            response = unitData.toJSONString();
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
    public String createUnit(@FormParam("idunit") int idunit,
                @FormParam("name") String name,@FormParam("shortname") String shortname,
                @FormParam("filename") String filename,@FormParam("menu") String menu,
                @FormParam("action") String action,@FormParam("addjob") String addjob,
                @Context HttpServletResponse servletResponse) throws IOException {
        String response="";
        UnitService unitService = new UnitService();
        String query = "INSERT INTO unit " +
                            "(name,shortname,filename,menu,action,addjob)"+
                            "VALUES"+    
                            "('"+name+"','"+shortname+"','"+filename+"','"+menu+
                            "','"+action+"','"+addjob+"')";
        try {
            System.out.println("POST Unit 1 SQL :"+query);
            response = unitService.createUnit(query);
        } catch (Exception ex) {
            Logger.getLogger(UnitResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }
    
    /**
     * Sub-resource locator method for {id}
     */
    @Path("{id}")
    public UnitResource getUnitResource(@PathParam("id") String id) {
        return UnitResource.getInstance(id);
    }
}
