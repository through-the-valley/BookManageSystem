package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author dell
 * @create 2020/4/29 10:36
 */
@Controller
public class index {
//    返回首页
    @GetMapping("/")
    public String firstPage(){
        return "index";
    }
}
