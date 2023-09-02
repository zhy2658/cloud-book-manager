package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
public class Torder implements Serializable {

    private Integer id;

    private String orderNo;

    private Integer bid;

    private Integer uid;

    private double unitPrice;

    private double amount;

    private Integer quantity;

    private Date createTime;

    private Tuser tuser;

    private Book book;

    private Integer status;

    private String address;

    private String tel;

    private String consignee;


    public Torder() {
    }

    public Torder(Integer id, String orderNo, Integer bid, Integer uid, double unitPrice, double amount, Integer quantity, Date createTime, Integer status) {
        this.id = id;
        this.orderNo = orderNo;
        this.bid = bid;
        this.uid = uid;
        this.unitPrice = unitPrice;
        this.amount = amount;
        this.quantity = quantity;
        this.createTime = createTime;
        this.status = status;
    }
    public Torder(Integer id, String orderNo, Integer bid, Integer uid, double unitPrice, double amount, Integer quantity, Date createTime, Integer status,String address,String tel,String consignee) {
        this.id = id;
        this.orderNo = orderNo;
        this.bid = bid;
        this.uid = uid;
        this.unitPrice = unitPrice;
        this.amount = amount;
        this.quantity = quantity;
        this.createTime = createTime;
        this.status = status;
        this.address=address;
        this.tel=tel;
        this.consignee=consignee;
    }
}
