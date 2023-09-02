package com.example.controller;

import com.example.entity.AuthTuser;
import com.example.entity.Result;
import com.example.entity.Tuser;
import com.example.service.AuthService;
import com.example.utils.JWTUtils;
import com.example.utils.RedisUtil;
import com.example.utils.Res;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
public class AuthController {

    @Resource
    RabbitTemplate rabbitTemplate;

    @Resource
    AuthService authService;


    @Resource
    private RedisTemplate redisTemplate;

    @RequestMapping("/auth/login")
    public Result loign(Tuser tuser, HttpServletResponse response, HttpServletRequest request){
        Map map = Res.getMap();

        Tuser finalTuser = authService.AuthUser(tuser);
        System.out.println(finalTuser);
        String jwtcode=JWTUtils.getToken(finalTuser);
        AuthTuser authTuser= JWTUtils.getUser(JWTUtils.verify(jwtcode));

        RedisUtil.inject(redisTemplate).saveUser(jwtcode,finalTuser);

        map.put("authTuser",authTuser);
        map.put("user",finalTuser);
        map.put("token",jwtcode);

        return Res.success(map);
    }

}
