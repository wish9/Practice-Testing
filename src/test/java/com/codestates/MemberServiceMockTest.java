package com.codestates;

import com.codestates.exception.BusinessLogicException;
import com.codestates.member.entity.Member;
import com.codestates.member.repository.MemberRepository;
import com.codestates.member.service.MemberService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class) // Spring을 사용하지 않고, Junit에서 Mockito의 기능을 사용하려면 추가해야 함
public class MemberServiceMockTest {
    @Mock // 해당 필드의 객체를 Mock 객체로 생성
    private MemberRepository memberRepository;
    @InjectMocks    // @Mock 애너테이션으로 생성한 Mock 객체를 주입
    private MemberService memberService;

    @Test
    public void createMemberTest() {
        Member member = new Member("hgd@gmail.com", "홍길동", "010-1111-1111");

        given(memberRepository.findByEmail(Mockito.anyString())).willReturn(Optional.of(member));
        // 무조건 member가 저장되어 있다고 가정하게 만드는 것
        assertThrows(BusinessLogicException.class, () -> memberService.createMember(member));
    }
}
