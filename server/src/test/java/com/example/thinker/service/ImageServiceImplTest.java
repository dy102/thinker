package com.example.thinker.service;

import com.example.thinker.domain.Member;
import com.example.thinker.dto.request.MemberDataRequest;
import com.example.thinker.repository.ImageRepository;
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
class ImageServiceImplTest {

    @Autowired
    ImageService imageService;

    @Autowired
    MemberService memberService;

    @Autowired
    ImageRepository imageRepository;

    @Autowired
    MemberRepository memberRepository;

    @AfterEach
    public void afterEach() {

    }

    @Test
    @DisplayName("회원가입 시 기본 이미지를 등록한다.")
    void makeBasicImage() {
        Member member = create();
        imageService.makeBasicImage(member);
        assertThat(member.getImage()).isEqualTo(imageRepository.findById(1L).get());
        assertThat(member.getImage()).isNotNull();
    }

    private Member create() {
        MemberDataRequest memberDataRequest = new MemberDataRequest(
                "customId", "pwpwpw", "name",
                2023, 1, 1, "woman"
        );
        return memberService.create(memberDataRequest);
    }
}