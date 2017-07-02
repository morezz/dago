package dago.parameter.filter;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import java.io.IOException;

/**
 * ResponseCleanFilter
 *
 * @author <a href="mailto:morezzww@gmail.com">More</a>
 */
public class ResponseCleanFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {


        APIContext context = APIContext.getCurrentInstance();
        if (context != null) {
            context.doOpLog();
        }
    }


}
