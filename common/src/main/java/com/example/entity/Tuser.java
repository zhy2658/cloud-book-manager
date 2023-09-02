package com.example.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Tuser implements Serializable {

    private Integer id;

    private String uname;

    private String password;

    private String nickname;

    private String userphoto;

    private String role;

    private double coin;

    private String address;

    private String tel;

    private String consignee;



}
