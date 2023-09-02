package com.example.controller.admin;

import com.example.entity.Book;
import com.example.entity.Result;
import com.example.service.BookService;
import com.example.utils.Res;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/book/admin")
public class AdminBookController {


    @Resource
    BookService bookService;

    @Resource   
    private RedisTemplate redisTemplate;

    @Value("${bookImgPath}")
    String bookImgPath;


    @RequestMapping("/list")
    public Result list(){
        Map map = Res.getMap();
        List<Book> bookList = bookService.getAll();
        map.put("bookList",bookList);

        return Res.success(map);
    }

    @RequestMapping("/add")
    public Result add(Book book, MultipartFile file1){
        Map map = Res.getMap();
        System.out.println(file1);
        File file= new File(bookImgPath+"//"+file1.getOriginalFilename());
        try{
            file1.transferTo(file);
        }catch (Exception e ){
            e.printStackTrace();
        }
        book.setImg("http://localhost/static/uploadImg/"+file1.getOriginalFilename());
        System.out.println(book);
        bookService.add(book);

        return Res.success(map);
    }

    @RequestMapping("/update")
    public Result update(Book book, MultipartFile file1){
        Map map = Res.getMap();
        System.out.println(file1);
        File file= new File(bookImgPath+"//"+file1.getOriginalFilename());
        try{
            file1.transferTo(file);
        }catch (Exception e ){
            e.printStackTrace();
        }
        book.setImg("http://localhost/static/uploadImg/"+file1.getOriginalFilename());
        System.out.println(book);
        bookService.update(book);

        return Res.success(map);

    }

    @RequestMapping("/remove/{id}")
    public Result remove(@PathVariable("id") Integer id){
        boolean success=bookService.delete(id);
        if(!success) return Res.err();
        return Res.success();
    }
    @RequestMapping("/count")
    public Result count(){
        Map map = Res.getMap();

        int count =bookService.getCount();
        map.put("bookCount",count);

        return  Res.success(map);
    }


}
