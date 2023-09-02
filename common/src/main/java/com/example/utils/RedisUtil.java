package com.example.utils;

import com.example.entity.Tuser;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

public class RedisUtil {

    private static RedisTemplate redisTemplate;



    public static RedisUtil inject(RedisTemplate redisTempl){
        redisTemplate = redisTempl;
        return new RedisUtil();
    }

    public void saveUser(String token,Tuser tuser){
        redisTemplate.opsForValue().set(token,tuser, 3, TimeUnit.DAYS);
    }
    public Tuser getUser(String token){
        return (Tuser) redisTemplate.opsForValue().get(token);
    }



}
