package com.ssafy.booxby.web;

import com.ssafy.booxby.domain.Book.Book;
import com.ssafy.booxby.service.BookService;
import com.ssafy.booxby.web.dto.BookDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RequiredArgsConstructor
@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    @GetMapping("/{bookId}")
    public ControllerResponse getBook(@PathVariable Long bookId){
        ControllerResponse response = null;

        Book book = bookService.findBookById(bookId);
        BookDto.DetailResponseDto detailResponseDto = new BookDto.DetailResponseDto(book);

        response = new ControllerResponse("success", detailResponseDto);

        return response;
    }

    @GetMapping("/author/{author}")
    public ControllerResponse getAuthorBook(@PathVariable String author){
        ControllerResponse response = null;

        List<Book> bookList = bookService.findBookByAuthor(author);
        List<BookDto.AuthorResponseDto> responseList = new ArrayList<>();

        for(Book book:bookList){
            BookDto.AuthorResponseDto authorResponseDto = new BookDto.AuthorResponseDto(book);
            responseList.add(authorResponseDto);
        }

        response = new ControllerResponse("success", responseList);
        return response;
    }

}
