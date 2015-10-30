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

//Path: http://localhost/<appln-folder-name>/device
@Path("/device")
public class DevicesResource {
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of DevicesResource
     */
    public DevicesResource() {
    }
    
    /**
     * Retrieves representation of an instance of com.aerothai.DevicesResource
     * @return an instance of java.lang.String
     */
    @GET
    // Produces JSON as response
    @Produces("application/json")
    //@Produces("application/xml")
    public String listDevice(@QueryParam("idunit") int idunit,@QueryParam("column") String column, @QueryParam("value") String value) {
        String response = null;
        String opt = null;
        DeviceService deviceService = new DeviceService();
        System.out.println("List Device All");
        if (deviceService.isNotNull(column) && deviceService.isNotNull(value) ){
            //System.out.println("column = :"+column+" v =:"+value);
            opt = " "+column+" = '"+value+"'";
        }
        
        try {
            JSONObject deviceData = null;
            
            deviceData = deviceService.GetDeviceAll(idunit,opt);
            
            response = deviceData.toJSONString();
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
    public String createDevice(@FormParam("iddevice") int iddevice,
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
        DeviceService deviceService = new DeviceService();
        String query = "INSERT INTO device " +
                            "(idunit,iddevice_type,idmodel,idaccessory,"+
                            "asset_no,serial_no,ip_address,mac_address,computer_name,os,"+
                            "firmware,vendor,customer,address,location,insert_date,"+
                            "receive_date,exp_date,image,id_door,in_out_side)"+
                            "VALUES"+    
                            "("+idunit+","+iddevice_type+","+idmodel+","+idaccessory+
                            ",'"+asset_no+"','"+serial_no+"','"+ip_address+"','"+mac_address+
                            "','"+computer_name+"','"+os+"','"+firmware+"','"+vendor+
                            "','"+customer+"','"+address+"','"+location+"','"+insert_date+
                            "','"+receive_date+"','"+exp_date+"','"+image+"','"+id_door+
                            "','"+in_out_side+"')";
        try {
            System.out.println("POST Device 1 SQL :"+query);
            response = deviceService.createDevice(query);
        } catch (Exception ex) {
            Logger.getLogger(DeviceResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }
    
    /**
     * Sub-resource locator method for {id}
     */
    @Path("{id}")
    public DeviceResource getDeviceResource(@PathParam("id") String id) {
        return DeviceResource.getInstance(id);
    }
}