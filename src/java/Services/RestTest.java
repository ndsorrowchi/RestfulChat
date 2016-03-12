/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import ViewModels.TestModel;
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

/**
 * REST Web Service
 *
 * @author chiming
 */
@Path("test")
public class RestTest {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of RestTest
     */
    public RestTest() {
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJsonRoot() {
        //TODO return proper representation object
        //throw new UnsupportedOperationException();
        try{
        TestModel x=new TestModel("123");
        return Response.status(200).entity(x).build();
        }
        catch(Exception e)
        {
            return Response.status(500).entity("{\"error\":\""+e.getMessage()+"\"}").build();
        }
        
    }
    /**
     * Retrieves representation of an instance of Services.RestTest
     * @return an instance of java.lang.String
     */
    @Path("/doget")
    @GET
    @Produces("application/json")
    public String getJson() {
        //TODO return proper representation object
        //throw new UnsupportedOperationException();
        JSONObject jo=new JSONObject("{\"aaa\":\"hi\"}");
        return jo.toString();
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
        JSONObject jo=new JSONObject("{\"aaa\":\"you did put\"}");
        return Response.status(200).entity(jo.toString()).build();
    }    
    
    @Path("/dopost")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postJson(String content) {
        JSONObject jo=new JSONObject("{\"aaa\":\"you did post\"}");
        return Response.status(200).entity(jo.toString()).build();
    }
}
