package com.example.thinker.constants;

public class ServiceConst {
    private ServiceConst() {
    }

    public static final String ORDER_BY_RECENT = "recent";
    public static final String ORDER_BY_LIKE = "like";
    public static final String ORDER_BY_VIEW = "view";
    public static final String ORDER_BY_POPULAR = "popular";

    public static final String NOT_AUTHOR_AND_NOT_ME = "other";
    public static final String NOT_AUTHOR_AND_ME = "me";
    public static final String AUTHOR_AND_NOT_ME = "author";
    public static final String AUTHOR_AND_ME = "me(author)";

    public static final int MAX_POST_COUNT = 100;
    public static final int MAX_PREMIUM_PAGE = 3;
}
