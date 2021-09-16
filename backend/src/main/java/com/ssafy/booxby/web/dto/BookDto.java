package com.ssafy.booxby.web.dto;

import com.ssafy.booxby.domain.book.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class BookDto {

    @Data
    @NoArgsConstructor
    public static class detailResponseDto {
        private Long bookId;
        private String title;
        private String author;
        private String description;
        private String publisher;
        private int pubDate;
        private String imgUrl;
        private int emotionScore;
        private int color;

        public detailResponseDto(Book book){
            this.bookId=book.getBookId();
            this.title=book.getTitle();
            this.author=book.getAuthor();
            this.description=book.getDescription();
            this.publisher=book.getPublisher();
            this.imgUrl=book.getImgUrl();
            this.emotionScore=book.getEmotionScore();
            this.color=book.getColor();
        }
    }

    @Data
    @NoArgsConstructor
    public static class authorResponseDto{
        private Long bookId;
        private String title;
        private String publisher;
        private String imgUrl;

        public authorResponseDto(Book book){
            this.bookId=book.getBookId();
            this.title=book.getTitle();
            this.publisher=book.getPublisher();
            this.imgUrl=book.getImgUrl();
        }
    }

    @Data
    @NoArgsConstructor
    public static class reviewSaveRequest{
        private Long bookId;
        private Long userId;
        private int reviewScore;
        private String reviewContent;
        private boolean reviewLike;
        private boolean reviewIdea;
        private boolean reviewUseful;
        private boolean reviewRead;
    }

    @Data
    @NoArgsConstructor
    public static class reviewDeleteRequest{
        private Long userId;
        private Long reviewId;
        private Long bookId;
    }
}
