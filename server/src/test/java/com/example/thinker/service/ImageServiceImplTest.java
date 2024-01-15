package com.example.thinker.service;

import com.example.thinker.domain.Member;
import com.example.thinker.dto.request.MemberDataRequest;
import com.example.thinker.repository.ImageRepository;
import com.example.thinker.repository.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2, replace = AutoConfigureTestDatabase.Replace.ANY)
@TestPropertySource("classpath:application-test.properties") //test용 properties 파일 설정
@SpringBootTest
@Transactional
class ImageServiceImplTest {

    @Autowired
    ImageRepository imageRepository;

    @Autowired
    MemberRepository memberRepository;

    @AfterEach
    public void afterEach() {
        memberRepository.deleteAll();
        imageRepository.deleteAll();
    }

    @Test
    @DisplayName("회원가입 시 기본 이미지를 등록한다.")
    void makeBasicImage() {
        Member member = create();
        ImageService imageService = new ImageServiceImpl(imageRepository, memberRepository);
        try {
            imageService.saveImage("src/test/resources/static/image/person.jpeg");
            imageService.makeBasicImage(member, "src/test/resources/static/image/person.jpeg");
            assertThat(member.getImage()).isEqualTo(imageRepository
                    .findByData(Files.readAllBytes(Paths.get("src/test/resources/static/image/person.jpeg"))));
        } catch (IOException e) {

        }
        assertThat(member.getImage()).isNotNull();
    }

    private Member create() {
        MemberService memberService = new MemberServiceImpl(memberRepository);
        MemberDataRequest memberDataRequest = new MemberDataRequest(
                "customId", "pwpwpw", "name",
                2023, 1, 1, "woman"
        );
        return memberService.create(memberDataRequest);
    }
}