/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerothai.database.radiosignal;

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

//Path: http://localhost/<appln-folder-name>/radiosignal
@Path("/radiosignal")
public class RadiosignalsResource {
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of RadiosignalsResource
     */
    public RadiosignalsResource() {
    }
    
    /**
     * Retrieves representation of an instance of com.aerothai.RadiosignalsResource
     * @return an instance of java.lang.String
     */
    @GET
    // Produces JSON as response
    @Produces("application/json")
    //@Produces("application/xml")
    public String listRadiosignal(@QueryParam("column") String column, @QueryParam("value") String value) {
        String response = null;
        String opt = null;
        RadiosignalService radiosignalService = new RadiosignalService();
        System.out.println("List Radiosignal All");
        if (radiosignalService.isNotNull(column) && radiosignalService.isNotNull(value) ){
            //System.out.println("column = :"+column+" v =:"+value);
            opt = " "+column+" = '"+value+"'";
        }
        
        try {
            JSONObject radiosignalData = null;
            
            radiosignalData = radiosignalService.GetRadiosignalAll(opt);
            
            response = radiosignalData.toJSONString();
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
    public String createRadiosignal(@FormParam("idsignal") int idsignal,
                @FormParam("idjob") int idjob,@FormParam("asset_no") int asset_no,
                @FormParam("freq") int freq,@FormParam("rms") int rms,
                @FormParam("peak") int peak,@FormParam("resid") int resid,
                @FormParam("time_err") int time_err,@FormParam("power_at_15") int power_at_15,
                @FormParam("power_at_30") int power_at_30,@FormParam("rec_level") int rec_level,
                @Context HttpServletResponse servletResponse) throws IOException {
        String response="";
        RadiosignalService radiosignalService = new RadiosignalService();
        String query = "INSERT INTO radio_signal " +
                            "(idjob,asset_no,freq,rms,"+
                            "peak,resid,time_err,power_at_15,power_at_30,rec_level) "+
                            "VALUES"+    
                            "("+idjob+","+asset_no+","+freq+","+rms+
                            ","+peak+","+resid+","+time_err+","+power_at_15+
                            ","+power_at_30+","+rec_level+")";
        try {
            System.out.println("POST Radiosignal 1 SQL :"+query);
            response = radiosignalService.createRadiosignal(query);
        } catch (Exception ex) {
            Logger.getLogger(RadiosignalResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }
    
    /**
     * Sub-resource locator method for {id}
     */
    @Path("{id}")
    public RadiosignalResource getRadiosignalResource(@PathParam("id") String id) {
        return RadiosignalResource.getInstance(id);
    }
}
