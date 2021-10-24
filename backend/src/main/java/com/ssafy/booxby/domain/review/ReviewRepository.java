package com.ssafy.booxby.domain.review;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    void deleteByReviewId(Long reviewId);

    List<Review> findByUserIdOrderByCreatedDateDesc(Long userId);

    List<Review> findByIsbnOrderByCreatedDateDesc(String isbn);

    List<Review> findByIsbnOrderByCreatedDateAsc(String isbn);

    List<Review> findByIsbnOrderByReviewScoreDescCreatedDateDesc(String isbn);

    List<Review> findByIsbnOrderByReviewScoreAscCreatedDateDesc(String isbn);
}
