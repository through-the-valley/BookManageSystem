package com.example.demo.service;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author dell
 * @create 2020/4/29 14:44
 */
public interface SessionService {
    boolean check(HttpServletRequest request);
}
