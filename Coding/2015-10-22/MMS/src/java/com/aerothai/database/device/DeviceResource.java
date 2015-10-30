/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerothai.database.device;

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
public class DeviceResource {
    private String id;

    /**
     * Creates a new instance of DeviceResource
     */
    private DeviceResource(String id) {
        this.id = id;
    }

    /**
     * Get instance of the DeviceResource
     */
    public static DeviceResource getInstance(String id) {
        // The device may use some kind of persistence mechanism
        // to store and restore instances of DeviceResource class.
        return new DeviceResource(id);
    }

    /**
     * Retrieves representation of an instance of com.aerothai.DeviceResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String listDeviceAt(@PathParam("id") int id) {
        String response = null;
        
        System.out.println("List Device At ID");
        try {
            JSONObject deviceData = null;
            DeviceService deviceService = new DeviceService();
            deviceData = deviceService.GetDeviceAt(id);
            
            response = deviceData.toJSONString();
	} catch (Exception e) {
            System.out.println("error");
	}
        
        return response;
    }

    /**
     * PUT method for updating or creating an instance of DeviceResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces("application/json")
    public String updateDevice(@PathParam("id") int id,@FormParam("iddevice") int iddevice,
                @FormParam("idunit") int idunit,@FormParam("iddevice_type") int iddevice_type,
                @FormParam("idmodel") int idmodel,@FormParam("idaccessory") int idaccessory,
                @FormParam("asset_no") String asset_no,@FormParam("serial_no") String serial_no,
                @FormParam("ip_address") String ip_address,@FormParam("mac_address") String mac_address,
                @FormParam("computer_name") String computer_name,@FormParam("os") String os,
                @FormParam("firmware") String firmware,@FormParam("vendor") String vendor,
                @FormParam("customer") String customer,@FormParam("address") String address,
                @FormParam("location") String location,@FormParam("insert_date") String insert_date,
                @FormParam("receive_date") String receive_date,@FormParam("exp_date") String exp_date,
                @FormParam("image") String image,@FormParam("id_door") String id_door,
                @FormParam("in_out_side") String in_out_side,
                @Context HttpServletResponse servletResponse) throws IOException {
        
        String response="";
        System.out.println("Put User At ID :"+id);
        DeviceService deviceService = new DeviceService();
        int i =0;
        String query = "UPDATE device " + "SET";
        if (idunit > 0) {
            if (i>0) query += ",";
            i++;
            query += " idunit = "+idunit;
        }
        if (iddevice_type > 0) {
            if (i>0) query += ",";
            i++;
            query += " iddevice_type = "+iddevice_type;
        }
        if (idmodel > 0) {
            if (i>0) query += ",";
            i++;
            query += " idmodel = "+idmodel;
        }
        if (idaccessory > 0) {
            if (i>0) query += ",";
            i++;
            query += " idaccessory = "+idaccessory;
        }
        if (deviceService.isNotNull(asset_no) && (!asset_no.isEmpty())) { 
            query += " asset_no = '"+asset_no+"'";
            i++;
        }
        if (deviceService.isNotNull(serial_no) && (!serial_no.isEmpty())) { 
            query += " serial_no = '"+serial_no+"'";
            i++;
        }
        if (deviceService.isNotNull(ip_address) && (!ip_address.isEmpty())) { 
            query += " ip_address = '"+ip_address+"'";
            i++;
        }
        if (deviceService.isNotNull(mac_address) && (!mac_address.isEmpty())) { 
            query += " mac_address = '"+mac_address+"'";
            i++;
        }
        if (deviceService.isNotNull(computer_name) && (!computer_name.isEmpty())) { 
            query += " computer_name = '"+computer_name+"'";
            i++;
        }
        if (deviceService.isNotNull(os) && (!os.isEmpty())) { 
            query += " os = '"+os+"'";
            i++;
        }
        if (deviceService.isNotNull(firmware) && (!firmware.isEmpty())) { 
            query += " firmware = '"+firmware+"'";
            i++;
        }
        if (deviceService.isNotNull(vendor) && (!vendor.isEmpty())) { 
            query += " vendor = '"+vendor+"'";
            i++;
        }
        if (deviceService.isNotNull(customer) && (!customer.isEmpty())) { 
            query += " customer = '"+customer+"'";
            i++;
        }
        if (deviceService.isNotNull(address) && (!address.isEmpty())) { 
            query += " address = '"+address+"'";
            i++;
        }
        if (deviceService.isNotNull(location) && (!location.isEmpty())) { 
            query += " location = '"+location+"'";
            i++;
        }
        if (deviceService.isNotNull(insert_date) && (!insert_date.isEmpty())) { 
            query += " insert_date = '"+insert_date+"'";
            i++;
        }
        if (deviceService.isNotNull(receive_date) && (!receive_date.isEmpty())) { 
            query += " receive_date = '"+receive_date+"'";
            i++;
        }
        if (deviceService.isNotNull(exp_date) && (!exp_date.isEmpty())) { 
            query += " exp_date = '"+exp_date+"'";
            i++;
        }
        if (deviceService.isNotNull(image) && (!image.isEmpty())) { 
            query += " image = '"+image+"'";
            i++;
        }
        if (deviceService.isNotNull(id_door) && (!id_door.isEmpty())) { 
            query += " id_door = '"+id_door+"'";
            i++;
        }
        if (deviceService.isNotNull(in_out_side) && (!in_out_side.isEmpty())) { 
            query += " in_out_side = '"+in_out_side+"'";
            i++;
        }
        query += " WHERE iddevice = "+id;    
        System.out.println("PUT Device 10 SQL :"+query);
        try {
            //System.out.println("PUT Device 11 SQL :"+sqlUpdate);
            response = deviceService.updateDevice(query);
           
        } catch (Exception ex) {
            Logger.getLogger(DeviceResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }

    /**
     * DELETE method for resource DeviceResource
     */
    @DELETE
    @Produces("application/json")
    public String deleteDevice(@PathParam("id") int id) {
        
        String response="";
        DeviceService deviceService = new DeviceService();
        String query = "DELETE FROM device WHERE iddevice = "+id;
        try {
            //System.out.println("PUT Device 11 SQL :"+sqlUpdate);
            response = deviceService.deleteDevice(query);
        } catch (Exception ex) {
            Logger.getLogger(DeviceResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }
}
