package com.jojodu.book.springboot.web;

import com.jojodu.book.springboot.config.auth.SecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@WebMvcTest //여러 스프링 테스트 어노테이션중 Web에 집중할 수 있는 어노테이션(JPA 기능 사용불가)
@RunWith(SpringRunner.class)//스프링 부트와 JUnit 사이에 연결자 역할
@WebMvcTest(controllers = HelloController.class,
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
        }
)
public class HelloControllerTest {

    @Autowired //스프링이 관리하는 빈을 주입
    private MockMvc mvc;//웹 API 테스트 할떄 사용 , 스프링 MVC 테스트 의 시작점(GET,POST API 테스트가능)

    @WithMockUser(roles="USER")
    @Test
    public void hello_return() throws Exception{
        String hello = "hello";

        mvc.perform(get("/hello"))// MockMvc를 통해 /hello 주소로 GET 요청
                .andExpect(status().isOk())//결과 검증,Http header의 Status 검증ex)404,500....
                .andExpect(content().string(hello));//결과 검증, 응답 본문의 내용을 검증  hello 리턴값이 맞는지 검증

    }

    @WithMockUser(roles="USER")
    @Test
    public void helloDto_return() throws Exception{
        String name = "hello";
        int amount = 1000;

        mvc.perform(get("/hello/dto")
                    .param("name", name)
                    .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",is(name)))
                .andExpect(jsonPath("$.amount",is(amount)));

    }
}
