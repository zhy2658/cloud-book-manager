package com.example.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
public class Result implements Serializable {

    private int code = 200;

    private String message = "success";

    private Map map;

    // 后面result生成器需要以下方法
    public Result setCode(int  code){
        this.code = code;
        return this;
    }

    public Result setMessage(String message){
        this.message = message;
        return this;
    }

    public Result setData(Map map){
        this.map = map;
        return this;
    }

}