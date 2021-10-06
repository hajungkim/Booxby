package com.ssafy.booxby.web.dto;

import com.ssafy.booxby.domain.review.Review;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ReviewDto {

    @Data
    @NoArgsConstructor
    public static class reviewSaveRequest{
        private String isbn;
        private Long userId;
        private int reviewScore;
        private String reviewContent;
        private boolean reviewLike;
        private boolean reviewIdea;
        private boolean reviewUseful;
        private boolean reviewRead;
        private String title;
        private String imgUrl;
    }

    @Data
    @NoArgsConstructor
    public static class reviewDeleteRequest{
        private Long userId;
        private Long reviewId;
        private String isbn;
    }

    @Data
    @NoArgsConstructor
    public static class reviewResponse{
        private Long reviewId;
        private String isbn;
        private String reviewContent;
        private String title;
        private String imgUrl;
        private int reviewScore;
        private boolean reviewLike;
        private boolean reviewIdea;
        private boolean reviewUseful;
        private boolean reviewRead;

        public reviewResponse(Review review){
            this.reviewId = review.getReviewId();
            this.isbn = review.getIsbn();
            this.reviewContent=review.getReviewContent();
            this.reviewScore = review.getReviewScore();
            this.reviewLike = review.isReviewLike();
            this.reviewIdea = review.isReviewIdea();
            this.reviewUseful = review.isReviewUseful();
            this.reviewRead = review.isReviewRead();
            this.title=review.getTitle();
            this.imgUrl=review.getImgUrl();
        }
    }

    @Data
    @NoArgsConstructor
    public static class reviewAllResponse{
        private Long reviewId;
        private String reviewContent;
        private int reviewScore;
        private boolean reviewLike;
        private boolean reviewIdea;
        private boolean reviewUseful;
        private boolean reviewRead;

        private Long userId;
        private String nickname;

        private int worldcupScore;
        private int hashScore;

        public reviewAllResponse(Review review, String nickname, int worldcupScore, int hashScore){
            this.reviewId = review.getReviewId();
            this.reviewScore = review.getReviewScore();
            this.reviewContent=review.getReviewContent();
            this.reviewLike = review.isReviewLike();
            this.reviewIdea = review.isReviewIdea();
            this.reviewUseful = review.isReviewUseful();
            this.reviewRead = review.isReviewRead();
            this.userId = review.getUserId();
            this.nickname = nickname;
            this.worldcupScore=worldcupScore;
            this.hashScore=hashScore;
        }
    }
}
