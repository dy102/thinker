package com.example.thinker.dto;

import java.util.List;

public record TotalReplyDtos(
        List<? extends ReplyDtoBase> replyDtos
) {
}
