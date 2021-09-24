package com.ssafy.booxby.domain.review;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long reviewId;

    @Column
    private Long userId;

    @Column
    private LocalDateTime createdDate;

    @Column
    private int reviewScore;

    @Column(length = 500)
    private String reviewContent;

    @Column
    private boolean reviewLike;

    @Column
    private boolean reviewIdea;

    @Column
    private boolean reviewUseful;

    @Column
    private boolean reviewRead;

    @Column
    private String isbn;

    @Column
    private String title;

    @Column
    private String imgUrl;

    @Builder
    public Review(String isbn, Long userId, int reviewScore, String reviewContent,
                  boolean reviewLike, boolean reviewIdea, boolean reviewUseful, boolean reviewRead, String title, String imgUrl){

        this.isbn=isbn;
        this.userId=userId;
        this.reviewScore=reviewScore;
        this.reviewContent=reviewContent;
        this.reviewLike=reviewLike;
        this.reviewIdea=reviewIdea;
        this.reviewUseful=reviewUseful;
        this.reviewRead=reviewRead;
        this.createdDate=LocalDateTime.now();

        this.title=title;
        this.imgUrl=imgUrl;
    }

}
