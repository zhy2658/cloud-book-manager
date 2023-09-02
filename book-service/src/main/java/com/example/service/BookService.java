package com.example.service;

import com.example.entity.Book;
import org.apache.ibatis.annotations.Select;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface BookService {

    Book getBookById(int id);

    List<Book> getAll();

    boolean add(Book book);

    boolean delete(int id);

    boolean update(Book book);

    int getCount();
}
