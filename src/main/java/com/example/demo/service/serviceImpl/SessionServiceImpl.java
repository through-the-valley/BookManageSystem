package com.example.demo.service.serviceImpl;

import com.example.demo.service.SessionService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author dell
 * @create 2020/4/29 14:44
 */
@Service
public class SessionServiceImpl implements SessionService {
    @Override
    public boolean check(HttpServletRequest request) {
        if(request.getSession()==null){
            return Boolean.TRUE;
        }else
            return Boolean.FALSE;
    }
}
