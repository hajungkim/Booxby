package com.ssafy.booxby.domain.Book;

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

    @Column
    private int pubDate;

    @Column(length = 100)
    private String imgUrl;

    @Column
    private int emotionScore;

    @Column
    private int color;

    @Column
    private int isbn13;

    @Column
    private int rank;

    @Column(length = 100)
    private String age;

    @Column(length = 10)
    private String gender;

    @Column
    private int reviewLike;

    @Column
    private int reviewIdea;

    @Column
    private int reviewUseful;

    @Column
    private int reviewRead;
}
