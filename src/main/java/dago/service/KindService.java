package dago.service;

import dago.common.SearchCondition;
import dago.model.Kind;
import dago.result.ServiceResult;

import java.util.List;

/**
 * KindService
 *
 * @author <a href="mailto:morezzww@gmail.com">More</a>
 */
public interface KindService {
    ServiceResult create(Kind kind);

    ServiceResult update(Long id, Kind kind);

    ServiceResult get(Long id);

    ServiceResult delete(Long id);

    @SuppressWarnings("unchecked")
    ServiceResult search(Integer page, Integer size, SearchCondition searchCondition, List<String> returnAttrs);
}
