package dago.parameter.filter;

import dago.parameter.annotation.API;
import dago.parameter.annotation.APIParam;
import dago.utils.JsonUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * ParameterFilter
 *
 * @author <a href="mailto:morezzww@gmail.com">More</a>
 */
@javax.ws.rs.ext.Provider
public class ParameterFilter implements ContainerRequestFilter {


    private static final Logger LOGGER = LoggerFactory.getLogger(ParameterFilter.class);

    @Context
    private ResourceInfo resourceInfo;

    @Context
    private HttpServletRequest servletRequest;

    @Context
    private HttpServletResponse servletResponse;

    @Context
    private ServletContext sc;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        Long startTime = System.currentTimeMillis();
        Method method = resourceInfo.getResourceMethod();
        API api = method.getAnnotation(API.class);
        for (Class<?> i : method.getDeclaringClass().getInterfaces()) {
            try {
                Method iMethod = i.getDeclaredMethod(method.getName(), method.getParameterTypes());
                api = iMethod.getDeclaredAnnotation(API.class);
            } catch (NoSuchMethodException e) {
                // ignore
            }
            if (api != null) {
                break;
            }
        }

        APIContext context = new APIContext(servletRequest, servletResponse, startTime);

        // 读取body信息，验证格式，并且根据body重新设置InputStream, 防止方法中读取不到body
        String body = IOUtils.toString(requestContext.getEntityStream(), "UTF-8");
        InputStream in = IOUtils.toInputStream(body, "UTF-8");
        requestContext.setEntityStream(in);
        if (!StringUtils.isBlank(body)) {
            try {
                Map bodyMap = JsonUtils.json2SObjectMap(body);
                context.setBodyParameter(bodyMap);
            } catch (Exception e) {
                requestContext.abortWith(Response.status(Response.Status.BAD_REQUEST)
                        .entity("post body is not json.").build());
                LOGGER.warn("errorMsg:" + e.getMessage() + ",body:" + body + ",ip:" + servletRequest.getRemoteHost());
                return;
            }
        }
        context.setQueryParameter(requestContext.getUriInfo().getQueryParameters());
        APIContext.setCurrentInstance(context);


        APIParam[] params = api.params();
        if (params != null && params.length > 0) {
            for (APIParam param : params) {
                APIParam.FROM from = param.from();
                Object value = null;
                switch (from) {
                case PATH:
                    value = context.getPathParameter().getFirst(param.name());
                    break;
                case QUERY:
                    value = context.getQueryParameter().getFirst(param.name());
                    break;
                case BODY:
                    value = context.getBodyParameter().get(param.name());
                    break;
                case HEADER:
                    value = context.getHeaderParameter().get(param.name());
                    break;
                default:
                    LOGGER.error("invalid from attr");
                    requestContext.abortWith(Response.status(Response.Status.BAD_REQUEST)
                            .entity("invalid from attr").build());
                }
                if (!param.nullable() && null == value) {
                    LOGGER.error(param.name() + " is null");
                    requestContext.abortWith(Response.status(Response.Status.BAD_REQUEST)
                            .entity("attr: \"" + param.name() + "\" is null").build());
                    return;
                }

                try {
                    Class<?> clazz = api.entity();
                    if (!clazz.getDeclaredField(param.name()).getType().getName().equals(value.getClass().getTypeName())) {
                        LOGGER.error("attr: \"" + param.name()
                                + "\" request " + clazz.getDeclaredField(param.name()).getType().getName()
                                + ", found " + value.getClass().getTypeName());
                        requestContext.abortWith(Response.status(Response.Status.BAD_REQUEST)
                                .entity("attr: \"" + param.name() + "\" request " + clazz.getDeclaredField(param.name()).getType().getName()
                                        + ", found " + value.getClass().getTypeName()).build());
                        return;
                    }
                } catch (NoSuchFieldException e) {
                    // ignore
                }
            }
        }
    }


}

