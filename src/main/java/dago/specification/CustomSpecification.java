package dago.specification;

import dago.common.SearchCondition;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * CustomSpecification
 *
 * @author <a href="mailto:morezzww@gmail.com">More</a>.
 */

public class CustomSpecification<T> implements Specification<T> {

    private SearchCondition searchCondition;

    public CustomSpecification(SearchCondition searchCondition) {
        this.searchCondition = searchCondition;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicateList = new ArrayList<Predicate>();
        for (SearchCondition.Condition condition : searchCondition.getConditionList()) {
            switch (condition.getOp().toLowerCase()) {
            case "eq":
                predicateList.add(builder.equal(root.get(condition.getName()), condition.getVal()));
                break;
            case "neq":
                predicateList.add(builder.notEqual(root.get(condition.getName()), condition.getVal()));
                break;
            case "in":
                predicateList.add(builder.in(root.get(condition.getName())).value(condition.getVal()));
                break;
            case "like":
                predicateList.add(builder.like(root.get(condition.getName()), condition.getVal().toString()));
                break;
            default:
            }
        }
        if ("desc".equals(searchCondition.getSort().toLowerCase())) {
            query.orderBy(builder.desc(root.get(searchCondition.getOrder())));
        } else if ("asc".equals(searchCondition.getSort().toLowerCase())) {
            query.orderBy(builder.asc(root.get(searchCondition.getOrder())));
        }
        Predicate[] pre = new Predicate[predicateList.size()];
        return query.where(predicateList.toArray(pre)).getRestriction();
    }
}
