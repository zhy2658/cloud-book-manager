package com.example.mapper;


import com.example.entity.Book;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;


@Mapper
public interface BookMapper {


    @Select("select * from book where id = #{id}")
    Book getBookById(int id);

    @Select("select * from book ")
    List<Book> getAll();

    @Insert("insert into book(name,description,price,author,img,wordCount,releaseTime,tags) values (#{name},#{description},#{price},#{author},#{img},#{wordCount},#{releaseTime},#{tags})")
    int add(Book book);

    @Update("delete from book where id= #{id}")
    int delete(int id);

    int update(Book book);

    @Select("select count(*) from book")
    int count();


}
