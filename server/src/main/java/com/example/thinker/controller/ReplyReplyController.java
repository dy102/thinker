package com.example.thinker.controller;

import com.example.thinker.domain.Member;
import com.example.thinker.dto.request.ContentsRequest;
import com.example.thinker.dto.response.RepliesResponse;
import com.example.thinker.service.ReplyReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import static com.example.thinker.constants.ErrorConst.NEED_TO_LOGIN;
import static com.example.thinker.constants.SessionConst.LOGIN_MEMBER;

@RestController
@RequiredArgsConstructor
public class ReplyReplyController {
    private final ReplyReplyService replyReplyService;

    @GetMapping("/replyreplies")
    public ResponseEntity<RepliesResponse> getReplyReply(
            @SessionAttribute(name = LOGIN_MEMBER, required = false) Member loginMember,
            @RequestParam Long thinkingId,
            @RequestParam Long replyId
    ) {
        RepliesResponse response = replyReplyService.getReplyReplies(loginMember, thinkingId, replyId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/replyreplies")
    public ResponseEntity<String> postReplyReply(
            @SessionAttribute(name = LOGIN_MEMBER, required = false) Member loginMember,
            @RequestBody ContentsRequest contentsRequest
    ) {
        checkAuthorization(loginMember);
        replyReplyService.postReplyReply(loginMember, contentsRequest);
        return new ResponseEntity<>("success save replyreply", HttpStatus.OK);
    }

    @PutMapping("/replyreplies")
    public ResponseEntity<String> updateReplyReply(
            @SessionAttribute(name = LOGIN_MEMBER, required = false) Member loginMember,
            @RequestBody ContentsRequest contentsRequest
    ) {
        checkAuthorization(loginMember);
        replyReplyService.updateReplyReply(loginMember, contentsRequest);
        return new ResponseEntity<>("success update replyreply", HttpStatus.OK);
    }

    @DeleteMapping("/replyreplies")
    public ResponseEntity<String> deleteReplyReply(
            @SessionAttribute(name = LOGIN_MEMBER, required = false) Member loginMember,
            @RequestParam Long replyReplyId
    ) {
        checkAuthorization(loginMember);
        replyReplyService.deleteReplyReply(loginMember, replyReplyId);
        return new ResponseEntity<>("success delete replyreply", HttpStatus.OK);
    }

    @PatchMapping("/replyreplies/like")
    public ResponseEntity<String> likeReplyReply(
            @SessionAttribute(name = LOGIN_MEMBER, required = false) Member loginMember,
            @RequestParam Long replyReplyId
    ) {
        checkAuthorization(loginMember);
        boolean answer = replyReplyService.likeReplyReplies(loginMember, replyReplyId);
        if (answer) {
            return new ResponseEntity<>("success add replyreply like", HttpStatus.OK);
        }
        return new ResponseEntity<>("success remove replyreply like", HttpStatus.OK);
    }

    private static void checkAuthorization(Member loginMember) {
        if (loginMember == null) {
            throw new IllegalArgumentException(NEED_TO_LOGIN);
        }
    }
}
