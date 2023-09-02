package com.example.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book implements Serializable {

    private Integer id;

    private String name;

    private String description;

    private double price;

    private String author;

    private String img;

    private String wordCount;

    private Integer hot;

    private Date releaseTime;

    private Integer sortId;

    private String tags;

    private Integer num;


}
