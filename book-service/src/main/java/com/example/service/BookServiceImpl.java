package com.example.service;

import com.alibaba.fastjson.JSONObject;
import com.example.entity.Book;
import com.example.entity.Result;
import com.example.entity.Tuser;
import com.example.mapper.BookMapper;
import com.example.service.client.UserClient;
import com.example.utils.Util;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.json.Json;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Service
public class BookServiceImpl implements BookService{

    @Resource
    BookMapper bookMapper;

    @Resource
    UserClient userClient;

    @Override
    public Book getBookById(int id) {

        return bookMapper.getBookById(id);


    }

    @Override
    public List<Book> getAll() {
        return bookMapper.getAll();
    }

    @Override
    public boolean add(Book book) {
        return bookMapper.add(book) > 0;
    }

    @Override
    public boolean delete(int id) {
        return bookMapper.delete(id) > 0;
    }

    @Override
    public boolean update(Book book) {
        return bookMapper.update(book) > 0 ;
    }

    @Override
    public int getCount() {
        return bookMapper.count();
    }
}
