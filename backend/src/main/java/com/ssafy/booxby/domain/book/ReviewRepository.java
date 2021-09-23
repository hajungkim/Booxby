package com.ssafy.booxby.domain.book;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    void deleteByReviewId(Long reviewId);

    List<Review> findByUserIdOrderByCreatedDateDesc(Long userId);
}
