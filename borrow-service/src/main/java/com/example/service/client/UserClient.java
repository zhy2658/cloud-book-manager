package com.example.service.client;

import com.example.entity.Tuser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "userservice")
public interface UserClient {

    @RequestMapping("/user/{uid}")
    Tuser getUser(@PathVariable("uid") int uid );

    @RequestMapping("/user/borrow/{uid}")
    boolean userBorrow(@PathVariable("uid") int uid);

    @RequestMapping("/user/remain/{uid}")
    int userRemain(@PathVariable("uid") int uid);

}
