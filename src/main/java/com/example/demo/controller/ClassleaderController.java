package com.example.demo.controller;

import com.example.demo.mapper.ApprovalMapper;
import com.example.demo.mapper.ClassleaderMapper;
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
public class ClassleaderController {
    @Autowired
    private ApprovalMapper approvalMapper;
    @Autowired
    private ClassleaderMapper classleaderMapper;

//    查看和修改个人信息
    @GetMapping("/classLeader/edit")
    public String edit(Model model,HttpServletRequest request){
        model.addAttribute("currentChoice","edit");
        int id= 0;
        id=(int) request.getSession().getAttribute("id");
        ClassleaderExample classleaderExample=new ClassleaderExample();
        classleaderExample.createCriteria().andIdEqualTo(id);
        Classleader classleader=classleaderMapper.selectByExample(classleaderExample).get(0);
        model.addAttribute("classleader",classleader);
        request.setAttribute("classleader",classleader);
        return "classleader";
    }
    @PostMapping("/classLeader/edit")
    public String edit(@RequestParam(name = "username")String username,
                       @RequestParam(name = "email")String email,
                       @RequestParam(name = "classid")String classid,
                       @RequestParam(name = "department")String department,
                       @RequestParam(name = "password")String password,
                       HttpServletRequest request,
                       Model model){
        int id= (int) request.getSession().getAttribute("id");
        ClassleaderExample classleaderExample=new ClassleaderExample();
        classleaderExample.createCriteria().andIdEqualTo(id);
        Classleader classleader=classleaderMapper.selectByExample(classleaderExample).get(0);
        if(!username.equals("")) classleader.setUsername(username);
        if(!email.equals("")) classleader.setEmail(email);
        if(!password.equals("")) classleader.setPassword(password);
        if(!department.equals("")) classleader.setDepartment(department);
        if(!classid.equals("")) classleader.setClassid(classid);
        classleaderMapper.updateByExampleSelective(classleader,classleaderExample);
        return "classleader";
    }

//    查看本班订书情况
    @GetMapping("/classLeader/check")
    public String check(HttpServletRequest request,
                        @RequestParam(name = "pageNum",defaultValue = "1")int pageNum,
                        @RequestParam(name = "pageSize",defaultValue = "10")int pageSize,
                        Model model){
        PageHelper.startPage(pageNum,pageSize);
        int id= (int) request.getSession().getAttribute("id");
        ClassleaderExample classleaderExample=new ClassleaderExample();
        classleaderExample.createCriteria().andIdEqualTo(id);
        Classleader classleader=classleaderMapper.selectByExample(classleaderExample).get(0);
        String classid=classleader.getClassid();
        ApprovalExample approvalExample=new ApprovalExample();
        approvalExample.createCriteria().andToclassEqualTo(classid);
        List<Approval> approvals=approvalMapper.selectByExample(approvalExample);
        PageInfo<Approval> pageInfo=new PageInfo<Approval>(approvals);
        model.addAttribute("currentChoice","check");
        model.addAttribute("pattern","select");
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("approvals",approvals);
        model.addAttribute("requestPreUrl","/classLeader/check");
        return "classleader";
    }
}
