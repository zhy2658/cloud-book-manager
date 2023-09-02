package com.example.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.fastjson.JSONObject;
import com.example.entity.Borrow;
import com.example.entity.BorrowDetail;
import com.example.entity.Result;
import com.example.service.BorrowService;
import com.example.utils.Res;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
public class BorrowController {

    @Resource
    BorrowService borrowService;

    @RequestMapping("/borrow/{id}")
    public Result getBorrow(@PathVariable Integer id){
        Map map =Res.getMap();
        BorrowDetail borrowDetail = borrowService.getBorrowDetailByUid(id);
        map.put("borrowDetail",borrowDetail);
        return Res.success(map);
    }

//    @RequestMapping("/borrow/take/{uid}/{bid}")
//    JSONObject borrow(@PathVariable("uid") Integer uid,
//                      @PathVariable("bid") Integer bid){
//        borrowService.doBorrow(uid,bid);
//
//        JSONObject object=new JSONObject();
//        object.put("code",200);
//        object.put("success",false);
//        object.put("message","借阅成功！");
//
//        return object;
//
//    }


}
