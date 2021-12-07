package org.zzj.common.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;

/**
 * @Author:fl
 * @Date:2017/11/15
 */
public class MapUtil {

    /**
     * listmap  过滤
     *
     * @param listMap
     * @param keys    过滤的key
     * @return
     */
    public static List<Map<String, Object>> filterMapKey(List<Map<String, Object>> listMap, String... keys) {

        List<Map<String, Object>> listMapNew = new ArrayList<>();
        if (listMap != null && keys != null) {

            for (Map<String, Object> map : listMap) {

                for (String key : keys) {
                    map.remove(key);
                }
                listMapNew.add(map);
            }

        }
        return listMapNew;

    }


    /**
     * listmap  过滤
     *
     * @param listMap
     * @param keys       保留的key
     * @param keyToCamel key转驼峰
     * @return
     */
    public static List<Map<String, Object>> filterMapIncludeKey(List<Map<String, Object>> listMap, boolean keyToCamel, String... keys) {

        List<Map<String, Object>> listMapNew = new ArrayList<>();
        if (listMap != null && keys != null) {

            for (Map<String, Object> map : listMap) {

                Map<String, Object> newMap = new HashMap<>();
                for (String key : keys) {
                    String keyTmp = key;
                    if (keyToCamel) {
                        keyTmp = Underline2CamelUtil.underline2Camel(key, true);
                    }
                    newMap.put(keyTmp, map.get(key));
                }
                listMapNew.add(newMap);
            }

        }
        return listMapNew;

    }

    /**
     * listmap  过滤
     *
     * @param listMap
     * @param keys    保留的key
     * @return
     */
    public static List<Map<String, Object>> filterMapIncludeKey(List<Map<String, Object>> listMap, String... keys) {

        List<Map<String, Object>> listMapNew = new ArrayList<>();
        if (listMap != null && keys != null) {

            for (Map<String, Object> map : listMap) {

                Map<String, Object> newMap = new HashMap<>();
                for (String key : keys) {
                    newMap.put(key, map.get(key));
                }
                listMapNew.add(newMap);
            }

        }
        return listMapNew;

    }


    /**
     * map key转下划线
     *
     * @param targetMap
     * @return
     */
    public static Map keyCamel2Underline(Map<String, Object> targetMap) {

        Map map = new HashMap();

        for (Map.Entry<String, Object> entry : targetMap.entrySet()) {
            String key = entry.getKey().toString();
            Object value = entry.getValue();
//            System.out.println("key=" + key + " value=" + value);
            if (key.contains("_")) {
                map.put(key, value);
            } else {
                map.put(Underline2CamelUtil.camel2Underline(key), value);
            }
        }

        return map;
    }

    public static <T> Object mapToObject(Map<String, Object> map, Class<T> beanClass) {
        if (map == null)
            return null;

        try {
            Object obj = beanClass.newInstance();

            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                Method setter = property.getWriteMethod();
                if (setter != null) {
                    Object object = map.get(property.getName());
                    try {
                        Class classType = property.getPropertyType();

//                    ,Integer,int
                        if (classType.isAssignableFrom(Byte.class)) {
                            if (StringUtil.isNotBlank(object)) {
                                String str = object.toString();
                                if ("false".equalsIgnoreCase(str) || "true".equalsIgnoreCase(str)) {
                                    if ("false".equalsIgnoreCase(str)) {
                                        str = "0";
                                    } else {
                                        str = "1";
                                    }
                                    setter.invoke(obj, Byte.valueOf(str));
                                } else {
                                    setter.invoke(obj, Byte.valueOf(object.toString()));
                                }
                            }
                        } else if (classType.isAssignableFrom(Integer.class)) {
                            if (StringUtil.isNotBlank(object)) {
                                setter.invoke(obj, Integer.valueOf(object.toString()));
                            }
                        }else if (classType.isAssignableFrom(Long.class)) {
                            if (StringUtil.isNotBlank(object)) {
                                setter.invoke(obj, Long.valueOf(object.toString()));
                            }
                        }
                        else if (classType.isAssignableFrom(BigDecimal.class)) {
                            if (StringUtil.isNotBlank(object)) {
                                setter.invoke(obj, new BigDecimal(object.toString()));
                            }
                        }else if (classType.isAssignableFrom(Date.class)) {

                            if (StringUtil.isNotBlank(object)) {
                                String str = object.toString();
                                Date date = null;
                                //2017-05-17
                                if (str.length() == 10) {
                                    date = DateUtil.parseDate(str, DateUtil.DF_YYYY_MM_DD);
                                    //2017-05-17 10:00:00
                                } else if (str.length() == 19) {
                                    date = DateUtil.parseDate(str, DateUtil.DF_YY_MM_DD_HH_MI_SS);

                                    //2017-05-17T10:00:00+08:00
//                                    2018-09-10 17:42:52.936
                                } else if (str.length() > 19&&str.contains("T")) {
                                    str = str.replace("T", " ").substring(0, str.indexOf("+"));
                                    date = DateUtil.parseDate(str, DateUtil.DF_YY_MM_DD_HH_MI_SS);

                                }else if (str.length() > 19&&str.contains("T")) {
                                    date = DateUtil.parseDate(str, DateUtil.DF_YY_MM_DD_HH_MI_SS_SSS);

                                }
                                setter.invoke(obj, date);
                            }
                        } else {
                            setter.invoke(obj, object!=null? String.valueOf(object):null);

                        }


                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("property:"+property+",object:"+object+",setter:"+setter.getName());
                    }
                }
            }

            return (T) obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Map<String, Object> objectToMap(Object obj, boolean keyCamel2Underline) throws Exception {
        if (obj == null)
            return null;

        Map<String, Object> map = new HashMap<String, Object>();

        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor property : propertyDescriptors) {
            String key = property.getName();
            if (key.compareToIgnoreCase("class") == 0) {
                continue;
            }
            Method getter = property.getReadMethod();
            Object value = getter != null ? getter.invoke(obj) : null;
            if (keyCamel2Underline) {
                map.put(Underline2CamelUtil.camel2Underline(key), value);
            } else {
                map.put(key, value);
            }

        }

        return map;
    }

    public static Map<String, Object> objectToMap(Object obj) {
        if (obj == null)
            return null;

        Map<String, Object> map = new HashMap<String, Object>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                if (key.compareToIgnoreCase("class") == 0) {
                    continue;
                }
                Method getter = property.getReadMethod();
                Object value = getter != null ? getter.invoke(obj) : null;
                if (StringUtil.isNotBlank(value)) {
                    map.put(key, value);
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return map;
    }


    /**
     * map key下划线传驼峰
     *
     * @param targetMap
     * @return
     */
    public static Map keyUnderline2Camel(Map<String, Object> targetMap) {

        Map map = new HashMap();

        for (Map.Entry<String, Object> entry : targetMap.entrySet()) {
            String key = entry.getKey().toString();
            Object value = entry.getValue();
//            System.out.println("key=" + key + " value=" + value);
            map.put(Underline2CamelUtil.underline2Camel(key, true), value);
        }

        return map;
    }

}
