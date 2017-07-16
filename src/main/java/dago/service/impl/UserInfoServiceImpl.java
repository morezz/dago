package dago.service.impl;

import dago.common.SearchCondition;
import dago.converter.CustomConverter;
import dago.model.UserInfo;
import dago.repository.UserInfoRepository;
import dago.result.ServiceResult;
import dago.service.UserInfoService;
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
 * UserInfoServiceImpl
 *
 * @author <a href="mailto:morezzww@gmail.com">More</a>
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserInfoServiceImpl.class);

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public ServiceResult create(UserInfo userInfo) {

        LOGGER.debug("userInfo create, userInfo: {}", userInfo);
        userInfoRepository.save(userInfo);
        return ServiceResult.successResult(userInfo.getId());

    }


    @Override
    public ServiceResult update(Long id, UserInfo userInfo) {
        LOGGER.debug("userInfo update, id: {}, userInfo: {}", id, userInfo);
        userInfo.setId(id);
        userInfoRepository.save(userInfo);
        return ServiceResult.successResult(userInfo.getId());
    }


    @Override
    public ServiceResult get(Long id) {
        LOGGER.debug("userInfo get, id: {}", id);
        UserInfo userInfo = userInfoRepository.findOne(id);
        return ServiceResult.successResult(userInfo);
    }

    @Override
    public ServiceResult delete(Long id) {
        LOGGER.debug("userInfo delete, id: {}", id);
        userInfoRepository.delete(id);
        return ServiceResult.successResult();
    }


    @Override
    @SuppressWarnings("unchecked")
    public ServiceResult search(Integer page, Integer size, SearchCondition searchCondition, List<String> returnAttrs) {
//        LOGGER.debug("userInfo search, page: {}, size: {}, searchCondition: {}, returnAttrs: {}", page, size,
//                searchCondition, returnAttrs);

        Specification specification = new CustomSpecification(searchCondition);

        Pageable pageable = new PageRequest(page, size);
        CustomConverter<UserInfo> customConverter = new CustomConverter<>(returnAttrs);
        Page<Map<Object, Object>> result = userInfoRepository.findAll(specification, pageable).map(customConverter);
        List<Map<Object, Object>> resultList = result.getContent();


        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("data", resultList);
        resultMap.put("total", resultList.size());
        return ServiceResult.successResult(resultMap);

    }

    public UserInfo findByUsername(String username) {
        return userInfoRepository.findByUsername(username);
    }
}
