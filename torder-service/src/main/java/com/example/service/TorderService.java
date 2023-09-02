package com.example.service;

import com.example.entity.Torder;

import java.util.List;

public interface TorderService {

    List<Torder> list(int uid);

    Torder getOne(int id);

    boolean add(Torder torder);

    List<Torder> allTorder();

    boolean delete(int id);

}
