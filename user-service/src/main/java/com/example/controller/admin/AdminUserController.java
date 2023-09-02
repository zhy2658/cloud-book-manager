package com.example.controller.admin;

import com.example.entity.Result;
import com.example.entity.Tuser;
import com.example.service.UserService;
import com.example.utils.Res;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user/admin")
public class AdminUserController {

    @Resource
    UserService userService;

    @Resource
    RabbitTemplate template;

    @Resource
    RedisTemplate redisTemplate;

    @RequestMapping("/list")
    public Result list(){
        Map map = Res.getMap();

         List<Tuser>  list =userService.list();
         map.put("list",list);

        return  Res.success(map);
    }

    @RequestMapping("/count")
    public Result count(){
        Map map = Res.getMap();

        int count =userService.getUserCount();
        map.put("userCount",count);

        return  Res.success(map);
    }

}
