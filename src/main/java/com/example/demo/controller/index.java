package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author dell
 * @create 2020/4/29 10:36
 */
@Controller
public class index {
    @GetMapping("/")
    public String index(){
        return "index";
    }
}
