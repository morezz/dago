package dago.resource.impl;

import com.google.gson.reflect.TypeToken;
import dago.common.SearchCondition;
import dago.model.Order;
import dago.resource.OrderResource;
import dago.result.ResourceResult;
import dago.result.ServiceResult;
import dago.service.OrderService;
import dago.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * OrderResourceImpl
 *
 * @author <a href="mailto:morezzww@gmail.com">More</a>
 */
public class OrderResourceImpl implements OrderResource {

    @Autowired
    private OrderService orderService;


    public ResourceResult create(String body) {
        Order order = JsonUtils.getAsEntity(body, new TypeToken<Order>() {
        });

        ServiceResult createResult = orderService.create(order);

        return new ResourceResult(createResult.getCode(), createResult.getMessage(),
                Collections.singletonMap("orderId", createResult.getResult()));

    }

    public ResourceResult update(Long id, String body) {
        Order order = JsonUtils.getAsEntity(body, new TypeToken<Order>() {
        });

        ServiceResult updateResult = orderService.update(id, order);

        return new ResourceResult(updateResult.getCode(), updateResult.getMessage(),
                Collections.singletonMap("orderId", updateResult.getResult()));

    }


    public ResourceResult get(Long id) {

        ServiceResult getResult = orderService.get(id);
        return new ResourceResult(getResult.getCode(), getResult.getMessage(), getResult.getResult());

    }

    public ResourceResult delete(Long id) {
        ServiceResult deleteResult = orderService.delete(id);
        return new ResourceResult(deleteResult.getCode(), deleteResult.getMessage());
    }


    public ResourceResult search(String body) {
        Integer page = JsonUtils.getAsInt(body, "page");
        Integer size = JsonUtils.getAsInt(body, "size");
        List<String> returnAttrs = JsonUtils.getAsStringList(body, "returnAttrs");
        SearchCondition searchCondition = new SearchCondition(body);

        ServiceResult searchResult = orderService.search(page, size, searchCondition, returnAttrs);
        Map<String, Object> resultMap = (Map<String, Object>) searchResult.getResult();
        resultMap.put("page", page);
        resultMap.put("size", size);
        return ResourceResult.successResult(resultMap);


    }

}
