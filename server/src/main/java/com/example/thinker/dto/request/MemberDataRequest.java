package com.example.thinker.dto.request;

public record MemberDataRequest(String customId, String pw,
                                String nickname,
                                int year, int month, int day,
                                String gender) {
}
