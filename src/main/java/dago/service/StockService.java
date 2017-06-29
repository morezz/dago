package dago.service;

import dago.common.SearchCondition;
import dago.model.Stock;
import dago.result.ServiceResult;

import java.util.List;

/**
 * StockService
 *
 * @author <a href="mailto:morezzww@gmail.com">More</a>
 */
public interface StockService {
    ServiceResult create(Stock stock);

    ServiceResult update(Long id, Stock stock);

    ServiceResult get(Long id);

    ServiceResult delete(Long id);

    @SuppressWarnings("unchecked")
    ServiceResult search(Integer page, Integer size, SearchCondition searchCondition, List<String> returnAttrs);
}
