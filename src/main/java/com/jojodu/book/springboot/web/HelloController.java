package com.jojodu.book.springboot.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController //Json 형태로 객체 데이터 반환 어노테이션
public class HelloController {

    @GetMapping("/hello")// Get 요청을 받을 수 있는 API
    public String hello(){
        return "hello";
    }
}
