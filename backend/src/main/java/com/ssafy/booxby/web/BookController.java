package com.ssafy.booxby.web;

import com.ssafy.booxby.domain.book.Book;
import com.ssafy.booxby.domain.book.ReviewCount;
import com.ssafy.booxby.service.BookService;
import com.ssafy.booxby.web.dto.BookDto;
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

    @GetMapping("/{bookId}")
    public ControllerResponse getBook(@PathVariable Long bookId){
        ControllerResponse response = null;

        try{
            Book book = bookService.findBookById(bookId);
            if(book==null){
                response = new ControllerResponse("success", false);
            }
            else{
                BookDto.detailResponseDto detailResponseDto = new BookDto.detailResponseDto(book);
                response = new ControllerResponse("success", detailResponseDto);
            }
        }catch (Exception e) {
            response = new ControllerResponse("fail", e.getMessage());
        }

        return response;
    }

    @GetMapping("/author/{author}")
    public ControllerResponse getAuthorBook(@PathVariable String author){
        ControllerResponse response = null;

        try{
            List<Book> bookList = bookService.findBookByAuthor(author);
            List<BookDto.authorResponseDto> responseList = new ArrayList<>();

            for(Book book:bookList){
                BookDto.authorResponseDto authorResponseDto = new BookDto.authorResponseDto(book);
                responseList.add(authorResponseDto);
            }

            response = new ControllerResponse("success", responseList);
        }catch (Exception e) {
            response = new ControllerResponse("fail", e.getMessage());
        }

        return response;
    }

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




}
