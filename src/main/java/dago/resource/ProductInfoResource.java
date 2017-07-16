package dago.resource;

import dago.common.SearchCondition;
import dago.model.ProductInfo;
import dago.parameter.annotation.API;
import dago.parameter.annotation.APIParam;
import dago.result.ResourceResult;
import org.apache.cxf.jaxrs.ext.PATCH;
import org.apache.shiro.authz.annotation.RequiresRoles;

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
    @RequiresRoles("admin")
    @API(params = {
                    @APIParam(name = "name", from = APIParam.FROM.BODY),
                    @APIParam(name = "description", from = APIParam.FROM.BODY),
                    @APIParam(name = "price", from = APIParam.FROM.BODY),
                    @APIParam(name = "model", from = APIParam.FROM.BODY),
                    @APIParam(name = "brand", from = APIParam.FROM.BODY),
                    @APIParam(name = "masterPic", from = APIParam.FROM.BODY),
                    @APIParam(name = "kindId", from = APIParam.FROM.BODY),
                    @APIParam(name = "userId", from = APIParam.FROM.BODY),
                    @APIParam(name = "remark", from = APIParam.FROM.BODY, nullable = true)
            },
            entity = ProductInfo.class)
    ResourceResult create(String body);

    @PATCH
    @Path("/product/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @API(authorizeType = API.AuthorizeType.ADMIN_ROLE,
            params = {
                    @APIParam(name = "id", from = APIParam.FROM.PATH),
                    @APIParam(name = "name", from = APIParam.FROM.BODY),
                    @APIParam(name = "description", from = APIParam.FROM.BODY),
                    @APIParam(name = "price", from = APIParam.FROM.BODY),
                    @APIParam(name = "model", from = APIParam.FROM.BODY),
                    @APIParam(name = "brand", from = APIParam.FROM.BODY),
                    @APIParam(name = "masterPic", from = APIParam.FROM.BODY),
                    @APIParam(name = "kindId", from = APIParam.FROM.BODY),
                    @APIParam(name = "userId", from = APIParam.FROM.BODY),
                    @APIParam(name = "remark", from = APIParam.FROM.BODY, nullable = true)
            },
            entity = ProductInfo.class)
    ResourceResult update(@PathParam("id") Long productId, String body);

    @GET
    @Path("/product/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @API(authorizeType = API.AuthorizeType.ANONYMOUS,
            params = {
                    @APIParam(name = "id", from = APIParam.FROM.PATH)
            },
            entity = ProductInfo.class)
    ResourceResult get(@PathParam("id") Long productId);


    @DELETE
    @Path("/product/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @API(authorizeType = API.AuthorizeType.ADMIN_ROLE,
            params = {
                    @APIParam(name = "id", from = APIParam.FROM.PATH)
            },
            entity = ProductInfo.class)
    ResourceResult delete(@PathParam("id") Long productId);


    @POST
    @Path("/product:search")
    @Produces(MediaType.APPLICATION_JSON)
    @API(authorizeType = API.AuthorizeType.ANONYMOUS,
            params = {
                    @APIParam(name = "page", from = APIParam.FROM.BODY),
                    @APIParam(name = "size", from = APIParam.FROM.BODY),
                    @APIParam(name = "sort", from = APIParam.FROM.BODY),
                    @APIParam(name = "order", from = APIParam.FROM.BODY),
                    @APIParam(name = "returnAttrs", from = APIParam.FROM.BODY),
                    @APIParam(name = "conditions", from = APIParam.FROM.BODY),
            },
            entity = SearchCondition.class)
    ResourceResult search(String body);
}
