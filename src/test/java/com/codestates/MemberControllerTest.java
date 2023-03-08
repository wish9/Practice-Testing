package com.codestates;

import com.codestates.member.dto.MemberDto;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get; // 이것도 자동가져오기 안됨
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post; // 이것도 자동가져오기 안됨
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
class MemberControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @Test
    void postMemberTest() throws Exception {
        MemberDto.Post post = new MemberDto.Post("hgd@gmail.com", "홍길동", "010-1234-5678");
        String content = gson.toJson(post); // 응답문 JSON화 시키기

        ResultActions actions =
                mockMvc.perform( // 컨트롤러와의 상호작용을 시뮬레이션하게 해줌
                        post("/v11/members")  // HTTP POST METHOD와 request URL을 설정
                                .accept(MediaType.APPLICATION_JSON) // 리턴 받을 응답 타입을 JSON 타입으로 설정
                                .contentType(MediaType.APPLICATION_JSON) // Content Type을 JSON 타입으로 설정
                                .content(content)   // request body 데이터를 설정
                );

        actions // perform 메서드를 통해 리턴받은 ResultActions 객체를 이용해 전송한 request에 대한 검증을 수행
                .andExpect(status().isCreated()) // 회원 정보가 잘 생성(저장)되었는지를 검증
                .andExpect(header().string("Location", is(startsWith("/v11/members/"))));
    }

    @Test
    void getMemberTest() throws Exception {
        // postMember()를 이용한 테스트 데이터 생성 시작
        MemberDto.Post post = new MemberDto.Post("hgd@gmail.com","홍길동","010-1111-1111");
        String postContent = gson.toJson(post);

        ResultActions postActions =
                mockMvc.perform(
                        post("/v11/members")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(postContent)
                );
        // postMember()를 이용한 테스트 데이터 생성 끝

        // Location header의 값 가져오기
        String location = postActions.andReturn().getResponse().getHeader("Location"); // Location header의 값("/v11/members/1") 가져오기

        mockMvc.perform(
                        get(location)      // get() 메서드를 통해 String타입의 리소스 위치값을 MockHttpServletRequestBuilder로 바꿔서 가져옴
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())    // HTTP status가 200 OK인지를 검증
                .andExpect(jsonPath("$.data.email").value(post.getEmail()))   // 각 요소들 검증
                .andExpect(jsonPath("$.data.name").value(post.getName()))
                .andExpect(jsonPath("$.data.phone").value(post.getPhone()));

        //System.out.println(postActions.andReturn().getResponse().getContentAsString()); // response body를 출력
    }
}