package com.example.demo.controller;

import com.example.demo.mapper.*;
import com.example.demo.model.*;
import com.example.demo.service.*;
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
//管理员的controller
@Controller
public class ManagerController {
    @Autowired
    private BookService bookService;
    @Autowired
    private ApprovalService approvalService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private ClassLeaderService classLeaderService;

//  分页显示所有教材
    @GetMapping("/manager/allBooks")
    public String listBooks(@RequestParam(name = "pageNum",defaultValue = "1")int pageNum,
                            @RequestParam(name = "pageSize",defaultValue = "10")int pageSize,
                            Model model,
                            HttpServletRequest request){
        PageHelper.startPage(pageNum,pageSize);
        List<Books> books=bookService.allBooks();
        PageInfo<Books> pageInfo=new PageInfo<Books>(books);
        model.addAttribute("currentChoice","allBooks");
        model.addAttribute("pattern","select");
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("requestPreUrl","/manager/allBooks");
        model.addAttribute("books",books);
        return "manager";
    }
//    更改教材库存。用currentChoice控制更新表单
    @GetMapping("/manager/changeBookNum")
    public String changeBookNum(Model model){

        model.addAttribute("currentChoice","allBooks");
        model.addAttribute("pattern","changeBookNum");
        return "manager";
    }
    @PostMapping("/manager/changeBookNum")
    public String doChangeBookNum(@RequestParam(name = "id")int id,
                                  @RequestParam(name = "bookQuantity")int bookQuantity){
        bookService.changeNum(id, bookQuantity);
        return "manager";
    }
//    新增库存教材。原理同上
    @GetMapping("/manager/addBooks")
    public String addBooks(Model model){
        model.addAttribute("pattern","addBooks");
        return "manager";
    }
    @PostMapping("/manager/addBooks")
    public String doAddBooks(@RequestParam(name = "bookName")String bookName,
                             @RequestParam(name = "bookQuantity")int bookQuantity,
                             @RequestParam(name = "press")String press,
                             @RequestParam(name = "price")double price){
        bookService.addBook(bookName, bookQuantity, press, price);
        return "manager";
    }
//    删除库存教材。原理同上
    @GetMapping("/manager/deleteBooks")
    public String deleteBooks(Model model){
        model.addAttribute("pattern","deleteBooks");
        return "manager";
    }
    @PostMapping("/manager/deleteBooks")
    public String doDeleteBooks(@RequestParam(name = "id")int id){
        bookService.deleteBook(id);
        return "manager";
    }

//  分页显示所有订单
    @GetMapping("/manager/allApprovals")
    public String listApprovals(@RequestParam(name = "pageNum",defaultValue = "1")int pageNum,
                                @RequestParam(name = "pageSize",defaultValue = "10")int pageSize,
                                Model model){
        PageHelper.startPage(pageNum,pageSize);
        List<Approval> approvals=approvalService.allApproval();
        PageInfo<Approval> pageInfo=new PageInfo<Approval>(approvals);
        model.addAttribute("currentChoice","allApprovals");
        model.addAttribute("pattern","select");
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("requestPreUrl","/manager/allApprovals");
        model.addAttribute("approvals",approvals);
        return "manager";
    }
//    更改订单状态。通过或未通过或删除订单
    @PostMapping("/manager/changeApproval")
    public String changeApproval(Model model,
                                 @RequestParam(name = "approvalId")int approvalId,
                                 @RequestParam(name = "act")int act){
        approvalService.changeApproval(approvalId,act);
        return "manager";
    }

//展示所有教师
    @GetMapping("/manager/allTeachers")
    public String listTeachers(@RequestParam(name = "pageNum",defaultValue = "1")int pageNum,
                                @RequestParam(name = "pageSize",defaultValue = "10")int pageSize,
                                Model model){
        PageHelper.startPage(pageNum,pageSize);
        List<Teacher> teachers=teacherService.allTeacher();
        PageInfo<Teacher> pageInfo=new PageInfo<Teacher>(teachers);
        model.addAttribute("currentChoice","allTeachers");
        model.addAttribute("pattern","select");
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("requestPreUrl","/manager/allTeachers");
        model.addAttribute("teachers",teachers);
        return "manager";
    }
//    添加教师
    @PostMapping("/manager/addTeacher")
    public String doAddTeacher(@RequestParam(name = "userName")String userName,
                             @RequestParam(name = "email")String email,
                             @RequestParam(name = "password")String password,
                             @RequestParam(name = "department")String department){
        teacherService.Add(userName, email, password, department);
        return "manager";
    }
//    编辑教师，这里编辑跟教师自己编辑自己不同，需要通过id查找到对应的目标。所以id是必填的，下面编辑班长一个道理
    @PostMapping("/manager/editTeacher")
    public String doEditTeacher(@RequestParam(name = "id")int id,
                                @RequestParam(name = "userName",defaultValue = "")String userName,
                                @RequestParam(name = "email",defaultValue = "")String email,
                                @RequestParam(name = "password",defaultValue = "")String password,
                                @RequestParam(name = "department",defaultValue = "")String department){
        teacherService.upperUpdate(id, userName, email, password, department);
        return "manager";
    }
//    删除教师
    @PostMapping("/manager/deleteTeacher")
    public String doDeleteTeacher(@RequestParam(name = "id")int id){
        teacherService.delete(id);
        return "manager";
    }

//  跟教师一个模子
    @GetMapping("/manager/allClassLeaders")
    public String listClassLeaders(@RequestParam(name = "pageNum",defaultValue = "1")int pageNum,
                               @RequestParam(name = "pageSize",defaultValue = "10")int pageSize,
                               Model model){
        PageHelper.startPage(pageNum,pageSize);
        List<Classleader> classleaders=classLeaderService.listClassLeader();
        PageInfo<Classleader> pageInfo=new PageInfo<Classleader>(classleaders);
        model.addAttribute("currentChoice","allClassLeaders");
        model.addAttribute("pattern","select");
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("requestPreUrl","/manager/allClassLeaders");
        model.addAttribute("classLeaders",classleaders);
        return "manager";
    }
    @PostMapping("/manager/addClassLeader")
    public String doAddClassLeader( @RequestParam(name = "userName")String userName,
                                    @RequestParam(name = "email")String email,
                                    @RequestParam(name = "password")String password,
                                    @RequestParam(name = "classid")String classid,
                                    @RequestParam(name = "department")String department){
        classLeaderService.add(userName, email, password, classid, department);
        return "manager";
    }
    @PostMapping("/manager/editClassLeader")
    public String doEditClassLeader(@RequestParam(name = "id")int id,
                                @RequestParam(name = "userName",defaultValue = "")String userName,
                                @RequestParam(name = "email",defaultValue = "")String email,
                                @RequestParam(name = "password",defaultValue = "")String password,
                                @RequestParam(name = "department",defaultValue = "")String department,
                                @RequestParam(name = "classid",defaultValue = "")String classid){
        classLeaderService.upperUpdate(id, userName, email, password, department, classid);
        return "manager";
    }
    @PostMapping("/manager/deleteClassLeader")
    public String doDeleteClassLeader(@RequestParam(name = "id")int id){
        classLeaderService.delete(id);
        return "manager";
    }
}
