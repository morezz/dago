package dago.resource;

import dago.result.ResourceResult;
import org.apache.cxf.jaxrs.ext.PATCH;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * OrderResource
 *
 * @author <a href="mailto:morezzww@gmail.com">More</a>
 */
public interface OrderResource {

    @POST
    @Path("/order")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    ResourceResult create(String body);

    @PATCH
    @Path("/order/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    ResourceResult update(@PathParam("id") Long orderId, String body);

    @GET
    @Path("/order/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    ResourceResult get(@PathParam("id") Long orderId);


    @DELETE
    @Path("/order/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    ResourceResult delete(@PathParam("id") Long orderId);


    @POST
    @Path("/order:search")
    @Produces(MediaType.APPLICATION_JSON)
    ResourceResult search(String body);




}
