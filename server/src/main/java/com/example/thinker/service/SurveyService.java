package com.example.thinker.service;

import com.example.thinker.domain.Member;
import com.example.thinker.dto.SurveyDtos;
import com.example.thinker.dto.request.SurveyMakeRequest;
import com.example.thinker.dto.response.PremiumSurveysResponse;
import com.example.thinker.dto.response.SurveyDataResponse;
import com.example.thinker.dto.response.SurveysResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface SurveyService {

    Long postSubjective(Member loginMember, Long subjectiveId, String answer);

    Long postMultiple(Member loginMember, Long multipleChoiceId, Long itemId, boolean isCheck);

    void makeSurveyBySurveyMakeRequest(Member loginMember, SurveyMakeRequest surveyMakeRequest, MultipartFile multipartFile) throws IOException;

    SurveyDataResponse getSurvey(Member loginMember, Long surveyId);

    void editSurvey(Member loginMember, Long surveyId, boolean isPremium);

    void deleteSurvey(Member loginMember, Long surveyId);

    PremiumSurveysResponse getPremiumSurveys(Member loginMember, int page, int size);

    SurveysResponse getSurveys(Member loginMember, String kind, int scrollSize, Long lastId);

    SurveyDtos getMySurveys(Member loginMember, String kind, int page, int size);

    SurveyDtos getSurveyParticipatedIn(Member loginMember, String kind, int page, int surveyListSize);

    SurveysResponse searchSurveysByTitle(Member loginMember, String kind, String title, Long lastId, int scrollSize);

    SurveysResponse searchSurveysByWriter(Member loginMember, String kind, String name, Long lastId, int scrollSize);

    SurveyDtos getSurveysByMember(Member loginMember, Long memberId, int page, int scrollSize);

    void participateSurvey(Member loginMember, Long surveyId);
}
