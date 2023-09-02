package com.example.service;

import com.example.entity.Tuser;

import java.util.List;

public interface UserService {

    Tuser getUserById(int uid);

    Tuser authUser(Tuser tuser);

    List<Tuser> list();

    boolean update(Tuser tuser);

    int getUserCount();

//    int getRemain(int uid);
//
//    boolean setRemain(int uid,int count);


}
