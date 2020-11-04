package com.jojodu.book.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)//스프링 부트와 JUnit 사이에 연결자 역할
@WebMvcTest //여러 스프링 테스트 어노테이션중 Web에 집중할 수 있는 어노테이션
public class HelloControllerTest {

    @Autowired //스프링이 관리하는 빈을 주입
    private MockMvc mvc;//웹 API 테스트 할떄 사용 , 스프링 MVC 테스트 의 시작점(GET,POST API 테스트가능)

    @Test
    public void hello_return() throws Exception{
        String hello = "hello";

        mvc.perform(get("/hello"))// MockMvc를 통해 /hello 주소로 GET 요청
                .andExpect(status().isOk())//결과 검증,Http header의 Status 검증ex)404,500....
                .andExpect(content().string(hello));//결과 검증, 응답 본문의 내용을 검증  hello 리턴값이 맞는지 검증

    }
}