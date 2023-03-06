package com.codestates;

import com.codestates.member.dto.MemberDto;
import com.codestates.member.entity.Member;
import com.codestates.member.mapper.MemberMapper;
import com.codestates.member.service.MemberService;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.is; // 자동가져오기 안됨
import static org.hamcrest.Matchers.startsWith;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post; // 자동가져오기 안됨
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc // MockMvc 객체를 생성해 컨트롤러의 테스트를 위한 API 테스트를 가능하게 해줌
public class MemberControllerMockTest {
    @Autowired // 의존성 주입 자동으로 해줌 + bean등록
    private MockMvc mockMvc;
    @Autowired
    private Gson gson;
    @MockBean // Mock 객체만들어서 주입해 줌 (Mocking할 수 있게 해줌)
    private MemberService memberService;
    @Autowired
    private MemberMapper mapper;

    @Test
    void postMemberTest() throws Exception {
        MemberDto.Post post = new MemberDto.Post("hgd@gmail.com",
                "홍길동",
                "010-1234-5678");

        Member member = mapper.memberPostToMember(post);
        member.setMemberId(1L);

        given(memberService.createMember(Mockito.any(Member.class))) // Mock객체의 createMember()메서드에 매개변수로 any Member객체가 들어가면
                .willReturn(member); // member 객체를 리턴하게 만드는 것

        String content = gson.toJson(post);

        ResultActions actions =
                mockMvc.perform(
                        post("/v11/members")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                );

        actions
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", is(startsWith("/v11/members/"))));
    }
}
