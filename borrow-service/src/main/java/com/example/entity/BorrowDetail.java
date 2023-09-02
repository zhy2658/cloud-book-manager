package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;


import java.util.List;

@Data
@AllArgsConstructor
public class BorrowDetail {

    private Tuser tuser;

    private List<Book> bookList;

}
