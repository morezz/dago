package dago.resource;

import dago.result.ResourceResult;
import org.apache.cxf.jaxrs.ext.PATCH;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * UserInfoResource
 *
 * @author <a href="mailto:morezzww@gmail.com">More</a>
 */
public interface UserInfoResource {



    @POST
    @Path("/user")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    ResourceResult create(String body);

    @PATCH
    @Path("/user/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    ResourceResult update(@PathParam("id") Long userId, String body);

    @GET
    @Path("/user/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    ResourceResult get(@PathParam("id") Long userId);


    @DELETE
    @Path("/user/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    ResourceResult delete(@PathParam("id") Long userId);


    @POST
    @Path("/user:search")
    @Produces(MediaType.APPLICATION_JSON)
    ResourceResult search(String body);
}
