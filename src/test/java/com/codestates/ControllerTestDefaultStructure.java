package com.codestates;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest       // Spring Boot 기반의 애플리케이션을 테스트 하기 위한 Application Context를 생성
@AutoConfigureMockMvc  // 자동 구성 작업을 해줌
public class ControllerTestDefaultStructure {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void postMemberTest() {
        // given - 테스트용 request body 생성

        // when - MockMvc 객체로 테스트 대상 Controller 호출

        // then - Controller 핸들러 메서드에서 응답으로 수신한 HTTP Status 및 response body 검증
    }
}