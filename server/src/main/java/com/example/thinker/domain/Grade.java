package com.example.thinker.domain;

import java.util.Arrays;

public enum Grade {
    BEGINNER("BEGINNER"),
    INTERMEDIATE("INTERMEDIATE"),
    ADVANCED("ADVANCED"),
    EXPERT("EXPERT"),
    MASTER("MASTER"),
    THINKER("THINKER");
    
    private final String name;

    private Grade(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Grade of(String name) {
        return Arrays.stream(values())
                .filter(v -> name.equals(v.getName()))
                .findFirst()
                .orElseThrow(() -> new NullPointerException("존재하지 않는 등급 이름입니다."));
        //개발자의 실수로 일어날 수 있는 오류를 명시하기 위해 예외를 던지도록 함.
    }
}
