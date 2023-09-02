package com.example.service;

import com.example.entity.Book;
import com.example.entity.Result;
import com.example.entity.Torder;
import com.example.entity.Tuser;
import com.example.mapper.TorderMapper;
import com.example.service.client.BookClient;
import com.example.service.client.UserClient;
import com.example.utils.Util;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import org.apache.shardingsphere.transaction.annotation.ShardingSphereTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class TorderServiceImpl implements TorderService{

    @Resource
    TorderMapper torderMapper;

    @Resource
    BookClient bookClient;

    @Resource
    UserClient userClient;

//    @GlobalTransactional
    @Override
    public List<Torder> list(int uid) {
        List<Torder> torderList = torderMapper.list(uid);
        for(Torder torder : torderList){
            Result bookResult = bookClient.getBook(torder.getBid());
            System.out.println(torder.getUid());
            Result userResult = userClient.getUser(torder.getUid());
            Map bookMap=bookResult.getMap();
            Map userMap=userResult.getMap();
//            System.out.println(userResult);
            Book book= (Book) Util.transMap(bookMap.get("book"),Book.class);
            Tuser tuser= (Tuser) Util.transMap(userMap.get("tuser"), Tuser.class);
//            System.out.println(tuser);
            torder.setBook(book);
            torder.setTuser(tuser);
        }
        return torderList;

    }
//    @GlobalTransactional
    @Override
    public Torder getOne(int id) {
        Torder torder = torderMapper.getOne(id);
        Result bookResult = bookClient.getBook(torder.getBid());
        System.out.println(torder.getUid());
        Result userResult = userClient.getUser(torder.getUid());
        Map bookMap=bookResult.getMap();
        Map userMap = userResult.getMap();
        System.out.println(userResult);
        Book book= (Book) Util.transMap(bookMap.get("book"),Book.class);
        Tuser tuser  = (Tuser) Util.transMap(userMap.get("tuser"), Tuser.class);
        System.out.println(tuser);
        torder.setBook(book);
        torder.setTuser(tuser);

        return torder;
    }

//    @GlobalTransactional
//    @Transactional
//    @ShardingSphereTransactionType(TransactionType.BASE)    // 使用 seata 注解
    @Override
    public boolean add(Torder torder) {
        double amount = torder.getAmount();

        Result result=userClient.reduceCoin(amount);
        if(result.getCode() == 500){
            return false;
        }
//        System.out.println("resukt--------"+result);
        userClient.getUser(1);
        boolean success = torderMapper.add(torder) > 0;
        return success;
    }

//    @GlobalTransactional
    @Override
    public List<Torder> allTorder() {
        List<Torder> torderList = torderMapper.allTorder();
        for(Torder torder : torderList){
            Result bookResult = bookClient.getBook(torder.getBid());
            System.out.println(torder.getUid());
            Result userResult = userClient.getUser(torder.getUid());
            Map bookMap=bookResult.getMap();
            Map userMap=userResult.getMap();
            System.out.println(userResult);
            Book book= (Book) Util.transMap(bookMap.get("book"),Book.class);
            Tuser tuser= (Tuser) Util.transMap(userMap.get("tuser"), Tuser.class);
            System.out.println(tuser);
            torder.setBook(book);
            torder.setTuser(tuser);
        }
        return torderList;
    }

//    @GlobalTransactional
    @Override
    public boolean delete(int id) {
        return torderMapper.delete(id) > 0;
    }
}
