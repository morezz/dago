package dago.utils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * JsonUtils
 *
 * @author <a href="mailto:morezzww@gmail.com">More</a>
 */
public abstract class JsonUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtils.class);
    private static final Character SEPARATOR = '/';

    //TODO JsonUtils的写法有点问题，这里更合适的是Parse成一个JsonObject的结果

    /**
     * @param json    example {"root":{"code":"x", result:"x"}}、{"root":{"code":{"a":"x", "b":"y"}, result:"x"}}......
     * @param pattern example root/code root/result ......
     *
     * @return
     */
    private static JsonElement resultEle(String json, String pattern) {
        if (StringUtils.isBlank(json)) {
            return null;
        }
        JsonObject jsonObject = string2JsonObject(json);
        if (jsonObject == null) {
            return null;
        }
        String[] items = StringUtils.split(pattern, SEPARATOR);
        JsonElement resultElement = jsonObject;
        for (String item : items) {
            try {
                resultElement = resultElement.getAsJsonObject().get(item);
            } catch (Throwable e) {
                LOGGER.error("parse json err, json:{}, pattern:{}", json, pattern);
                resultElement = null;
                break;
            }
        }
        return resultElement;
    }

    public static String getAsString(String json, String pattern) {
        JsonElement resultElement = resultEle(json, pattern);
        String result = null;
        try {
            result = resultElement != null ? StringUtils.strip(resultElement.getAsString(), "\"") : null;
        } catch (UnsupportedOperationException e) {
            result = StringUtils.strip(resultElement.toString(), "\"");
        }
        return result;
    }

    private static String element2String(JsonElement ele) {
        return ele == null ? null : StringUtils.strip(ele.toString(), "\"");
    }

    public static Map<String, String> getAsString(String json, String... patterns) {
        Map<String, String> result = new HashMap<String, String>();
        for (String pattern : patterns) {
            JsonElement resultElement = resultEle(json, pattern);
            String patternResult = resultElement != null ? element2String(resultElement) : null;
            if (StringUtils.isNotBlank(patternResult)) {
                result.put(pattern, patternResult);
            }
        }
        return result;
    }

    public static Integer getAsInt(String json, String pattern) {
        JsonElement resultElement = resultEle(json, pattern);
        return resultElement != null && resultElement.isJsonPrimitive() ? resultElement.getAsInt() : null;
    }

    public static Map<String, Integer> getAsInt(String json, String... patterns) {
        Map<String, Integer> result = new HashMap<String, Integer>();
        for (String pattern : patterns) {
            JsonElement resultElement = resultEle(json, pattern);
            Integer patternResult = resultElement != null && resultElement.isJsonPrimitive() ? resultElement.getAsInt() : null;
            if (patternResult != null) {
                result.put(pattern, patternResult);
            }
        }
        return result;
    }

    public static Long getAsLong(String json, String pattern) {
        JsonElement resultElement = resultEle(json, pattern);
        return resultElement != null && resultElement.isJsonPrimitive() ? resultElement.getAsLong() : null;
    }

    public static Map<String, Long> getAsLong(String json, String... patterns) {
        Map<String, Long> result = new HashMap<String, Long>();
        for (String pattern : patterns) {
            JsonElement resultElement = resultEle(json, pattern);
            Long patternResult = resultElement != null && resultElement.isJsonPrimitive() ? resultElement.getAsLong() : null;
            if (patternResult != null) {
                result.put(pattern, patternResult);
            }
        }
        return result;
    }

    public static Double getAsDouble(String json, String pattern) {
        JsonElement resultElement = resultEle(json, pattern);
        return resultElement != null && resultElement.isJsonPrimitive() ? resultElement.getAsDouble() : null;
    }

    public static Map<String, Double> getAsDouble(String json, String... patterns) {
        Map<String, Double> result = new HashMap<String, Double>();
        for (String pattern : patterns) {
            JsonElement resultElement = resultEle(json, pattern);
            Double patternResult = resultElement != null && resultElement.isJsonPrimitive() ? resultElement.getAsDouble() : null;
            if (patternResult != null) {
                result.put(pattern, patternResult);
            }
        }
        return result;
    }

    public static Float getAsFloat(String json, String pattern) {
        JsonElement resultElement = resultEle(json, pattern);
        return resultElement != null && resultElement.isJsonPrimitive() ? resultElement.getAsFloat() : null;
    }

    public static Map<String, Float> getAsFloat(String json, String... patterns) {
        Map<String, Float> result = new HashMap<String, Float>();
        for (String pattern : patterns) {
            JsonElement resultElement = resultEle(json, pattern);
            Float patternResult = resultElement != null && resultElement.isJsonPrimitive() ? resultElement.getAsFloat() : null;
            if (patternResult != null) {
                result.put(pattern, patternResult);
            }
        }
        return result;
    }

    public static Boolean getAsBoolean(String json, String pattern) {
        JsonElement resultElement = resultEle(json, pattern);
        return resultElement != null && resultElement.isJsonPrimitive() ? resultElement.getAsBoolean() : null;
    }

    public static Map<String, Boolean> getAsBoolean(String json, String... patterns) {
        Map<String, Boolean> result = new HashMap<String, Boolean>();
        for (String pattern : patterns) {
            JsonElement resultElement = resultEle(json, pattern);
            Boolean patternResult = resultElement != null && resultElement.isJsonPrimitive() ? resultElement.getAsBoolean() : null;
            if (patternResult != null) {
                result.put(pattern, patternResult);
            }
        }
        return result;
    }

    public static Map<String, String> getAsStringMap(String json, String pattern) {
        Map<String, String> result = new HashMap<String, String>();
        JsonElement resultElement = resultEle(json, pattern);
        if (resultElement != null) {
            if (!resultElement.isJsonObject()) {
                result.put(element2String(resultElement), element2String(resultElement));
            } else {
                // jsonObject 解析成Map,
                JsonObject resultObj = resultElement.getAsJsonObject();
                for (Map.Entry<String, JsonElement> entry : resultObj.entrySet()) {
                    result.put(entry.getKey(), element2String(entry.getValue()));
                }
            }
        }
        return result;
    }

    public static List<Map<String, String>> getAsListMap(String json, String pattern) {
        List<String> liststr = getAsStringList(json, pattern);
        List<Map<String, String>> result = new ArrayList<Map<String, String>>();
        for (String childJson : liststr) {
            result.add(json2Map(childJson));
        }
        return result;
    }

    public static List<String> getAsStringList(String json, String pattern) {
        List<String> result = new ArrayList<String>();
        JsonElement resultElement = resultEle(json, pattern);
        if (resultElement != null) {
            if (!resultElement.isJsonArray()) {
                result.add(element2String(resultElement));
            } else {
                for (JsonElement ele : resultElement.getAsJsonArray()) {
                    result.add(element2String(ele));
                }
            }
        }
        return result;
    }

    public static List<Long> getAsLongList(String json, String pattern) {
        List<Long> result = new ArrayList<Long>();
        JsonElement resultElement = resultEle(json, pattern);
        if (resultElement != null && resultElement.isJsonArray()) {
            for (JsonElement ele : resultElement.getAsJsonArray()) {
                try {
                    result.add(ele.getAsLong());
                } catch (Throwable e) {
                    // ignore
                }
            }
        }
        return result;
    }

    public static List<Integer> getAsIntList(String json, String pattern) {
        List<Integer> result = new ArrayList<Integer>();
        JsonElement resultElement = resultEle(json, pattern);
        if (resultElement != null && resultElement.isJsonArray()) {
            for (JsonElement ele : resultElement.getAsJsonArray()) {
                try {
                    result.add(ele.getAsInt());
                } catch (Throwable e) {
                    // ignore
                }
            }
        }
        return result;
    }

    public static <T> List<T> getAsGenList(String json, String pattern, TypeToken<List<T>> typeToken) {
        Gson gson = new Gson();
        return gson.fromJson(getAsString(json, pattern), typeToken.getType());
    }


    public static <T> T getAsEntity(String json, TypeToken<T> typeToken) {
        Gson gson = new Gson();
        return gson.fromJson(json, typeToken.getType());
    }


    public static String object2Json(Object obj) {
        Gson gson = new Gson();
        return gson.toJson(obj);
    }

    public static Map<String, String> json2Map(String json) {
        Gson gson = new Gson();
        Map<String, String> result = gson.fromJson(json, new TypeToken<Map<String, String>>() {
        }.getType());
        return result;
    }

    public static JsonObject string2JsonObject(String httpEntity) {
        JsonParser parser = new JsonParser();
        JsonElement je = parser.parse(httpEntity);
        return je.getAsJsonObject();
    }

}
