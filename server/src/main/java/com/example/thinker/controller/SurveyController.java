package com.example.thinker.controller;

import com.example.thinker.domain.Member;
import com.example.thinker.dto.request.SurveyMakeRequest;
import com.example.thinker.dto.response.SurveyDataResponse;
import com.example.thinker.service.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.io.IOException;

import static com.example.thinker.constants.SessionConst.LOGIN_MEMBER;

@RestController
@RequiredArgsConstructor
public class SurveyController {
    private static final String NEED_TO_LOGIN = "로그인이 필요합니다.";

    private final SurveyService surveyService;

    @GetMapping("/surveys")
    public ResponseEntity<SurveyDataResponse> getSurvey(
            @SessionAttribute(name = LOGIN_MEMBER, required = false) Member loginMember,
            @RequestParam Long surveyId
    ) {
        checkAuthorization(loginMember);
        SurveyDataResponse surveyDataResponse = surveyService.getSurvey(loginMember, surveyId);
        return new ResponseEntity<>(surveyDataResponse, HttpStatus.OK);
    }

    @PutMapping("/surveys")
    public ResponseEntity<String> editSurvey(
            @SessionAttribute(name = LOGIN_MEMBER, required = false) Member loginMember,
            @RequestParam Long surveyId,
            @RequestParam boolean isPremium
    ) {
        checkAuthorization(loginMember);
        surveyService.editSurvey(loginMember, surveyId, isPremium);
        return new ResponseEntity<>("success make premium", HttpStatus.OK);
    }

    @DeleteMapping("/surveys")
    public ResponseEntity<String> deleteSurvey(
            @SessionAttribute(name = LOGIN_MEMBER, required = false) Member loginMember,
            @RequestParam Long surveyId
    ) {
        checkAuthorization(loginMember);
        surveyService.deleteSurvey(loginMember, surveyId);
        return new ResponseEntity<>("success delete survey", HttpStatus.OK);
    }

    @PostMapping("/surveys/subjective")
    public ResponseEntity<Long> postSubjective(
            @SessionAttribute(name = LOGIN_MEMBER, required = false) Member loginMember,
            @RequestParam Long subjectiveId,
            @RequestParam String answer
    ) {
        checkAuthorization(loginMember);
        Long surveyId = surveyService.postSubjective(loginMember, subjectiveId, answer);
        return new ResponseEntity<>(surveyId, HttpStatus.OK);
    }

    @PostMapping("/surveys/multiple")
    public ResponseEntity<Long> postMultiple(
            @SessionAttribute(name = LOGIN_MEMBER, required = false) Member loginMember,
            @RequestParam Long multipleChoiceId,
            @RequestParam Long itemId,
            @RequestParam boolean isCheck
    ) {
        checkAuthorization(loginMember);
        Long surveyId = surveyService.postMultiple(loginMember, multipleChoiceId, itemId, isCheck);
        return new ResponseEntity<>(surveyId, HttpStatus.OK);
    }

    @PostMapping("/surveys/make")
    public ResponseEntity<String> makeSurvey(
            @SessionAttribute(name = LOGIN_MEMBER, required = false) Member loginMember,
            @RequestBody SurveyMakeRequest surveyMakeRequest
    ) throws IOException {
        checkAuthorization(loginMember);
        surveyService.makeSurveyBySurveyMakeRequest(loginMember, surveyMakeRequest);
        return new ResponseEntity<>("success make survey", HttpStatus.OK);
    }

    private static void checkAuthorization(Member loginMember) {
        if (loginMember == null) {
            throw new IllegalArgumentException(NEED_TO_LOGIN);
        }
    }
}
