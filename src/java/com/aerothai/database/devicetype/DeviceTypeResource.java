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
public class DeviceTypeResource {
    private String id;

    /**
     * Creates a new instance of DeviceTypeResource
     */
    private DeviceTypeResource(String id) {
        this.id = id;
    }

    /**
     * Get instance of the DeviceTypeResource
     */
    public static DeviceTypeResource getInstance(String id) {
        // The deviceType may use some kind of persistence mechanism
        // to store and restore instances of DeviceTypeResource class.
        return new DeviceTypeResource(id);
    }

    /**
     * Retrieves representation of an instance of com.aerothai.DeviceTypeResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String listDeviceTypeAt(@PathParam("id") int id) {
        String response = null;
        
        System.out.println("List DeviceType At ID");
        try {
            JSONObject deviceTypeData = null;
            DeviceTypeService deviceTypeService = new DeviceTypeService();
            deviceTypeData = deviceTypeService.GetDeviceTypeAt(id);
            
            response = deviceTypeData.toJSONString();
	} catch (Exception e) {
            System.out.println("error");
	}
        
        return response;
    }

    /**
     * PUT method for updating or creating an instance of DeviceTypeResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces("application/json")
    public String updateDeviceType(@PathParam("id") int id,@FormParam("iddevice_type") int iddeviceType,
                @FormParam("details") String details,@FormParam("idunit") int idunit,
                @Context HttpServletResponse servletResponse) throws IOException {
        
        String response="";
        DeviceTypeService deviceTypeService = new DeviceTypeService();
        String query = "UPDATE device_type " + "SET"+
                            " details = '"+details+"', idunit = "+idunit+
                            " WHERE iddevice_type = "+id;
        try {
            //System.out.println("PUT DeviceType 11 SQL :"+sqlUpdate);
            response = deviceTypeService.updateDeviceType(query);
           
        } catch (Exception ex) {
            Logger.getLogger(DeviceTypeResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }

    /**
     * DELETE method for resource DeviceTypeResource
     */
    @DELETE
    @Produces("application/json")
    public String deleteDeviceType(@PathParam("id") int id) {
        
        String response="";
        DeviceTypeService deviceTypeService = new DeviceTypeService();
        String query = "DELETE FROM device_type WHERE iddevice_type = "+id;
        try {
            //System.out.println("PUT DeviceType 11 SQL :"+sqlUpdate);
            response = deviceTypeService.deleteDeviceType(query);
        } catch (Exception ex) {
            Logger.getLogger(DeviceTypeResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }
}
