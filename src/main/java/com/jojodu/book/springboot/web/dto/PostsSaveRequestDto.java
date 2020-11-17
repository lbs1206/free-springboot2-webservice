package com.jojodu.book.springboot.web.dto;


import com.jojodu.book.springboot.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor // 기본 생성자 자동 추가 public Posts(){} 와 같은 효과 (lombok)
public class PostsSaveRequestDto {
    private String title;
    private String content;
    private String author;

    @Builder // 해당 클래스의 빌더 패턴 클래스를 생성 , 생성자 상단에 선언시 생성자에 포함된 필드만 빌더에 포함
    public PostsSaveRequestDto(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity(){
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
