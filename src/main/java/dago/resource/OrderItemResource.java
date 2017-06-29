package dago.resource;

import dago.result.ResourceResult;
import org.apache.cxf.jaxrs.ext.PATCH;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * OrderItemResource
 *
 * @author <a href="mailto:morezzww@gmail.com">More</a>
 */
public interface OrderItemResource {



    @POST
    @Path("/order/{orderId}/item")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    ResourceResult create(@PathParam("orderId") Long orderId, String body);

    @PATCH
    @Path("/order/{orderId}/item/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    ResourceResult update(@PathParam("orderId") Long orderId, @PathParam("id") Long orderItemId, String body);

    @GET
    @Path("/order/{orderId}/item/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    ResourceResult get(@PathParam("orderId") Long orderId, @PathParam("id") Long orderItemId);


    @DELETE
    @Path("/order/{orderId}/item/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    ResourceResult delete(@PathParam("orderId") Long orderId, @PathParam("id") Long orderItemId);


    @POST
    @Path("/order/item:search")
    @Produces(MediaType.APPLICATION_JSON)
    ResourceResult search(String body);
}
