package com.example.thinker.controller;

import com.example.thinker.domain.Member;
import com.example.thinker.dto.ThinkingDtos;
import com.example.thinker.dto.request.ThinkingRequest;
import com.example.thinker.dto.response.PremiumThinkingResponse;
import com.example.thinker.dto.response.ThinkingDetailResponse;
import com.example.thinker.dto.response.ThinkingResponse;
import com.example.thinker.service.ThinkingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.io.IOException;

import static com.example.thinker.constants.SessionConst.LOGIN_MEMBER;

@RestController
@RequiredArgsConstructor
public class ThinkingController {

    private static final int THINKING_LIST_SIZE = 8;
    private static final int PREMIUM_THINKING_SIZE = 3;
    private static final int SCROLL_SIZE = 8;
    private static final String NEED_TO_LOGIN = "로그인이 필요합니다.";

    private final ThinkingService thinkingService;

    @GetMapping("/thinking/premium")
    public ResponseEntity<PremiumThinkingResponse> getPremiumThinking(@RequestParam int page) {
        PremiumThinkingResponse premiumThinking = thinkingService.findPremiumThinkings(page, PREMIUM_THINKING_SIZE);
        return new ResponseEntity<>(premiumThinking, HttpStatus.OK);
    }

    @GetMapping("/thinking/{kind}")
    public ResponseEntity<ThinkingResponse> getThinking(@PathVariable String kind,
                                                        Long lastId) {
        ThinkingResponse thinkingResponse = thinkingService.findThinkings(kind, SCROLL_SIZE, lastId);
        return new ResponseEntity<>(thinkingResponse, HttpStatus.OK);
    }

    @GetMapping("/thinking")
    public ResponseEntity<ThinkingDetailResponse> getThinkingDetail(
            @SessionAttribute(name = LOGIN_MEMBER, required = false) Member loginMember,
            @RequestParam Long thinkingId) {
        ThinkingDetailResponse thinkingDetailResponse = thinkingService.getThinkingDetail(thinkingId, loginMember);
        return new ResponseEntity<>(thinkingDetailResponse, HttpStatus.OK);
    }

    @PostMapping("/thinking")
    public ResponseEntity<String> postThinking(
            @SessionAttribute(name = LOGIN_MEMBER, required = false) Member loginMember,
            @RequestBody ThinkingRequest thinkingRequest) throws IOException {
        checkAuthorization(loginMember);
        thinkingService.makeThinking(thinkingRequest, loginMember);
        return new ResponseEntity<>("success save thinking", HttpStatus.OK);
    }

    @PutMapping("/thinking")
    public ResponseEntity<String> putThinking(
            @SessionAttribute(name = LOGIN_MEMBER, required = false) Member loginMember,
            @RequestParam Long thinkingId,
            @RequestPart ThinkingRequest thinkingRequest) throws IOException {
        checkAuthorization(loginMember);
        thinkingService.updateThinking(thinkingId, thinkingRequest, loginMember);
        return new ResponseEntity<>("success save thinking", HttpStatus.OK);
    }

    @DeleteMapping("/thinking")
    public ResponseEntity<String> deleteThinking(
            @SessionAttribute(name = LOGIN_MEMBER, required = false) Member loginMember,
            @RequestParam Long thinkingId
    ) {
        checkAuthorization(loginMember);
        thinkingService.deleteThinking(loginMember, thinkingId);
        return new ResponseEntity<>("success delete thinking", HttpStatus.OK);
    }

    @GetMapping("/thinking/search/title")
    public ResponseEntity<ThinkingResponse> searchThinkingByTitle(
            @RequestParam String title,
            @RequestParam String kind,
            @RequestParam Long lastId
    ) {
        ThinkingResponse thinkingResponse = thinkingService.findThinkingByTitle(title, kind, SCROLL_SIZE, lastId);
        return new ResponseEntity<>(thinkingResponse, HttpStatus.OK);
    }

    @GetMapping("/thinking/search/content")
    public ResponseEntity<ThinkingResponse> searchThinkingByContent(
            @RequestParam String content,
            @RequestParam String kind,
            @RequestParam Long lastId
    ) {
        ThinkingResponse thinkingResponse = thinkingService.findThinkingByContent(content, kind, SCROLL_SIZE, lastId);
        return new ResponseEntity<>(thinkingResponse, HttpStatus.OK);
    }

    @GetMapping("/thinking/search/writer")
    public ResponseEntity<ThinkingResponse> searchThinkingByWriter(
            @RequestParam String writer,
            @RequestParam String kind,
            @RequestParam Long lastId
    ) {
        ThinkingResponse thinkingResponse = thinkingService.findThinkingByWriter(writer, kind, SCROLL_SIZE, lastId);
        return new ResponseEntity<>(thinkingResponse, HttpStatus.OK);
    }

    @GetMapping("/thinking/manager")
    public ResponseEntity<ThinkingResponse> getActivityDetails(
            @SessionAttribute(name = LOGIN_MEMBER, required = false) Member loginMember,
            @RequestParam Long memberId,
            @RequestParam Long lastId
    ) {
        checkAuthorization(loginMember);
        ThinkingResponse thinkingResponse = thinkingService.getMemberActivityDetails(memberId, SCROLL_SIZE, lastId);
        return new ResponseEntity<>(thinkingResponse, HttpStatus.OK);
    }

    @GetMapping("/thinking/owner/{kind}")
    public ResponseEntity<ThinkingDtos> getMyThinkings(
            @SessionAttribute(name = LOGIN_MEMBER, required = false) Member loginMember,
            @PathVariable String kind,
            @RequestParam int page
    ) {
        checkAuthorization(loginMember);
        ThinkingDtos myThinkings = thinkingService.getMyThinkings(loginMember, kind, page, THINKING_LIST_SIZE);
        return new ResponseEntity<>(myThinkings, HttpStatus.OK);
    }

    @GetMapping("/thinking/reply/{kind}")
    public ResponseEntity<ThinkingDtos> getThinkingsByMyReply(
            @SessionAttribute(name = LOGIN_MEMBER, required = false) Member loginMember,
            @PathVariable String kind,
            @RequestParam int page
    ) {
        checkAuthorization(loginMember);
        ThinkingDtos thinkingDtos = thinkingService.getThinkingsByMyReply(loginMember, kind, page, THINKING_LIST_SIZE);
        return new ResponseEntity<>(thinkingDtos, HttpStatus.OK);
    }

    private static void checkAuthorization(Member loginMember) {
        if (loginMember == null) {
            throw new IllegalArgumentException(NEED_TO_LOGIN);
        }
    }
}
