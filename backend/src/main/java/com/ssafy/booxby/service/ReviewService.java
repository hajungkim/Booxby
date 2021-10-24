package com.ssafy.booxby.service;

import com.ssafy.booxby.domain.review.*;
import com.ssafy.booxby.web.dto.ReviewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewCountRepository reviewCountRepository;

    @Transactional
    public void saveReview(ReviewDto.reviewSaveRequest request) {
        Review review = Review.builder()
                .isbn(request.getIsbn())
                .userId(request.getUserId())
                .reviewContent(request.getReviewContent())
                .reviewScore(request.getReviewScore())
                .reviewLike(request.isReviewLike())
                .reviewIdea(request.isReviewIdea())
                .reviewUseful(request.isReviewUseful())
                .reviewRead(request.isReviewRead())
                .title(request.getTitle())
                .imgUrl(request.getImgUrl())
                .build();

        //리뷰 등록
        reviewRepository.save(review);

        //리뷰 카운트 증가
        Optional<ReviewCount> reviewCount = reviewCountRepository.findByIsbn(request.getIsbn());
        if (reviewCount.isPresent()){
            reviewCount.get().updateReview(request);
        }
        else {
            ReviewCount newReview = ReviewCount.builder()
                    .isbn(request.getIsbn())
                    .reviewLikeCnt(request.isReviewLike())
                    .reviewIdeaCnt(request.isReviewIdea())
                    .reviewUsefulCnt(request.isReviewUseful())
                    .reviewReadCnt(request.isReviewRead())
                    .build();
            reviewCountRepository.save(newReview);
        }

    }

    @Transactional
    public void deleteReview(ReviewDto.reviewDeleteRequest request) {
        Review review = reviewRepository.getById(request.getReviewId());
        Optional<ReviewCount> reviewCount = reviewCountRepository.findByIsbn(request.getIsbn());
        reviewCount.get().deleteReviewCount(review);
        reviewRepository.deleteByReviewId(request.getReviewId());
    }

    @Transactional
    public List<Review> findReviewByUserId(Long userId) {
        return reviewRepository.findByUserIdOrderByCreatedDateDesc(userId);
    }

    public List<Review> findReviewByBookId(String isbn) {
        return reviewRepository.findByIsbnOrderByCreatedDateDesc(isbn);
    }

    public List<Review> findOldReviewByBookId(String isbn) {
        return reviewRepository.findByIsbnOrderByCreatedDateAsc(isbn);
    }

    public List<Review> findHighReviewByBookId(String isbn) {
        return reviewRepository.findByIsbnOrderByReviewScoreDescCreatedDateDesc(isbn);
    }

    public List<Review> findLowReviewByBookId(String isbn) {
        return reviewRepository.findByIsbnOrderByReviewScoreAscCreatedDateDesc(isbn);
    }
}
