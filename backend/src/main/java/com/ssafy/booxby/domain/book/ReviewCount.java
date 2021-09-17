package com.ssafy.booxby.domain.book;


import com.ssafy.booxby.web.dto.BookDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Optional;

@Getter
@NoArgsConstructor
@Entity
public class ReviewCount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewCountId;

    @Column
    private Long bookId;

    @Column
    private int reviewLikeCnt;

    @Column
    private int reviewIdeaCnt;

    @Column
    private int reviewUsefulCnt;

    @Column
    private int reviewReadCnt;

    @Builder
    public ReviewCount(Long bookId, boolean reviewLikeCnt, boolean reviewIdeaCnt, boolean reviewUsefulCnt, boolean reviewReadCnt){
        this.bookId=bookId;
        this.reviewLikeCnt = (reviewLikeCnt?1:0);
        this.reviewIdeaCnt=(reviewIdeaCnt?1:0);
        this.reviewUsefulCnt = (reviewUsefulCnt?1:0);
        this.reviewReadCnt = (reviewReadCnt?1:0);
    }

    public void updateReview(BookDto.reviewSaveRequest reviewCount) {
        this.reviewLikeCnt += (reviewCount.isReviewLike()? 1:0);
        this.reviewIdeaCnt += (reviewCount.isReviewIdea()? 1:0);
        this.reviewUsefulCnt += (reviewCount.isReviewUseful()? 1:0);
        this.reviewReadCnt += (reviewCount.isReviewRead()? 1:0);
    }

    public void deleteReviewCount(Review review) {
        this.reviewLikeCnt-=(review.isReviewLike()?1:0);
        this.reviewIdeaCnt-=(review.isReviewIdea()?1:0);
        this.reviewUsefulCnt-=(review.isReviewUseful()?1:0);
        this.reviewReadCnt-=(review.isReviewRead()?1:0);
    }
}
