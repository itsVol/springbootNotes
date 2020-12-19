package com.example.demo.cache;

/**
 * @author itsVol
 */
import org.springframework.web.bind.annotation.PostMapping;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 缓存工具类
 * @author itsVol
 */
public class CacheUtil {
    private static Map<String, Object>cacheMap=new ConcurrentHashMap<>();

    /**
     * 添加缓存
     * @param key 键
     * @param value 值
     */
    public static void addCache(String key,Object value){
        cacheMap.put(key, value);
    }
    public static Object getValue(String key){
        return cacheMap.get(key);
    }
    public static boolean containKey(String key){
        return cacheMap.containsKey(key);
    }
    public static void removeKey(String key){
        cacheMap.remove(key);
    }
    public static void setValue(String key,Object value){
        cacheMap.put(key, value);
    }

    @PostMapping("/getSubmitToken")
    public Object getSubmitToken(){
        String getSubmitToken= UUID.randomUUID().toString();
        CacheUtil.addCache(getSubmitToken,false);
       // JSONObject result = new JSONObject();
       // result.put("submitToken", submitToken);
       // return result;
        return false;
    }

}
