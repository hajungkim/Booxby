package com.ssafy.booxby.web;

import com.ssafy.booxby.service.ScrapService;
import com.ssafy.booxby.web.dto.ScrapDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RequiredArgsConstructor
@RestController
@RequestMapping
public class ScrapController {

    private final ScrapService scrapService;

    @ApiOperation(value = "책 찜하기", notes = "", response = ControllerResponse.class)
    @PostMapping("/scrap")
    public ControllerResponse saveScrap(@RequestBody ScrapDto.saveScrapRequest request) {
        ControllerResponse response = null;
        try {
            scrapService.saveScrap(request);
            response = new ControllerResponse("success", "찜하기 성공");
        } catch (Exception e) {
            response = new ControllerResponse("fail", e.getMessage());
        }
        return response;
    }

    @ApiOperation(value = "책 찜목록 리스트", notes = "찜목록이 없으면 false, 있으면 [isbn, imgUrl, title] 리스트 반환", response = ControllerResponse.class)
    @GetMapping("/scrap/{id}")
    public ControllerResponse findScrap(@PathVariable("id") Long userId) {
        ControllerResponse response = null;
        try {
            List<ScrapDto.scrapResponse> scrapList = scrapService.findScrap(userId);
            if (scrapList.size() == 0) {
                response = new ControllerResponse("success", false);
            } else {
                response = new ControllerResponse("success", scrapList);
            }
        } catch (Exception e) {
            response = new ControllerResponse("fail", e.getMessage());
        }
        return response;
    }

    @ApiOperation(value = "책 찜하기 취소", notes = "", response = ControllerResponse.class)
    @DeleteMapping("/scrap/{userId}/{isbn}")
    public ControllerResponse deleteScrap(@PathVariable Long userId, @PathVariable String isbn) {
        ControllerResponse response = null;
        try {
            scrapService.deleteScrap(userId, isbn);
            response = new ControllerResponse("success", "찜하기 취소 성공");
        } catch (Exception e) {
            response = new ControllerResponse("fail", e.getMessage());
        }
        return response;
    }
}
