package com.ssafy.booxby.web.dto;

import com.ssafy.booxby.domain.scrap.Scrap;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ScrapDto {

    @Getter
    @NoArgsConstructor
    public static class saveScrapRequest {
        private Long userId;
        private String isbn;
        private String imgUrl;
        private String title;
    }

    @Getter
    @NoArgsConstructor
    public static class scrapResponse {
        private String isbn;
        private String imgUrl;
        private String title;

        public scrapResponse(Scrap scrap){
            this.isbn=scrap.getIsbn();
            this.imgUrl=scrap.getImgUrl();
            this.title=scrap.getTitle();
        }
    }
}
