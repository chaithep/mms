/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerothai.database.user;

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
public class UserResource {
    private String id;

    /**
     * Creates a new instance of UserResource
     */
    private UserResource(String id) {
        this.id = id;
    }

    /**
     * Get instance of the UserResource
     */
    public static UserResource getInstance(String id) {
        // The user may use some kind of persistence mechanism
        // to store and restore instances of UserResource class.
        return new UserResource(id);
    }

    /**
     * Retrieves representation of an instance of com.aerothai.UserResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String listUserAt(@PathParam("id") int id) {
        String response = null;
        
        System.out.println("List User At ID :"+id);
        try {
            JSONObject userData = null;
            UserService userService = new UserService();
            userData = userService.GetUserAt(id);
            
            response = userData.toJSONString();
	} catch (Exception e) {
            System.out.println("error");
	}
        
        return response;
    }

    /**
     * PUT method for updating or creating an instance of UserResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    //@Consumes("application/x-www-form-urlencoded")
    //@Produces("text/plain")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces("application/json")
    public String updateUser(@PathParam("id") int id,@FormParam("iduser") int iduser,
                @FormParam("name") String name,@FormParam("lastname") String lastname,
                @FormParam("idposition") int idposition,@FormParam("iddept") int iddept,
                @FormParam("idunit") int idunit,@FormParam("idrole") int idrole,
                @FormParam("address") String address,@FormParam("email") String email,
                @FormParam("tel") String tel,@FormParam("actunit") String actunit,
                @FormParam("username") String username,@FormParam("pwd") String pwd,
                @FormParam("photo") String photo,@FormParam("actcust") String actcust,
                @Context HttpServletResponse servletResponse) throws IOException {
        
        String response="";
        System.out.println("Put User At ID :"+id);
        //System.out.println("Photo :"+photo);
        UserService userService = new UserService();
        int i =0;
        String query = "UPDATE user " + "SET";
        if (userService.isNotNull(name) && (!name.isEmpty())) { 
            query += " name = '"+name+"'";
            i++;
        }
        if (userService.isNotNull(lastname) && (!lastname.isEmpty())) {
            if (i>0) query += ",";
            i++;
            query += " lastname = '"+lastname+"'";
        }
        if (userService.isNotNull(address) && (!address.isEmpty())) {
            if (i>0) query += ",";
            i++;
            query += " address = '"+address+"'";
        }
        if (userService.isNotNull(email) && (!email.isEmpty())) {
            if (i>0) query += ",";
            i++;
            query += " email = '"+email+"'";
        }
        if (userService.isNotNull(tel) && (!tel.isEmpty())) {
            if (i>0) query += ",";
            i++;
            query += " tel = '"+tel+"'";
        }
        if (userService.isNotNull(actunit) && (!actunit.isEmpty())) {
            if (i>0) query += ",";
            i++;
            query += " actunit = '"+actunit+"'";
        }
        if (userService.isNotNull(username) && (!username.isEmpty())) {
            if (i>0) query += ",";
            i++;
            query += " username = '"+username+"'";
        }
        if (userService.isNotNull(pwd) && (!pwd.isEmpty())) {
            if (i>0) query += ",";
            i++;
            query += " pwd = '"+pwd+"'";
        }
        if (userService.isNotNull(photo) && (!photo.isEmpty())) {
            if (i>0) query += ",";
            i++;
            query += " photo = '"+photo+"'";
        }  
        if (userService.isNotNull(actcust) && (!actcust.isEmpty())) {
            if (i>0) query += ",";
            i++;
            query += " actcust = '"+actcust+"'";
        } 
        if (idposition > 0) {
            if (i>0) query += ",";
            i++;
            query += " idposition = "+idposition;
        }
        if (iddept > 0) {
            if (i>0) query += ",";
            i++;
            query += " iddept = "+iddept;
        }
        if (idunit > 0) {
            if (i>0) query += ",";
            i++;
            query += " idunit = "+idunit;
        }
        if (idrole > 0) {
            if (i>0) query += ",";
            i++;
            query += " idrole = "+idrole;
        }
        query += " WHERE iduser = "+id;    
        System.out.println("PUT User 10 SQL :"+query);
        
        
        try {
            System.out.println("PUT User 11 SQL :"+query);
            response = userService.updateUser(query);
           
        } catch (Exception ex) {
            Logger.getLogger(UserResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }

    /**
     * DELETE method for resource UserResource
     */
    @DELETE
    @Produces("application/json")
    public String deleteUser(@PathParam("id") int id) {
        
        String response="";
        System.out.println("Put User At ID :"+id);
        UserService userService = new UserService();
        String query = "DELETE FROM user WHERE iduser = "+id;
        try {
            //System.out.println("PUT User 11 SQL :"+sqlUpdate);
            response = userService.deleteUser(query);
        } catch (Exception ex) {
            Logger.getLogger(UserResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }
}
