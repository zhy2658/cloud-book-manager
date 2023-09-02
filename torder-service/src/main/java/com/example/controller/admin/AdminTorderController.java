package com.example.controller.admin;

import com.example.entity.Result;
import com.example.entity.Torder;
import com.example.entity.Tuser;
import com.example.service.TorderService;
import com.example.utils.RedisUtil;
import com.example.utils.Res;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/torder/admin")
public class AdminTorderController {

    @Resource
    TorderService torderService;

    @Resource
    RedisTemplate redisTemplate;

    @RequestMapping("/list")
    public Result list(HttpServletRequest request){
        Map map = Res.getMap();
        List<Torder> torderList=torderService.allTorder();
        map.put("torderList",torderList);

        return  Res.success(map);
    }

    @RequestMapping("/remove/{id}")
    public Result remove(@PathVariable("id") Integer id){
        boolean success=torderService.delete(id);
        if(!success) return Res.err();
        return Res.success();
    }
}

