package cn.lu.vblog.util;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * com.example.vblog.util
 *
 * @author lkxBruce
 * @date 2021/11/9 23:21
 * @email lkxbruce@gmail.com
 * @project VBlog
 */

@Component
public class RedisUtils {

    private final static long NOT_EXPIRE = -1;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    /**
     * 创建键值对并设置过期时间
     * @param key
     * @param value
     * @param expire
     */
    public void set(String key,Object value,long expire){
        redisTemplate.opsForValue().set(key, JSON.toJSONString(value), expire, TimeUnit.SECONDS);
    }

    /**
     * 创建键值对
     * @param key
     * @param value
     */
    public void set(String key,Object value){
        redisTemplate.opsForValue().set(key, JSON.toJSONString(value));
    }

    public void expire(String key,long expire){
        redisTemplate.expire(key,expire,TimeUnit.SECONDS);
    }

    public <T> T getObj(String key,Class<T> type,long expire){
        String json = (String) redisTemplate.opsForValue().get(key);
        T value = JSON.parseObject(json, type);
        if(expire != NOT_EXPIRE){
            expire(key,expire);
        }
        return value;
    }

    public <T> T getObj(String key,Class<T> type){
        return getObj(key, type, NOT_EXPIRE);
    }

    public String get(String key,long expire){
        String value = getObj(key,String.class);
        if(expire != NOT_EXPIRE){
            expire(key,expire);
        }
        return value;
    }

    public String get(String key){
        return get(key,NOT_EXPIRE);
    }
}
