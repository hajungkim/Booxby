package com.ssafy.booxby.domain.review;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewCountRepository extends JpaRepository<ReviewCount, Long> {

    Optional<ReviewCount> findByIsbn(String isbn);

}
