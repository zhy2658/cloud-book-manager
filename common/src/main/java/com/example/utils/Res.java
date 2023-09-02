package com.example.utils;

import com.example.entity.Result;

import java.util.HashMap;
import java.util.Map;

public class Res {

    public static Map<String,Object> getMap(){
        return new HashMap<String,Object>();
    }
    public static Result success(){
        return  new Result();
    }
    public static Result success(Map map){
       return  new Result().setData(map);
    }
    public static Result err(){
        Result result=new Result();
        result.setCode(500);
        result.setMessage("服务器错误");
        return  result;
    }
    public static Result err(Map map){
        Result result=new Result();
        result.setCode(500);
        result.setMessage("服务器错误");
        return  result.setData(map);
    }
    public static Result err(Map map,String msg){
        Result result=new Result();
        result.setCode(500);
        result.setMessage(msg);
        return  result.setData(map);
    }

    public static Result fail(){
        Result result=new Result();
        result.setCode(403);
        result.setMessage("权限不足");
        return  result;
    }

    public static Result fail(Map map){
        Result result=new Result();
        result.setCode(403);
        result.setMessage("权限不足");
        return  result.setData(map);
    }
    public static Result fail(Map map,String msg){
        Result result=new Result();
        result.setCode(403);
        result.setMessage(msg);
        return  result.setData(map);
    }

}
