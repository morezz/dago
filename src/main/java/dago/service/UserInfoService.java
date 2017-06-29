package dago.service;

import dago.common.SearchCondition;
import dago.model.UserInfo;
import dago.result.ServiceResult;

import java.util.List;

/**
 * UserInfoService
 *
 * @author <a href="mailto:morezzww@gmail.com">More</a>
 */
public interface UserInfoService {
    ServiceResult create(UserInfo userInfo);

    ServiceResult update(Long id, UserInfo userInfo);

    ServiceResult get(Long id);

    ServiceResult delete(Long id);

    @SuppressWarnings("unchecked")
    ServiceResult search(Integer page, Integer size, SearchCondition searchCondition, List<String> returnAttrs);
}
