package com.ssafy.booxby.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

public class ScrapDto {

    @Getter
    @NoArgsConstructor
    public static class saveScrapRequest {
        private Long userId;
        private Long bookId;
    }
}
