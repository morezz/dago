package dago.converter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CustomConverter
 *
 * @author <a href="mailto:morezzww@gmail.com">More</a>.
 */

public class CustomConverter<S> implements Converter<S, Map> {
    private List<String> attrList;

    public CustomConverter(List<String> attrList) {
        this.attrList = attrList;
    }

    @Override
    public Map convert(S source) {

        Map result = new HashMap();
        for (String attr : attrList) {
            try {
                Method method = source.getClass().getDeclaredMethod("get" + StringUtils.capitalize(attr), null);
                result.put(attr, method.invoke(source));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
//                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return result;
    }


}
