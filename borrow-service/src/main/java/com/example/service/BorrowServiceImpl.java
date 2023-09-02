package com.example.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.example.entity.Book;
import com.example.entity.Borrow;
import com.example.entity.BorrowDetail;
import com.example.entity.Tuser;
import com.example.mapper.BorrowMapper;
import com.example.service.client.BookClient;
import com.example.service.client.UserClient;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BorrowServiceImpl implements  BorrowService{

    @Resource
    BorrowMapper borrowMapper;

    @Resource
    UserClient userClient;

    @Resource
    BookClient bookClient;

    @SentinelResource(value = "detailssss",blockHandler = "blocked")
    @Override
    public BorrowDetail getBorrowDetailByUid(int uid) {
        List<Borrow> borrowList= borrowMapper.getBorrowByUid(uid);

        Tuser tuser = userClient.getUser(uid);
        List<Book> bookList= borrowList
                .stream()
                .map( b -> bookClient.getBook(b.getId()))
                .collect(Collectors.toList());

        return new BorrowDetail(tuser,bookList);
    }

    @GlobalTransactional
    @Override
    public boolean doBorrow(int uid, int bid) {
        System.out.println("uid:"+ uid+"      bid:"+bid);

        if(bookClient.bookRemain(bid) < 1){
            throw new RuntimeException("图书数量不足");
        }
        if(userClient.userRemain(uid) < 1){
            throw new RuntimeException("用户借阅量不足");
        }
        if(!bookClient.bookBorrow(bid)){
            throw new RuntimeException("在借阅图书时出现错误！");
        }
        if(borrowMapper.getBorrow(uid,bid) != null){
            throw new RuntimeException("此书籍已经被此用户借阅了！");
        }
        if(borrowMapper.addBorrow(uid,bid) <= 0){
            throw new RuntimeException("在录入借阅信息时出现错误！");
        }
        if(!userClient.userBorrow(uid)){
            throw new RuntimeException("在借阅时出现错误！");
        }
//        完成
        return true;
    }

    public BorrowDetail blocked(int uid, BlockException blockException) {
        return new BorrowDetail(null, Collections.emptyList());
    }


}
