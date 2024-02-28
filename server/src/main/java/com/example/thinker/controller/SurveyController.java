package com.example.thinker.controller;

import com.example.thinker.domain.Member;
import com.example.thinker.dto.SurveyDtos;
import com.example.thinker.dto.request.SurveyMakeRequest;
import com.example.thinker.dto.response.PremiumSurveysResponse;
import com.example.thinker.dto.response.SurveyDataResponse;
import com.example.thinker.dto.response.SurveysResponse;
import com.example.thinker.service.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
public class SurveyController {
    private static final int SURVEY_LIST_SIZE = 8;
    private static final int PREMIUM_SURVEY_SIZE = 3;
    private static final int SCROLL_SIZE = 8;

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

    @GetMapping("/surveys/premium")
    public ResponseEntity<PremiumSurveysResponse> getPremiumSurveys(
            @SessionAttribute(name = LOGIN_MEMBER, required = false) Member loginMember,
            @RequestParam int page
    ) {
        PremiumSurveysResponse premiumSurveysResponse
                = surveyService.getPremiumSurveys(loginMember, page, PREMIUM_SURVEY_SIZE);
        return new ResponseEntity<>(premiumSurveysResponse, HttpStatus.OK);
    }

    @GetMapping("/surveys/{kind}")
    public ResponseEntity<SurveysResponse> getSurveys(
            @SessionAttribute(name = LOGIN_MEMBER, required = false) Member loginMember,
            @PathVariable String kind,
            @RequestParam Long lastId
    ) {
        SurveysResponse surveysResponse = surveyService.getSurveys(loginMember, kind, SCROLL_SIZE, lastId);
        return new ResponseEntity<>(surveysResponse, HttpStatus.OK);
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

    @PostMapping("/surveys/participate")
    public ResponseEntity<String> participateSurvey(
            @SessionAttribute(name = LOGIN_MEMBER, required = false) Member loginMember,
            @RequestParam Long surveyId
    ) {
        checkAuthorization(loginMember);
        surveyService.participateSurvey(loginMember, surveyId);
        return new ResponseEntity<>("success participate survey", HttpStatus.OK);
    }

    @PostMapping("/surveys/make")
    public ResponseEntity<String> makeSurvey(
            @SessionAttribute(name = LOGIN_MEMBER, required = false) Member loginMember,
            @RequestPart SurveyMakeRequest surveyMakeRequest,
            @RequestPart MultipartFile multipartFile
    ) throws IOException {
        checkAuthorization(loginMember);
        surveyService.makeSurveyBySurveyMakeRequest(loginMember, surveyMakeRequest, multipartFile);
        return new ResponseEntity<>("success make survey", HttpStatus.OK);
    }

    @GetMapping("/surveys/owner/{kind}")
    public ResponseEntity<SurveyDtos> getMySurveys(
            @SessionAttribute(name = LOGIN_MEMBER, required = false) Member loginMember,
            @PathVariable String kind,
            @RequestParam int page
    ) {
        checkAuthorization(loginMember);
        SurveyDtos surveyDtos = surveyService.getMySurveys(loginMember, kind, page, SURVEY_LIST_SIZE);
        return new ResponseEntity<>(surveyDtos, HttpStatus.OK);
    }

    @GetMapping("/surveys/participate/{kind}")
    public ResponseEntity<SurveyDtos> getSurveysParticipatedIn(
            @SessionAttribute(name = LOGIN_MEMBER, required = false) Member loginMember,
            @PathVariable String kind,
            @RequestParam int page
    ) {
        checkAuthorization(loginMember);
        SurveyDtos surveyDtos = surveyService.getSurveyParticipatedIn(loginMember, kind, page, SURVEY_LIST_SIZE);
        return new ResponseEntity<>(surveyDtos, HttpStatus.OK);
    }

    @GetMapping("/surveys/search/title")
    public ResponseEntity<SurveysResponse> searchSurveysByTitle(
            @SessionAttribute(name = LOGIN_MEMBER, required = false) Member loginMember,
            @RequestParam String kind,
            @RequestParam String title,
            @RequestParam Long lastId
    ) {
        SurveysResponse surveysResponse = surveyService.searchSurveysByTitle(loginMember, kind, title, lastId, SCROLL_SIZE);
        return new ResponseEntity<>(surveysResponse, HttpStatus.OK);
    }

    @GetMapping("/surveys/search/writer")
    public ResponseEntity<SurveysResponse> searchSurveysByWriter(
            @SessionAttribute(name = LOGIN_MEMBER, required = false) Member loginMember,
            @RequestParam String kind,
            @RequestParam String name,
            @RequestParam Long lastId
    ) {
        SurveysResponse surveysResponse = surveyService.searchSurveysByWriter(loginMember, kind, name, lastId, SCROLL_SIZE);
        return new ResponseEntity<>(surveysResponse, HttpStatus.OK);
    }

    @GetMapping("/surveys/manager")
    public ResponseEntity<SurveyDtos> getSurveysByMember(
            @SessionAttribute(name = LOGIN_MEMBER, required = false) Member loginMember,
            @RequestParam Long memberId,
            @RequestParam int page
    ) {
        checkAuthorization(loginMember);
        SurveyDtos surveyDtos = surveyService.getSurveysByMember(loginMember, memberId, page, SCROLL_SIZE);
        return new ResponseEntity<>(surveyDtos, HttpStatus.OK);
    }

    private static void checkAuthorization(Member loginMember) {
        if (loginMember == null) {
            throw new IllegalArgumentException(NEED_TO_LOGIN);
        }
    }
}
