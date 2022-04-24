package jg.com.gonhub.reviews;

import jg.com.gonhub.users.CurrentUser;
import jg.com.gonhub.users.User;
import jg.com.gonhub.users.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReviewRestController {
    private final ReviewService reviewService;

    @PostMapping("/review")
    public String makeReview(@CurrentUser User user, @RequestBody ReviewCreateRequest request){
        reviewService.makeReview(user , request);
        return "Ok";
    }

    @PostMapping("/review/edit/{id}")
    public Long editReview(@CurrentUser User user, @PathVariable Long id, @RequestBody ReviewEditRequest request){


        return reviewService.editReview(user, id, request);
    }



}
