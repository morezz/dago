package dago.service;

import dago.common.SearchCondition;
import dago.model.ProductInfo;
import dago.result.ServiceResult;

import java.util.List;

/**
 * ProductInfoService
 *
 * @author <a href="mailto:morezzww@gmail.com">More</a>
 */

public interface ProductInfoService {


    ServiceResult create(ProductInfo productInfo);

    ServiceResult update(Long id, ProductInfo productInfo);

    ServiceResult get(Long id);

    ServiceResult delete(Long id);

    ServiceResult search(Integer page, Integer size, SearchCondition searchCondition, List<String> returnAttrs);

}
