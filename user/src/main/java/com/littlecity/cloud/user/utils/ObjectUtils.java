package com.littlecity.cloud.user.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

/**
 * @author huangxiaocheng
 * @Date 2019/10/12
 **/
public class ObjectUtils {

    public static <T> T mapToObject(Map<String, Object> map, Class<T> beanClass) {
        if (map == null) {
            return null;
        }

        ObjectMapper mapper = new ObjectMapper();
        T value = mapper.convertValue(map, beanClass);

        return value;
    }

    public static Map<String, Object> toMap(Object object) {
        if (object == null) {
            return null;
        }

        ObjectMapper mapper = new ObjectMapper();

        Map<String, Object> map = mapper.convertValue(object, Map.class);

        return map;
    }

}
