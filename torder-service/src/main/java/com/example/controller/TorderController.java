package com.example.controller;

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
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/torder")
public class TorderController {

    @Resource
    TorderService torderService;

    @Resource
    RedisTemplate redisTemplate;

    @RequestMapping("/list")
    public Result list(HttpServletRequest request){
        Map map = Res.getMap();
        Tuser tuser =RedisUtil.inject(redisTemplate).getUser(request.getHeader("token"));
        List<Torder> torderList=torderService.list(tuser.getId());
        map.put("torderList",torderList);

        return  Res.success(map);
    }

    @RequestMapping("/{id}")
    public Result list(@PathVariable("id") int id){
        Map map = Res.getMap();
        Torder torder=torderService.getOne(id);
        map.put("torder",torder);

        return  Res.success(map);
    }

    @RequestMapping("/add")
    public Result add(Torder torder){
        Map map=Res.getMap();
        torder.setOrderNo(UUID.randomUUID().toString());
        boolean success = torderService.add(torder);
        System.out.println(success);
        return  success ? Res.success():Res.fail(map).setMessage("你的硬币数量不够！");
    }

}
