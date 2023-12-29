package com.example.thinker.dto.request;

public record MemberDataRequest(String customId, String pw,
                                String nickname,
                                String birthday,
                                String gender) {
}
