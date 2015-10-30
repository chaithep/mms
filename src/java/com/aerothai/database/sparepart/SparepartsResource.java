/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerothai.database.sparepart;

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

//Path: http://localhost/<appln-folder-name>/sparepart
@Path("/sparepart")
public class SparepartsResource {
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of SparepartsResource
     */
    public SparepartsResource() {
    }
    
    /**
     * Retrieves representation of an instance of com.aerothai.SparepartsResource
     * @return an instance of java.lang.String
     */
    @GET
    // Produces JSON as response
    @Produces("application/json")
    //@Produces("application/xml")
    public String listSparepart(@QueryParam("idunit") int idunit,@QueryParam("column") String column, @QueryParam("value") String value) {
        String response = null;
        String opt = null;
        SparepartService sparepartService = new SparepartService();
        System.out.println("List Sparepart All");
        if (sparepartService.isNotNull(column) && sparepartService.isNotNull(value) ){
            //System.out.println("column = :"+column+" v =:"+value);
            opt = " "+column+" = '"+value+"'";
        }
        
        try {
            JSONObject sparepartData = null;
            
            sparepartData = sparepartService.GetSparepartAll(idunit,opt);
            
            response = sparepartData.toJSONString();
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
    public String createSparepart(@FormParam("idspare") int idsparepart,
                @FormParam("details") String details,@FormParam("amount") int amount,
                @FormParam("price") int price,@FormParam("date") int date,
                @FormParam("idunit") int idunit,
                @Context HttpServletResponse servletResponse) throws IOException {
        String response="";
        SparepartService sparepartService = new SparepartService();
        String query = "INSERT INTO sparepart " +
                            "(details,amount,price,date,idunit)"+
                            "VALUES"+    
                            "('"+details+"',"+amount+","+price+
                            ",'"+date+"',"+idunit+")";
        try {
            System.out.println("POST Sparepart 1 SQL :"+query);
            response = sparepartService.createSparepart(query);
        } catch (Exception ex) {
            Logger.getLogger(SparepartResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }
    
    /**
     * Sub-resource locator method for {id}
     */
    @Path("{id}")
    public SparepartResource getSparepartResource(@PathParam("id") String id) {
        return SparepartResource.getInstance(id);
    }
}
