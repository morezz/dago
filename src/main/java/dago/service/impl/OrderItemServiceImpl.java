package dago.service.impl;

import dago.common.SearchCondition;
import dago.converter.CustomConverter;
import dago.model.OrderItem;
import dago.repository.OrderItemRepository;
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
 * OrderItemServiceImpl
 *
 * @author <a href="mailto:morezzww@gmail.com">More</a>
 */
@Service
public class OrderItemServiceImpl implements dago.service.OrderItemService {


    private static final Logger LOGGER = LoggerFactory.getLogger(OrderItemServiceImpl.class);

    @Autowired
    private OrderItemRepository orderItemRepository;


    @Override
    public ServiceResult create(OrderItem orderItem) {

        LOGGER.debug("orderItem create, orderItem: {}", orderItem);

        orderItemRepository.save(orderItem);

        return ServiceResult.successResult(orderItem.getId());


    }

    @Override
    public ServiceResult update(Long id, OrderItem orderItem) {
        LOGGER.debug("orderItem update, id: {}, orderItem: {}", id, orderItem);
        orderItem.setId(id);
        orderItemRepository.save(orderItem);
        return ServiceResult.successResult(orderItem.getId());

    }

    @Override
    public ServiceResult get(Long orderId, Long id) {
        LOGGER.debug("orderItem get, id: {}", id);
        OrderItem orderItem = orderItemRepository.findOne(id);
        return ServiceResult.successResult(orderItem);
    }

    @Override
    public ServiceResult delete(Long orderId, Long id) {
        LOGGER.debug("orderItem delete, id: {}", id);
        orderItemRepository.delete(id);
        return ServiceResult.successResult();
    }


    @Override
    @SuppressWarnings("unchecked")
    public ServiceResult search(Integer page, Integer size, SearchCondition searchCondition, List<String> returnAttrs) {
        LOGGER.debug("orderItem search, page: {}, size: {}, searchCondition: {}, returnAttrs: {}", page, size,
                searchCondition, returnAttrs);

        Specification specification = new CustomSpecification(searchCondition);

        Pageable pageable = new PageRequest(page, size);
        CustomConverter<OrderItem> customConverter = new CustomConverter<>(returnAttrs);
        Page<Map<Object, Object>> result = orderItemRepository.findAll(specification, pageable).map(customConverter);
        List<Map<Object, Object>> resultList = result.getContent();


        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("data", resultList);
        resultMap.put("total", resultList.size());
        return ServiceResult.successResult(resultMap);

    }

}
