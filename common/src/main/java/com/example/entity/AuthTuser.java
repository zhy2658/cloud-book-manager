package com.example.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class AuthTuser  implements Serializable {

    private Integer id;

    private String uname;

    private String role;
}
