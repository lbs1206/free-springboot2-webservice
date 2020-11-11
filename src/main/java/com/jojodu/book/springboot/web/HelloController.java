package com.jojodu.book.springboot.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.jojodu.book.springboot.web.dto.HelloResponseDto;

@RestController //Json 형태로 객체 데이터 반환 어노테이션
public class HelloController {

    @GetMapping("/hello")// Get 요청을 받을 수 있는 API
    public String hello(){
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name
                                            ,@RequestParam("amount") int amount){

        return new HelloResponseDto(name, amount);
    }
    //@RequestParam 외부 에서 API로 넘긴 파라미터를 가져 오는 어노테이션
    //여기서는 외부 에서 name(@RequestParam("name"))이란 이름 으로 넘긴 파라미터를 메소드 파라미터 name(String name)에 저장
}
