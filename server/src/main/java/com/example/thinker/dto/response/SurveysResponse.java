package com.example.thinker.dto.response;

import com.example.thinker.dto.SurveyDto;
import com.example.thinker.dto.SurveyDtos;
import com.example.thinker.util.ScrollPaginationCollection;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SurveysResponse {
    private static final long LAST_CURSOR = -1L;

    private SurveyDtos contents;
    private long totalElements;
    private long nextCursor;

    private SurveysResponse(SurveyDtos contents, long totalElements, long nextCursor) {
        this.contents = contents;
        this.totalElements = totalElements;
        this.nextCursor = nextCursor;
    }

    public static SurveysResponse form(ScrollPaginationCollection<SurveyDto> surveyScroll, long totalElements) {
        if (surveyScroll.isLastScroll()) {
            return SurveysResponse.newLastScroll(surveyScroll.getCurrentScrollItems(), totalElements);
        }
        return SurveysResponse.newScrollHasNext(surveyScroll.getCurrentScrollItems(), totalElements,
                surveyScroll.getNextCursor().surveyId());
    }

    private static SurveysResponse newLastScroll(List<SurveyDto> surveyScroll, long totalElements) {
        return newScrollHasNext(surveyScroll, totalElements, LAST_CURSOR);
    }

    private static SurveysResponse newScrollHasNext(List<SurveyDto> surveyScroll, long totalElements, long nextCursor) {
        return new SurveysResponse(getContents(surveyScroll), totalElements, nextCursor);
    }

    private static SurveyDtos getContents(List<SurveyDto> surveyScroll) {
        return new SurveyDtos(surveyScroll);
    }

}
