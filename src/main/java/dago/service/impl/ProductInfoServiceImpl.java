package dago.service.impl;

import dago.common.SearchCondition;
import dago.converter.CustomConverter;
import dago.model.ProductInfo;
import dago.repository.ProductInfoRepository;
import dago.result.ServiceResult;
import dago.service.ProductInfoService;
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
 * ProductInfoServiceImpl
 *
 * @author <a href="mailto:morezzww@gmail.com">More</a>
 */

@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductInfoServiceImpl.class);

    @Autowired
    private ProductInfoRepository productInfoRepository;


    public ServiceResult create(ProductInfo productInfo) {

        LOGGER.debug("product create, productInfo: {}", productInfo);

        productInfoRepository.save(productInfo);

        return ServiceResult.successResult(productInfo.getId());


    }

    public ServiceResult update(Long id, ProductInfo productInfo) {
        LOGGER.debug("product update, id: {}, productInfo: {}", id, productInfo);
        productInfo.setId(id);
        productInfoRepository.save(productInfo);
        return ServiceResult.successResult(productInfo.getId());

    }

    public ServiceResult get(Long id) {
        LOGGER.debug("product get, id: {}", id);
        ProductInfo productInfo = productInfoRepository.findOne(id);
        return ServiceResult.successResult(productInfo);
    }

    public ServiceResult delete(Long id) {
        LOGGER.debug("product delete, id: {}", id);
        productInfoRepository.delete(id);
        return ServiceResult.successResult();
    }


    @SuppressWarnings("unchecked")
    public ServiceResult search(Integer page, Integer size, SearchCondition searchCondition, List<String> returnAttrs) {
        LOGGER.debug("product search, page: {}, size: {}, searchCondition: {}, returnAttrs: {}", page, size,
                searchCondition, returnAttrs);

        Specification specification = new CustomSpecification(searchCondition);

        Pageable pageable = new PageRequest(page, size);
        CustomConverter<ProductInfo> customConverter = new CustomConverter<>(returnAttrs);
        Page<Map<Object, Object>> result = productInfoRepository.findAll(specification, pageable).map(customConverter);
        List<Map<Object, Object>> resultList = result.getContent();


        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("data", resultList);
        resultMap.put("total", resultList.size());
        return ServiceResult.successResult(resultMap);

    }
}
