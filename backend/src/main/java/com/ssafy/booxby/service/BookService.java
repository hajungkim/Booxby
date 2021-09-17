package com.ssafy.booxby.service;

import com.ssafy.booxby.domain.book.*;
import com.ssafy.booxby.web.dto.BookDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepository;
    private final ReviewRepository reviewRepository;
    private final ReviewCountRepository reviewCountRepository;

    @Transactional
    public Book findBookById(Long bookId) {
        Book book = bookRepository.findByBookId(bookId);
        return book;
    }

    @Transactional
    public List<Book> findBookByAuthor(String author) {
        List<Book> list = bookRepository.findByAuthor(author);
        return list;
    }

    @Transactional
    public void saveReview(BookDto.reviewSaveRequest request) {
        Review review = Review.builder()
                .bookId(request.getBookId())
                .userId(request.getUserId())
                .reviewScore(request.getReviewScore())
                .reviewLike(request.isReviewLike())
                .reviewIdea(request.isReviewIdea())
                .reviewUseful(request.isReviewUseful())
                .reviewRead(request.isReviewRead())
                .build();

        //리뷰 등록
        reviewRepository.save(review);

        //리뷰 카운트 증가
        Optional<ReviewCount> reviewCount = reviewCountRepository.findByBookId(request.getBookId());
        if (reviewCount.isPresent()){
            reviewCount.get().updateReview(request);
        }
        else {
            ReviewCount newReview = ReviewCount.builder()
                    .bookId(request.getBookId())
                    .reviewLikeCnt(request.isReviewLike())
                    .reviewIdeaCnt(request.isReviewIdea())
                    .reviewUsefulCnt(request.isReviewUseful())
                    .reviewReadCnt(request.isReviewRead())
                    .build();
            reviewCountRepository.save(newReview);
        }

    }

    @Transactional
    public void deleteReview(BookDto.reviewDeleteRequest request) {
        Review review = reviewRepository.getById(request.getReviewId());
        ReviewCount reviewCount = reviewCountRepository.getById(request.getBookId());
        reviewCount.deleteReviewCount(review);
        reviewRepository.deleteByReviewId(request.getReviewId());
    }

    @Transactional
    public List<Review> findReviewByUserId(Long userId) {
        return reviewRepository.findByUserIdOrderByCreatedDateDesc(userId);
    }
}
