package dago.common;

import com.google.gson.reflect.TypeToken;
import dago.utils.JsonUtils;

import java.util.List;

/**
 * Condition
 *
 * @author <a href="mailto:morezzww@gmail.com">More</a>.
 */

public class SearchCondition {

    private String sort;
    private String order;
    private List<String> returnAttrs;
    private List<Condition> conditionList;

    public SearchCondition(String json) {
        this.sort = JsonUtils.getAsString(json, "sort");
        this.order = JsonUtils.getAsString(json, "order");
        this.returnAttrs = JsonUtils.getAsStringList(json, "returnAttrs");
        this.conditionList = JsonUtils.getAsGenList(json, "conditions", new TypeToken<List<Condition>>() {
        });
    }

    public void setCondition(String name, String op, String val) {
        Condition condition = new Condition(name, op, val);
        this.conditionList.add(condition);
    }

    public class Condition {
        private String name;
        private String op;
        private Object val;

        Condition(String name, String op, Object val) {
            this.name = name;
            this.op = op;
            this.val = val;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getOp() {
            return op;
        }

        public void setOp(String op) {
            this.op = op;
        }

        public Object getVal() {
            return val;
        }

        public void setVal(Object val) {
            this.val = val;
        }
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public List<Condition> getConditionList() {
        return conditionList;
    }

    public void setConditionList(List<Condition> conditionList) {
        this.conditionList = conditionList;
    }

    public List<String> getReturnAttrs() {
        return returnAttrs;
    }

    public void setReturnAttrs(List<String> returnAttrs) {
        this.returnAttrs = returnAttrs;
    }
}
