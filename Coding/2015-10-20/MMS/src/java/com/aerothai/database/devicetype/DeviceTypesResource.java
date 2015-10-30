/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerothai.database.devicetype;

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
//Path: http://localhost/<appln-folder-name>/devicetypes
@Path("/devicetype")
public class DeviceTypesResource {
@Context
    private UriInfo context;

    /**
     * Creates a new instance of DeviceTypesResource
     */
    public DeviceTypesResource() {
    }
    
    /**
     * Retrieves representation of an instance of com.aerothai.DeviceTypesResource
     * @return an instance of java.lang.String
     */
    @GET
    // Produces JSON as response
    @Produces("application/json")
    //@Produces("application/xml")
    public String listDeviceType(@QueryParam("column") String column, @QueryParam("value") String value) {
        String response = null;
        String opt = null;
        DeviceTypeService deviceTypeService = new DeviceTypeService();
        System.out.println("List DeviceType All");
        if (deviceTypeService.isNotNull(column) && deviceTypeService.isNotNull(value) ){
            //System.out.println("column = :"+column+" v =:"+value);
            opt = " "+column+" = '"+value+"'";
        }
        try {
            JSONObject deviceTypeData = null;
            
            deviceTypeData = deviceTypeService.GetDeviceTypeAll(opt);
            
            response = deviceTypeData.toJSONString();
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
    public String createDeviceType(@FormParam("iddevice_type") int iddeviceType,
                @FormParam("details") String details,@FormParam("idunit") int idunit,
                @Context HttpServletResponse servletResponse) throws IOException {
        String response="";
        DeviceTypeService deviceTypeService = new DeviceTypeService();
        String query = "INSERT INTO device_type " +
                            "(details,idunit)"+
                            "VALUES"+    
                            "('"+details+"',"+idunit+")";
        try {
            System.out.println("POST DeviceType 1 SQL :"+query);
            response = deviceTypeService.createDeviceType(query);
        } catch (Exception ex) {
            Logger.getLogger(DeviceTypeResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }
    
    /**
     * Sub-resource locator method for {id}
     */
    @Path("{id}")
    public DeviceTypeResource getDeviceTypeResource(@PathParam("id") String id) {
        return DeviceTypeResource.getInstance(id);
    }
}