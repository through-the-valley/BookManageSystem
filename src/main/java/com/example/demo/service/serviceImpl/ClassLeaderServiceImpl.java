package com.example.demo.service.serviceImpl;

import com.example.demo.mapper.ApprovalMapper;
import com.example.demo.mapper.ClassleaderMapper;
import com.example.demo.model.Approval;
import com.example.demo.model.ApprovalExample;
import com.example.demo.model.Classleader;
import com.example.demo.model.ClassleaderExample;
import com.example.demo.service.ClassLeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author dell
 * @create 2020/4/29 10:54
 */
@Service
public class ClassLeaderServiceImpl implements ClassLeaderService {
    @Autowired
    private ClassleaderMapper classleaderMapper;
    @Autowired
    private ApprovalMapper approvalMapper;
    @Override
    public Classleader getCurrentClassLeader(HttpServletRequest request) {
        int id= 0;
        id=(int) request.getSession().getAttribute("id");
        ClassleaderExample classleaderExample=new ClassleaderExample();
        classleaderExample.createCriteria().andIdEqualTo(id);
        return classleaderMapper.selectByExample(classleaderExample).get(0);
    }

    @Override
    public int getCurrentClassLeaderId(HttpServletRequest request) {
        return (int) request.getSession().getAttribute("id");
    }

    @Override
    public void selfUpdateClassLeader(HttpServletRequest request,
                                      String username,
                                      String email,
                                      String classid,
                                      String department,
                                      String password){

        int id=getCurrentClassLeaderId(request);
        ClassleaderExample classleaderExample=new ClassleaderExample();
        classleaderExample.createCriteria().andIdEqualTo(id);
        Classleader classleader=getCurrentClassLeader(request);
        if(!username.equals("")) classleader.setUsername(username);
        if(!email.equals("")) classleader.setEmail(email);
        if(!password.equals("")) classleader.setPassword(password);
        if(!department.equals("")) classleader.setDepartment(department);
        if(!classid.equals("")) classleader.setClassid(classid);
        classleaderMapper.updateByExampleSelective(classleader,classleaderExample);
    }

    @Override
    public String getCurrentClassLeaderClassId(HttpServletRequest request) {
        int id= 0;
        id=(int) request.getSession().getAttribute("id");
        ClassleaderExample classleaderExample=new ClassleaderExample();
        classleaderExample.createCriteria().andIdEqualTo(id);
        return classleaderMapper.selectByExample(classleaderExample).get(0).getClassid();
    }

    @Override
    public List<Approval> listApproval(HttpServletRequest request) {
        String classid=getCurrentClassLeaderClassId(request);
        ApprovalExample approvalExample=new ApprovalExample();
        approvalExample.createCriteria().andToclassEqualTo(classid);
        return approvalMapper.selectByExample(approvalExample);
    }

    @Override
    public List<Classleader> listClassLeader() {
        ClassleaderExample classleaderExample=new ClassleaderExample();
        classleaderExample.createCriteria().getAllCriteria();
        return classleaderMapper.selectByExample(classleaderExample);
    }

    @Override
    public void add(String userName, String email, String password, String classid, String department) {
        Classleader classleader=new Classleader();
        classleader.setUsername(userName);classleader.setEmail(email);classleader.setDepartment(department);
        classleader.setPassword(password);classleader.setClassid(classid);
        classleaderMapper.insertSelective(classleader);
    }

    @Override
    public void upperUpdate(int id, String userName, String email, String password, String department, String classid) {
        ClassleaderExample classleaderExample=new ClassleaderExample();
        classleaderExample.createCriteria().andIdEqualTo(id);
        Classleader classleader=classleaderMapper.selectByExample(classleaderExample).get(0);
        if(!userName.equals("")) classleader.setUsername(userName);
        if(!email.equals("")) classleader.setEmail(email);
        if(!password.equals("")) classleader.setPassword(password);
        if(!department.equals("")) classleader.setDepartment(department);
        if(!classid.equals("")) classleader.setClassid(classid);
        classleaderMapper.updateByExampleSelective(classleader,classleaderExample);
    }

    @Override
    public void delete(int id) {
        ClassleaderExample classleaderExample=new ClassleaderExample();
        classleaderExample.createCriteria().andIdEqualTo(id);
        classleaderMapper.deleteByExample(classleaderExample);
    }
}
