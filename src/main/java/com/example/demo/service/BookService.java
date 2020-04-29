package com.example.demo.service;

import com.example.demo.model.Books;


import java.util.List;

/**
 * @Author dell
 * @create 2020/4/29 12:52
 */
public interface BookService {
    List<Books> allBooks();
    void changeNum(int id,int bookQuantity);
    void addBook(String bookName,
                 int bookQuantity,
                 String press,
                 double price);
    void deleteBook(int id);
}
