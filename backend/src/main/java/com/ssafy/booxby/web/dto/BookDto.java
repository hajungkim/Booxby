package com.ssafy.booxby.web.dto;

import com.ssafy.booxby.domain.book.Book;
import com.ssafy.booxby.domain.book.Review;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class BookDto {

    @Data
    @NoArgsConstructor
    public static class detailResponse {
        private Long bookId;
        private String title;
        private String author;
        private String description;
        private String publisher;
        private int pubDate;
        private String imgUrl;
        private int emotionScore;
        private int color;

        public detailResponse(Book book){
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
    public static class authorResponse{
        private Long bookId;
        private String title;
        private String publisher;
        private String imgUrl;

        public authorResponse(Book book){
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

    @Data
    @NoArgsConstructor
    public static class reviewResponse{
        private Long reviewId;
        private Long bookId;
        private String title;
        private String imgUrl;
        private int reviewScore;
        private boolean reviewLike;
        private boolean reviewIdea;
        private boolean reviewUseful;
        private boolean reviewRead;

        public reviewResponse(Review review, String title, String imgUrl){
            this.reviewId = review.getReviewId();
            this.bookId = review.getBookId();
            this.reviewScore = review.getReviewScore();
            this.reviewLike = review.isReviewLike();
            this.reviewIdea = review.isReviewIdea();
            this.reviewUseful = review.isReviewUseful();
            this.reviewRead = review.isReviewRead();
            this.title=title;
            this.imgUrl=imgUrl;
        }
    }
}
