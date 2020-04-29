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
//对数据库操作的模子就是创建xxxExample，通过createCriteria选择自己所需要的筛选方式。最后用xxxmapper.xxxbyExample，注意这样返回的一般都是list
@Service
public class ClassLeaderServiceImpl implements ClassLeaderService {
    @Autowired
    private ClassleaderMapper classleaderMapper;
    @Autowired
    private ApprovalMapper approvalMapper;
//    通过session获取id然后查找班长
    @Override
    public Classleader getCurrentClassLeader(HttpServletRequest request) {
        int id= 0;
        id=(int) request.getSession().getAttribute("id");
        ClassleaderExample classleaderExample=new ClassleaderExample();
        classleaderExample.createCriteria().andIdEqualTo(id);
        return classleaderMapper.selectByExample(classleaderExample).get(0);
    }

//    通过session获取id，需要类型转换
    @Override
    public int getCurrentClassLeaderId(HttpServletRequest request) {
        return (int) request.getSession().getAttribute("id");
    }

//    编辑个人信息，此时是对自己操作，不需要提交id。
//    创建旧信息的副本。传过来值不为""时就进行对应的更新。
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

//    通过id找到班长然后获取classid，用于后续查看班级订单
    @Override
    public String getCurrentClassLeaderClassId(HttpServletRequest request) {
        int id= 0;
        id=(int) request.getSession().getAttribute("id");
        ClassleaderExample classleaderExample=new ClassleaderExample();
        classleaderExample.createCriteria().andIdEqualTo(id);
        return classleaderMapper.selectByExample(classleaderExample).get(0).getClassid();
    }
//通过classid找到班级订单
    @Override
    public List<Approval> listApproval(HttpServletRequest request) {
        String classid=getCurrentClassLeaderClassId(request);
        ApprovalExample approvalExample=new ApprovalExample();
        approvalExample.createCriteria().andToclassEqualTo(classid);
        return approvalMapper.selectByExample(approvalExample);
    }

//    getAllCriteria用于获取所有记录
    @Override
    public List<Classleader> listClassLeader() {
        ClassleaderExample classleaderExample=new ClassleaderExample();
        classleaderExample.createCriteria().getAllCriteria();
        return classleaderMapper.selectByExample(classleaderExample);
    }
//管理员新增班长。这种都是需要通过id来确定对象的
    @Override
    public void add(String userName, String email, String password, String classid, String department) {
        Classleader classleader=new Classleader();
        classleader.setUsername(userName);classleader.setEmail(email);classleader.setDepartment(department);
        classleader.setPassword(password);classleader.setClassid(classid);
        classleaderMapper.insertSelective(classleader);
    }
//管理员更新班长
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
// 管理员删除班长
    @Override
    public void delete(int id) {
        ClassleaderExample classleaderExample=new ClassleaderExample();
        classleaderExample.createCriteria().andIdEqualTo(id);
        classleaderMapper.deleteByExample(classleaderExample);
    }
}
