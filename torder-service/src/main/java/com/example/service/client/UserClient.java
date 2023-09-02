package com.example.service.client;

import com.example.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@FeignClient(value = "userservice")
public interface UserClient {

    @RequestMapping("/user/{id}")
    Result getUser(@PathVariable("id") int id);

    @RequestMapping("/user/reduceCoin/{coin}")
    Result reduceCoin(@PathVariable("coin") double coin);

//    @RequestMapping("/user/{uid}")
//    Tuser getUser(@PathVariable("uid") int uid );


}
