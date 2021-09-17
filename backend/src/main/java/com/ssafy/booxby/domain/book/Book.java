package com.ssafy.booxby.domain.book;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bookId;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(length = 100)
    private String author;

    @Column(length = 500)
    private String description;

    @Column(length = 50)
    private String publisher;

    @Column(length = 100)
    private String imgUrl;

    @Column
    private int emotionScore;

    @Column
    private int color;

    @Column
    private int isbn;

    @Column
    private int rank;
}
