package dago.resource.impl;

import com.google.gson.reflect.TypeToken;
import dago.common.SearchCondition;
import dago.model.UserInfo;
import dago.resource.UserInfoResource;
import dago.result.ResourceResult;
import dago.result.ServiceResult;
import dago.service.UserInfoService;
import dago.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * UserInfoResourceImpl
 *
 * @author <a href="mailto:morezzww@gmail.com">More</a>
 */
public class UserInfoResourceImpl implements UserInfoResource {


    @Autowired
    private UserInfoService userInfoService;


    public ResourceResult create(String body) {
        UserInfo userInfo = JsonUtils.getAsEntity(body, new TypeToken<UserInfo>() {
        });

        ServiceResult createResult = userInfoService.create(userInfo);

        return new ResourceResult(createResult.getCode(), createResult.getMessage(),
                Collections.singletonMap("userInfoId", createResult.getResult()));

    }

    public ResourceResult update(Long id, String body) {
        UserInfo userInfo = JsonUtils.getAsEntity(body, new TypeToken<UserInfo>() {
        });

        ServiceResult updateResult = userInfoService.update(id, userInfo);

        return new ResourceResult(updateResult.getCode(), updateResult.getMessage(),
                Collections.singletonMap("userInfoId", updateResult.getResult()));

    }


    public ResourceResult get(Long id) {

        ServiceResult getResult = userInfoService.get(id);
        return new ResourceResult(getResult.getCode(), getResult.getMessage(), getResult.getResult());

    }

    public ResourceResult delete(Long id) {
        ServiceResult deleteResult = userInfoService.delete(id);
        return new ResourceResult(deleteResult.getCode(), deleteResult.getMessage());
    }


    public ResourceResult search(String body) {
        Integer page = JsonUtils.getAsInt(body, "page");
        Integer size = JsonUtils.getAsInt(body, "size");
        List<String> returnAttrs = JsonUtils.getAsStringList(body, "returnAttrs");
        SearchCondition searchCondition = new SearchCondition(body);

        ServiceResult searchResult = userInfoService.search(page, size, searchCondition, returnAttrs);
        Map<String, Object> resultMap = (Map<String, Object>) searchResult.getResult();
        resultMap.put("page", page);
        resultMap.put("size", size);
        return ResourceResult.successResult(resultMap);


    }
}
