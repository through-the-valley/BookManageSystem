package com.example.demo.controller;

import com.example.demo.mapper.ClassleaderMapper;
import com.example.demo.mapper.ManagerMapper;
import com.example.demo.mapper.TeacherMapper;
import com.example.demo.model.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author dell
 * @create 2020/4/27 23:29
 */
@Controller
public class LoginController {

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @Autowired
    private ManagerMapper managerMapper;
    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private ClassleaderMapper classleaderMapper;
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String doLogin(@RequestParam String email,
                          @RequestParam String password,
                          @RequestParam String identity,
                          HttpServletRequest request,
                          Model model){
        switch (identity) {
            case "管理员":
                ManagerExample managerExample = new ManagerExample();
                managerExample.createCriteria().andEmailEqualTo(email);
                List<Manager> managers = managerMapper.selectByExample(managerExample);
                if (managers.size() != 0) {
                    Manager manager = managers.get(0);
                    if (!password.equals(manager.getPassword())) return "redirect:/login";
                    else {
                        model.addAttribute("username",manager.getUsername());
                        request.getSession().setAttribute("username",manager.getUsername());
                        request.getSession().setAttribute("id",manager.getId());
                        return "/manager";}
                }
                break;
            case "教师":
                TeacherExample teacherExample = new TeacherExample();
                teacherExample.createCriteria().andEmailEqualTo(email);
                List<Teacher> teachers = teacherMapper.selectByExample(teacherExample);
                if (teachers.size() != 0) {
                    Teacher teacher = teachers.get(0);
                    if (!password.equals(teacher.getPassword())) return "redirect:/login";
                    else {
                        model.addAttribute("username",teacher.getUsername());
                        request.getSession().setAttribute("username",teacher.getUsername());
                        request.getSession().setAttribute("id",teacher.getId());
                        return "/teacher";
                    }
                }
                break;
            case "班长":
                ClassleaderExample classleaderExample = new ClassleaderExample();
                classleaderExample.createCriteria().andEmailEqualTo(email);
                List<Classleader> classleaders = classleaderMapper.selectByExample(classleaderExample);
                if (classleaders.size() != 0) {
                    Classleader classleader = classleaders.get(0);
                    if (!password.equals(classleader.getPassword())) return "redirect:/login";
                    else {
                        model.addAttribute("username",classleader.getUsername());
                        request.getSession().setAttribute("username",classleader.getUsername());
                        request.getSession().setAttribute("id",classleader.getId());
                        return "/classleader";
                    }
                }
                break;
        }

        return "redirect:/login";
    }
}
