package dago.parameter.filter;

import dago.parameter.annotation.API;
import dago.result.ResourceResult;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MultivaluedMap;
import java.util.Map;

/**
 * APIContext
 *
 * @author <a href="mailto:morezzww@gmail.com">More</a>
 */
public class APIContext {


    private static final Logger LOGGER = LoggerFactory.getLogger(APIContext.class);


    private Map headerParameter;
    private MultivaluedMap<String, String> pathParameter;
    private MultivaluedMap<String, String> queryParameter;
    private Map bodyParameter;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private Long startTime;
    private ResourceResult result;

    private API API;

    private static ThreadLocal<APIContext> instance = new ThreadLocal<>();

    public APIContext(HttpServletRequest request, HttpServletResponse response, Long startTime) {
        this.request = request;
        this.response = response;
        this.startTime = startTime;
    }


    public void doOpLog() {

        StringBuffer buff = new StringBuffer("request:{");
        buff.append("pathParams:").append(this.parseToJson(pathParameter))
                .append(",queryParams:").append(this.parseToJson(queryParameter))
                .append(",bodyParams").append(this.parseToJson(bodyParameter));
        buff.append("}, response:").append(this.parseToJson(this.result));
        buff.append(", opTime:").append(System.currentTimeMillis() - startTime);
        buff.append(", requestURL:").append(request.getRequestURL());
        buff.append(", ip:").append(request.getRemoteHost());
        LOGGER.info(buff.toString());

    }


    private String parseToJson(Object object) {

        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonResult = mapper.writer().writeValueAsString(object);
            return jsonResult;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }


    public Map getHeaderParameter() {
        return headerParameter;
    }

    public void setHeaderParameter(Map headerParameter) {
        this.headerParameter = headerParameter;
    }

    public MultivaluedMap<String, String> getPathParameter() {
        return pathParameter;
    }

    public void setPathParameter(MultivaluedMap<String, String> pathParameter) {
        this.pathParameter = pathParameter;
    }

    public MultivaluedMap<String, String> getQueryParameter() {
        return queryParameter;
    }

    public void setQueryParameter(MultivaluedMap<String, String> queryParameter) {
        this.queryParameter = queryParameter;
    }

    public Map getBodyParameter() {
        return bodyParameter;
    }

    public void setBodyParameter(Map bodyParameter) {
        this.bodyParameter = bodyParameter;
    }

    public API getAPI() {
        return API;
    }

    public void setAPI(API API) {
        this.API = API;
    }

    public static APIContext getCurrentInstance() {
        return instance.get();
    }

    static void setCurrentInstance(APIContext context) {
        instance.set(context);
    }
}
