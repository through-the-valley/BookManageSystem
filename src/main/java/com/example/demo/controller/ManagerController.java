package com.example.demo.controller;

import com.example.demo.mapper.*;
import com.example.demo.model.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author dell
 * @create 2020/4/28 13:35
 */
@Controller
public class ManagerController {
    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private ClassleaderMapper classleaderMapper;
    @Autowired
    private BooksMapper booksMapper;
    @Autowired
    private ApprovalMapper approvalMapper;

    @GetMapping("/manager/allBooks")
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
        model.addAttribute("requestPreUrl","/manager/allBooks");
        model.addAttribute("books",books);
        return "manager";
    }
    @GetMapping("/manager/changeBookNum")
    public String changeBookNum(Model model){
        model.addAttribute("currentChoice","allBooks");
        model.addAttribute("pattern","changeBookNum");
        return "manager";
    }
    @PostMapping("/manager/changeBookNum")
    public String doChangeBookNum(@RequestParam(name = "id")int id,
                                  @RequestParam(name = "bookQuantity")int bookQuantity){
        BooksExample booksExample=new BooksExample();
        booksExample.createCriteria().andIdEqualTo(id);
        List<Books> books=booksMapper.selectByExample(booksExample);
        Books book=books.get(0);
        book.setBookQuantity(bookQuantity);
        booksMapper.updateByExampleSelective(book,booksExample);
        return "manager";
    }
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
        BooksExample booksExample=new BooksExample();
        Books book=new Books();
        book.setBookQuantity(bookQuantity);book.setBookName(bookName);book.setPress(press);book.setPrice(price);
        booksMapper.insertSelective(book);
        return "manager";
    }
    @GetMapping("/manager/deleteBooks")
    public String deleteBooks(Model model){
        model.addAttribute("pattern","deleteBooks");
        return "manager";
    }
    @PostMapping("/manager/deleteBooks")
    public String doDeleteBooks(@RequestParam(name = "id")int id){
        BooksExample booksExample=new BooksExample();
        booksExample.createCriteria().andIdEqualTo(id);
        booksMapper.deleteByExample(booksExample);
        return "manager";
    }


    @GetMapping("/manager/allApprovals")
    public String listApprovals(@RequestParam(name = "pageNum",defaultValue = "1")int pageNum,
                                @RequestParam(name = "pageSize",defaultValue = "10")int pageSize,
                                Model model){
        PageHelper.startPage(pageNum,pageSize);
        ApprovalExample approvalExample=new ApprovalExample();
        approvalExample.createCriteria().getAllCriteria();
        List<Approval> approvals=approvalMapper.selectByExample(approvalExample);
        PageInfo<Approval> pageInfo=new PageInfo<Approval>(approvals);
        model.addAttribute("currentChoice","allApprovals");
        model.addAttribute("pattern","select");
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("requestPreUrl","/manager/allApprovals");
        model.addAttribute("approvals",approvals);
        return "manager";
    }
    @PostMapping("/manager/changeApproval")
    public String changeApproval(Model model,
                                 @RequestParam(name = "approvalId")int approvalId,
                                 @RequestParam(name = "act")int act){
        ApprovalExample approvalExample=new ApprovalExample();
        approvalExample.createCriteria().andIdEqualTo(approvalId);
        List<Approval> approvals=approvalMapper.selectByExample(approvalExample);
        Approval approval=approvals.get(0);
        switch (act){
            case 1:
                approval.setPass(1);
                approvalMapper.updateByExampleSelective(approval,approvalExample);
                break;
            case 2:
                approval.setPass(2);
                approvalMapper.updateByExampleSelective(approval,approvalExample);
                break;
            case 0:
                approvalMapper.deleteByExample(approvalExample);
                break;
        }
        return "manager";
    }


    @GetMapping("/manager/allTeachers")
    public String listTeachers(@RequestParam(name = "pageNum",defaultValue = "1")int pageNum,
                                @RequestParam(name = "pageSize",defaultValue = "10")int pageSize,
                                Model model){
        PageHelper.startPage(pageNum,pageSize);
        TeacherExample teacherExample=new TeacherExample();
        teacherExample.createCriteria().getAllCriteria();
        List<Teacher> teachers=teacherMapper.selectByExample(teacherExample);
        PageInfo<Teacher> pageInfo=new PageInfo<Teacher>(teachers);
        model.addAttribute("currentChoice","allTeachers");
        model.addAttribute("pattern","select");
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("requestPreUrl","/manager/allTeachers");
        model.addAttribute("teachers",teachers);
        return "manager";
    }
    @PostMapping("/manager/addTeacher")
    public String doAddTeacher(@RequestParam(name = "userName")String userName,
                             @RequestParam(name = "email")String email,
                             @RequestParam(name = "password")String password,
                             @RequestParam(name = "department")String department){
        Teacher teacher=new Teacher();
        teacher.setUsername(userName);teacher.setEmail(email);teacher.setDepartment(department);teacher.setPassword(password);
        teacherMapper.insertSelective(teacher);
        return "manager";
    }
    @PostMapping("/manager/editTeacher")
    public String doEditTeacher(@RequestParam(name = "id")int id,
                                @RequestParam(name = "userName",defaultValue = "")String userName,
                                @RequestParam(name = "email",defaultValue = "")String email,
                                @RequestParam(name = "password",defaultValue = "")String password,
                                @RequestParam(name = "department",defaultValue = "")String department){
        TeacherExample teacherExample=new TeacherExample();
        teacherExample.createCriteria().andIdEqualTo(id);
        Teacher teacher=teacherMapper.selectByExample(teacherExample).get(0);
        if(!userName.equals("")) teacher.setUsername(userName);
        if(!email.equals("")) teacher.setEmail(email);
        if(!password.equals("")) teacher.setPassword(password);
        if(!department.equals("")) teacher.setDepartment(department);
        teacherMapper.updateByExampleSelective(teacher,teacherExample);
        return "manager";
    }
    @PostMapping("/manager/deleteTeacher")
    public String doDeleteTeacher(@RequestParam(name = "id")int id){
        TeacherExample teacherExample=new TeacherExample();
        teacherExample.createCriteria().andIdEqualTo(id);
        teacherMapper.deleteByExample(teacherExample);
        return "manager";
    }


    @GetMapping("/manager/allClassLeaders")
    public String listClassLeaders(@RequestParam(name = "pageNum",defaultValue = "1")int pageNum,
                               @RequestParam(name = "pageSize",defaultValue = "10")int pageSize,
                               Model model){
        PageHelper.startPage(pageNum,pageSize);
        ClassleaderExample classleaderExample=new ClassleaderExample();
        classleaderExample.createCriteria().getAllCriteria();
        List<Classleader> classleaders=classleaderMapper.selectByExample(classleaderExample);
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
        Classleader classleader=new Classleader();
        classleader.setUsername(userName);classleader.setEmail(email);classleader.setDepartment(department);
        classleader.setPassword(password);classleader.setClassid(classid);
        classleaderMapper.insertSelective(classleader);
        return "manager";
    }
    @PostMapping("/manager/editClassLeader")
    public String doEditClassLeader(@RequestParam(name = "id")int id,
                                @RequestParam(name = "userName",defaultValue = "")String userName,
                                @RequestParam(name = "email",defaultValue = "")String email,
                                @RequestParam(name = "password",defaultValue = "")String password,
                                @RequestParam(name = "department",defaultValue = "")String department,
                                @RequestParam(name = "classid",defaultValue = "")String classid){
        ClassleaderExample classleaderExample=new ClassleaderExample();
        classleaderExample.createCriteria().andIdEqualTo(id);
        Classleader classleader=classleaderMapper.selectByExample(classleaderExample).get(0);
        if(!userName.equals("")) classleader.setUsername(userName);
        if(!email.equals("")) classleader.setEmail(email);
        if(!password.equals("")) classleader.setPassword(password);
        if(!department.equals("")) classleader.setDepartment(department);
        if(!classid.equals("")) classleader.setClassid(classid);
        classleaderMapper.updateByExampleSelective(classleader,classleaderExample);
        return "manager";
    }
    @PostMapping("/manager/deleteClassLeader")
    public String doDeleteClassLeader(@RequestParam(name = "id")int id){
        ClassleaderExample classleaderExample=new ClassleaderExample();
        classleaderExample.createCriteria().andIdEqualTo(id);
        classleaderMapper.deleteByExample(classleaderExample);
        return "manager";
    }
}
