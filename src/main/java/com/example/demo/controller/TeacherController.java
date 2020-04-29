package com.example.demo.controller;


import com.example.demo.mapper.ApprovalMapper;
import com.example.demo.mapper.BooksMapper;
import com.example.demo.mapper.ClassleaderMapper;
import com.example.demo.mapper.TeacherMapper;
import com.example.demo.model.*;
import com.example.demo.service.ApprovalService;
import com.example.demo.service.BookService;
import com.example.demo.service.TeacherService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author dell
 * @create 2020/4/28 13:35
 */
@Controller
//teacher的controller
//因为classleader中讲了大体思路，重复的不再赘述
public class TeacherController {
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private ApprovalService approvalService;
    @Autowired
    private BookService bookService;


//    编辑个人信息，用于激活当前个人信息和编辑用的表单，随后更新model和session中的信息
    @GetMapping("/teacher/edit")
    public String edit(Model model,
                       HttpServletRequest request){
        model.addAttribute("currentChoice","edit");
        Teacher teacher=teacherService.getCurrentTeacher(request);
        model.addAttribute("teacher",teacher);
        request.setAttribute("teacher",teacher);
        return "teacher";
    }
    @PostMapping("/teacher/edit")
    public String edit(@RequestParam(name = "username")String username,
                       @RequestParam(name = "email")String email,
                       @RequestParam(name = "department")String department,
                       @RequestParam(name = "password")String password,
                       HttpServletRequest request,
                       Model model){
        teacherService.selfUpdateTeacher(username, email, department, password, request);
        return "teacher";
    }

//    查看个人订单。因为是列表所以同样需要分页。原理和班长处同。并且打开显示列表开关
    @GetMapping("/teacher/check")
    public String check(HttpServletRequest request,
                        @RequestParam(name = "pageNum",defaultValue = "1")int pageNum,
                        @RequestParam(name = "pageSize",defaultValue = "10")int pageSize,
                        Model model){
        PageHelper.startPage(pageNum,pageSize);
        List<Approval> approvals=teacherService.teacherApproval(request);
        PageInfo<Approval> pageInfo=new PageInfo<Approval>(approvals);
        model.addAttribute("currentChoice","check");
        model.addAttribute("pattern","select");
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("approvals",approvals);
        model.addAttribute("requestPreUrl","/teacher/check");
        return "teacher";
    }

//    查看教材信息。与查看个人订单雷同。
@GetMapping("/teacher/allBooks")
public String listBooks(@RequestParam(name = "pageNum",defaultValue = "1")int pageNum,
                        @RequestParam(name = "pageSize",defaultValue = "10")int pageSize,
                        Model model){
    PageHelper.startPage(pageNum,pageSize);
    List<Books> books=bookService.allBooks();
    PageInfo<Books> pageInfo=new PageInfo<Books>(books);
    model.addAttribute("currentChoice","allBooks");
    model.addAttribute("pattern","select");
    model.addAttribute("pageInfo",pageInfo);
    model.addAttribute("requestPreUrl","/teacher/allBooks");
    model.addAttribute("books",books);
    return "teacher";
}


//    增加订单。同样采用了get打开开关，post提交表单结合的方式。
    @GetMapping("/teacher/addApproval")
    public String addApproval(Model model){
        model.addAttribute("currentChoice","addApproval");
        return "teacher";
    }
    @PostMapping("/teacher/addApproval")
    public String doAddApproval(@RequestParam(name = "bookId")int bookId,
                                @RequestParam(name = "quantity")int quantity,
                                @RequestParam(name = "toclass")String toclass,
                                HttpServletRequest request,
                                Model model){
        model.addAttribute("currentChoice","addApproval");
        approvalService.add(bookId,quantity,toclass,request);
        return "teacher";
    }
}
