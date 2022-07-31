package com.yanzhen.utils;

import com.yanzhen.entity.User;
import org.springframework.cglib.beans.BeanMap;

import java.util.HashMap;
import java.util.Map;

public class MapUtils {

    //目标对象
    private Map<String,Object> paramMap = new HashMap<>();

    private MapUtils() {}

    private MapUtils(String key,String value) {
        paramMap.put(key,value);
    }

    //build方法的目的是构建MapUtils对象，因为MapUtils对象有一个Map集合
    public static MapUtils build(String key,String value) {
        //支持构建传参数
        return new MapUtils(key,value);
    }

    //构建空MapUtils对象
    public static MapUtils build() {
        return new MapUtils();
    }

    //通过方法设置key,value
    public MapUtils put(String key,Object value) {
        paramMap.put(key,value);
        return this;
    }

    //向Map集合中追加Map对象（支持链式调用）
    public MapUtils put(Map<String,Object> map) {
        for (Map.Entry<String,Object> entry : map.entrySet()) {
            map.put(entry.getKey(),entry.getValue());
        }
        return this;
    }

    //获取目标对象
    public Map<String,Object> getMap() {
        return paramMap;
    }

    //对象转成Map
    public <T> Map<String,Object> beanToMap(T bean) {
        if(bean != null) {
            //借助Spring的工具类
            BeanMap beanMap = BeanMap.create(bean);
            //循环
            for (Object key : beanMap.keySet()) {
                paramMap.put(key+"",beanMap.get(key));
            }
        }
        return paramMap;
    }

    //对象转成Map（针对修改操作，适应dao层的sql语句，为了区分修改字段和修改条件）
    public <T> Map<String,Object> beanToMapForUpdate(T bean) {
        if(bean != null) {
            //借助Spring的工具类
            BeanMap beanMap = BeanMap.create(bean);
            //循环
            for (Object key : beanMap.keySet()) {
                paramMap.put("update"+MapUtils.upperFirstLetter(key+""),beanMap.get(key));
            }
        }
        return paramMap;
    }

    //将字符串的首字母转成大写
    public static String upperFirstLetter(String str) {
        char[] chars = str.toCharArray();
        if(chars[0] >= 'a' && chars[0] <= 'z') {
            chars[0] = (char) (chars[0] - 32);
        }
        return new String(chars);
    }

    //测试
    public static void main(String[] args) {
        User user = new User();
        user.setId(1);
        user.setUserName("zhangsan");
        Map<String, Object> map = MapUtils.build().beanToMap(user);
        Map<String, Object> map2 = MapUtils.build().beanToMapForUpdate(user);
        System.out.println(map);
        System.out.println("========================");
        System.out.println(map2);
    }
}
