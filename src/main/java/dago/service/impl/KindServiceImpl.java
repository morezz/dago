package dago.service.impl;

import dago.common.SearchCondition;
import dago.converter.CustomConverter;
import dago.model.Kind;
import dago.repository.KindRepository;
import dago.result.ServiceResult;
import dago.service.KindService;
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
 * KindServiceImpl
 *
 * @author <a href="mailto:morezzww@gmail.com">More</a>
 */

@Service
public class KindServiceImpl implements KindService {

    private static final Logger LOGGER = LoggerFactory.getLogger(KindServiceImpl.class);

    @Autowired
    private KindRepository kindRepository;

    @Override
    public ServiceResult create(Kind kind) {

        LOGGER.debug("kind create, kind: {}", kind);
        kindRepository.save(kind);
        return ServiceResult.successResult(kind.getId());

    }


    @Override
    public ServiceResult update(Long id, Kind kind) {
        LOGGER.debug("kind update, id: {}, kind: {}", id, kind);
        kind.setId(id);
        kindRepository.save(kind);
        return ServiceResult.successResult(kind.getId());
    }


    @Override
    public ServiceResult get(Long id) {
        LOGGER.debug("kind get, id: {}", id);
        Kind kind = kindRepository.findOne(id);
        return ServiceResult.successResult(kind);
    }

    @Override
    public ServiceResult delete(Long id) {
        LOGGER.debug("kind delete, id: {}", id);
        kindRepository.delete(id);
        return ServiceResult.successResult();
    }


    @Override
    @SuppressWarnings("unchecked")
    public ServiceResult search(Integer page, Integer size, SearchCondition searchCondition, List<String> returnAttrs) {
        LOGGER.debug("kind search, page: {}, size: {}, searchCondition: {}, returnAttrs: {}", page, size,
                searchCondition, returnAttrs);

        Specification specification = new CustomSpecification(searchCondition);

        Pageable pageable = new PageRequest(page, size);
        CustomConverter<Kind> customConverter = new CustomConverter<>(returnAttrs);
        Page<Map<Object, Object>> result = kindRepository.findAll(specification, pageable).map(customConverter);
        List<Map<Object, Object>> resultList = result.getContent();


        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("data", resultList);
        resultMap.put("total", resultList.size());
        return ServiceResult.successResult(resultMap);

    }
}
