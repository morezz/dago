package dago.service.impl;

import dago.common.SearchCondition;
import dago.converter.CustomConverter;
import dago.model.Stock;
import dago.repository.StockRepository;
import dago.result.ServiceResult;
import dago.service.StockService;
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
 * StockServiceImpl
 *
 * @author <a href="mailto:morezzww@gmail.com">More</a>
 */
@Service
public class StockServiceImpl implements StockService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StockServiceImpl.class);

    @Autowired
    private StockRepository stockRepository;



    public ServiceResult create(Stock stock) {

        LOGGER.debug("product create, stock: {}", stock);

        stockRepository.save(stock);

        return ServiceResult.successResult(stock.getId());


    }

    public ServiceResult update(Long id, Stock stock) {
        LOGGER.debug("stock update, id: {}, stock: {}", id, stock);
        stock.setId(id);
        stockRepository.save(stock);
        return ServiceResult.successResult(stock.getId());

    }

    public ServiceResult get(Long id) {
        LOGGER.debug("stock get, id: {}", id);
        Stock stock = stockRepository.findOne(id);
        return ServiceResult.successResult(stock);
    }

    public ServiceResult delete(Long id) {
        LOGGER.debug("stock delete, id: {}", id);
        stockRepository.delete(id);
        return ServiceResult.successResult();
    }


    @SuppressWarnings("unchecked")
    public ServiceResult search(Integer page, Integer size, SearchCondition searchCondition, List<String> returnAttrs) {
        LOGGER.debug("stock search, page: {}, size: {}, searchCondition: {}, returnAttrs: {}", page, size,
                searchCondition, returnAttrs);

        Specification specification = new CustomSpecification(searchCondition);

        Pageable pageable = new PageRequest(page, size);
        CustomConverter<Stock> customConverter = new CustomConverter<>(returnAttrs);
        Page<Map<Object, Object>> result = stockRepository.findAll(specification, pageable).map(customConverter);
        List<Map<Object, Object>> resultList = result.getContent();


        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("data", resultList);
        resultMap.put("total", resultList.size());
        return ServiceResult.successResult(resultMap);

    }


}
