package com.example.service.client;

import com.example.entity.Result;
import com.example.entity.Tuser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "userservice")
public interface UserClient {

    @PostMapping("/user/login")
    Result login(@RequestBody Tuser tuser);


//    @RequestMapping("/user/{uid}")
//    Tuser getUser(@PathVariable("uid") int uid );


}
