package dago.resource.impl;

import com.google.gson.reflect.TypeToken;
import dago.common.SearchCondition;
import dago.model.OrderItem;
import dago.resource.OrderItemResource;
import dago.result.ResourceResult;
import dago.result.ServiceResult;
import dago.service.OrderItemService;
import dago.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * OrderItemItemResourceImpl
 *
 * @author <a href="mailto:morezzww@gmail.com">More</a>
 */
public class OrderItemResourceImpl implements OrderItemResource {


    @Autowired
    private OrderItemService orderItemItemService;


    public ResourceResult create(Long orderId, String body) {
        OrderItem orderItem = JsonUtils.getAsEntity(body, new TypeToken<OrderItem>() {
        });

        orderItem.setOrderId(orderId);
        ServiceResult createResult = orderItemItemService.create(orderItem);

        return new ResourceResult(createResult.getCode(), createResult.getMessage(),
                Collections.singletonMap("orderItemId", createResult.getResult()));

    }

    public ResourceResult update(Long orderId, Long id, String body) {
        OrderItem orderItem = JsonUtils.getAsEntity(body, new TypeToken<OrderItem>() {
        });
        orderItem.setOrderId(orderId);
        ServiceResult updateResult = orderItemItemService.update(id, orderItem);

        return new ResourceResult(updateResult.getCode(), updateResult.getMessage(),
                Collections.singletonMap("orderItemId", updateResult.getResult()));

    }


    public ResourceResult get(Long orderId, Long id) {

        ServiceResult getResult = orderItemItemService.get(orderId, id);
        return new ResourceResult(getResult.getCode(), getResult.getMessage(), getResult.getResult());

    }

    public ResourceResult delete(Long orderId, Long id) {
        ServiceResult deleteResult = orderItemItemService.delete(orderId, id);
        return new ResourceResult(deleteResult.getCode(), deleteResult.getMessage());
    }


    public ResourceResult search(String body) {
        Integer page = JsonUtils.getAsInt(body, "page");
        Integer size = JsonUtils.getAsInt(body, "size");
        List<String> returnAttrs = JsonUtils.getAsStringList(body, "returnAttrs");
        SearchCondition searchCondition = new SearchCondition(body);

        ServiceResult searchResult = orderItemItemService.search(page, size, searchCondition, returnAttrs);
        Map<String, Object> resultMap = (Map<String, Object>) searchResult.getResult();
        resultMap.put("page", page);
        resultMap.put("size", size);
        return ResourceResult.successResult(resultMap);

    }

}
