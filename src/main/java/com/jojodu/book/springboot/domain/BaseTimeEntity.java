package com.jojodu.book.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass // JPA Entity 클래스들이 BaseTimeEntity을 상속할 경우 필드들도 칼럼으로 인식
@EntityListeners(AuditingEntityListener.class) //Auditing 기능을 포함
public class BaseTimeEntity {

    @CreatedDate //Entity 생성 시간 자동 저장
    private LocalDateTime createdDate;

    @LastModifiedDate //Entity 수정 시간 자동 저장
    private LocalDateTime modifiedDate;


}
