package com.ssafy.booxby.web.dto;

import com.ssafy.booxby.domain.Book.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class BookDto {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DetailResponseDto {
        private Long bookId;
        private String title;
        private String author;
        private String description;
        private String publisher;
        private int pubDate;
        private String imgUrl;
        private int emotionScore;
        private int color;

        public DetailResponseDto(Book book){
            this.bookId=book.getBookId();
            this.title=book.getTitle();
            this.author=book.getAuthor();
            this.description=book.getDescription();
            this.publisher=book.getPublisher();
            this.pubDate=book.getPubDate();
            this.imgUrl=book.getImgUrl();
            this.emotionScore=book.getEmotionScore();
            this.color=book.getColor();
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AuthorResponseDto{
        private Long bookId;
        private String title;
        private String publisher;
        private String imgUrl;

        public AuthorResponseDto(Book book){
            this.bookId=book.getBookId();
            this.title=book.getTitle();
            this.publisher=book.getPublisher();
            this.imgUrl=book.getImgUrl();
        }
    }


}
