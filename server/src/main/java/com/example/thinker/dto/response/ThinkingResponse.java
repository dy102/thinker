package com.example.thinker.dto.response;

import com.example.thinker.domain.Thinking;
import com.example.thinker.dto.ThinkingDtos;
import com.example.thinker.util.ScrollPaginationCollection;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringExpressions;
import com.querydsl.core.types.dsl.StringTemplate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ThinkingResponse {
    private static final long LAST_CURSOR = -1L;

    private ThinkingDtos contents;
    private long totalElements;
    private long nextCursor;

    private ThinkingResponse(ThinkingDtos contents, long totalElements, long nextCursor) {
        this.contents = contents;
        this.totalElements = totalElements;
        this.nextCursor = nextCursor;
    }

    public static ThinkingResponse of(ScrollPaginationCollection<Thinking> thinkingScroll, long totalElements) {
        if (thinkingScroll.isLastScroll()) {
            return ThinkingResponse.newLastScroll(thinkingScroll.getCurrentScrollItems(), totalElements);
        }
        return ThinkingResponse.newScrollHasNext(thinkingScroll.getCurrentScrollItems(), totalElements,
                thinkingScroll.getNextCursor().getId());//getId X
    }

    private BooleanExpression customCursor(String customCursor, Thinking thinking) {
        if (customCursor == null) {
            return null;
        }

        StringTemplate stringTemplate = Expressions.stringTemplate(
                "FORMAT({0}, %d)",
                thinking.getLikeCount()
        );

        return StringExpressions.lpad(stringTemplate, 10, '0')
                .concat(StringExpressions
                        .lpad(Expressions
                                .stringTemplate("CAST({0} AS STRING)", thinking.getId()), 10, '0'))
                .gt(customCursor);
    }

    private static ThinkingResponse newLastScroll(List<Thinking> thinkingScroll, long totalElements) {
        return newScrollHasNext(thinkingScroll, totalElements, LAST_CURSOR);
    }

    private static ThinkingResponse newScrollHasNext(List<Thinking> thinkingScroll, long totalElements, long nextCursor) {
        return new ThinkingResponse(getContents(thinkingScroll), totalElements, nextCursor);
    }

    private static ThinkingDtos getContents(List<Thinking> thinkingScroll) {
        return ThinkingDtos.form(thinkingScroll);
    }
}
