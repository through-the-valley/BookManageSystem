package com.example.demo.service.serviceImpl;

import com.example.demo.mapper.BooksMapper;
import com.example.demo.model.Books;
import com.example.demo.model.BooksExample;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author dell
 * @create 2020/4/29 12:52
 */
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BooksMapper booksMapper;
//    展示所有教材
    @Override
    public List<Books> allBooks() {
        BooksExample booksExample=new BooksExample();
        booksExample.createCriteria().getAllCriteria();
        return booksMapper.selectByExample(booksExample);
    }
//  管理员改变教材库存
    @Override
    public void changeNum(int id, int bookQuantity) {
        BooksExample booksExample=new BooksExample();
        booksExample.createCriteria().andIdEqualTo(id);
        List<Books> books=booksMapper.selectByExample(booksExample);
        Books book=books.get(0);
        book.setBookQuantity(bookQuantity);
        booksMapper.updateByExampleSelective(book,booksExample);
    }
//  管理员新增库存教材
    @Override
    public void addBook(String bookName, int bookQuantity, String press, double price) {
        Books book=new Books();
        book.setBookQuantity(bookQuantity);book.setBookName(bookName);book.setPress(press);book.setPrice(price);
        booksMapper.insertSelective(book);
    }
//    管理员删除库存教材
    @Override
    public void deleteBook(int id) {
        BooksExample booksExample=new BooksExample();
        booksExample.createCriteria().andIdEqualTo(id);
        booksMapper.deleteByExample(booksExample);
    }
}
