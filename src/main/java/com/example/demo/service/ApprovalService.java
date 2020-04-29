package com.example.demo.service;

import com.example.demo.model.Approval;


import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author dell
 * @create 2020/4/29 14:15
 */
//3个方法分别为查看所有订单，更改订单状态，教师增加订单
public interface ApprovalService {
    List<Approval> allApproval();
    void changeApproval(int id,int act);
    void add(int bookId,
             int quantity,
             String toclass,
             HttpServletRequest request);
}
