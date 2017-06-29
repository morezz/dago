package dago.service;

import dago.common.SearchCondition;
import dago.model.Order;
import dago.result.ServiceResult;

import java.util.List;

/**
 * OrderService
 *
 * @author <a href="mailto:morezzww@gmail.com">More</a>
 */
public interface OrderService {
    ServiceResult create(Order order);

    ServiceResult update(Long id, Order order);

    ServiceResult get(Long id);

    ServiceResult delete(Long id);

    @SuppressWarnings("unchecked")
    ServiceResult search(Integer page, Integer size, SearchCondition searchCondition, List<String> returnAttrs);
}
