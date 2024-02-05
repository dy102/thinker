package com.example.thinker.service;

import com.example.thinker.domain.Member;
import com.example.thinker.dto.MemberDataDto;
import com.example.thinker.dto.request.MemberDataRequest;
import com.example.thinker.repository.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2, replace = AutoConfigureTestDatabase.Replace.ANY)
@TestPropertySource("classpath:application-test.properties") //test용 properties 파일 설정
@SpringBootTest
@Transactional
class MemberServiceImplTest {

    @Autowired
    MemberRepository memberRepository;

    @AfterEach
    public void afterEach() {
        memberRepository.deleteAll();
    }

    @Test
    @DisplayName("올바른 정보 입력시 멤버가 생성된다.")
    void createMember() {
        Member member = create();
        assertThat(member.getCustomId()).isEqualTo("customId");
    }

    @Test
    @DisplayName("멤버 정보와 일치하는 로그인 정보를 입력시 로그인에 성공한다.")
    void login() {
        Member member = create();
        MemberService memberService = new MemberServiceImpl(memberRepository);

        Member loginMember = memberService.login("customId", "pwpwpw");
        assertThat(loginMember).isEqualTo(member);
    }

    @Test
    @DisplayName("가져온 멤버정보가 기존 멤버정보와 일치하는지 확인한다.")
    void read() {
        Member member = create();
        MemberService memberService = new MemberServiceImpl(memberRepository);
        MemberDataDto memberDataDto = memberService.read(member);
        assertThat(memberDataDto.customId()).isEqualTo(member.getCustomId());
    }

    @DisplayName("잘못된 형식의 아이디를 입력할 시 오류가 발생한다.")
    @ParameterizedTest
    @MethodSource("inValidParameters")
    void validateCustomId(MemberDataRequest memberDataRequest, String message) {
        MemberService memberService = new MemberServiceImpl(memberRepository);
        assertThatThrownBy(() -> memberService.create(memberDataRequest))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(message);
    }

    static Stream<Arguments> inValidParameters() {
        return Stream.of(
                Arguments.of(new MemberDataRequest(
                        "c", "pwpwpw", "name",
                        2023, 1, 1, "woman"
                ), "아이디는 2~20자여야 합니다."),
                Arguments.of(new MemberDataRequest(
                        "cccccccccccccccccccccccccccc", "pwpwpw", "name",
                        2023, 1, 1, "woman"
                ), "아이디는 2~20자여야 합니다."),
                Arguments.of(new MemberDataRequest(
                        "aa!a", "pwpwpw", "name",
                        2023, 1, 1, "woman"
                ), "아이디에는 영어와 숫자만 입력 가능합니다."),
                Arguments.of(new MemberDataRequest(
                        "aaaa@", "pwpwpw", "name",
                        2023, 1, 1, "woman"
                ), "아이디에는 영어와 숫자만 입력 가능합니다."),
                Arguments.of(new MemberDataRequest(
                        "aa ", "pwpwpw", "name",
                        2023, 1, 1, "woman"
                ), "아이디에는 영어와 숫자만 입력 가능합니다."),
                Arguments.of(new MemberDataRequest(
                        "아이디", "pwpwpw", "name",
                        2023, 1, 1, "woman"
                ), "아이디에는 영어와 숫자만 입력 가능합니다.")
        );
    }

    @Test
    @DisplayName("이미 존재하는 아이디를 입력할 시 오류가 발생한다.")
    void validateCustomIdByDuplication() {
        create();
        MemberService memberService = new MemberServiceImpl(memberRepository);
        assertThatThrownBy(() -> memberService.create(new MemberDataRequest(
                "customId", "pwpwpw", "name",
                2023, 1, 1, "woman"
        )))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미 존재하는 아이디입니다.");
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