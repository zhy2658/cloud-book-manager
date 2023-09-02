package com.example.mapper;


import com.example.entity.Borrow;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BorrowMapper {

    @Select("select * from db_borrow where uid = #{uid}")
    List<Borrow> getBorrowByUid(int uid);

    @Select("select * from db_borrow where uid = #{uid} and bid = #{bid}")
    Borrow getBorrow(int uid,int bid);

    @Insert("insert into db_borrow(uid,bid) values(#{uid},#{bid})")
    int addBorrow(@Param("uid") int uid,@Param("bid") int bid);



}
