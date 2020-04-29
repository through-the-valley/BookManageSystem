package com.example.demo.service;

import com.example.demo.model.Approval;
import com.example.demo.model.Classleader;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author dell
 * @create 2020/4/29 10:53
 */
public interface ClassLeaderService {
    Classleader getCurrentClassLeader(HttpServletRequest request);
    int getCurrentClassLeaderId(HttpServletRequest request);
    String getCurrentClassLeaderClassId(HttpServletRequest request);
    void selfUpdateClassLeader(HttpServletRequest request,
                               String username,
                               String email,
                               String classid,
                               String department,
                               String password);
    List<Approval> listApproval(HttpServletRequest request);
    List<Classleader> listClassLeader();
    void add(String userName,
             String email,
             String password,
             String classid,
             String department);
    void upperUpdate(int id,
                     String userName,
                     String email,
                     String password,
                     String department,
                     String classid);
    void delete(int id);
}
