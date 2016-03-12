/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DataAccess.MyUserService;
import Models.*;
import ViewModels.*;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.*;

import Utils.CommonUtils;
import Utils.ConvertModels;


/**
 * REST Web Service
 *
 * @author chiming
 */
@Path("users")
public class RestUserService {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of RestTest
     */
    public RestUserService() {
    }
    
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserInfo(@PathParam("id") final String id) {
        //TODO return proper representation object
        //throw new UnsupportedOperationException();
        try{
            
            UserModel um=MyUserService.getUserInfo(id);
            if(um==null)
            {
                return Response.status(400).entity(CommonUtils.getErrorJSON("Invalid input","User does not exist")).build();
            }
            UserViewModel uvm=ConvertModels.db2view(um);
            return Response.status(200).entity(uvm).build();
        }
        catch(Exception e)
        {
            return Response.status(500).entity(CommonUtils.getErrorJSON(e)).build();
        }
        
    }

    @GET
    @Path("{id}/friends")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserFriends(@PathParam("id") final String id) {
        //TODO return proper representation object
        //throw new UnsupportedOperationException();
        try{
            UserListModel ulm=MyUserService.getUserFriendsList(id);
            if(ulm==null)
            {
                return Response.status(400).entity(CommonUtils.getErrorJSON("Invalid input","User does not exist")).build();
            }
            UserListViewModel ulvm=ConvertModels.db2view(ulm);
            return Response.status(200).entity(ulvm).build();
        }
        catch(Exception e)
        {
            return Response.status(500).entity(CommonUtils.getErrorJSON(e)).build();
        }
        
    }
    /**
     * Retrieves representation of an instance of Services.RestTest
     * @return an instance of java.lang.String
     */
    @GET
    @Path("/search/{content}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSearchResult(@PathParam("content") final String content) {
        //TODO return proper representation object
        //throw new UnsupportedOperationException();
        try{
            UserListModel ulm=MyUserService.getSearchResultList(content);
            if(ulm==null)
            {
                return Response.status(400).entity(CommonUtils.getErrorJSON("Invalid input","User does not exist")).build();
            }
            UserListViewModel ulvm=ConvertModels.db2view(ulm);
            return Response.status(200).entity(ulvm).build();
        }
        catch(Exception e)
        {
            return Response.status(500).entity(CommonUtils.getErrorJSON(e)).build();
        }
        
    }

    /**
     * PUT method for updating or creating an instance of RestTest
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @Path("/doput")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response putJson(String content) {
        JSONObject jo=new JSONObject("{\"msg\":\"you called put\"}");
        return Response.status(200).entity(jo.toString()).build();
    }    
    
    @Path("/newuser")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createNewUser(UserRegisterViewModel urvm) {
        
        //JSONObject jo=new JSONObject("{\"msg\":\"you called post\"}");
        try{
            boolean x=MyUserService.register(ConvertModels.view2db(urvm));
            
            return Response.status(200).entity("{\"success\":\"you have successfully registered\"}").build();
        }
        catch(Exception e)
        {
            return Response.status(500).entity(CommonUtils.getErrorJSON(e)).build();
        }
    }
    
    @Path("/follow")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response followUser(AddFriendViewModel afvm) {
        
        //JSONObject jo=new JSONObject("{\"msg\":\"you called post\"}");
        try{
            int x=MyUserService.follow(ConvertModels.view2db(afvm));
            
            switch(x)
            {
                case 1:return Response.status(200).entity("{\"success\":\"you have successfully followed\"}").build();
                case 0:return Response.status(200).entity("{\"success\":\"you have already followed the user\"}").build();
                case -1:return Response.status(400).entity(CommonUtils.getErrorJSON("Invalid input", "input IDs are the same")).build();
                case -2:return Response.status(400).entity(CommonUtils.getErrorJSON("Invalid input", "one or more input users does not exist")).build();
                default:return Response.status(500).entity(CommonUtils.getErrorJSON("uncaught Error", "No details.")).build();
            }
            
        }
        catch(Exception e)
        {
            return Response.status(500).entity(CommonUtils.getErrorJSON(e)).build();
        }
    }    
}
