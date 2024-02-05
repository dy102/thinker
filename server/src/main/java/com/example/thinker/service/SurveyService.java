package com.example.thinker.service;

import com.example.thinker.domain.Member;

public interface SurveyService {

    Long postSubjective(Member loginMember, Long subjectiveId, String answer);

    Long postMultiple(Member loginMember, Long multipleChoiceId, Long itemId, boolean isCheck);

//    void makeSurveyBySurveyMakeRequest(Member loginMember, SurveyMakeRequest surveyMakeRequest) throws IOException;

}
