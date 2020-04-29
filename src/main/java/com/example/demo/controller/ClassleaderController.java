package com.example.demo.controller;

import com.example.demo.mapper.ApprovalMapper;
import com.example.demo.mapper.ClassleaderMapper;
import com.example.demo.model.*;
import com.example.demo.service.ClassLeaderService;
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
    private ClassLeaderService classLeaderService;
//    查看和修改个人信息
    @GetMapping("/classLeader/edit")
    public String edit(Model model,HttpServletRequest request){
        model.addAttribute("currentChoice","edit");
        Classleader classleader=classLeaderService.getCurrentClassLeader(request);
        model.addAttribute("classleader",classleader);
        request.setAttribute("classleader",classleader);
        return "classleader";
    }
    @PostMapping("/classLeader/edit")
    public String edit(@RequestParam(name = "username",defaultValue = "")String username,
                       @RequestParam(name = "email",defaultValue = "")String email,
                       @RequestParam(name = "classid",defaultValue = "")String classid,
                       @RequestParam(name = "department",defaultValue = "")String department,
                       @RequestParam(name = "password",defaultValue = "")String password,
                       HttpServletRequest request,
                       Model model){
        classLeaderService.selfUpdateClassLeader(request, username, email, classid, department, password);
        return "classleader";
    }

//    查看本班订书情况
    @GetMapping("/classLeader/check")
    public String check(HttpServletRequest request,
                        @RequestParam(name = "pageNum",defaultValue = "1")int pageNum,
                        @RequestParam(name = "pageSize",defaultValue = "10")int pageSize,
                        Model model){
        PageHelper.startPage(pageNum,pageSize);
        Classleader classleader=classLeaderService.getCurrentClassLeader(request);
        List<Approval> approvals=classLeaderService.listApproval(request);
        PageInfo<Approval> pageInfo=new PageInfo<Approval>(approvals);
        model.addAttribute("currentChoice","check");
        model.addAttribute("pattern","select");
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("approvals",approvals);
        model.addAttribute("requestPreUrl","/classLeader/check");
        return "classleader";
    }
}
