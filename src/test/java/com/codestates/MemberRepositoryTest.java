package com.codestates;

import com.codestates.member.entity.Member;
import com.codestates.member.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void saveMemberTest() { // 정보 저장 테스트
        Member member = new Member();
        member.setEmail("hgd@gmail.com");
        member.setName("홍길동");
        member.setPhone("010-1111-2222");

        Member savedMember = memberRepository.save(member);

        assertNotNull(savedMember);
        assertTrue(member.getEmail().equals(savedMember.getEmail()));
        assertTrue(member.getName().equals(savedMember.getName()));
        assertTrue(member.getPhone().equals(savedMember.getPhone()));
    }

    @Test
    public void findByEmailTest() { // 정보 조회 테스트
        Member member = new Member();
        member.setEmail("hgd@gmail.com");
        member.setName("홍길동");
        member.setPhone("010-1111-2222");

        memberRepository.save(member);
        Optional<Member> findMember = memberRepository.findByEmail(member.getEmail());

        assertTrue(findMember.isPresent()); // null이 아닌지를 검증
        assertTrue(findMember.get().getEmail().equals(member.getEmail())); // 조회한 회원의 이메일 주소와 테스트 데이터의 이메일과 일치하는지 검증
    }
}
