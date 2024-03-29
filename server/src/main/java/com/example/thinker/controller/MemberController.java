package com.example.thinker.controller;

import com.example.thinker.domain.Image;
import com.example.thinker.domain.Member;
import com.example.thinker.dto.MemberDataDto;
import com.example.thinker.dto.MemberSimpleDto;
import com.example.thinker.dto.request.MemberDataRequest;
import com.example.thinker.service.ImageService;
import com.example.thinker.service.MemberService;
import com.example.thinker.util.MyLogger;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static com.example.thinker.constants.ErrorConst.NEED_TO_LOGIN;
import static com.example.thinker.constants.SessionConst.LOGIN_MEMBER;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private static final Long BASIC_IMAGE_ID = 1L;

    private final MemberService memberService;
    private final ImageService imageService;

    private final MyLogger myLogger;

    @GetMapping("/members/login")
    public ResponseEntity<String> login(@RequestParam String customId,
                                        @RequestParam String password,
                                        HttpServletRequest request) {
        String requestURL = request.getRequestURI();
        myLogger.setRequestURL(requestURL);
        myLogger.log("login");

        Member loginMember = memberService.login(customId, password);
        HttpSession session = request.getSession();
        session.setAttribute(LOGIN_MEMBER, loginMember);
        return new ResponseEntity<>("success login", HttpStatus.OK);
    }

    @GetMapping("/members")
    public ResponseEntity<MemberDataDto> readMemberData(
            @SessionAttribute(name = LOGIN_MEMBER, required = false) Member loginMember
    ) {
        checkAuthorization(loginMember);
        MemberDataDto memberDataDto = memberService.read(loginMember);
        return new ResponseEntity<>(memberDataDto, HttpStatus.OK);
    }

    @GetMapping("/members/simple")
    public ResponseEntity<MemberSimpleDto> readMemberSimpleData(Long memberId) {
        MemberSimpleDto memberSimpleDto;
        memberSimpleDto = memberService.readSimple(memberId);
        return new ResponseEntity<>(memberSimpleDto, HttpStatus.OK);
    }

    @PostMapping("/members")
    public ResponseEntity<String> createMemberData(@RequestBody MemberDataRequest memberDataRequest) throws IOException {
        Member member;
        try {
            member = memberService.create(memberDataRequest);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        imageService.makeBasicImage(member, BASIC_IMAGE_ID);
        return new ResponseEntity<>("success create memberData", HttpStatus.OK);
    }

    @PutMapping("/members/edit")
    public ResponseEntity<String> updateMemberData(
            @SessionAttribute(name = LOGIN_MEMBER, required = false) Member loginMember,
            @RequestBody MemberDataRequest memberDataRequest) {
        checkAuthorization(loginMember);
        memberService.update(loginMember, memberDataRequest);
        return new ResponseEntity<>("success update memberData", HttpStatus.OK);
    }

    @PutMapping("/members/edit/image")
    public ResponseEntity<String> updateImage(
            @SessionAttribute(name = LOGIN_MEMBER, required = false) Member loginMember,
            @RequestPart MultipartFile file) {
        checkAuthorization(loginMember);
        String fileName = file.getOriginalFilename();
        try {
            imageService.uploadImage(fileName, file, loginMember);
        } catch (IOException e) {
            return new ResponseEntity<>("failed by IOException", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("success update memberImage", HttpStatus.OK);
    }

    @GetMapping("members/image")
    public ResponseEntity<byte[]> getImage(
            @SessionAttribute(name = LOGIN_MEMBER, required = false) Member loginMember
    ) {
        checkAuthorization(loginMember);
        Image image = imageService.getImageByMember(loginMember);
        if (image == null) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }

        byte[] imageData = image.getData();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG); // 이미지 유형에 맞게 수정
        return new ResponseEntity<>(imageData, headers, HttpStatus.OK);

    }

    private static void checkAuthorization(Member loginMember) {
        if (loginMember == null) {
            throw new IllegalArgumentException(NEED_TO_LOGIN);
        }
    }

}
