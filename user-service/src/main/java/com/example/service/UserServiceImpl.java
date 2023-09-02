package com.example.service;

import com.example.entity.Tuser;
import com.example.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

    @Override
    public Tuser getUserById(int id) {
        return userMapper.getUserById(id);
    }

    @Override
    public Tuser authUser(Tuser tuser) {

        return userMapper.getUser(tuser.getId(),tuser.getPassword());

    }

    @Override
    public List<Tuser> list() {
        return userMapper.list();
    }

    @Override
    public boolean update(Tuser tuser) {
        return userMapper.update(tuser) > 0;
    }

    @Override
    public int getUserCount() {
        return userMapper.count();
    }

//    @Override
//    public int getRemain(int uid) {
//        return userMapper.getUserBookRemain(uid);
//    }
//
//    @Override
//    public boolean setRemain(int uid, int count) {
//
//        return userMapper.updateBookCount(uid,count) > 0;
//    }


}
