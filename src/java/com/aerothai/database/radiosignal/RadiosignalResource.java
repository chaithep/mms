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
public class RadiosignalResource {
    private String id;

    /**
     * Creates a new instance of RadiosignalResource
     */
    private RadiosignalResource(String id) {
        this.id = id;
    }

    /**
     * Get instance of the RadiosignalResource
     */
    public static RadiosignalResource getInstance(String id) {
        // The radiosignal may use some kind of persistence mechanism
        // to store and restore instances of RadiosignalResource class.
        return new RadiosignalResource(id);
    }

    /**
     * Retrieves representation of an instance of com.aerothai.RadiosignalResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String listRadiosignalAt(@PathParam("id") int id) {
        String response = null;
        
        System.out.println("List Radiosignal At ID");
        try {
            JSONObject radiosignalData = null;
            RadiosignalService radiosignalService = new RadiosignalService();
            radiosignalData = radiosignalService.GetRadiosignalAt(id);
            
            response = radiosignalData.toJSONString();
	} catch (Exception e) {
            System.out.println("error");
	}
        
        return response;
    }

    /**
     * PUT method for updating or creating an instance of RadiosignalResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces("application/json")
    public String updateRadiosignal(@PathParam("id") int id,@FormParam("idsignal") int idsignal,
                @FormParam("idjob") int idjob,@FormParam("asset_no") int asset_no,
                @FormParam("freq") int freq,@FormParam("rms") int rms,
                @FormParam("peak") int peak,@FormParam("resid") int resid,
                @FormParam("time_err") int time_err,@FormParam("power_at_15") int power_at_15,
                @FormParam("power_at_30") int power_at_30,@FormParam("rec_level") int rec_level,
                @Context HttpServletResponse servletResponse) throws IOException {
        
        String response="";
        System.out.println("Put User At ID :"+id);
        RadiosignalService radiosignalService = new RadiosignalService();
        int i =0;
        String query = "UPDATE radio_signal " + "SET";
        if (idjob > 0) {
            if (i>0) query += ",";
            i++;
            query += " idjob = "+idjob;
        }
        if (asset_no > 0) {
            if (i>0) query += ",";
            i++;
            query += " asset_no = "+asset_no;
        }
        if (freq > 0) {
            if (i>0) query += ",";
            i++;
            query += " freq = "+freq;
        }
        if (rms > 0) {
            if (i>0) query += ",";
            i++;
            query += " rms = "+rms;
        }
        if (peak > 0) {
            if (i>0) query += ",";
            i++;
            query += " peak = "+peak;
        }
        if (resid > 0) {
            if (i>0) query += ",";
            i++;
            query += " resid = "+resid;
        }
        if (time_err > 0) {
            if (i>0) query += ",";
            i++;
            query += " time_err = "+time_err;
        }
        if (power_at_15 > 0) {
            if (i>0) query += ",";
            i++;
            query += " power_at_15 = "+power_at_15;
        }
        if (power_at_30 > 0) {
            if (i>0) query += ",";
            i++;
            query += " power_at_30 = "+power_at_30;
        }
        if (rec_level > 0) {
            if (i>0) query += ",";
            i++;
            query += " rec_level = "+rec_level;
        }
        query += " WHERE idsignal = "+id;    
        System.out.println("PUT User 10 SQL :"+query);
        try {
            //System.out.println("PUT Radiosignal 11 SQL :"+sqlUpdate);
            response = radiosignalService.updateRadiosignal(query);
           
        } catch (Exception ex) {
            Logger.getLogger(RadiosignalResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }

    /**
     * DELETE method for resource RadiosignalResource
     */
    @DELETE
    @Produces("application/json")
    public String deleteRadiosignal(@PathParam("id") int id) {
        
        String response="";
        RadiosignalService radiosignalService = new RadiosignalService();
        String query = "DELETE FROM radio_signal WHERE idsignal = "+id;
        try {
            //System.out.println("PUT Radiosignal 11 SQL :"+sqlUpdate);
            response = radiosignalService.deleteRadiosignal(query);
        } catch (Exception ex) {
            Logger.getLogger(RadiosignalResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }
}
