package com.example.utils;

import com.alibaba.fastjson.JSONObject;
import com.example.entity.Tuser;

import java.util.Map;

public class Util {



    public static Object transMap(Object str ,Class clazz){
        String s=JSONObject.toJSONString(str);
        Object object=JSONObject.parseObject(s,clazz);

        return object;
    }
}
