package jg.com.gonhub.reviews;

import jg.com.gonhub.users.User;
import jg.com.gonhub.users.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReviewServiceTest {
    @Autowired
    ReviewService reviewService;
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("사진 없는 리뷰 생성 테스트")
    void createReviewTest() {
        Long l = reviewService.makeReview(null, new ReviewCreateRequest("review", "content", new ArrayList<>()));
        Review review = reviewRepository.findById(l).orElseThrow(() -> new IllegalArgumentException("failed test"));
        Assertions.assertThat(review.getTitle()).isEqualTo("review");
        Assertions.assertThat(review.getContent()).isEqualTo("content");

    }


}