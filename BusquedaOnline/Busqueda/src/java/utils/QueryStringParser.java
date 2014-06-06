package utils;
import java.util.*;
import java.net.URLDecoder;

/**
 * Like HttpUtils.parseQueryString, except that it never throws parse
 * exceptions.
 */
public class QueryStringParser {

    public static Map<String, String> getQueryMap(String query) {
        String[] params = query.split("&");
        Map<String, String> map = new HashMap<String, String>();
        for (String param : params) {
            String name = param.split("=")[0];
            String value = param.split("=")[1];
            map.put(name, value);
        }
        return map;
    }

    public static String getParamValue(String query, String keyName) {
        Map<String, String> map = getQueryMap(query);
        Set<String> keys = map.keySet();
        String value = null;
        for (String key : keys) {
            if (key.equals(keyName)) {
                value = URLDecoder.decode(map.get(key).replace('+', ' '));
            }
        }
        return value;
    }

    public static void printQuereyStringValues(String query) {
        Map<String, String> map = getQueryMap(query);
        Set<String> keys = map.keySet();
        for (String key : keys) {
            System.out.println("Name=" + key);
            System.out.println("Value=" + URLDecoder.decode(map.get(key).replace('+', ' ')));
        }
    }
}
