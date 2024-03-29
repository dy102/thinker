package com.example.thinker.dto;

import com.example.thinker.domain.Member;

import java.util.Arrays;
import java.util.Objects;

public record MemberSimpleDto(String memberName,
                              byte[] memberProfile,
                              String memberGrade) {
    public static MemberSimpleDto form(Member member) {
        return new MemberSimpleDto(member.getName(), member.getImage().getData(), member.getGrade());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        MemberSimpleDto memberSimpleDto = (MemberSimpleDto) obj;

        // 배열 내용을 비교
        return Arrays.equals(memberProfile, memberSimpleDto.memberProfile) &&
                Objects.equals(memberName, memberSimpleDto.memberName) &&
                Objects.equals(memberGrade, memberSimpleDto.memberGrade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Arrays.hashCode(memberProfile), memberName, memberGrade);
    }

    @Override
    public String toString() {
        // Arrays 클래스의 toString 메서드를 사용하여 배열의 내용을 문자열로 변환
        return "MemberSimpleDto{" +
                "memberName=" + memberName +
                "memberProfile=" + Arrays.toString(memberProfile) +
                "memberGrade=" + memberGrade +
                '}';
    }

}
