package com.example.thinker.constants;

public class PointConst {
    private PointConst() {
    }

    public static final Long PREMIUM_THINKING_BONUS = 2L;
    public static final Long PREMIUM_SURVEY_BONUS = 10L;
    public static final Long PREMIUM_THINKING_COST = 20L;
    public static final Long PREMIUM_SURVEY_COST = 50L;

    public static final int POINT_LENGTH = 10;
    public static final int POINT_MAX_BOUND_IN_REPLY = 12;
    public static final int POINT_MAX_BOUND_IN_SURVEY = 26;
    public static final int MULTIPLY_TO_SURVEY_SIZE = 2;

    public static final String REPLY_TO_PREMIUM_THINKING = "댓글 작성(프리미엄)";
    public static final String REPLY_TO_THINKING = "댓글 작성";
    public static final String CREATE_PREMIUM_THINKING = "프리미엄 게시글 생성";
    public static final String CREATE_PREMIUM_SURVEY = "프리미엄 설문조사 생성";
    public static final String NO_POINT = "포인트가 부족합니다.";
}
