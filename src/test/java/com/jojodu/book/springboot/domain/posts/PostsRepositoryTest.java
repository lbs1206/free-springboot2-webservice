package com.jojodu.book.springboot.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest // 사용할 경우 H2DB 자동 실행
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After //Junit에서 단위 테스트가 끝날 떄마다 수행되는 메소드를 지정,
           // 배포전 테스트간 데이터 침범을 막기위해 사용,
           // 여러 테스트가 동시에 수행되면 H2에 데이터가 그대로 남아 있어 다음 테스트 실행시 테스트가 실패 할 수 있음
    public void cleanup(){
        postsRepository.deleteAll();
    }

    @Test
    public void postSave_Get(){
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        postsRepository.save(Posts.builder()
                             .title(title)
                             .content(content)
                             .author("jojodu@gmail.com")
                             .build());
        //postsRepository.save 테이블 posts에 insert/update 쿼리 실행 id 값이 있다면 update 없다면 insert


        //when
        List<Posts> postsList = postsRepository.findAll();
        //findAll() 해당 테이블 모든 데이터 조회

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTimeEntity_Save(){
        //given
        LocalDateTime now = LocalDateTime.of(2019,6,4,0,0,0);
        postsRepository.save(Posts.builder().title("title").content("content").author("author").build());

        //when
        List<Posts> postsList =  postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);

        System.out.println(">>>>>>>>>>>> createDate=" + posts.getCreatedDate()+", modifiedDate=" +posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }
}
