package com.example.thinker.service;

import com.example.thinker.domain.Member;
import com.example.thinker.dto.request.SurveyMakeRequest;
import com.example.thinker.dto.response.SurveyDataResponse;

import java.io.IOException;

public interface SurveyService {

    Long postSubjective(Member loginMember, Long subjectiveId, String answer);

    Long postMultiple(Member loginMember, Long multipleChoiceId, Long itemId, boolean isCheck);

    void makeSurveyBySurveyMakeRequest(Member loginMember, SurveyMakeRequest surveyMakeRequest) throws IOException;

    SurveyDataResponse getSurvey(Member loginMember, Long surveyId);

    void editSurvey(Member loginMember, Long surveyId, boolean isPremium);

    void deleteSurvey(Member loginMember, Long surveyId);
}
