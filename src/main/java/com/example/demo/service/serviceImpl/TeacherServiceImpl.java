package com.example.demo.service.serviceImpl;

import com.example.demo.mapper.ApprovalMapper;
import com.example.demo.mapper.TeacherMapper;
import com.example.demo.model.Approval;
import com.example.demo.model.ApprovalExample;
import com.example.demo.model.Teacher;
import com.example.demo.model.TeacherExample;
import com.example.demo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author dell
 * @create 2020/4/29 11:18
 */
@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private ApprovalMapper approvalMapper;
//    跟班长类似，有获取当前用户，获取用户id
    @Override
    public Teacher getCurrentTeacher(HttpServletRequest request) {
        int id= 0;
        id=getCurrentTeacherId(request);
        TeacherExample teacherExample=new TeacherExample();
        teacherExample.createCriteria().andIdEqualTo(id);
        return teacherMapper.selectByExample(teacherExample).get(0);
    }

    @Override
    public int getCurrentTeacherId(HttpServletRequest request) {
        int id= 0;
        id=(int) request.getSession().getAttribute("id");
        TeacherExample teacherExample=new TeacherExample();
        teacherExample.createCriteria().andIdEqualTo(id);
        return teacherMapper.selectByExample(teacherExample).get(0).getId();
    }
//  也是自己编辑自己信息，先获取id然后创建旧副本这样的方法
    @Override
    public void selfUpdateTeacher(String username, String email, String department, String password, HttpServletRequest request) {
        int id= 0;
        id=getCurrentTeacherId(request);
        TeacherExample teacherExample=new TeacherExample();
        teacherExample.createCriteria().andIdEqualTo(id);
        Teacher teacher=teacherMapper.selectByExample(teacherExample).get(0);
        if(!username.equals("")) teacher.setUsername(username);
        if(!email.equals("")) teacher.setEmail(email);
        if(!password.equals("")) teacher.setPassword(password);
        if(!department.equals("")) teacher.setDepartment(department);
        teacherMapper.updateByExampleSelective(teacher,teacherExample);
    }
//查看所有教师
    @Override
    public List<Teacher> allTeacher() {
        TeacherExample teacherExample=new TeacherExample();
        teacherExample.createCriteria().getAllCriteria();
        return teacherMapper.selectByExample(teacherExample);
    }
//  管理员增加教师
    @Override
    public void Add(String userName, String email, String password, String department) {
        Teacher teacher=new Teacher();
        teacher.setUsername(userName);teacher.setEmail(email);teacher.setDepartment(department);teacher.setPassword(password);
        teacherMapper.insertSelective(teacher);
    }
//管理员编辑教师
    @Override
    public void upperUpdate(int id,String userName, String email, String password, String department) {
        TeacherExample teacherExample=new TeacherExample();
        teacherExample.createCriteria().andIdEqualTo(id);
        Teacher teacher=teacherMapper.selectByExample(teacherExample).get(0);
        if(!userName.equals("")) teacher.setUsername(userName);
        if(!email.equals("")) teacher.setEmail(email);
        if(!password.equals("")) teacher.setPassword(password);
        if(!department.equals("")) teacher.setDepartment(department);
        teacherMapper.updateByExampleSelective(teacher,teacherExample);
    }
//管理员删除教师
    @Override
    public void delete(int id) {
        TeacherExample teacherExample=new TeacherExample();
        teacherExample.createCriteria().andIdEqualTo(id);
        teacherMapper.deleteByExample(teacherExample);
    }
//  教师查看自己的订单
    @Override
    public List<Approval> teacherApproval(HttpServletRequest request) {
        Teacher teacher=getCurrentTeacher(request);
        int teacherId=teacher.getId();
        ApprovalExample approvalExample=new ApprovalExample();
        approvalExample.createCriteria().andTeacherIdEqualTo(teacherId);
        return approvalMapper.selectByExample(approvalExample);
    }
}
