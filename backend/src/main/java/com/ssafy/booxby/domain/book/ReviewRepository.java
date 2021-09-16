package com.ssafy.booxby.domain.book;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    void deleteByReviewId(Long reviewId);
}
