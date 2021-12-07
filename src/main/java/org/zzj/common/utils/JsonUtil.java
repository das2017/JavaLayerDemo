package org.zzj.common.utils;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.*;

public class JsonUtil {


    public static Map jsonToMap(JSONObject json) {

        Map map = new HashMap();
        if (json != null) {
            Iterator keys = json.keySet().iterator();

            while (keys.hasNext()) {
                Object key = keys.next();
                map.put(key, json.get(key));
            }

        }

        return map;
    }


    public static <T> List<T> converList(JSONArray jsonArray, Class<T> tclass) {

        List list = new ArrayList<T>();

        if (jsonArray != null && jsonArray.size() > 0) {

            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject json = jsonArray.getJSONObject(i);
                T object = (T) converObject(json, tclass);
                if (object != null) {
                    list.add(object);
                }
            }
        }
        return list;

    }

    /**
     * json转对象
     * @param jsonoBject
     * @param tclass
     * @param <T>
     * @return
     */
    public static <T> Object converObject(JSONObject jsonoBject, Class<T> tclass) {

        try {
            Map map = JsonUtil.jsonToMap(jsonoBject);
            if (map.size() > 0) {
                map = MapUtil.keyCamel2Underline(map);
                map = MapUtil.keyUnderline2Camel(map);
                Object object = MapUtil.mapToObject(map, tclass);
                return (T) object;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
}
