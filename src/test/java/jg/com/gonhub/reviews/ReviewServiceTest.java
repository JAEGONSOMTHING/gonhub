package jg.com.gonhub.reviews;

import jg.com.gonhub.users.User;
import jg.com.gonhub.users.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
    void test() {
        User user = User.builder()
                .id(1000l)
                .build();

        ReviewCreateRequest reviewCreateRequest = new ReviewCreateRequest();
        reviewCreateRequest.setContent("sex");
        reviewCreateRequest.setTitle("titleSEx");
        Long id = reviewService.makeReview(user, reviewCreateRequest);

        Review review = reviewRepository.findById(id).orElse(null);



    }


}