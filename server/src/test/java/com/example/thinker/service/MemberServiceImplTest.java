package com.example.thinker.service;

import com.example.thinker.domain.Member;
import com.example.thinker.dto.request.MemberDataRequest;
import com.example.thinker.repository.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class MemberServiceImplTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @AfterEach
    public void afterEach() {
        memberRepository.deleteAll();
    }

    @Test
    @DisplayName("올바른 정보 입력시 멤버가 생성된다.")
    void createMember() {
        MemberDataRequest memberDataRequest = new MemberDataRequest(
                "customId", "pwpwpw", "name", "231219", "woman"
        );
        Member member = memberService.create(memberDataRequest);
        assertThat(member.getCustomId()).isEqualTo("customId");
    }

    @Test
    @DisplayName("멤버 정보와 일치하는 로그인 정보를 입력시 로그인에 성공한다.")
    void login() {
        MemberDataRequest memberDataRequest = new MemberDataRequest(
                "customId", "pwpwpw", "name", "231219", "woman"
        );
        Member member = memberService.create(memberDataRequest);

        Member loginMember = memberService.login("customId", "pwpwpw");
        assertThat(loginMember).isEqualTo(member);
    }
}