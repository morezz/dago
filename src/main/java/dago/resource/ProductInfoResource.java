package dago.resource;

import dago.result.ResourceResult;
import org.apache.cxf.jaxrs.ext.PATCH;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * ProductInfoResource
 *
 * @author <a href="mailto:morezzww@gmail.com">More</a>
 */
public interface ProductInfoResource {


    @POST
    @Path("/product")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    ResourceResult create(String body);

    @PATCH
    @Path("/product/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    ResourceResult update(@PathParam("id") Long productId, String body);

    @GET
    @Path("/product/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    ResourceResult get(@PathParam("id") Long productId);


    @DELETE
    @Path("/product/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    ResourceResult delete(@PathParam("id") Long productId);


    @POST
    @Path("/product:search")
    @Produces(MediaType.APPLICATION_JSON)
    ResourceResult search(String body);
}
