package com.example.mapper;

import com.example.entity.Torder;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TorderMapper {

    @Select("select * from torder where uid = #{uid}")
    List<Torder> list(@Param("uid") int uid);

    @Select("select * from torder ")
    List<Torder> allTorder();

    @Select("select * from torder where id = #{id}")
    Torder getOne(@Param("id") int id);

    @Update("delete from torder where id= #{id}")
    int delete(int id);

    @Insert("insert into torder(orderNo,bid,uid,unitPrice,amount,quantity,address,tel,consignee) values(#{orderNo},#{bid},#{uid},#{unitPrice},#{amount},#{quantity},#{address},#{tel},#{consignee})")
    int add(Torder torder);

}
