package com.example.service;

import com.alibaba.fastjson.JSONObject;
import com.example.entity.Result;
import com.example.entity.Tuser;
import com.example.service.client.UserClient;
import com.example.utils.Util;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService{

    @Resource
    UserClient userClient;


    @Override
    public Tuser AuthUser(Tuser tuser) {

        Result result =userClient.login(tuser);
        Tuser tuser2= (Tuser) Util.transMap(result.getMap().get("tuser"),Tuser.class);
        return tuser2;
    }
}
