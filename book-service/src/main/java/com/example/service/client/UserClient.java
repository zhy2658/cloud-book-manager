package com.example.service.client;

import com.example.entity.Result;
import com.example.entity.Tuser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@FeignClient(value = "userservice")
public interface UserClient {

    @PostMapping("/user/info")
    Result getInfo();

//    @RequestMapping("/user/{uid}")
//    Tuser getUser(@PathVariable("uid") int uid );


}
