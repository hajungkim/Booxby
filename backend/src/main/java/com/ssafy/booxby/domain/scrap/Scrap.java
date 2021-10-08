package com.ssafy.booxby.domain.scrap;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Scrap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scrapId;

    private Long userId;

    private String isbn;

    private String imgUrl;

    private String title;

    //private LocalDateTime createdDate;

    @Builder
    public Scrap(Long userId, String isbn, String imgUrl, String title) {
        this.userId = userId;
        this.isbn = isbn;
        this.imgUrl= imgUrl;
        this.title = title;
        //this.createdDate = LocalDateTime.now();
    }
}
