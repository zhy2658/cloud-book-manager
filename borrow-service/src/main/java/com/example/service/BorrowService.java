package com.example.service;

import com.example.entity.Borrow;
import com.example.entity.BorrowDetail;

import java.util.List;

public interface BorrowService {

    BorrowDetail getBorrowDetailByUid(int uid);

    boolean doBorrow(int uid,int bid);

}
