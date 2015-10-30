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
public class SparepartResource {
    private String id;

    /**
     * Creates a new instance of SparepartResource
     */
    private SparepartResource(String id) {
        this.id = id;
    }

    /**
     * Get instance of the SparepartResource
     */
    public static SparepartResource getInstance(String id) {
        // The sparepart may use some kind of persistence mechanism
        // to store and restore instances of SparepartResource class.
        return new SparepartResource(id);
    }

    /**
     * Retrieves representation of an instance of com.aerothai.SparepartResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String listSparepartAt(@PathParam("id") int id) {
        String response = null;
        
        System.out.println("List Sparepart At ID");
        try {
            JSONObject sparepartData = null;
            SparepartService sparepartService = new SparepartService();
            sparepartData = sparepartService.GetSparepartAt(id);
            
            response = sparepartData.toJSONString();
	} catch (Exception e) {
            System.out.println("error");
	}
        
        return response;
    }

    /**
     * PUT method for updating or creating an instance of SparepartResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces("application/json")
    public String updateSparepart(@PathParam("id") int id,@FormParam("idspare") int idsparepart,
                @FormParam("details") String details,@FormParam("amount") int amount,
                @FormParam("price") int price,@FormParam("date") int date,
                @FormParam("idunit") int idunit,
                @Context HttpServletResponse servletResponse) throws IOException {
        
        String response="";
        SparepartService sparepartService = new SparepartService();
        String query = "UPDATE sparepart " + "SET"+
                            " details = '"+details+"', amount = "+amount+
                            ", price = "+price+", date = "+date+
                            ", idunit = "+idunit+
                            " WHERE idsparepart = "+id;
        try {
            //System.out.println("PUT Sparepart 11 SQL :"+sqlUpdate);
            response = sparepartService.updateSparepart(query);
           
        } catch (Exception ex) {
            Logger.getLogger(SparepartResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }

    /**
     * DELETE method for resource SparepartResource
     */
    @DELETE
    @Produces("application/json")
    public String deleteSparepart(@PathParam("id") int id) {
        
        String response="";
        SparepartService sparepartService = new SparepartService();
        String query = "DELETE FROM spare_part WHERE idspare = "+id;
        try {
            //System.out.println("PUT Sparepart 11 SQL :"+sqlUpdate);
            response = sparepartService.deleteSparepart(query);
        } catch (Exception ex) {
            Logger.getLogger(SparepartResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }
}
