package dago.resource;

import dago.result.ResourceResult;
import org.apache.cxf.jaxrs.ext.PATCH;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * StockResource
 *
 * @author <a href="mailto:morezzww@gmail.com">More</a>
 */
public interface StockResource {


    @POST
    @Path("/stock")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    ResourceResult create(String body);

    @PATCH
    @Path("/stock/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    ResourceResult update(@PathParam("id") Long stockId, String body);

    @GET
    @Path("/stock/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    ResourceResult get(@PathParam("id") Long stockId);


    @DELETE
    @Path("/stock/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    ResourceResult delete(@PathParam("id") Long stockId);


    @POST
    @Path("/stock:search")
    @Produces(MediaType.APPLICATION_JSON)
    ResourceResult search(String body);
}
