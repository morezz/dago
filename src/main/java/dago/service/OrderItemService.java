package dago.service;

import dago.common.SearchCondition;
import dago.model.OrderItem;
import dago.result.ServiceResult;

import java.util.List;

/**
 * OrderItemService
 *
 * @author <a href="mailto:morezzww@gmail.com">More</a>
 */
public interface OrderItemService {
    ServiceResult create(OrderItem orderItem);

    ServiceResult update(Long id, OrderItem orderItem);

    ServiceResult get(Long orderId, Long id);

    ServiceResult delete(Long orderId, Long id);

    @SuppressWarnings("unchecked")
    ServiceResult search(Integer page, Integer size, SearchCondition searchCondition, List<String> returnAttrs);
}
