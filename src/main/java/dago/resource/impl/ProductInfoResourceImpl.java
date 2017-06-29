package dago.resource.impl;

import com.google.gson.reflect.TypeToken;
import dago.common.SearchCondition;
import dago.model.ProductInfo;
import dago.resource.ProductInfoResource;
import dago.result.ResourceResult;
import dago.result.ServiceResult;
import dago.service.ProductInfoService;
import dago.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * ProductInfoResourceImpl
 *
 * @author <a href="mailto:morezzww@gmail.com">More</a>
 */

public class ProductInfoResourceImpl implements ProductInfoResource {


    @Autowired
    private ProductInfoService productInfoService;


    public ResourceResult create(String body) {
        ProductInfo productInfo = JsonUtils.getAsEntity(body, new TypeToken<ProductInfo>() {
        });

        ServiceResult createResult = productInfoService.create(productInfo);

        return new ResourceResult(createResult.getCode(), createResult.getMessage(),
                Collections.singletonMap("productInfoId", createResult.getResult()));

    }

    public ResourceResult update(Long id, String body) {
        ProductInfo productInfo = JsonUtils.getAsEntity(body, new TypeToken<ProductInfo>() {
        });

        ServiceResult updateResult = productInfoService.update(id, productInfo);

        return new ResourceResult(updateResult.getCode(), updateResult.getMessage(),
                Collections.singletonMap("productInfoId", updateResult.getResult()));

    }


    public ResourceResult get(Long id) {

        ServiceResult getResult = productInfoService.get(id);
        return new ResourceResult(getResult.getCode(), getResult.getMessage(), getResult.getResult());

    }

    public ResourceResult delete(Long id) {
        ServiceResult deleteResult = productInfoService.delete(id);
        return new ResourceResult(deleteResult.getCode(), deleteResult.getMessage());
    }


    public ResourceResult search(String body) {
        Integer page = JsonUtils.getAsInt(body, "page");
        Integer size = JsonUtils.getAsInt(body, "size");
        List<String> returnAttrs = JsonUtils.getAsStringList(body, "returnAttrs");
        SearchCondition searchCondition = new SearchCondition(body);

        ServiceResult searchResult = productInfoService.search(page, size, searchCondition, returnAttrs);
        Map<String, Object> resultMap = (Map<String, Object>) searchResult.getResult();
        resultMap.put("page", page);
        resultMap.put("size", size);
        return ResourceResult.successResult(resultMap);


    }

}
