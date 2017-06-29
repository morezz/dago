package dago.resource;

import dago.result.ResourceResult;
import org.apache.cxf.jaxrs.ext.PATCH;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * KindResource
 *
 * @author <a href="mailto:morezzww@gmail.com">More</a>
 */

public interface KindResource {



    @POST
    @Path("/kind")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    ResourceResult create(String body);

    @PATCH
    @Path("/kind/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    ResourceResult update(@PathParam("id") Long productId, String body);

    @GET
    @Path("/kind/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    ResourceResult get(@PathParam("id") Long productId);


    @DELETE
    @Path("/kind/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    ResourceResult delete(@PathParam("id") Long productId);


    @POST
    @Path("/kind:search")
    @Produces(MediaType.APPLICATION_JSON)
    ResourceResult search(String body);
}
