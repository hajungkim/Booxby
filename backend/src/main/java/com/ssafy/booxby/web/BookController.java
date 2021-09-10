package com.ssafy.booxby.web;

import com.ssafy.booxby.domain.Book.Book;
import com.ssafy.booxby.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
        response = new ControllerResponse("success", book);

        System.out.println(book.getTitle());
        return response;
    }
}
