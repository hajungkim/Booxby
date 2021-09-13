package com.ssafy.booxby.service;

import com.ssafy.booxby.domain.Book.Book;
import com.ssafy.booxby.domain.Book.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepository;

    @Transactional
    public Book findBookById(Long bookId) {
        Book book = bookRepository.findByBookId(bookId);
        return book;
    }

    public List<Book> findBookByAuthor(String author) {
        List<Book> list = bookRepository.findByAuthor(author);
        return list;
    }
}
