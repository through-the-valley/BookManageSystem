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
//登陆的controller
@Controller
public class LoginController {
//    处理从主页的登陆按钮传来的请求，转到login页面
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(){
        return "login";
    }
//注入对应的mapper。其实应该传service，不过没有复杂的业务逻辑，所以没什么影响。
    @Autowired
    private ManagerMapper managerMapper;
    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private ClassleaderMapper classleaderMapper;
//    处理登陆表单
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String doLogin(@RequestParam String email,
                          @RequestParam String password,
                          @RequestParam String identity,
                          HttpServletRequest request,
                          Model model){
/**
 * 首先根据选择的身份来判断要去哪个数据库寻找。采用switch语句
 * 使用mybatis和mbg来操作数据库。操作方式不难，一般我们创建xxxexample，用createCriteria()方法，里面有丰富的选项。一般用andxxxequalto即可
 * 返回的list我们先用size确定下是否无结果。无结果就是用户名或密码无效，将用户重定向到login界面。
 * 不无效的情况下。因为id是自增即唯一的，所以从list中取第一个就是我们需要的用户，将其用户名和id放入session用于后续的功能如根据id查找。
 * 其实可以直接把用户对象放进session中更方便点，这里没有这么做
 * 存好后转到对应身份的界面
 */
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

//    处理退出登录界面。删除session的值并返回到登录界面
    @GetMapping("/quit")
    public String quit(HttpServletRequest request){
        request.getSession().removeAttribute("username");
        request.getSession().removeAttribute("id");
        return "redirect:/";
    }
}
