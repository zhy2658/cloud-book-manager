package com.example.service.client;

import com.example.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "bookservice")
public interface BookClient {

    @RequestMapping("/book/{bid}")
    Result getBook(@PathVariable int bid);

}
