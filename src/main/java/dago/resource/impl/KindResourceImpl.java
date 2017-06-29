package dago.resource.impl;

import com.google.gson.reflect.TypeToken;
import dago.common.SearchCondition;
import dago.model.Kind;
import dago.resource.KindResource;
import dago.result.ResourceResult;
import dago.result.ServiceResult;
import dago.service.KindService;
import dago.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * KindResourceImpl
 *
 * @author <a href="mailto:morezzww@gmail.com">More</a>
 */
public class KindResourceImpl implements KindResource {


    private static final Logger LOGGER = LoggerFactory.getLogger(KindResourceImpl.class);


    @Autowired
    private KindService kindService;


    public ResourceResult create(String body) {
        Kind kind = JsonUtils.getAsEntity(body, new TypeToken<Kind>() {
        });

        ServiceResult createResult = kindService.create(kind);

        return new ResourceResult(createResult.getCode(), createResult.getMessage(),
                Collections.singletonMap("kindId", createResult.getResult()));
    }


    public ResourceResult update(Long id, String body) {
        Kind kind = JsonUtils.getAsEntity(body, new TypeToken<Kind>() {
        });

        ServiceResult updateResult = kindService.update(id, kind);

        return new ResourceResult(updateResult.getCode(), updateResult.getMessage(),
                Collections.singletonMap("kindId", updateResult.getResult()));
    }

    public ResourceResult get(Long id) {
        ServiceResult getResult = kindService.get(id);
        return new ResourceResult(getResult.getCode(), getResult.getMessage(), getResult.getResult());
    }


    public ResourceResult delete(Long id) {
        ServiceResult deleteResult = kindService.delete(id);
        return new ResourceResult(deleteResult.getCode(), deleteResult.getMessage(), deleteResult.getResult());
    }


    public ResourceResult search(String body) {
        Integer page = JsonUtils.getAsInt(body, "page");
        Integer size = JsonUtils.getAsInt(body, "size");
        List<String> returnAttrs = JsonUtils.getAsStringList(body, "returnAttrs");
        SearchCondition searchCondition = new SearchCondition(body);

        ServiceResult searchResult = kindService.search(page, size, searchCondition, returnAttrs);
        Map<String, Object> resultMap = (Map<String, Object>) searchResult.getResult();
        resultMap.put("page", page);
        resultMap.put("size", size);
        return ResourceResult.successResult(resultMap);
    }
}
