package com.subin.board.springboot.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.subin.board.springboot.domain.posts.Posts;
import com.subin.board.springboot.domain.posts.PostsRepository;
import com.subin.board.springboot.web.dto.PostsResponseDto;
import com.subin.board.springboot.web.dto.PostsSaveRequestDto;
import com.subin.board.springboot.web.dto.PostsUpdateRequestDto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*
- @SpringBootTest
    - 기존의 @WebMvcTest의 경우 JPA 기능이 작동하지 않음 그렇기 때문에 JPA를 지원하는 @SpringBootTest와 TestRestTemplate를 사용
    - 랜덤 포트 실행법
        - 호스트가 사용하지 않는 랜덤 포트를 사용하겠다는 의미
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApiControllerTest {

    @LocalServerPort
    private int port;

    /*
    - TestRestTemplate
        - TestRestTemplate 이라는 클래스를 사용해서 기존 RestTemplate 클래스의 사용방식처럼 테스트를 할 수 있다.
        - restTemplate.postForEntity(url, 메서드의 매개변수, 메서드의 리턴타입)
            - 생성을 진행할때 사용하는 메서드
     */
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostsRepository postsRepository;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setup(){
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @After
    public void tearDown() throws Exception{
        postsRepository.deleteAll();
    }

    /*@Test
    @WithMockUser(roles = "USER")
    public void Posts_조회한다() throws  Exception{

        // 테이블 생성 및 값 넣어주는 용
        // 등록하기는 USER 권한이 있어야 하지만 조회 메서드에 어노테이션으로 달아줘서 가능해짐
        Posts_등록된다();
        String url = "http://localhost:" + port + "/api/v1/posts/1";

        // getForObject() 사용
        PostsResponseDto responseDto = restTemplate.getForObject(url, PostsResponseDto.class, 1);
        assertThat(responseDto.getId()).isNotNull().isEqualTo(1);


        // exchange() 사용
        // exchange(url, 메서드 종류(get,post,put,delete), 매개변수에 엔티티가 있는지..?, 리턴 타입)
        ResponseEntity<PostsResponseDto> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, PostsResponseDto.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        // JSON 변환(ResponseEntity의 내용물을 보기 위해 사용)
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(responseEntity);

        System.out.println(">>>>>> " + jsonString);
    }*/

    /*
    @WithMockUser(roles = "USER") → 해당 테스트 코드에 임의의 사용자 인증 추가
        - 인증된 모의(가짜) 사용자를 만들어서 사용
        - roles에 권한을 추가할 수 있음
        - 즉, 이 어노테이션으로 인해 ROLE_USER 권한을 가진 사용자가 API를 요청하는 것과 동일한 효과를 가지게 됨
     */
    @Test
    @WithMockUser(roles = "USER")
    public void Posts_등록된다() throws  Exception{

        // given
        String title = "title";
        String content = "content";

        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                .title(title)
                .content(content)
                .author("author")
                .build();

        String url = "http://localhost:" + port + "/api/v1/posts";

        //when
        mvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());

        //then
        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);

    }

    @Test
    @WithMockUser(roles = "USER")
    public void Posts_수정된다() throws Exception{

        // 테이블 생성 및 게시글 한개 생성
        Posts savePosts = postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        // update
        Long updateId = savePosts.getId();
        String expectedTitle = "title2";
        String expectedContent = "content2";

        PostsUpdateRequestDto requestDto = PostsUpdateRequestDto.builder()
                .title(expectedTitle)
                .content(expectedContent)
                .build();

        String url = "http://localhost:" + port + "/api/v1/posts/" + updateId;

        // HttpEntity<객체타입> requestEntity = new HttpEntity<>(객체);
        HttpEntity<PostsUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        //when
        mvc.perform(put(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());

        //then
        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
        assertThat(all.get(0).getContent()).isEqualTo(expectedContent);

    }


}
