package jg.com.gonhub.reviews;

import jg.com.gonhub.users.CurrentUser;
import jg.com.gonhub.users.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final PictureRepository pictureRepository;

    @Transactional
    public Long makeReview(@CurrentUser User user, ReviewCreateRequest request){
        Review review = new Review();
        review.builder().user(user)
                .title(request.getTitle())
                .content(request.getContent())
                .build();
        Review saved = reviewRepository.save(review);

        List<Long> uploadedPictures = request.getUploadedPictures();
        for (Long id : uploadedPictures) {
            Picture picture = pictureRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("등록되지 않은 사진입니다"));
            picture.setReview(saved);
        }
        return saved.getId();
    }
    

}
