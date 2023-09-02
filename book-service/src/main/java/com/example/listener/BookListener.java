package com.example.listener;



import com.example.entity.Book;
import com.example.entity.Tuser;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class BookListener {

//    @RabbitListener(queues = "yyds", messageConverter = "jackson1")
//    public Map<String,Object> test(Book book){
//
//        System.out.println(book);
//
//        Map<String,Object> map = new HashMap<>();
//        map.put("success",true);
//
//        return map;
//    }
}
