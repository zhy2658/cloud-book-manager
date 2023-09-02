package com.example.controller;

import com.example.entity.Result;
import com.example.entity.Tuser;
import com.example.service.UserService;
import com.example.utils.RedisUtil;
import com.example.utils.Res;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RefreshScope
@RequestMapping("/user")
public class UserController {

    @Resource
    UserService userService;

    @Resource
    RabbitTemplate template;

    @Resource
    RedisTemplate redisTemplate;



    @RequestMapping("/{id}")
    public Result getUser(@PathVariable("id") int id , HttpServletRequest request){
        Map map = Res.getMap();
        Tuser tuser = userService.getUserById(id);
        map.put("tuser",tuser);
       return Res.success(map);
    }

    @PostMapping("/login")
    public Result login(HttpServletRequest request,@RequestBody Tuser tuser){
//        System.out.println("/user/login  我被调用！！！");
        Map map = Res.getMap();
        Tuser tuser1=userService.authUser(tuser);
        map.put("tuser",tuser1);
        return Res.success(map);
    }

    @PostMapping("/info")
    public Result getInfo(HttpServletRequest request){
        Map map = Res.getMap();

        String token =request.getHeader("token");

        Tuser tuser = RedisUtil.inject(redisTemplate).getUser(token);
        map.put("tuser",tuser);

        return Res.success(map);
    }

    @RequestMapping("/reduceCoin/{coin}")
    public Result reduceCoin(@PathVariable("coin") double coin ,HttpServletRequest request){


        String token =request.getHeader("token");

        Tuser tuser = RedisUtil.inject(redisTemplate).getUser(token);

        double finalNum = Math.round( ( tuser.getCoin() - coin ) * 100) / 100;

        if(finalNum < 0){
            return  Res.err();
        }

        tuser.setCoin(finalNum);
        RedisUtil.inject(redisTemplate).saveUser(token,tuser);
        userService.update(tuser);

        return Res.success();
    }



//    @RequestMapping("/user/remain/{uid}")
//    public int userRemain(@PathVariable("uid") int uid){
//        return userService.getRemain(uid);
//    }
//
//    @RequestMapping("/user/borrow/{uid}")
//    public boolean userBorrow(@PathVariable("uid") int uid){
//        int remain=userService.getRemain(uid);
//        System.out.println(remain+"___");
//        return userService.setRemain(uid,remain-1);
//    }




}
