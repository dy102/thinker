package com.example.thinker.domain;

import java.util.Arrays;

public enum Grade {
    BEGINNER("BEGINNER", 0L, 99L),
    INTERMEDIATE("INTERMEDIATE", 100L, 299L),
    ADVANCED("ADVANCED", 300L, 999L),
    EXPERT("EXPERT", 1000L, 1999L),
    MASTER("MASTER", 2000L, 4999L),
    THINKER("THINKER", 5000L, 999999999L),
    MANAGER("MANAGER", null, null);

    private final String name;
    private final Long minimumPoint;
    private final Long maximumPoint;

    private Grade(String name, Long minimumPoint, Long maximumPoint) {
        this.name = name;
        this.minimumPoint = minimumPoint;
        this.maximumPoint = maximumPoint;
    }

    public String getName() {
        return name;
    }

    public Long getMinimumPoint() {
        return minimumPoint;
    }

    public Long getMaximumPoint() {
        return maximumPoint;
    }

    public static Grade of(String name) {
        return Arrays.stream(values())
                .filter(v -> name.equals(v.getName()))
                .findFirst()
                .orElseThrow(() -> new NullPointerException("존재하지 않는 등급 이름입니다."));
        //개발자의 실수로 일어날 수 있는 오류를 명시하기 위해 예외를 던지도록 함.
    }
}
