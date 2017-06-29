package dago.resource.impl;

import com.google.gson.reflect.TypeToken;
import dago.common.SearchCondition;
import dago.model.Stock;
import dago.resource.StockResource;
import dago.result.ResourceResult;
import dago.result.ServiceResult;
import dago.service.StockService;
import dago.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * StockResourceImpl
 *
 * @author <a href="mailto:morezzww@gmail.com">More</a>
 */
public class StockResourceImpl implements StockResource {

    @Autowired
    private StockService stockService;


    public ResourceResult create(String body) {
        Stock stock = JsonUtils.getAsEntity(body, new TypeToken<Stock>() {
        });

        ServiceResult createResult = stockService.create(stock);

        return new ResourceResult(createResult.getCode(), createResult.getMessage(),
                Collections.singletonMap("stockId", createResult.getResult()));

    }

    public ResourceResult update(Long id, String body) {
        Stock stock = JsonUtils.getAsEntity(body, new TypeToken<Stock>() {
        });

        ServiceResult updateResult = stockService.update(id, stock);

        return new ResourceResult(updateResult.getCode(), updateResult.getMessage(),
                Collections.singletonMap("stockId", updateResult.getResult()));

    }


    public ResourceResult get(Long id) {

        ServiceResult getResult = stockService.get(id);
        return new ResourceResult(getResult.getCode(), getResult.getMessage(), getResult.getResult());

    }

    public ResourceResult delete(Long id) {
        ServiceResult deleteResult = stockService.delete(id);
        return new ResourceResult(deleteResult.getCode(), deleteResult.getMessage());
    }


    public ResourceResult search(String body) {
        Integer page = JsonUtils.getAsInt(body, "page");
        Integer size = JsonUtils.getAsInt(body, "size");
        List<String> returnAttrs = JsonUtils.getAsStringList(body, "returnAttrs");
        SearchCondition searchCondition = new SearchCondition(body);

        ServiceResult searchResult = stockService.search(page, size, searchCondition, returnAttrs);
        Map<String, Object> resultMap = (Map<String, Object>) searchResult.getResult();
        resultMap.put("page", page);
        resultMap.put("size", size);
        return ResourceResult.successResult(resultMap);


    }
}
