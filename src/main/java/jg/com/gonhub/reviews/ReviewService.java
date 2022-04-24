package jg.com.gonhub.reviews;

import jg.com.gonhub.users.CurrentUser;
import jg.com.gonhub.users.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final PictureRepository pictureRepository;


    public Page<Review> showReviews(Pageable pageable){
        return reviewRepository.findAll(pageable);
    }


    @Transactional
    public Long makeReview(User user, ReviewCreateRequest request){
        Review review = new Review();
        review.setContent(request.getContent());
        review.setCreatedAt(LocalDateTime.now());
        review.setTitle(request.getTitle());
        review.setUser(user);
        Review saved = reviewRepository.save(review);
        List<Long> uploadedPictures = request.getUploadedPictures();
        for (Long id : uploadedPictures) {
            Picture picture = pictureRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("등록되지 않은 사진입니다"));
            picture.setReview(saved);
        }
        return saved.getId();
    }
    @Transactional
    public Long editReview(User user, Long id, ReviewEditRequest request){
        Review review = reviewRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("잘못된 경로입니다"));
        if (review.getUser()!= user){
            new IllegalArgumentException("잘못된 경로입니다");
        }
        if (request.getTitle()!=null){
            review.setTitle(request.getTitle());
        }
        if (request.getContent()!= null){
            review.setContent(request.getContent());
        }
        List<Long> deletedPictures = request.getDeletedPictures();
        for (Long deletedPicture : deletedPictures) {
            Picture picture = pictureRepository.findById(deletedPicture).orElseThrow(()-> new IllegalArgumentException("업로드되지 않은 사진입니다"));
            pictureRepository.delete(picture);
        }
        List<Long> uploadedPictures = request.getUploadedPictures();
        for (Long uploadedPicture : uploadedPictures) {
            Picture picture = pictureRepository.findById(uploadedPicture).orElseThrow(() -> new IllegalArgumentException("업로드되지 않은 사진입니다"));
            picture.setReview(review);
            pictureRepository.save(picture);
        }
        reviewRepository.save(review);
        return review.getId();
    }
    @Transactional
    public void deleteReview(User user, Long id){
        Review review = reviewRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("잘못된 경로입니다"));
        if (review.getUser()!= user){
            new IllegalArgumentException("잘못된 경로입니다");
        }
        reviewRepository.delete(review);
    }



}
