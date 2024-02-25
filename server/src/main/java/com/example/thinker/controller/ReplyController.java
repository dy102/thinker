package com.example.thinker.controller;

import com.example.thinker.domain.Member;
import com.example.thinker.dto.response.RepliesResponse;
import com.example.thinker.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import static com.example.thinker.constants.ErrorConst.NEED_TO_LOGIN;
import static com.example.thinker.constants.SessionConst.LOGIN_MEMBER;

@RestController
@RequiredArgsConstructor
public class ReplyController {
    private final ReplyService replyService;

    @GetMapping("/replies")
    public ResponseEntity<RepliesResponse> getReplies(
            @SessionAttribute(name = LOGIN_MEMBER, required = false) Member loginMember,
            @RequestParam Long thinkingId
    ) {
        RepliesResponse response = replyService.getReplies(loginMember, thinkingId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/replies")
    public ResponseEntity<String> postReplies(
            @SessionAttribute(name = LOGIN_MEMBER, required = false) Member loginMember,
            @RequestParam Long thinkingId,
            @RequestParam String replyContent
    ) {
        checkAuthorization(loginMember);
        replyService.postReplies(loginMember, thinkingId, replyContent);
        return new ResponseEntity<>("success save reply", HttpStatus.OK);
    }

    @PutMapping("/replies")
    public ResponseEntity<String> updateReplies(
            @SessionAttribute(name = LOGIN_MEMBER, required = false) Member loginMember,
            @RequestParam Long replyId,
            @RequestParam String replyContent
    ) {
        checkAuthorization(loginMember);
        replyService.updateReplies(loginMember, replyId, replyContent);
        return new ResponseEntity<>("success update reply", HttpStatus.OK);
    }

    @DeleteMapping("/replies")
    public ResponseEntity<String> deleteReplies(
            @SessionAttribute(name = LOGIN_MEMBER, required = false) Member loginMember,
            @RequestParam Long replyId
    ) {
        checkAuthorization(loginMember);
        replyService.deleteReplies(loginMember, replyId);
        return new ResponseEntity<>("success delete reply", HttpStatus.OK);
    }

    @PatchMapping("/replies/like")
    public ResponseEntity<String> likeReplies(
            @SessionAttribute(name = LOGIN_MEMBER, required = false) Member loginMember,
            @RequestParam Long replyId
    ) {
        checkAuthorization(loginMember);
        boolean answer = replyService.likeReplies(loginMember, replyId);
        if (answer) {
            return new ResponseEntity<>("success add reply like", HttpStatus.OK);
        }
        return new ResponseEntity<>("success remove reply like", HttpStatus.OK);
    }

    @GetMapping("/replies/manager")
    public ResponseEntity<RepliesResponse> findRepliesByMember(
            @SessionAttribute(name = LOGIN_MEMBER, required = false) Member loginMember,
            @RequestParam Long memberId
    ) {
        checkAuthorization(loginMember);
        RepliesResponse response = replyService.findRepliesByMember(loginMember, memberId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    private static void checkAuthorization(Member loginMember) {
        if (loginMember == null) {
            throw new IllegalArgumentException(NEED_TO_LOGIN);
        }
    }
}
