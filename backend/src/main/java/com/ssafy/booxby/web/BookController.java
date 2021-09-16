package com.ssafy.booxby.web;

import com.ssafy.booxby.domain.book.Book;
import com.ssafy.booxby.domain.book.Review;
import com.ssafy.booxby.domain.book.ReviewCount;
import com.ssafy.booxby.service.BookService;
import com.ssafy.booxby.web.dto.BookDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

//@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RequiredArgsConstructor
@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    @ApiOperation(value = "책 상세 조회", notes = "로그인 성공 시 책 정보 반환 / 없는 책일 경우 false 반환", response = ControllerResponse.class)
    @GetMapping("/{bookId}")
    public ControllerResponse getBook(@PathVariable Long bookId){
        ControllerResponse response = null;

        try{
            Book book = bookService.findBookById(bookId);
            if(book==null){
                response = new ControllerResponse("success", false);
            }
            else{
                BookDto.detailResponse detailResponse = new BookDto.detailResponse(book);
                response = new ControllerResponse("success", detailResponse);
            }
        }catch (Exception e) {
            response = new ControllerResponse("fail", e.getMessage());
        }

        return response;
    }

    @ApiOperation(value = "작가 책리스트 조회", notes = "성공 시 리스트 반환", response = ControllerResponse.class)
    @GetMapping("/author/{author}")
    public ControllerResponse getAuthorBook(@PathVariable String author){
        ControllerResponse response = null;

        try{
            List<Book> bookList = bookService.findBookByAuthor(author);
            List<BookDto.authorResponse> responseList = new ArrayList<>();

            for(Book book:bookList){
                BookDto.authorResponse authorResponseDto = new BookDto.authorResponse(book);
                responseList.add(authorResponseDto);
            }

            response = new ControllerResponse("success", responseList);
        }catch (Exception e) {
            response = new ControllerResponse("fail", e.getMessage());
        }

        return response;
    }

    @ApiOperation(value = "리뷰 등록", notes = "리뷰등록 성공 시 '리뷰등록성공' 반환", response = ControllerResponse.class)
    @PostMapping("/review")
    public ControllerResponse saveReview(@RequestBody BookDto.reviewSaveRequest request){
        ControllerResponse response = null;

        try{
            bookService.saveReview(request);
            response = new ControllerResponse("success", "리뷰등록성공");
        }catch (Exception e) {
            response = new ControllerResponse("fail", e.getMessage());
        }

        return response;
    }

    @ApiOperation(value = "리뷰 삭제", notes = "성공 시 '리뷰삭제성공' 반환", response = ControllerResponse.class)
    @DeleteMapping
    public ControllerResponse deleteReview(@RequestBody BookDto.reviewDeleteRequest request){
        ControllerResponse response = null;

        try{
            bookService.deleteReview(request);
            response = new ControllerResponse("success", "리뷰삭제성공");
        }catch (Exception e) {
            response = new ControllerResponse("fail", e.getMessage());
        }

        return response;
    }

    @ApiOperation(value = "유저가 작성한 리뷰 조회", notes = "성공 시 리뷰 리스트 반환", response = ControllerResponse.class)
    @GetMapping("/review/{userId}")
    public ControllerResponse findMyReview(@PathVariable Long userId){
        ControllerResponse response = null;

        try{
            List<Review> reviewList = bookService.findReviewByUserId(userId);
            List<BookDto.reviewResponse> list = new ArrayList<>();

            for(Review review:reviewList){
                Book book = bookService.findBookById(review.getBookId());
                BookDto.reviewResponse reviewResponse = new BookDto.reviewResponse(review, book.getTitle(), book.getImgUrl());
                list.add(reviewResponse);
                response = new ControllerResponse("success", list);
            }

        }catch (Exception e) {
            response = new ControllerResponse("fail", e.getMessage());
        }

        return response;
    }

}
