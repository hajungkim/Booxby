package com.ssafy.booxby.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScrapRepository extends JpaRepository<Scrap, Long> {

    List<Scrap> findByUserId(Long userId);

    void deleteByUserIdAndBookId(Long userId, Long bookId);
}
