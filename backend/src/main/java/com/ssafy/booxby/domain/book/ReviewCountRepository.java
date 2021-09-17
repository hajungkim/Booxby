package com.ssafy.booxby.domain.book;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewCountRepository extends JpaRepository<ReviewCount, Long> {

    Optional<ReviewCount> findByBookId(Long bookId);
}
