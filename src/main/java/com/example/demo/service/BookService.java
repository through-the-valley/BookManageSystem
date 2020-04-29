package com.example.demo.service;

import com.example.demo.model.Books;


import java.util.List;

/**
 * @Author dell
 * @create 2020/4/29 12:52
 */
//四个方法分别为查看所有教材，更改教材库存，增加库存教材，删除库存教材
public interface BookService {
    List<Books> allBooks();
    void changeNum(int id,int bookQuantity);
    void addBook(String bookName,
                 int bookQuantity,
                 String press,
                 double price);
    void deleteBook(int id);
}
