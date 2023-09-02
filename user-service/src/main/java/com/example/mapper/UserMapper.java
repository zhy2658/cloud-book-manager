package com.example.mapper;

import com.example.entity.Tuser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select * from tuser where id = #{id} ")
    Tuser getUserById(int id);

    @Select("select * from tuser where id = #{id} and password= #{password} ")
    Tuser getUser(int id, String password);

    @Select("select * from tuser")
    List<Tuser> list();

    int update(Tuser tuser);

    @Select("select count(*) from tuser")
    int count();

//    @Update("update db_user set book_count = #{count} where uid=#{uid}")
//    int updateBookCount( @Param("uid") int uid, @Param("count") int count);
}
