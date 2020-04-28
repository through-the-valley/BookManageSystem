package com.example.demo.controller;


import com.example.demo.mapper.ApprovalMapper;
import com.example.demo.mapper.BooksMapper;
import com.example.demo.mapper.ClassleaderMapper;
import com.example.demo.mapper.TeacherMapper;
import com.example.demo.model.*;
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
public class TeacherController {
    @Autowired
    private ApprovalMapper approvalMapper;
    @Autowired
    private ClassleaderMapper classleaderMapper;
    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private BooksMapper booksMapper;


//    编辑个人信息
    @GetMapping("/teacher/edit")
    public String edit(Model model,
                       HttpServletRequest request){
        model.addAttribute("currentChoice","edit");
        int id= 0;
        id=(int) request.getSession().getAttribute("id");
        TeacherExample teacherExample=new TeacherExample();
        teacherExample.createCriteria().andIdEqualTo(id);
        Teacher teacher=teacherMapper.selectByExample(teacherExample).get(0);
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
        int id=0;
        id=(int) request.getSession().getAttribute("id");
        TeacherExample teacherExample=new TeacherExample();
        teacherExample.createCriteria().andIdEqualTo(id);
        Teacher teacher=teacherMapper.selectByExample(teacherExample).get(0);
        if(!username.equals("")) teacher.setUsername(username);
        if(!email.equals("")) teacher.setEmail(email);
        if(!password.equals("")) teacher.setPassword(password);
        if(!department.equals("")) teacher.setDepartment(department);
        teacherMapper.updateByExampleSelective(teacher,teacherExample);
        return "classleader";
    }

//    查看个人订单
    @GetMapping("/teacher/check")
    public String check(HttpServletRequest request,
                        @RequestParam(name = "pageNum",defaultValue = "1")int pageNum,
                        @RequestParam(name = "pageSize",defaultValue = "10")int pageSize,
                        Model model){
        PageHelper.startPage(pageNum,pageSize);
        int id=0;
        id=(int) request.getSession().getAttribute("id");
        TeacherExample teacherExample=new TeacherExample();
        teacherExample.createCriteria().andIdEqualTo(id);
        Teacher teacher=teacherMapper.selectByExample(teacherExample).get(0);
        int teacherId=teacher.getId();
        ApprovalExample approvalExample=new ApprovalExample();
        approvalExample.createCriteria().andTeacherIdEqualTo(teacherId);
        List<Approval> approvals=approvalMapper.selectByExample(approvalExample);
        PageInfo<Approval> pageInfo=new PageInfo<Approval>(approvals);
        model.addAttribute("currentChoice","check");
        model.addAttribute("pattern","select");
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("approvals",approvals);
        model.addAttribute("requestPreUrl","/teacher/check");
        return "teacher";
    }

//    查看图书信息
@GetMapping("/teacher/allBooks")
public String listBooks(@RequestParam(name = "pageNum",defaultValue = "1")int pageNum,
                        @RequestParam(name = "pageSize",defaultValue = "10")int pageSize,
                        Model model){
    PageHelper.startPage(pageNum,pageSize);
    BooksExample booksExample=new BooksExample();
    booksExample.createCriteria().getAllCriteria();
    List<Books> books=booksMapper.selectByExample(booksExample);
    PageInfo<Books> pageInfo=new PageInfo<Books>(books);
    model.addAttribute("currentChoice","allBooks");
    model.addAttribute("pattern","select");
    model.addAttribute("pageInfo",pageInfo);
    model.addAttribute("requestPreUrl","/teacher/allBooks");
    model.addAttribute("books",books);
    return "teacher";
}


//    增加订单
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
        int id=0;
        id=(int) request.getSession().getAttribute("id");
        Approval approval=new Approval();
        approval.setBookId(bookId);approval.setQuantity(quantity);approval.setTeacherId(id);
        approval.setToclass(toclass);approval.setPass(2);
        approvalMapper.insertSelective(approval);
        return "teacher";
    }
}
