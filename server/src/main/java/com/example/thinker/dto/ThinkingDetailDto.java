package com.example.thinker.dto;

import com.example.thinker.domain.Image;
import com.example.thinker.domain.Thinking;

import java.util.List;

public record ThinkingDetailDto(Long thinkingId,
                                List<byte[]> thinkingImage,
                                String thinkingWriter,
                                String thinkingTitle,
                                String thinkingContents,
                                String thinkingDate,
                                boolean isPremium,
                                Long likeCount,
                                Long repliesCount,
                                Long viewCount) {
    public static ThinkingDetailDto form(Thinking thinking) {
        List<byte[]> thinkingImage;
        if (thinking.getImage() == null) {
            thinkingImage = null;
        } else {
            thinkingImage = thinking.getImage()
                    .stream()
                    .map(Image::getData)
                    .toList();
        }
        return new ThinkingDetailDto(thinking.getId(), thinkingImage,
                thinking.getWriter().getName(), thinking.getTitle(), thinking.getContents(),
                thinking.getDateTime().toString(), thinking.getIsPremium(),
                thinking.getLikeCount(), thinking.getRepliesCount(),
                thinking.getViewCount());
    }
}
