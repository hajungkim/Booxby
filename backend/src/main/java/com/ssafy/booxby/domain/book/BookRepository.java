package com.ssafy.booxby.domain.book;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    Book findByBookId(Long bookId);

    List<Book> findByAuthor(String author);
}
