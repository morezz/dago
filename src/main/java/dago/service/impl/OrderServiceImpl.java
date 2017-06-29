package dago.service.impl;

import dago.common.SearchCondition;
import dago.converter.CustomConverter;
import dago.model.Order;
import dago.repository.OrderRepository;
import dago.result.ServiceResult;
import dago.specification.CustomSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * OrderServiceImpl
 *
 * @author <a href="mailto:morezzww@gmail.com">More</a>
 */
@Service
public class OrderServiceImpl implements dago.service.OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderRepository orderRepository;


    @Override
    public ServiceResult create(Order order) {

        LOGGER.debug("order create, order: {}", order);

        orderRepository.save(order);

        return ServiceResult.successResult(order.getId());


    }

    @Override
    public ServiceResult update(Long id, Order order) {
        LOGGER.debug("order update, id: {}, order: {}", id, order);
        order.setId(id);
        orderRepository.save(order);
        return ServiceResult.successResult(order.getId());

    }

    @Override
    public ServiceResult get(Long id) {
        LOGGER.debug("order get, id: {}", id);
        Order order = orderRepository.findOne(id);
        return ServiceResult.successResult(order);
    }

    @Override
    public ServiceResult delete(Long id) {
        LOGGER.debug("order delete, id: {}", id);
        orderRepository.delete(id);
        return ServiceResult.successResult();
    }


    @Override
    @SuppressWarnings("unchecked")
    public ServiceResult search(Integer page, Integer size, SearchCondition searchCondition, List<String> returnAttrs) {
        LOGGER.debug("order search, page: {}, size: {}, searchCondition: {}, returnAttrs: {}", page, size,
                searchCondition, returnAttrs);

        Specification specification = new CustomSpecification(searchCondition);

        Pageable pageable = new PageRequest(page, size);
        CustomConverter<Order> customConverter = new CustomConverter<>(returnAttrs);
        Page<Map<Object, Object>> result = orderRepository.findAll(specification, pageable).map(customConverter);
        List<Map<Object, Object>> resultList = result.getContent();


        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("data", resultList);
        resultMap.put("total", resultList.size());
        return ServiceResult.successResult(resultMap);

    }
}
