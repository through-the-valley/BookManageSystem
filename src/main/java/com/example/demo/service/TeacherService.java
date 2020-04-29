package com.example.demo.service;

import com.example.demo.model.Approval;
import com.example.demo.model.Teacher;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author dell
 * @create 2020/4/29 11:18
 */
//分别用于获取登陆用户，获取登陆用户id。查看所有教师，教师查看个人订单，编辑个人信息，管理员增加，更新，删除教师
public interface TeacherService {
    Teacher getCurrentTeacher(HttpServletRequest request);

    int getCurrentTeacherId(HttpServletRequest request);

    List<Teacher> allTeacher();

    List<Approval> teacherApproval(HttpServletRequest request);

    void selfUpdateTeacher(String username,
                           String email,
                           String department,
                           String password,
                           HttpServletRequest request);

    void Add(String userName,
                  String email,
                  String password,
                  String department);

    void upperUpdate(int id,
                     String userName,
                     String email,
                     String password,
                     String department);

    void delete(int id);
}
