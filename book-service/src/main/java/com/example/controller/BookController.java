package com.example.controller;


import com.example.entity.Book;
import com.example.entity.Result;
import com.example.entity.Tuser;
import com.example.mapper.BookMapper;
import com.example.service.BookService;
import com.example.utils.RedisUtil;
import com.example.utils.Res;
import com.example.utils.Util;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
public class BookController {

    @Resource
    BookService bookService;

    @Resource
    private RedisTemplate redisTemplate;


    @RequestMapping("/book/{bid}")
    public Result getBook(@PathVariable int bid){
        Map map = Res.getMap();
        map.put("book",bookService.getBookById(bid));

        return Res.success(map);
    }


    @RequestMapping("/book/all")
    public Result getall(){
        Map map = Res.getMap();
        map.put("bookList",bookService.getAll());

        return Res.success(map);
    }

    @RequestMapping("/book/random")
    public Result random(){
        Map map = Res.getMap();
        List<Book> bookList= bookService.getAll();
        Collections.shuffle(bookList);
        map.put("bookList",bookList);
        return Res.success(map);
    }



//    @RequestMapping("/book/remain/{bid}")
//    public int bookRemain(@PathVariable("bid") int bid){
//        return bookService.getRemain(bid);
//    }
//
//    @RequestMapping("/book/borrow/{bid}")
//    public boolean bookBorrow(@PathVariable("bid") int bid){
//        int remain=bookService.getRemain(bid);
//        return bookService.setRemain(bid,remain-1);
//    }

}
