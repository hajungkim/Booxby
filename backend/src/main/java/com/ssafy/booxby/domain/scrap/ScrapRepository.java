package com.ssafy.booxby.domain.scrap;

import com.ssafy.booxby.domain.scrap.Scrap;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScrapRepository extends JpaRepository<Scrap, Long> {

    List<Scrap> findByUserId(Long userId);

    void deleteByUserIdAndIsbn(Long userId, String isbn);
}
