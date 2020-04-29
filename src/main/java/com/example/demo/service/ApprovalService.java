package com.example.demo.service;

import com.example.demo.model.Approval;


import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author dell
 * @create 2020/4/29 14:15
 */
public interface ApprovalService {
    List<Approval> allApproval();
    void changeApproval(int id,int act);
    void add(int bookId,
             int quantity,
             String toclass,
             HttpServletRequest request);
}
