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
//Path: http://localhost/<appln-folder-name>/user
@Path("/user")
public class UsersResource {
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of UsersResource
     */
    public UsersResource() {
    }
    
    /**
     * Retrieves representation of an instance of com.aerothai.UsersResource
     * @return an instance of java.lang.String
     */
    @GET
    // Produces JSON as response
    @Produces("application/json")
    //@Produces("application/xml")
    public String listUser(@QueryParam("role") String role,@QueryParam("column") String column, @QueryParam("value") String value) {
        String response = null;
        String opt = null;
        UserService userService = new UserService();
        System.out.println("List User All");
        if (!userService.isNotNull(role)) {
            role = "0";
        }
        if (userService.isNotNull(column) && userService.isNotNull(value) ){
            //System.out.println("column = :"+column+" v =:"+value);
            opt = column+" = '"+value+"'";
        }
        try {
            JSONObject userData = null;
            
            userData = userService.GetUserAll(role,opt);
            
            response = userData.toJSONString();
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
    //@Consumes("application/xml")
    @Produces("application/json")
    //@Produces(MediaType.TEXT_XML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String createUser(@FormParam("iduser") int iduser,
                @FormParam("name") String name,@FormParam("lastname") String lastname,
                @FormParam("idposition") int idposition,@FormParam("iddept") int iddept,
                @FormParam("idunit") int idunit,@FormParam("idrole") int idrole,
                @FormParam("address") String address,@FormParam("email") String email,
                @FormParam("tel") String tel,@FormParam("actunit") String actunit,
                @FormParam("username") String username,@FormParam("pwd") String pwd,
                @FormParam("photo") String photo,@FormParam("actcust") String actcust,
                @Context HttpServletResponse servletResponse) throws IOException {
        String response="";
        System.out.println("Post User :");
        UserService userService = new UserService();
        String query = "INSERT INTO user " +
                            "(name,lastname,idposition,iddept,idunit,idrole,"+
                            "address,email,tel,actunit,username,pwd,photo,actcust)"+
                            "VALUES"+    
                            "('"+name+"','"+lastname+"',"+idposition+","+iddept+","+idunit+","+idrole+
                            ",'"+address+"','"+email+"','"+tel+"','"+actunit+"','"+username+"','"+pwd+
                            "','"+photo+"','"+actcust+
                            "')";
        try {
            System.out.println("POST User 1 SQL :"+query);
            response = userService.createUser(query);
        } catch (Exception ex) {
            Logger.getLogger(UserResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }
    
    @Path("/login")
    // HTTP Get Method
    @GET
    // Produces JSON as response
    @Produces("application/json")
    //@Consumes("application/json")
    //@Produces(MediaType.APPLICATION_JSON) 
    // Query parameters are parameters: http://localhost/<appln-folder-name>/user/login?username=abc&pwd=xyz
    public String doLogin(@QueryParam("username") String uname, @QueryParam("pwd") String pwd){
     
	String response = "";
        UserService userService = new UserService();
        System.out.println("username :"+uname+" pwd : "+pwd);
        
        try {
            response = userService.checkLogin(uname,pwd);
        } catch (Exception ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return response;
    }
    @Path("/img")
    // HTTP Get Method
    @POST
    // Produces JSON as response
    @Produces("application/json")
    //@Consumes("application/json")
    //@Produces(MediaType.APPLICATION_JSON) 
    // Query parameters are parameters: http://localhost/<appln-folder-name>/user/login?username=abc&pwd=xyz
    public String doimg(@QueryParam("name") String uname, @QueryParam("lastname") String lastname
                        
                        ,@Context HttpServletResponse servletResponse) throws IOException {
        String response = "";
        System.out.println(" post/img username :"+uname+" lastname : "+lastname);
        return response;
    }
    /**
     * Sub-resource locator method for {id}
     */
    @Path("{id}")
    public UserResource getUserResource(@PathParam("id") String id) {
        return UserResource.getInstance(id);
    }
    
}
