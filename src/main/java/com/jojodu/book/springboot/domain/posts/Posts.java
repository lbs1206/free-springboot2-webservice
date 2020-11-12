package com.jojodu.book.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Getter // Getter 메소드 자동 생성
@NoArgsConstructor // 기본 생성자 자동 추가 public Posts(){} 와 같은 효과 (lombok)
@Entity // 테이블과 링크될 클래스 : 기본값으로 클래스의 카멜케이스 이름을 _으로 테이블 이름을 매칭 (SalesManager.java -> sales_manager table)
public class Posts {

    @Id //PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) //PK 규칙
    private Long id;

    @Column(length = 500, nullable = false) // 컬럼 굳이 선언하지 않더라도  해당 클래스의 필드는 모두 컬럼
    private String title;                   //사용하는 이유는 옵션 추가 (문자열의 경우 VARCHAR(255)기본 사이즈 늘리거나 TEXT로 변경위해서 사용)

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder // 해당 클래스의 빌더 패턴 클래스를 생성 , 생성자 상단에 선언시 생성자에 포함된 필드만 빌더에 포함
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

}
