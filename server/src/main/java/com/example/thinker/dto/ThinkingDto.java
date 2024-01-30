package com.example.thinker.dto;

import com.example.thinker.domain.Thinking;

import java.util.Arrays;
import java.util.Objects;

public record ThinkingDto(Long thinkingId,
                          byte[] thinkingThumbnail,
                          String thinkingWriter,
                          String thinkingTitle,
                          boolean isPremium,
                          Long likeCount,
                          Long repliesCount,
                          Long viewCount) {

    public static ThinkingDto form(Thinking thinking) {//썸네일 null 고려할 필요 있나? ㅇㅇ
        byte[] image = null;
        if (thinking.getImage() != null && !thinking.getImage().isEmpty()) {
            image = thinking.getImage().get(0).getData();
        }
        return new ThinkingDto(thinking.getId(), image,
                thinking.getWriter().getName(), thinking.getTitle(), thinking.getIsPremium(),
                thinking.getLikeCount(), thinking.getRepliesCount(), thinking.getViewCount());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        ThinkingDto thinkingDto = (ThinkingDto) obj;

        // 배열 내용을 비교
        return Arrays.equals(thinkingThumbnail, thinkingDto.thinkingThumbnail) &&
                Objects.equals(thinkingId, thinkingDto.thinkingId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Arrays.hashCode(thinkingThumbnail), thinkingId);
    }

    @Override
    public String toString() {
        // Arrays 클래스의 toString 메서드를 사용하여 배열의 내용을 문자열로 변환
        return "PremiumThinkingDto{" +
                "thinkingId=" + thinkingId +
                "thinkingThumbnail=" + Arrays.toString(thinkingThumbnail) +
                "thinkingWriter=" + thinkingWriter +
                "thinkingTitle=" + thinkingTitle +
                "isPremium=" + isPremium +
                "likeCount=" + likeCount +
                "repliesCount=" + repliesCount +
                "viewCount=" + viewCount +
                '}';
    }
}
