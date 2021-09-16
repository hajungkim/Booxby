package com.ssafy.booxby.service;

import com.ssafy.booxby.domain.user.Scrap;
import com.ssafy.booxby.domain.user.ScrapRepository;
import com.ssafy.booxby.web.dto.ScrapDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScrapService {

    private final ScrapRepository scrapRepository;

    @Transactional
    public void saveScrap(ScrapDto.saveScrapRequest request) {
        Scrap scrap = Scrap.builder()
                .userId(request.getUserId())
                .bookId(request.getBookId())
                .build();
        scrapRepository.save(scrap);
    }

    @Transactional
    public List<Long> findScrap(Long userId) {
        List<Long> userIdList = new ArrayList<>();
        List<Scrap> scrapList = scrapRepository.findByUserId(userId);
        for (Scrap scrap : scrapList) {
            userIdList.add(scrap.getBookId());
        }
        return userIdList;
    }

    @Transactional
    public void deleteScrap(ScrapDto.saveScrapRequest request) {
        scrapRepository.deleteByUserIdAndBookId(request.getUserId(), request.getBookId());
    }
}
