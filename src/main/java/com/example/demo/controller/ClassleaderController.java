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
//这是班长界面的controller
@Controller
public class ClassleaderController {
//    注入班长的service
    @Autowired
    private ClassLeaderService classLeaderService;
/**
 *  查看和修改个人信息。当用户点击链接为/classLeader/edit的按钮时，向model中写入currentChocie：edit的键值对。
    因为实际上只有一个classleader.html，所有的诸如表格表单都是写在一起的，所以这里采用类似情景转换的效果
    通过点击不同的按钮，给currentChoice设不同的值，通过th：if表达式判断currentChoice是否符合当前块的预设值
    如果符合就显示，不符合就不显示，实现类似开关的效果。
    参数中的request用于获取session，因为在login页面中，如果登陆成功就会将用户名和用户id写入session中。
    所以我们调用session读取用户id，并在对应的用户中进行查找就可以知道当前用户的具体信息。
    同样通过model将classleader这个对象放进去，这样就能让前端使用这个对象，其获取信息
 **/
    @GetMapping("/classLeader/edit")
    public String edit(Model model,HttpServletRequest request){
        model.addAttribute("currentChoice","edit");
        Classleader classleader=classLeaderService.getCurrentClassLeader(request);
        model.addAttribute("classleader",classleader);
        request.setAttribute("classleader",classleader);
        return "classleader";
    }
/**
 * 这里和上面的虽然处理同一个请求，但是方法一个是Get，一个是Post
    上面的get用于通过情景选择原理显示用于更改的表单
    而这里的post用于处理用户提交的表单，获取其值并进行修改
    这里因为修改并不一定是修改全部值。所以将5个参数的默认值设为""
    更新时会先创建一个classleader对象，然后根据现有id，先把旧的几个属性给该对象
    然后仅当传过来的值不为""时才将其再赋值，这样就能只更改用户提交的项目。
 **/
    @PostMapping("/classLeader/edit")
    public String edit(@RequestParam(name = "username",defaultValue = "")String username,
                       @RequestParam(name = "email",defaultValue = "")String email,
                       @RequestParam(name = "classid",defaultValue = "")String classid,
                       @RequestParam(name = "department",defaultValue = "")String department,
                       @RequestParam(name = "password",defaultValue = "")String password,
                       HttpServletRequest request){
        classLeaderService.selfUpdateClassLeader(request, username, email, classid, department, password);
        return "classleader";
    }

/**
 *  查看本班订书情况
    这里用了PageHelper插件。实际上出现表格，有可能需要分页的地方都用了这个工具。
    使用这个工具需要传起始页码（pageNum）和每页多少记录（pageSize）这里直接用了默认值，没要求用户设置
    用startPage初始化之后，调用service的展示班长所属班级的订单的命令获取List<Approval>用于创建pageInfo，并添加到model中就可以使用了。
    pageInfo仅用于显示分页的页数选项，具体数据值是用表格显示的
    currentChoice同样是用来设置开关
    pattern用来打开分页，也是个开关。为select时显示
    将approvals放入model中，前端就能用th：each的方式显示各条记录。当然是按照在pageHelper中设置的值进行
    requestPreUrl用于分页按钮的超链接。只要修改pageNum的值就可以去到不同的页数。
 **/
    @GetMapping("/classLeader/check")
    public String check(HttpServletRequest request,
                        @RequestParam(name = "pageNum",defaultValue = "1")int pageNum,
                        @RequestParam(name = "pageSize",defaultValue = "10")int pageSize,
                        Model model){
        PageHelper.startPage(pageNum,pageSize);
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
