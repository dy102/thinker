package com.example.thinker.service;

import com.example.thinker.domain.Member;
import com.example.thinker.domain.Thinking;
import com.example.thinker.dto.request.MemberDataRequest;
import com.example.thinker.dto.request.ThinkingRequest;
import com.example.thinker.dto.response.PremiumThinkingResponse;
import com.example.thinker.dto.response.ThinkingResponse;
import com.example.thinker.repository.ImageRepository;
import com.example.thinker.repository.MemberRepository;
import com.example.thinker.repository.ReplyRepository;
import com.example.thinker.repository.ThinkingRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2, replace = AutoConfigureTestDatabase.Replace.ANY)
@TestPropertySource("classpath:application-test.properties") //test용 properties 파일 설정
@Transactional
class ThinkingServiceImplTest {
    @Autowired
    ThinkingService thinkingService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    ThinkingRepository thinkingRepository;
    @Autowired
    ReplyRepository replyRepository;
    @Autowired
    ImageRepository imageRepository;

//    @AfterEach
//    public void afterEach() {
//        thinkingRepository.deleteAll();
//        memberRepository.deleteAll();
//    }

    @Test
    @DisplayName("페이지를 입력하면 그에 해당하는 프리미엄 게시글들을 보여준다.")
    void findPremiumThinking() {
        PremiumThinkingResponse premiumThinking = thinkingService.findPremiumThinking(1, 3);
        //0페이지 : 7,6,5 //1페이지 : 4,3,2 //2페이지 : 1,0
        System.out.println(premiumThinking.toString());
        assertThat(premiumThinking.premiumThinkingDtos().dtos().get(0).thinkingTitle())
                .isEqualTo("title6");
    }

    @Test
    @DisplayName("게시글을 등록한뒤 게시글을 확인할 때, 등록한 정보와 일치하는지 확인한다.")
    void postThinkingAndGetThinkingDetail() {
        Member member = create();
        ThinkingRequest thinkingRequest = new ThinkingRequest(null, "title",
                "myContents", true);
        Thinking thinking;
        try {
            thinking = thinkingService.makeThinking(thinkingRequest, member);
            System.out.println(thinking.getId());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assertThat(thinkingService.getThinkingDetail(thinking.getId(), member)
                .thinkingDetailDto().thinkingContents())
                .isEqualTo("myContents");
    }

    @Test
    @DisplayName("무한스크롤 테스트")
    void scrollThinking() {
        ThinkingResponse thinkingResponse = thinkingService.findThinking("like", 2, 5L);
        System.out.println(thinkingResponse);
        assertThat(thinkingResponse.getContents().dtos().get(1).thinkingId()).isEqualTo(7L);
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