package com.example.demo.service.serviceImpl;

import com.example.demo.mapper.ApprovalMapper;
import com.example.demo.model.Approval;
import com.example.demo.model.ApprovalExample;
import com.example.demo.service.ApprovalService;
import com.example.demo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author dell
 * @create 2020/4/29 14:15
 */
@Service
public class ApprovalServiceImpl implements ApprovalService {
    @Autowired
    private ApprovalMapper approvalMapper;
    @Autowired
    private TeacherService teacherService;
//    展示所有订单
    @Override
    public List<Approval> allApproval() {
        ApprovalExample approvalExample=new ApprovalExample();
        approvalExample.createCriteria().getAllCriteria();
        return approvalMapper.selectByExample(approvalExample);
    }
//  订单状态更新。1通过，2不通过，0删除
    @Override
    public void changeApproval(int id, int act) {
        ApprovalExample approvalExample=new ApprovalExample();
        approvalExample.createCriteria().andIdEqualTo(id);
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
    }
//教师新增订单
    @Override
    public void add(int bookId, int quantity, String toclass, HttpServletRequest request) {
        int id=teacherService.getCurrentTeacherId(request);
        Approval approval=new Approval();
        approval.setBookId(bookId);approval.setQuantity(quantity);approval.setTeacherId(id);
        approval.setToclass(toclass);approval.setPass(2);
        approvalMapper.insertSelective(approval);
    }
}
