package com.ssafy.booxby.web;

import com.ssafy.booxby.domain.review.Review;
import com.ssafy.booxby.domain.user.User;
import com.ssafy.booxby.service.ReviewService;
import com.ssafy.booxby.service.UserService;
import com.ssafy.booxby.web.dto.ReviewDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RequiredArgsConstructor
@RestController
@RequestMapping("/book")
public class ReviewController {

    private final ReviewService bookService;
    private final UserService userService;

    @ApiOperation(value = "리뷰 등록", notes = "리뷰등록 성공 시 '리뷰 등록 성공' 반환", response = ControllerResponse.class)
    @PostMapping("/review")
    public ControllerResponse saveReview(@RequestBody ReviewDto.reviewSaveRequest request){
        ControllerResponse response = null;
        System.out.println(request);
        try{
            bookService.saveReview(request);
            response = new ControllerResponse("success", "리뷰 등록 성공");
        }catch (Exception e) {
            response = new ControllerResponse("fail", e.getMessage());
        }

        return response;
    }

    @ApiOperation(value = "리뷰 삭제", notes = "성공 시 '리뷰 삭제 성공' 반환", response = ControllerResponse.class)
    @DeleteMapping("/review")
    public ControllerResponse deleteReview(@RequestBody ReviewDto.reviewDeleteRequest request){
        ControllerResponse response = null;

        try{
            bookService.deleteReview(request);
            response = new ControllerResponse("success", "리뷰 삭제 성공");
        }catch (Exception e) {
            response = new ControllerResponse("fail", e.getMessage());
        }

        return response;
    }

    @ApiOperation(value = "유저가 작성한 리뷰 조회", notes = "성공 시 리뷰 리스트 반환 / 리뷰가 없으면 false 반환", response = ControllerResponse.class)
    @GetMapping("/review/my/{userId}")
    public ControllerResponse findMyReview(@PathVariable Long userId){
        ControllerResponse response = null;

        try{
            List<Review> reviewList = bookService.findReviewByUserId(userId);
            List<ReviewDto.reviewResponse> list = new ArrayList<>();

            for(Review review:reviewList){
                ReviewDto.reviewResponse reviewResponse = new ReviewDto.reviewResponse(review);
                list.add(reviewResponse);
            }

            if(list.isEmpty())
                response = new ControllerResponse("success", false);
            else
                response = new ControllerResponse("success", list);

        }catch (Exception e) {
            response = new ControllerResponse("fail", e.getMessage());
        }

        return response;
    }

    @ApiOperation(value = "책 카테고리별(평점순, 등록순) 리뷰 조회", notes = "오래된 순-oldest / 최신순-newest(default) / 평점 높은 순-highscore / 평점 낮은 순-lowscore" +
            "성공 시 리뷰 리스트 반환 / 리뷰가 없을 경우 false 반환", response = ControllerResponse.class)
    @GetMapping("/review/{isbn}/{category}")
    public ControllerResponse findAllReviews(@PathVariable String isbn, @PathVariable String category){
        ControllerResponse response = null;

        try{
            List<Review> reviewList = new ArrayList<>();
            switch (category){
                case "oldest":
                    reviewList = bookService.findOldReviewByBookId(isbn);
                    break;
                case "newest":
                    reviewList = bookService.findReviewByBookId(isbn);
                    break;
                case "highscore":
                    reviewList = bookService.findHighReviewByBookId(isbn);
                    break;
                case "lowscore" :
                    reviewList = bookService.findLowReviewByBookId(isbn);
                    break;
                default:
                    reviewList = bookService.findReviewByBookId(isbn);
                    break;
            }

            List<ReviewDto.reviewAllResponse> list = new ArrayList<>();

            for(Review review : reviewList){
                User user = userService.findUserByUserId(review.getUserId());
                ReviewDto.reviewAllResponse reviewAllResponse = new ReviewDto.reviewAllResponse(review, user.getNickname(), user.getWorldcupScore(), user.getHashScore());
                list.add(reviewAllResponse);
            }

            if(list.isEmpty())
                response = new ControllerResponse("success", false);
            else
                response = new ControllerResponse("success", list);

        }catch (Exception e){
            response = new ControllerResponse("fail", e.getMessage());
        }
        return response;
    }

}
