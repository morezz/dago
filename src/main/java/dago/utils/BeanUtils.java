package dago.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import javax.persistence.Column;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * BeanUtils
 *
 * @author <a href="mailto:jzchen@coremail.cn">jzchen</a>
 */
public abstract class BeanUtils {

    public static <T> T map2Bean(Map<String, Object> map, Class<T> c) {
        try {
            T t = c.newInstance();
            BeanWrapperImpl beanWrapper = new BeanWrapperImpl(t);
            PropertyDescriptor[] propertyDescriptors = beanWrapper.getPropertyDescriptors();
            if (propertyDescriptors != null) {
                for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
                    if (propertyDescriptor.getReadMethod() != null && propertyDescriptor.getWriteMethod() != null) {
                        String name = propertyDescriptor.getName();
                        beanWrapper.setPropertyValue(name, map.get(name));
                    }
                }
                return t;
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static  Map<String, Object> bean2Map(Object obj) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        BeanWrapper beanWrapper = new BeanWrapperImpl(obj);
        PropertyDescriptor[] descriptor = beanWrapper.getPropertyDescriptors();
        for (PropertyDescriptor aDescriptor : descriptor) {
            String name = aDescriptor.getName();
            paramMap.put(name, beanWrapper.getPropertyValue(name));
        }
        return paramMap;
    }

    public static void copyProperties(Object sourceObj, Object targetObj) {
        org.springframework.beans.BeanUtils.copyProperties(sourceObj, targetObj);
    }

    public static String sqlOrderColumn(String property, Class<?> clazz) {
        try {
            Field field = clazz.getDeclaredField(property);
            Column column = field.getAnnotation(Column.class);
            if (column != null) {
                String name = column.name();
                if (StringUtils.isNotBlank(name)) {
                    return name;
                }
            }
        } catch (Exception e) {
            // ignore
        }
        return null;
    }
}
