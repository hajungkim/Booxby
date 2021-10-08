package com.ssafy.booxby.service;

import com.ssafy.booxby.domain.scrap.Scrap;
import com.ssafy.booxby.domain.scrap.ScrapRepository;
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
                .isbn(request.getIsbn())
                .imgUrl(request.getImgUrl())
                .title(request.getTitle())
                .build();
        scrapRepository.save(scrap);
    }

    @Transactional
    public List<ScrapDto.scrapResponse> findScrap(Long userId) {
        List<ScrapDto.scrapResponse> userScrapList = new ArrayList<>();
        List<Scrap> scrapList = scrapRepository.findByUserId(userId);
        for (Scrap scrap : scrapList) {
            userScrapList.add(new ScrapDto.scrapResponse(scrap));
        }
        return userScrapList;
    }

    @Transactional
    public void deleteScrap(Long userId, String isbn) {
        scrapRepository.deleteByUserIdAndIsbn(userId, isbn);
    }
}
