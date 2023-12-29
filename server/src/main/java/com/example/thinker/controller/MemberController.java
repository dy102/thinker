package com.example.thinker.controller;

import com.example.thinker.domain.Member;
import com.example.thinker.dto.request.MemberDataRequest;
import com.example.thinker.dto.response.MemberDataResponse;
import com.example.thinker.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.net.URI;

import static com.example.thinker.session.SessionConst.LOGIN_MEMBER;

@RestController
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/members/login")
    public ResponseEntity<String> login(@RequestParam String customId,
                                        @RequestParam String password,
                                        HttpServletRequest request) {
        Member loginMember;
        try {
            loginMember = memberService.login(customId, password);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }

        HttpSession session = request.getSession();
        session.setAttribute(LOGIN_MEMBER, loginMember);
        return new ResponseEntity<>("success login", HttpStatus.OK);
    }

    @GetMapping("/members")
    public ResponseEntity<MemberDataResponse> readMemberData(
            @SessionAttribute(name = LOGIN_MEMBER, required = false) Member loginMember
    ) {
        if (loginMember == null) {
            URI loginPageUri = URI.create("/members/login");
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(loginPageUri);
            return new ResponseEntity<>(headers, HttpStatus.SEE_OTHER);
        }

        return new ResponseEntity<>(memberService.read(loginMember), HttpStatus.OK);


    }

    @PostMapping("/members")
    public ResponseEntity<String> createMemberData(@RequestBody MemberDataRequest memberDataRequest) {
        try {
            memberService.create(memberDataRequest);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>("success create memberData", HttpStatus.OK);
    }

    @PutMapping("/members")
    public ResponseEntity<String> updateMemberData(
            @SessionAttribute(name = LOGIN_MEMBER, required = false) Member loginMember,
            @RequestBody MemberDataRequest memberDataRequest) {
        if (loginMember == null) {
            URI loginPageUri = URI.create("/members/login");
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(loginPageUri);
            return new ResponseEntity<>(headers, HttpStatus.SEE_OTHER);
        }
        try {
            memberService.update(loginMember, memberDataRequest);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>("success update memberData", HttpStatus.OK);
    }
}
